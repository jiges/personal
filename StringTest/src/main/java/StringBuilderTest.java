/**
 * Created by ccr at 2018/6/6.
 */
public class StringBuilderTest {
    public static void main(String[] args) {
//        String s1 = "hello";
//        String s2 = "hello";
//        String s3 = "he" + "llo";
//        //这个会打印true，因为s1和s2都指向了常量池中的"hello"
//        System.out.println(s1 == s2);
//        //这个也会打印true，因为编译器会做优化将"he" + "llo"合并为"hello"
//        System.out.println(s1 == s3);
        String s1 = "hello";
        String s2 = new String("hello");
        //false s1指向常量池，s2指向堆
        System.out.println(s1 == s2);
        //true s2.intern()返回常量池的引用
        System.out.println(s1 == s2.intern());
    }

//    String str;
//
//    public void setStr() {
//        this.str = "hello";
//    }
}
