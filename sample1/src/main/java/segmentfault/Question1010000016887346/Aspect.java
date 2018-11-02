package segmentfault.Question1010000016887346;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * https://segmentfault.com/q/1010000016887346
 * @author ccr12312@163.com at 2018-11-2
 */
// 定义的切面
@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {

    @Pointcut("@annotation(segmentfault.Question1010000016887346.Profiling)")
    private void pointCut(){
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("do something before the real invocation....");
        pjp.proceed();
        System.out.println("do something after the real invocation....");
    }
}
