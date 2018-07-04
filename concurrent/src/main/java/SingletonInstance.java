import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 懒汉式单例模式的线程安全分析
 */
public class SingletonInstance {

//    private static final SingletonInstance instance = new SingletonInstance();
    //volatile 避免重排序
//    private static volatile SingletonInstance instance = null;
    private static SingletonInstance instance = null;
    private static Object lock = new Object();

    //如果对整个方法加锁，将验证影响性能
    public static SingletonInstance getInstance() throws InterruptedException {
        Thread.sleep(1000);
//        if (instance == null) {
//            instance = new SingletonInstance();
//        }
        //双重检查看似完美，其实并不安全，存在this逸出问题。
        //在外面加判断时为了提升效率，避免每次进来都要去获取锁
        if (instance == null) {
            synchronized (lock) {
                if(instance == null) {
                    instance = new SingletonInstance();
                }
            }
        }
        return instance;
    }

    private SingletonInstance() {
    }

    //安全单例模型
    //这种写法仍然使用JVM本身机制保证了线程安全问题；
    // 由于 SingletonHolder 是私有的，除了 getInstance() 之外没有办法访问它，
    // 因此它是懒汉式的；同时读取实例的时候不会进行同步，没有性能缺陷；也不依赖 JDK 版本。
//    private static class SingletonHolder {
//        private static final SingletonInstance INSTANCE = new SingletonInstance();
//    }
//    public static final SingletonInstance getInstance() {
//        return SingletonHolder.INSTANCE;
//    }


    public static void main(String[] args) throws InterruptedException {
        //线程数量
        int count = 50;
        /*
         * 为了让多个线程同时启动，这里使用了栅栏，可以这么理解：
         * 当所有线程到达栅栏位置时，才会一起继续向下执行
         */
        CyclicBarrier barrier = new CyclicBarrier(count);
        List<Thread> subThreads = new ArrayList<>();
        Long nanoTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //栅栏位置
                        barrier.await();
                        System.out.println("线程" + Thread.currentThread().getId() + ":" + SingletonInstance.getInstance());
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            subThreads.add(t);
        }

        for (Thread subThread : subThreads) {
            subThread.join();
        }
        System.out.println("main thread is done... 耗时" + (System.currentTimeMillis() - nanoTime));
    }

}
