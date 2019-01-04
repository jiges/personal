package segmentfault.Question1010000017720845.test;

/**
 * 将该类Class文件放在 D:\temp\b\segmentfault\Question1010000017720845目录下
 */
public class ClassB {
    public void method(){
        System.out.println("调用ClassB...");
        ClassA a = new ClassA();
        //ClassC not found 也有可能在这里就报了，比如说ClassC确实没找到
        ClassC c = new ClassC();
        //这里调用了ClassA的方法，必然需要先加载ClassA，
        //说明ClassA的加载器是ClassB加载器的父加载器
        a.method(c);
    }
}
