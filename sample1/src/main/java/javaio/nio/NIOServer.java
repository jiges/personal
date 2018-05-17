package javaio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by ccr at 2018/1/17.
 */
public class NIOServer {

    /**
     * NIO网络编程的几个要素
     * 1、ServerSocketChannel，对应传统IO中ServerSocket。
     * 2、SocketChannel，对应传统IO中Socket。
     * 3、Selector，监听ServerSocket和Socket相应事件的发生。
     */

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();

        //绑定监听端口
        serverChannel.socket().bind(new InetSocketAddress(9999));
        serverChannel.configureBlocking(false);

        //注册到selector,对ACCEPT事件感兴趣
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        //selector监听，一旦事件到来，则处理
        while (true) {
            //阻塞监听
            selector.select();
            //获取已经到来的事件
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            //如果有事件则处理，否则继续监听
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                // 当获取一个 SelectionKey 后, 就要将它删除, 表示我们已经对这个 IO 事件进行了处理.
                keyIterator.remove();

                if (key.isAcceptable()) {
                    // 当 OP_ACCEPT 事件到来时, 我们就有从 ServerSocketChannel 中获取一个 SocketChannel,
                    // 代表客户端的连接
                    // 注意, 在 OP_ACCEPT 事件中, 从 key.channel() 返回的 Channel 是 ServerSocketChannel.
                    // 而在 OP_WRITE 和 OP_READ 中, 从 key.channel() 返回的是 SocketChannel.
                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                    clientChannel.configureBlocking(false);
                    //在 OP_ACCEPT 到来时, 再将这个 Channel 的 OP_READ 注册到 Selector 中.
                    // 注意, 这里我们如果没有设置 OP_READ 的话, 即 interest set 仍然是 OP_CONNECT 的话, 那么 select 方法会一直直接返回.
                    clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(256));
                } else {
                    if (key.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer buf = (ByteBuffer) key.attachment();
                        long bytesRead = clientChannel.read(buf);
                        if (bytesRead == -1) {
                            clientChannel.close();
                        } else if (bytesRead > 0) {
                            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                            System.out.println("Get data length: " + bytesRead);
                        }
                    }

                    if (key.isValid() && key.isWritable()) {
                        ByteBuffer buf = (ByteBuffer) key.attachment();
                        buf.flip();
                        SocketChannel clientChannel = (SocketChannel) key.channel();

                        clientChannel.write(buf);

                        if (!buf.hasRemaining()) {
                            key.interestOps(SelectionKey.OP_READ);
                        }
                        buf.compact();
                    }
                }

            }
        }
    }
}
