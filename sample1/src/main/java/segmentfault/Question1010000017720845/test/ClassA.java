package segmentfault.Question1010000017720845.test;

/**
 * 将该类Class文件放在 D:\temp\a\segmentfault\Question1010000017720845目录下
 */
public class ClassA {

    public void method(ClassC c) {
        System.out.println("调用ClassA.method()");
        System.out.println(c);
        //打印的是ClassB和ClassC的加载器 segmentfault.Question1010000017720845.MyClassLoader_2@23fc625e
        System.out.println(c.getClass().getClassLoader());
        //只有在ClassA中显式的加载ClassC才会报 classC not found
        try {
            Class classC = this.getClass().getClassLoader().loadClass("segmentfault.Question1010000017720845.ClassC");
        } catch (ClassNotFoundException e) {
            System.out.println("classC not found");
        }

    }
}
