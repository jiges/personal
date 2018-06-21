package javaio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ccr at 2018/3/7.
 * @author ccr
 */
public class AsynchronousTest {
    private static void testFuture() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            final int finalI = i;
            Future<String> future = executor.submit(() -> {
                System.out.println("task["+ finalI +"] started!");
                // cost some time
                Thread.sleep(1000*(3-finalI));
                System.out.println("task["+ finalI +"]finished!");
                return "result["+ finalI +"]";
            });
            futureList.add(future);
        }

        for (Future<String> future : futureList) {
            System.out.println(future.get());
        }
        System.out.println("Main thread finished.");
        executor.shutdown();
    }

    private static void testCompletionService() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<String> service = new ExecutorCompletionService<String>(executor);
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            service.submit(() -> {
                System.out.println("task["+ finalI +"] started!");
                // cost some time
                Thread.sleep(1000*(3-finalI));
                System.out.println("task["+ finalI +"]finished!");
                return "result["+ finalI +"]";
            });
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(service.take().get());
        }
        System.out.println("Main thread finished.");
        executor.shutdown();
    }

    public static void testCompletableFuture(){
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                System.out.println("task["+finalI+"] started!");
                try {
                    // time cost
                    Thread.sleep(1000*(3-finalI));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task["+finalI+"] finished");
                return "result["+finalI+"]";
            }, executor);
            future.thenAccept(System.out::println);
        }

        System.out.println("Main thread finished.");
        executor.shutdown();
    }

    public static Object get(){
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AsynchronousTest.testFuture();
        Object o = get();
    }
}
