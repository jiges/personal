import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by ccr at 2018/4/29.
 */
public class MyInvocationHandler implements InvocationHandler {

    Object subject;

    public MyInvocationHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke method " + method.getName() + " args " + Arrays.toString(args));
        return  method.invoke(subject, args);
    }
}
