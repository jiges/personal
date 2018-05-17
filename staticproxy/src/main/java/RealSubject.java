/**
 * Created by ccr at 2018/4/29.
 */
public class RealSubject implements Subject {

    @Override
    public void doSomething(String args) {
        System.out.println("real subject is doing something...." + " args: " + args);
    }

    @Override
    public void method2() {
        System.out.println("method2....");
    }
}
