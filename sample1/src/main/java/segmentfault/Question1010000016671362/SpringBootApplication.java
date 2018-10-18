package segmentfault.Question1010000016671362;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;

/**
 * spring事物问题，在一个方法里需要更新某张表的数据并使用最新的这个数据，使用事物注解应该如何写？
 */
//@org.springframework.boot.autoconfigure.SpringBootApplication
//@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootApplication.class);
        Map<String, Object> defaultMap = new HashMap<String, Object>();
        //设置数据库连接
        defaultMap.put("spring.datasource.driverClassName", "com.mysql.jdbc.Driver");
        defaultMap.put("spring.datasource.url", "jdbc:mysql://192.168.1.105:3306/sakila?useUnicode=true&characterEncoding=utf-8");
        defaultMap.put("spring.datasource.username", "root");
//        defaultMap.put("spring.datasource.password", "5Yg6f4x1%bDiX%Q*");
        defaultMap.put("spring.datasource.password", "root");
        application.setDefaultProperties(defaultMap);
        ApplicationContext context = application.run(args);

        //调用事物1
        SpringTransaction_1 transaction = context.getBean("springTransaction_1", SpringTransaction_1.class);
        transaction.transaction_1();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("JVM正在关闭.....");
        }));
    }
}
