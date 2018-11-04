package segmentfault.Question1010000016887346;

import org.springframework.stereotype.Component;

/**
 * https://segmentfault.com/q/1010000016887346
 * @author ccr12312@163.com at 2018-11-2
 */
@Component("a")
public class A implements B {
    // 加上了Profiling注解，想要此方法被拦截，但实际未被拦截
    @Profiling
    @Override
    public void foo() {
        System.out.println("method invoking...");
    }
}
