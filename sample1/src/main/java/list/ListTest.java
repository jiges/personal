package list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by ccr at 2018/5/4.
 */
public class ListTest {

    public class TimeObj {
        Date time;
    }

    public static void main(String[] args) {
        List<TimeObj> list = new ArrayList<>();
        list.sort((o1, o2) -> o1.time.compareTo(o2.time));
    }
}
