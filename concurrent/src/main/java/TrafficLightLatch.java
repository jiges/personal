import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 交通信号灯实现
 * 等红灯亮时把所有汽车线程都暂停，当绿灯亮时把所有线程都启动起来
 * @author ccr at 20181008
 */
public class TrafficLightLatch {
    private final class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int ignored) {
            return redLight == 0 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return true;
        }
    }

    /**
     * 信号灯状态，非0代表红灯，0代表绿灯
     */
    volatile private int redLight;

    private Sync sync;

    /**
     * 初始化
     * @param redLight 初始红绿灯状态，非0代表红灯，0代表绿灯
     */
    public TrafficLightLatch(int redLight) {
        this.redLight = redLight;
        sync = new Sync();
    }

    /**
     * 红灯则等待，绿灯则直接通过
     * @throws InterruptedException
     */
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    /**
     * 切换为红灯
     */
    public void switchRed(){
        this.redLight = 1;
    }

    /**
     * 切换为绿灯
     */
    public void switchGreen() {
        this.redLight = 0;
        sync.releaseShared(0);
    }

    /**
     * 是否有线程等待
     */
    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    /**
     * 等待中的线程
     * @return Collection
     */
    public Collection<Thread> getQueuedThreads() {
        return sync.getQueuedThreads();
    }

    public String getLightColor() {
        return redLight == 0 ? "绿灯" : "红灯";
    }

    //测试用例
    public static void main(String[] args) throws IOException, InterruptedException {
        //初始化交通信号灯为红灯
        TrafficLightLatch light = new TrafficLightLatch(1);
        List<Thread> threads = new ArrayList<>();
        //10个线程模拟车辆
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    //System.out.println(String.format("%s: 信号灯-%s",Thread.currentThread().getName(),light.getLightColor()));
                    try {
                        //绿灯直接通行，红灯阻塞等待
                        light.await();
                    } catch (InterruptedException e) {
                        //恢复中断
                        Thread.currentThread().interrupt();
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        //恢复中断
                        Thread.currentThread().interrupt();
                    }
                    System.out.println(String.format("%s: 信号灯-%s 通行",Thread.currentThread().getName(),light.getLightColor()));
                }
            });
            threads.add(thread);
            thread.start();
        }

        //等待输入命令切换交通信号灯
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while ((command = reader.readLine()) != null) {
            if(command.equals("switchRed")) {
                light.switchRed();
                Thread.sleep(1000);
                System.out.println(String.format("等待线程数：%d",light.getQueuedThreads().size()));
            } else if(command.equals("switchGreen")) {
                light.switchGreen();
                Thread.sleep(500);
                System.out.println(String.format("等待线程数：%d",light.getQueuedThreads().size()));
            } else if (command.equals("stop")){
                System.out.println("terminating...");
                threads.forEach(Thread::interrupt);
                break;
            }
        }
    }
}
