package segmentfault.Question1010000016803089;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * mysql更新死锁问题
 */
@EnableAutoConfiguration
@ComponentScan
public class SpringBootApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication application = new SpringApplication(segmentfault.Question1010000016803089.SpringBootApplication.class);
        Map<String, Object> defaultMap = new HashMap<String, Object>();
        //设置数据库连接
        defaultMap.put("spring.datasource.driverClassName", "com.mysql.jdbc.Driver");
        defaultMap.put("spring.datasource.url", "jdbc:mysql://192.168.1.105:3306/sakila?useUnicode=true&characterEncoding=utf-8");
        defaultMap.put("spring.datasource.username", "root");
//        defaultMap.put("spring.datasource.password", "5Yg6f4x1%bDiX%Q*");
        defaultMap.put("spring.datasource.password", "root");
        application.setDefaultProperties(defaultMap);
        ApplicationContext context = application.run(args);

        BusinessService businessService = context.getBean("businessService", BusinessService.class);
        int threadCount = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        CountDownLatch flagLatch = new CountDownLatch(1);

        for (int i=0;i<threadCount;i++) {
            new Thread(() -> {
                try {
                    flagLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    businessService.addScore1();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        // 所有线程同时开始执行
        flagLatch.countDown();
        countDownLatch.await();
        System.out.println("执行完成~~");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("JVM正在关闭.....");
        }));
    }
}
