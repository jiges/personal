import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列
 * @author ccr
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue(10);

        //生产者线程
        new Thread(() ->{
            for (int i = 0; i < 12; i ++) {
                try {
                    queue.put(String.valueOf(i));
                    System.out.println("我是生产者线程，我现在往队列中赛数据，i = " + i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //消费者线程
        new Thread(() ->{
            try {
                Thread.sleep(13000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    System.out.println("我是消费者线程，我现在从队列中获取数据，i = " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
