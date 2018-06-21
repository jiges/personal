package javaio.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ccr at 2018/3/16.
 * @author ccr
 */
public class NetBioServer {
    public static void main(String[] args) {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(3);
            ServerSocket server = new ServerSocket(8888);
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
