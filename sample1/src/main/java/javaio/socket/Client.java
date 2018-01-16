package javaio.socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by ccr at 2018/1/8.
 */
public class Client {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            //连接server
            socket = new Socket("localhost",9999);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //获取Socket的输入流，用来接收从服务端发送过来的数据
            DataInputStream in =  new DataInputStream(socket.getInputStream());
            out.writeUTF("hello");
            String echo = in.readUTF();
            System.out.println("client receive: " + echo);
        } catch (IOException e1) {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e2) {
                    socket = null;
                }
            }
            e1.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    socket = null;
                }
            }
        }
    }
}
