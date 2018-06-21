package caos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ccr at 2018/5/22.
 */
public class forEachTest {
    public static void forEach1(List<String> list) {
        list.forEach(System.out::println);
    }

    public static void forEach2(List<String> list) {
        for (String item:list) {
            System.out.println(item);
        }
    }
    public static void forEach3(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("1","2"));
        forEach1(list);
        forEach2(list);
        forEach3(list);
    }
}
