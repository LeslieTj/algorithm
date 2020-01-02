package com.mosaicsheep.sort.bubblesort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSortOptimizedTest {
    public static void main(String[] args) {

        // 测试冒泡排序法的速度O(n^2)
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }

        Date d1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1str = simpleDateFormat.format(d1);
        System.out.println("排序前的时间：" + d1str);

        bubbleSort(arr);

        Date d2 = new Date();
        String d2str = simpleDateFormat.format(d2);
        System.out.println("排序后的时间：" + d2str);


    }

    // 将优化后的冒泡排序法封装成方法:
    public static int[] bubbleSort(int[] arr) {

        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }

        return arr;

    }
}
