import java.io.*;
import java.util.concurrent.CountDownLatch;

/**
 * 该示例用来说明：
 * 在调用new File()函数时，操作系统为该文件在内核缓冲区分配一个高速缓冲
 * 如果有多个线程访问这个高速缓冲，将会出现互斥问题。如果为每一个线程都创建
 * 一个文件，将会有多个高速缓冲，不会有互斥问题，但是在真正写入文件时，
 * 由于写入的是同一个文件，操作系统将会维护一个队列，将内核缓冲区的内容按照
 * 最优的方式写入磁盘。
 */
public class SharingFileReadWrite {
//    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(20);
//        final RandomAccessFile writer = new RandomAccessFile (new File("D:\\Temp\\data.txt"),"rw");
//        for (int i = 0; i < 5; i++) {
//            final int pos = i;
//            new Thread(() -> {
//                countDownLatch.countDown();
//                InputStream inputStream = null;
//                try {
////                    inputStream = new FileInputStream(new File("D:\\Temp\\data.txt"));
////                    byte[] bytes = new byte[3];
////                    int len = inputStream.read(bytes);
////                    System.out.println(new String(bytes));
//
//                    long nanotime = System.nanoTime();
//                    System.out.println(Thread.currentThread().getName() + "开始写入");
//
//                    writer.seek(pos * 3);
//                    writer.write(new byte[1024 * 1024 * 300]);
//                    System.out.println("写入时间：" + System.currentTimeMillis());
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
////                    try {
////                        inputStream.close();
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
//
//                }
//            }).start();
//        }
//        Thread.sleep(30000);
//        try {
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 5; i++) {
            final int pos = i;
            new Thread(() -> {
                countDownLatch.countDown();
                try {
                    RandomAccessFile writer = new RandomAccessFile (new File("D:\\Temp\\data.txt"),"rw");
                    long nanotime = System.nanoTime();
                    System.out.println(writer.getFD());
                    System.out.println(Thread.currentThread().getName() + "开始写入");
                    writer.seek(pos * 3);
                    writer.write(new byte[1024 * 1024 * 100]);
                    System.out.println("写入时间：" + System.currentTimeMillis());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
