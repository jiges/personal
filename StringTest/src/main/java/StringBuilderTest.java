/**
 * Created by ccr at 2018/6/6.
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        String s = new StringBuilder("ja").append("va").toString();
        System.out.println(s.intern() == s);
    }
}
