package javaio.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by ccr at 2018/1/8.
 */
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9999);
            while (true) {
                //会阻塞线程，等待请求
                Socket client = server.accept();
                //请求到来时，开启新的线程处理请求
                System.out.println("新请求到来");
                new Thread(new Handler(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Handler implements Runnable {
    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            //获取Socket的输出流，用来向客户端发送数据
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String input = in.readUTF();
            System.out.println("server receive: " + input);
            TimeUnit.SECONDS.sleep(3);
            out.writeUTF("echo:" + input + " too");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}