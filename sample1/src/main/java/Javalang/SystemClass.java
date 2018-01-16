package Javalang;

import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Created by ccr at 2017/11/13.
 */
public class SystemClass {

    public static void main(String[] args) {
        System.loadLibrary("HelloJNI");
        System.out.println(System.getProperty("java.library.path"));
//        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
//        System.out.println("===================java options=============== ");
//        System.out.println(inputArguments);
//        while (true) {
//            System.out.println(ClassInit.finalStr);
//        }

    }
}
