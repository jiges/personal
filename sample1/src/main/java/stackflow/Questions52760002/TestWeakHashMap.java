package stackflow.Questions52760002;


import java.util.Map;
import java.util.WeakHashMap;

public class TestWeakHashMap {

    public static void main(String[] args) throws InterruptedException {
        Object key = new Object();

        Map map = new WeakHashMap();
        map.put(key,new Object());

//        System.out.println(map);

        key = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println(map);


    }
}
