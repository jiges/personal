/**
 * Created by ccr at 2018/4/29.
 */
public class Client {
    public static void main(String[] args) {
        Subject proxy = new Proxy();
        proxy.doSomething("static proxy");
        proxy.method2();
    }
}
