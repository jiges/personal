package Javalang;

/**
 * Created by ccr at 2017/12/26.
 */
public class ParamPass {
    void invoke(A a) {
//        a = new A();
        a.a = 1;
    }

    public static void main(String[] args) {
        A a = new A();
        ParamPass instance = new ParamPass();
        System.out.println(a.a);
        instance.invoke(a);

        System.out.println(a.a);
    }
}

class A {
    int a = 0;
}

