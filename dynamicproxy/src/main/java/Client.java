import java.lang.reflect.Proxy;

/**
 * Created by ccr at 2018/4/29.
 */
public class Client {
    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        MyInvocationHandler handler = new MyInvocationHandler(realSubject);
        Subject proxy = (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),realSubject.getClass().getInterfaces(),handler);
        proxy.doSomething("test");
    }
}
