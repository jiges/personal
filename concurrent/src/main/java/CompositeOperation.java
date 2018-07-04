import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by ccr at 2018/7/2.
 */
public class CompositeOperation {
    public static int value = 0;

    public static void main(String[] args) throws InterruptedException {
        //线程数量
        int count = 100;
        /*
         * 为了让多个线程同时启动，这里使用了栅栏，可以这么理解：
         * 当所有线程到达栅栏位置时，才会一起继续向下执行
         */
        CountDownLatch latch = new CountDownLatch(count);
        List<Thread> subThreads = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //栅栏位置
                        latch.await();
                        for (int j = 0; j < 1000; j++) {
                            value ++;
                        }
                        CompositeOperation.value ++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            latch.countDown();
            subThreads.add(t);
        }

        for (Thread subThread : subThreads) {
            subThread.join();
        }
        System.out.println("main thread is done,the value is " + value);
    }
}
