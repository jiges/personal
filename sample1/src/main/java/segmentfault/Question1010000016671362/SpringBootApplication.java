package segmentfault.Question1010000016671362;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring事物问题，在一个方法里需要更新某张表的数据并使用最新的这个数据，使用事物注解应该如何写？
 */
//@org.springframework.boot.autoconfigure.SpringBootApplication
//@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("JVM正在关闭.....");
        }));
    }
}
