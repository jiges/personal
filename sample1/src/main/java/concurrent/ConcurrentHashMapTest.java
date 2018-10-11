package concurrent;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    static ConcurrentHashMap<String,Object> chm = new ConcurrentHashMap<>();

    static {
        chm.put("AA","AA");
        chm.put("BB","BB");
        chm.put("CC","CC");
        chm.put("DD","DD");
    }

    public static void testKeySet(){
        ConcurrentHashMap.KeySetView keySetView = chm.keySet();

        keySetView.add("EE");
        System.out.println(chm);
    }

    public static void main(String[] args) {
        testKeySet();
    }
}
