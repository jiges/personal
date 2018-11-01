package concurrent;

import java.util.concurrent.Semaphore;

/**
 * @author ccr12312@163.com at 2018-10-23
 */
public class AbcProblem {

    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);

        Thread t1 = new Thread(() -> {
            int i = 0;
            while (i++ < n) {
                try {
                    s1.acquire();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.print("a");
                s2.release();
            }
        });

        Thread t2 = new Thread(() -> {
            int i = 0;
            while (i++ < n) {
                try {
                    s2.acquire();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.print("b");
                s3.release();
            }
        });

        Thread t3 = new Thread(() -> {
            int i = 0;
            while (i++ < n) {
                try {
                    s3.acquire();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.print("c");
                s1.release();
            }
        });

        t1.join();
        t2.join();
        t3.join();
        t1.start();
        t2.start();
        t3.start();

    }
}
