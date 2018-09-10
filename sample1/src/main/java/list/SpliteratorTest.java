package list;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

public class SpliteratorTest {
    public static void main(String[] args) {
        List<String> arrs = new ArrayList<>();
        arrs.add("a");
        arrs.add("b");
        arrs.add("c");
        arrs.add("d");
        arrs.add("e");
        arrs.add("f");
        arrs.add("h");
        arrs.add("i");
        arrs.add("j");
        Spliterator<String> a =  arrs.spliterator();
        System.out.println(a.tryAdvance(System.out::println));

//        a.forEachRemaining(System.out::println);
        Spliterator<String> b =  a.trySplit();
        System.out.println(a.estimateSize());
        Spliterator<String> c=  b.trySplit();
        a.forEachRemaining(System.out::println);
        System.out.println(a.estimateSize());
        System.out.println("-----");
        b.forEachRemaining(System.out::println);
        System.out.println("-----");
        c.forEachRemaining(System.out::println);
//        System.out.println(a.characteristics());
    }
}
