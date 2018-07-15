import java.util.concurrent.CountDownLatch;

/**
 * Created by ccr at 2018/7/2.
 */
public class ThreadLocalDemo {

    public static ThreadLocal<ThreadLocalDemo> sharedVar = new ThreadLocal<>();

    public String var;

    public ThreadLocalDemo(String var) {
        this.var = var;
    }

    public static void main(String[] args) {
        //线程数量
        int count = 10;
        /*
         * 为了让多个线程同时启动，这里使用了栅栏，可以这么理解：
         * 当所有线程到达栅栏位置时，才会一起继续向下执行
         */
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sharedVar.set(new ThreadLocalDemo(Thread.currentThread().getName()));
                new B().invoke();
            }).start();
            latch.countDown();
        }

    }

}

class B {
    public void invoke() {
        System.out.println(Thread.currentThread().getName() + ": " + ThreadLocalDemo.sharedVar.get().var);
    }
}

class ThreadLocalExample {

    public static class MyRunnable implements Runnable {

        private ThreadLocal threadLocal = new ThreadLocal();

        @Override
        public void run() {
            threadLocal.set((int) (Math.random() * 100D));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
            System.out.println(threadLocal.get());
        }
    }

    public static void main(String[] args) {
        MyRunnable sharedRunnableInstance = new MyRunnable();
        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);
        thread1.start();
        thread2.start();
    }
}
