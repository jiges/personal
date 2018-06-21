import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 懒汉式单例模式的线程安全分析
 */
public class SingletonInstance {

    private static SingletonInstance instance = null;

    public static SingletonInstance getInstance() {
        if (instance == null) {
            instance = new SingletonInstance();
        }
        return instance;
    }


    public static void main(String[] args) throws InterruptedException {
        //线程数量
        int count = 10;
        /*
         * 为了让多个线程同时启动，这里使用了栅栏，可以这么理解：
         * 当所有线程到达栅栏位置时，才会一起继续向下执行
         */
        CyclicBarrier barrier = new CyclicBarrier(count);
        List<Thread> subThreads = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //栅栏位置
                        barrier.await();
                        Thread.sleep(10000);
                        System.out.println("线程" + Thread.currentThread().getId() + ":" + SingletonInstance.getInstance());
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            subThreads.add(t);
        }

//        for (Thread subThread : subThreads) {
//            subThread.join();
//        }
        System.out.println("main thread is done...");
    }

}
