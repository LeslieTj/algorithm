package com.mosaicsheep.sort.selectsort;

import java.text.SimpleDateFormat;
import java.util.Date;
// 选择排序和冒泡排序的时间复杂度是一样的
// 但是选择排序的运行时间要短于冒泡排序
public class SelectSortTest {

    public static void main(String[] args) {

        // 测试选择排序法的速度O(n^2)
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }

        Date d1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1str = simpleDateFormat.format(d1);
        System.out.println("排序前的时间：" + d1str);

        selectSort(arr);

        Date d2 = new Date();
        String d2str = simpleDateFormat.format(d2);
        System.out.println("排序后的时间：" + d2str);
    }


    public static int[] selectSort(int[] arr) {

        int minIndex;
        int min;

        for (int i = 0; i < arr.length - 1; i++) {

            minIndex = i;
            min = arr[i];

            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];

                }
            }

            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }


        }

        return arr;
    }
}