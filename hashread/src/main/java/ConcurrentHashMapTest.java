import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap测试
 */
public class ConcurrentHashMapTest {
    static ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
    public static void main(String[] args) {


        map.putIfAbsent("key","value");
        Iterator it = map.entrySet().iterator();
    }

}
