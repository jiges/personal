package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by ccr at 2018/3/21.
 * @author ccr
 */
public class TestSynchronized {

    public synchronized void method1() throws InterruptedException {
        System.out.println("method1 invoked by thread:" + Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(10);
    }

    public synchronized void method2() throws InterruptedException {
        System.out.println("method2 invoked by thread:" + Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(10);
    }

    public static void main(String[] args) {
//        TestSynchronized testSynchronized = new TestSynchronized();
        Thread t1 = new MyThread1("thread1",new TestSynchronized());
        Thread t2 = new MyThread1("thread2",new TestSynchronized());
        t1.start();
        t2.start();

    }
}

class MyThread1 extends Thread {

    TestSynchronized testSynchronized;

    public MyThread1(String name, TestSynchronized testSynchronized) {
        super(name);
        this.testSynchronized = testSynchronized;
    }

    @Override
    public void run() {
        try {
            testSynchronized.method1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class MyThread2 extends Thread {

    TestSynchronized testSynchronized;

    public MyThread2(String name, TestSynchronized testSynchronized) {
        super(name);
        this.testSynchronized = testSynchronized;
    }

    @Override
    public void run() {
        try {
            testSynchronized.method2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}