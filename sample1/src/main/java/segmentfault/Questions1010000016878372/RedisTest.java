package segmentfault.Questions1010000016878372;

import java.util.concurrent.*;

public class RedisTest {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(1000);
    private static CyclicBarrier c = new CyclicBarrier(30);
    private static CountDownLatch latch = new CountDownLatch(30);

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(RedisUtil.get("foo"));
        for (int i = 0; i < 30; i++) {

            executorService.submit(() -> {

                System.out.println("num  end end====" + c.getNumberWaiting() + "   " + Thread.currentThread().getId() + " " + Thread.currentThread().getName());
                System.out.println("id =====>:" + Thread.currentThread().getId() + " " + Thread.currentThread().getName() + " " + RedisUtil.get("foo"));
                latch.countDown();
            });
        }
        latch.await();
        executorService.shutdown();
    }
}
