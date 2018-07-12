package com.ccr;

import java.util.Random;

/**
 * 构造序列
 * Created by ccr at 2018/7/10.
 */
public class ArrayConstructor {
    /**
     * 构造一个随机序列
     * @param cnt 序列规模
     * @return 随机序列
     */
    public static Comparable[] constructRandom(int cnt){
        Integer[] arr = new Integer[cnt];
        Random rnd = new Random();
        for (int i = 0; i < cnt; i++) {
            arr[i] = rnd.nextInt(cnt * 10);
        }
        return arr;
    }

    /**
     * 构造一个接近排序的序列
     * @param cnt 序列规模
     * @return NearlySortedArr
     */
    public static Comparable[] constructNearlySorted(int cnt){
        Integer[] arr = new Integer[cnt];
        Random rnd = new Random();
        for (int i = 0; i < cnt; i++) {
            arr[i] = rnd.nextInt(cnt * 10);
        }
        return arr;
    }
}
