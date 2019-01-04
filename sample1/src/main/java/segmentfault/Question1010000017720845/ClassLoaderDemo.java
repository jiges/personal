package segmentfault.Question1010000017720845;



import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ClassLoaderDemo.java
 */
class MyClassLoader_1 extends ClassLoader {

    public MyClassLoader_1(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) {
        String myPath = "file:/D:/temp/a/" + name.replace(".","/") + ".class";
        System.out.println("classLoader_1 :" + myPath);
        byte[] cLassBytes = null;
        Path path = null;
        try {
            path = Paths.get(new URI(myPath));
            cLassBytes = Files.readAllBytes(path);
        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
            System.out.println("找不到资源");
            return null;
        }
        Class clazz = defineClass(name, cLassBytes, 0, cLassBytes.length);
        return clazz;
    }
}

/**
 * 加载ClassB和ClassC
 */
class MyClassLoader_2 extends ClassLoader {
    public MyClassLoader_2(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) {
        String myPath = "file:/D:/temp/b/" + name.replace(".","/") + ".class";
        System.out.println("classLoader_2 :" + myPath);
        byte[] cLassBytes = null;
        Path path = null;
        try {
            path = Paths.get(new URI(myPath));
            cLassBytes = Files.readAllBytes(path);
        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
            System.out.println("找不到资源");
            return null;
        }
        Class clazz = defineClass(name, cLassBytes, 0, cLassBytes.length);
        return clazz;
    }
}

public class ClassLoaderDemo {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader_1 classLoader_1 = new MyClassLoader_1(ClassLoaderDemo.class.getClassLoader());
        MyClassLoader_2 classLoader_2 = new MyClassLoader_2(classLoader_1);
        Class classB = classLoader_2.loadClass("segmentfault.Question1010000017720845.ClassB");
        Object classBInstance = classB.newInstance();
        Method method = classB.getMethod("method");
        method.invoke(classBInstance);
    }

}
