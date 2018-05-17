package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by ccr at 2018/3/23.
 */
public class VolatileTest {

    public static volatile boolean asleep;

    public static void main(String[] args) {

        new Thread(){
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                while (!asleep) {
//                    System.out.println("11");
                }
                System.out.println(System.currentTimeMillis() - time);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(5000);
                    asleep = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
