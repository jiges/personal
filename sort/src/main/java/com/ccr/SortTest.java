package com.ccr;

/**
 * Created by ccr at 2018/7/10.
 */
public class SortTest {
    public static void main(String[] args) {
        //先构造一个序列
        int cnt = 60000;
        Comparable[] a = ArrayConstructor.constructRandom(cnt);
        SelectionSort.show(a);
        long time = System.currentTimeMillis();
        SelectionSort.sort(a);
        System.out.println("耗时：" + (System.currentTimeMillis() - time));
        SelectionSort.show(a);

//        int cnt = 60000;
//        Comparable[] a = ArrayConstructor.constructRandom(cnt);
//        BubbleSort.show(a);
//        System.out.println(a.length);
//        long time = System.currentTimeMillis();
//        BubbleSort.sort(a);
//        System.out.println("耗时：" + (System.currentTimeMillis() - time));
//        System.out.println(a.length);
//        BubbleSort.show(a);
    }
}
