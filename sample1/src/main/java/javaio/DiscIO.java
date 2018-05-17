package javaio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * Created by ccr at 2018/3/1.
 */
public class DiscIO {



    //单字节写入200M文件
    public static void writeAFileByBIO(){
        long length = 200 * 1024 * 1024;
        byte b = 1;
        File tmp = new File("E:\\tmp.dat");
        FileOutputStream output = null;
        try {
            if (!tmp.exists()) {
               tmp.createNewFile();
            }
            output = new FileOutputStream(tmp);
            long start = System.currentTimeMillis();
            while (length -- > 0) {
                output.write(b);
            }
            System.out.println("写入时间:" + (System.currentTimeMillis() - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    //200M文件，一次写入1M
    public static void writeAFileByBIO2(){

        File tmp = new File("E:\\tmp.dat");
        FileOutputStream output = null;
        long length = 2000;
        try {
            if (!tmp.exists()) {
                tmp.createNewFile();
            }
            output = new FileOutputStream(tmp);
            byte[] byteArr = new byte[1024 * 1024];
            //一次写入1M的内容
            for (int i = 0; i < 1024 * 1024; i++) {
                byteArr[i] = 1;
            }
            long start = System.currentTimeMillis();
            while (length -- > 0) {
                output.write(byteArr);
            }
            System.out.println("写入时间:" + (System.currentTimeMillis() - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //使用BufferedStream
    public static void writeAFileByBIO3(){
        File tmp = new File("E:\\tmp.dat");
        BufferedOutputStream output = null;
        long length = 2000 * 1024 * 1024;
        try {
            if (!tmp.exists()) {
                tmp.createNewFile();
            }
            output = new BufferedOutputStream(new FileOutputStream(tmp),1024 * 1024);
            long start = System.currentTimeMillis();
            while (length -- > 0) {
                output.write(1);
            }
            System.out.println("写入时间:" + (System.currentTimeMillis() - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readFromFileByBIO1(){
        File tmp = new File("E:\\tmp.dat");
        FileInputStream is = null;
        try {
            is = new FileInputStream(tmp);
            long start = System.currentTimeMillis();
            while (is.read() > -1) {

            }
            System.out.println("读取时间:" + (System.currentTimeMillis() - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readFromFileByBIO2(){
        File tmp = new File("E:\\tmp.dat");
        FileInputStream is = null;
        try {
            is = new FileInputStream(tmp);
            byte[] buf = new byte[1024 * 1024];
            long start = System.currentTimeMillis();
            while (is.read(buf) > -1) {
            }
            System.out.println("读取时间:" + (System.currentTimeMillis() - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readFromFileByBIO3(){
        File tmp = new File("E:\\tmp.dat");
        BufferedInputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(tmp),1024);
            long start = System.currentTimeMillis();
            while (is.read() > -1) {

            }
            System.out.println("读取时间:" + (System.currentTimeMillis() - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeAFileByNIO(){
        File tmp = new File("E:\\tmp.dat");
        FileChannel inChannel = null;
        try {
            if (!tmp.exists()) {
                tmp.createNewFile();
            }
            RandomAccessFile aFile = new RandomAccessFile(tmp, "rw");
            inChannel = aFile.getChannel();
            //创建一个ByteBuffer，默认是写模式
            ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
            //将buffer的position设为0，limit设为capacity
            buf.clear();
            for (int j = 0; j < 1024 * 1024; j++) {
                buf.put((byte) 1);
            }
            //转换为读模式
            buf.flip();
            buf.mark();
            long start = System.currentTimeMillis();
            for (int i = 0; i < 2000 ; i ++) {
                buf.reset();
                while (buf.hasRemaining()) {
                    inChannel.write(buf);
                }
            }
            System.out.println("写入时间:" + (System.currentTimeMillis() - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readFromFileByNIO(){
        FileChannel inChannel = null;
        try {
            RandomAccessFile aFile = new RandomAccessFile("E:\\tmp.dat", "r");
            inChannel = aFile.getChannel();
            //创建一个ByteBuffer，默认是写模式
            ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
            long start = System.currentTimeMillis();
            while (inChannel.read(buf) > -1) {
                buf.clear();
            }
            System.out.println("读取时间:" + (System.currentTimeMillis() - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //10392ms
    public static void writeAFileByAIO(){
        Path path = Paths.get("E:\\tmp.dat");
        AsynchronousFileChannel afc = null;
        try {
            afc   = AsynchronousFileChannel.open(path, WRITE, CREATE);
            ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
            //将buffer的position设为0，limit设为capacity
            buf.clear();
            for (int j = 0; j < 1024 * 1024; j++) {
                buf.put((byte) 1);
            }
            long start = System.currentTimeMillis();
            for (int i = 0; i < 2000 ; i ++) {
                buf.flip();
                Future<Integer> result = afc.write(buf, i * 1024 *1024);
                //poll的方式
                while (!result.isDone()) {
//                    System.out.println(result.get());
                    result.get();
                }
                //阻塞的方式
//                int writtenNumberOfBytes = result.get();
//                System.out.println(writtenNumberOfBytes);
            }
            System.out.println("写入时间:" + (System.currentTimeMillis() - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            if(afc != null) {
                try {
                    afc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeAFileByAIO2(){
        Path path = Paths.get("E:\\tmp.dat");
        AsynchronousFileChannel afc = null;
        try {
            afc   = AsynchronousFileChannel.open(path, WRITE, CREATE);
            List<Future<Integer>> results = new ArrayList<>();
            ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
            //将buffer的position设为0，limit设为capacity
            buf.clear();
            for (int j = 0; j < 1024 * 1024; j++) {
                buf.put((byte) 1);
            }
            buf.flip();
            buf.mark();
            long start = System.currentTimeMillis();
            for (int i = 0; i < 2000; i ++) {
                buf.reset();
                results.add(afc.write(buf, i * 1024 *1024));
            }
            for(Future<Integer> future : results) {
                future.get();
            }
            System.out.println("写入时间:" + (System.currentTimeMillis() - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            if(afc != null) {
                try {
                    afc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void writeAFileByAIO3(){
        Path path = Paths.get("E:\\tmp.dat");
        AsynchronousFileChannel afc = null;
        try {
            afc   = AsynchronousFileChannel.open(path, WRITE, CREATE);
            ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
            //将buffer的position设为0，limit设为capacity
            buf.clear();
            for (int j = 0; j < 1024 * 1024; j++) {
                buf.put((byte) 1);
            }
            buf.flip();
            buf.mark();
            Attachment attach  = new Attachment();
            attach.asyncChannel = afc;
            attach.buffer = buf;
            attach.path = path;
            WriteHandler handler = new WriteHandler();
            long start = System.currentTimeMillis();
            for (int i = 0; i < 2000; i ++) {
                buf.reset();
                afc.write(buf, i * 1024 * 1024, attach, handler);
            }
            System.out.println("写入时间:" + (System.currentTimeMillis() - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(afc != null) {
                try {
                    afc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        DiscIO.writeAFileByAIO3();
    }
}

class Attachment {
    public Path  path;
    public ByteBuffer buffer;
    public AsynchronousFileChannel asyncChannel;
    public int count = 2000;

    public synchronized void close(){
        System.out.println(count);
        if(count -- <= 0) {
            try {
                this.asyncChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
class WriteHandler implements CompletionHandler<Integer, Attachment> {
    @Override
    public void completed(Integer result, Attachment attachment) {
        System.out.format("%s bytes written  to  %s%n", result,
                attachment.path.toAbsolutePath());
        attachment.close();
    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {
        System.out.println(exc.getMessage());
        attachment.close();
    }
}