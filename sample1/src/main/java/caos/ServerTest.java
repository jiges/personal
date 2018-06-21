package caos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
    public static void main(String[] args)  {
        try {
            ServerSocket server = new ServerSocket(9697);
            while(true) {
                Socket client = server.accept();
                InputStream read = client.getInputStream();
                byte [] info = new byte [1024];
                int len = read.read(info);
                String cli = client.getInetAddress().getHostName();
                System.out.println(cli+":"+new String(info,0,len));
                client.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Server carete defeat");
        }
    }
}
class Client{
    public static void main(String[] args) {
        try {
            for (int i=0;i<10;i++) {
                Socket client = new Socket("127.0.0.1",9697);
                OutputStream out = client.getOutputStream();
                String info = "the"+i+"count";
                out.write(info.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("client carete defeat");
        }
    }
}