package concurrent;

/**
 * Created by ccr at 2018/5/9.
 */
public class WaitTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("线程运行结束...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        System.out.println("中断线程...");
        thread.interrupt();
    }
}
