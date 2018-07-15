package com.ccr.quicksort;

import java.util.Random;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int length = 20;
        int[] arr = new int[length];
        Random rdm = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = rdm.nextInt(length);
        }
        printArr(arr);


    }

    public static void quickSort(int[] arr,int start,int end) {
        int middle = arr[start];
        while (start < end) {
            if(arr[start] < middle) {

            }
        }

    }

    public static void printArr(int[] arr){
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }
}
