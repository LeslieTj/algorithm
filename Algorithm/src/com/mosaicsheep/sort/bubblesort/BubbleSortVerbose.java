package com.mosaicsheep.sort.bubblesort;

import java.util.Arrays;

public class BubbleSortVerbose {
    public static void main(String[] args) {
        // 将arr由小到大排序
        int[] arr = {3, 9, -1, 10, -2};
        int temp = 0;// 用作交换时的临时变量

        // 第一次排序，将最大的元素冒泡出来
        for (int i = 0; i < arr.length - 1; i++) {
            // 由小到大排序，那么小的永远在前
            // 如果前面的数比后面的大，则进行交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第一次排序结果:" + Arrays.toString(arr));

        System.out.println("----------------------------------");

        // 第二次排序，刨去最大的元素(即当前arr中最后一个元素)，将剩余元素中最大的元素冒泡出来
        for (int i = 0; i < arr.length - 2; i++) {
            // 由小到大排序，那么小的永远在前
            // 如果前面的数比后面的大，则进行交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第二次排序结果:" + Arrays.toString(arr));

        System.out.println("----------------------------------");

        // 第三次排序，继续刨去最大的元素(即当前arr中最后一个元素)，将剩余元素中最大的元素冒泡出来
        for (int i = 0; i < arr.length - 3; i++) {
            // 由小到大排序，那么小的永远在前
            // 如果前面的数比后面的大，则进行交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第三次排序结果:" + Arrays.toString(arr));

        System.out.println("----------------------------------");

        // 第四次排序，继续刨去最大的元素(即当前arr中最后一个元素)，将剩余元素中最大的元素冒泡出来
        for (int i = 0; i < arr.length - 4; i++) {
            // 由小到大排序，那么小的永远在前
            // 如果前面的数比后面的大，则进行交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第四次排序结果:" + Arrays.toString(arr));



    }
}
