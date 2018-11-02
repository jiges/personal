package segmentfault.Question1010000016887346;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootApplication.class);

        ApplicationContext context = application.run(args);

        //调用事物1
        B bean = context.getBean("a", B.class);
        bean.foo();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("JVM正在关闭.....");
        }));
    }
}
