package com.mosaicsheep.sort.quicksort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuickSortTest {
    public static void main(String[] args) {

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }

        Date d1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1str = simpleDateFormat.format(d1);
        System.out.println("排序前的时间：" + d1str);

        quickSort(arr, 0, arr.length - 1);

        Date d2 = new Date();
        String d2str = simpleDateFormat.format(d2);
        System.out.println("排序后的时间：" + d2str);

    }

    public static void quickSort(int[] arr, int left, int right) {

        int leftIndex = left;
        int rightIndex = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;

        while (leftIndex < rightIndex) {

            while (arr[leftIndex] < pivot) {
                leftIndex++;
            }
            while (arr[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex >= rightIndex) {
                break;
            }

            temp = arr[leftIndex];
            arr[leftIndex] = arr[rightIndex];
            arr[rightIndex] = temp;

            if (arr[leftIndex] == pivot) {
                rightIndex--;
            }
            if (arr[rightIndex] == pivot) {
                leftIndex++;
            }

        }

        if (leftIndex == rightIndex) {
            leftIndex++;
            rightIndex--;
        }
        if (left < rightIndex) {
            quickSort(arr, left, rightIndex);
        }
        if (right > leftIndex) {
            quickSort(arr, leftIndex, right);
        }

    }
}
