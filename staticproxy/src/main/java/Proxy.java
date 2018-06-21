/**
 * Created by ccr at 2018/4/29.
 */
public class Proxy implements Subject{

    Subject realSubject;

    public Proxy() {
        this.realSubject = new RealSubject();
    }

    @Override
    public void doSomething(String args) {
        System.out.println("before doing something...");
        realSubject.doSomething(args);
        System.out.println("after doing something...");
    }

    @Override
    public void method2() {
        System.out.println("before method2...");
        realSubject.method2();
        System.out.println("after method2...");
    }
}
