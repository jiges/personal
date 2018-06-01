import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ccr at 2018/5/16.
 */
public class HashMapTest {
    public static void main(String[] args) throws InterruptedException {
        Map<String,Object> m = new HashMap<>();
//        m.put("1",null);
//        m.put("2",null);
//        m.put("3",null);
//        m.put("4",null);
//        m.put("5",null);
//        m.put("6",null);
//        m.put("7",null);
//        m.put("8",null);
//        m.put("9",null);
//        m.put("0",null);
//        m.put("11",null);
//        m.put("12",null);
//        m.put("13",null);
        m.put("abcd",null);
        m.put("abdE",null);
        m.put("abe&",null);
        m.put("acDd",null);
        m.put("acEE",null);
        m.put("acF&",null);
        m.put("ad&E",null);
        m.put("bCcd",null);
        m.put("bCdE",null);
        m.put("bDDd",null);
        m.put("bDEE",null);
        m.put("bDF&",null);
        m.put("bE%d",null);
        m.put("bE&E",null);
        m.put("bE'&",null);
        m.put("c$cd",null);
        Iterator<Map.Entry<String,Object>> it = m.entrySet().iterator();

        System.out.println(m);
        System.out.println(m.hashCode());

        System.out.println("打印空心菱形");
        System.out.println("  *  \n * * \n  *  ");
//        System.out.println("");
//        System.out.println("");
    }
}
