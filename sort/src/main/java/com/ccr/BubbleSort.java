package com.ccr;

/**
 * Created by ccr at 2018/7/10.
 */
public class BubbleSort {
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            //记录交换次数，如果这次遍历没有交换,说明无序区已经有序，不用再排序
            int exchCnt = 0;
            for (int j = a.length - 1; j > i; j --) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                    exchCnt ++;
                }
            }
            if(exchCnt == 0) {
                break;
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
