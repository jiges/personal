package com.ccr;

import java.util.Arrays;

/**
 * @author ccr
 * @date 2018/8/22
 */
public class QuickSort {
    /**
     * 分区，取第一个数作为分区基准数
     * 先从左边扫描，小于基准数继续扫描，大于基准数时左边停止扫描，
     * 从右边扫描，找到小于基准数的和左边的进行交换，然后继续扫描
     * @param arr 数组
     * @param left 扫描区间左
     * @param right 扫描区间右
     * @return 基准数位置
     */
    public static int partition(int[] arr,int left,int right) {
        int pivotIndex = left;

        int pivot = arr[left ++];
        while(left <= right) {
            /*
             * 1、arr[left] < pivot继续扫描左边
             * 2、否则扫描右边
             * 3、arr[right] >= pivot继续扫描右边
             * 4、否则交换
             */
            if (arr[left] <= pivot) {
                left ++;
            } else {
                if(arr[right] >= pivot) {
                    right --;
                } else {
                    exch(arr,left,right);
                    left ++;
                    right --;
                }
            }
        }
        //将基准数交换到中间
        exch(arr,pivotIndex,left - 1);
        return left - 1;
    }

    public static void quickSort(int[] arr,int begin,int end) {
        int pivotIndex = partition(arr,begin,end);
        if(begin < pivotIndex - 1)
            quickSort(arr,begin,pivotIndex - 1);
        if(end > pivotIndex + 1)
            quickSort(arr,pivotIndex + 1,end);
    }

    private static void exch(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5,6,7,8,9,10};
//        int[] arr = {10,9,8,7,6,5,4,3,2,1};
//        int[] arr = {3,5,1,4,6,7,10,8,9,2};
        int[] arr = {5,4,6,8,5,5,4,5,4,4};
        System.out.println(Arrays.toString(arr));
//        System.out.println("基准位置：" + partition(arr,0,arr.length - 1));
        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
