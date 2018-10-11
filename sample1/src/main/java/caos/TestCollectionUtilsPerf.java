package caos;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * stackoverflow questions
 * https://stackoverflow.com/questions/52723826/performance-difference-between-collectionutils-isnotempty-and-in-place-check
 */
public class TestCollectionUtilsPerf {

    public static void main(String[] args) {
        List<String> stringList = Arrays
                .asList(new String[] { "StringOne", "StringTwo", "StringThree", "StringFour", "StringFive" });
        CollectionUtils.emptyCollection();
        long startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            if (stringList != null && stringList.size() != 0) {
                continue;
            }
        }

        System.out.format("Manual Inplace Check Time taken is : %d µs %n", (System.nanoTime() - startTime) / 1000);

        startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            if (CollectionUtils.isNotEmpty(stringList)) {
                continue;
            }
        }

        System.out.format("Collection Utils Time taken is     : %d µs %n", (System.nanoTime() - startTime) / 1000);

        startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            if (isNotEmpty(stringList)) {
                continue;
            }
        }

        System.out.format("Manual Method Check Time taken is  : %d µs %n", (System.nanoTime() - startTime) / 1000);

    }

    public static boolean isEmpty(final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(final Collection<?> coll) {
        return !isEmpty(coll);
    }

}