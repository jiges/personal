import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 其实称结构化并发应用程序更合适点，不仅仅是线程池，可以叫做任务处理服务，这个概念和线程池是有区别的
 * 线程池的意思是，提供一个装满线程的池子，需要线程时从里面拿，用完了放回去就行
 * 而任务处理服务意思是，有任务直接丢进去，不管里面是怎么实现的。
 *
 * 了解 Callable  和 Runnable 的区别
 *
 * 了解 CompletionService 和 ExecutorService 的区别
 * @author ccr
 */
public class ThreadPoolDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future<String>> futureList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            futureList.add(executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
//                    Thread.sleep(1000);
                    Thread.sleep(new Random().nextInt(1000) + 1000);
                    return "I'm executed by thread " + Thread.currentThread().getName();
                }
            }));
            Thread.sleep(50);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(futureList.get(i).get());
        }
        executorService.shutdown();
    }

//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
//
//        for (int i = 0; i < 10; i++) {
//            completionService.submit(new Callable<String>() {
//                @Override
//                public String call() throws Exception {
////                    Thread.sleep(1000);
//                    Thread.sleep(new Random().nextInt(1000) + 1000);
//                    return "I'm executed by thread " + Thread.currentThread().getName();
//                }
//            });
//            Thread.sleep(50);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(completionService.take().get());
//        }
//        executorService.shutdown();
//    }
}
