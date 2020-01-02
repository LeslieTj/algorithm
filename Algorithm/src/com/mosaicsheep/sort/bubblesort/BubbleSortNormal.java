package com.mosaicsheep.sort.bubblesort;

import java.util.Arrays;

public class BubbleSortNormal {
    public static void main(String[] args) {
        // 将arr由小到大排序
        int[] arr = {3, 9, -1, 10, -2};

        int temp = 0;// 用作交换时的临时变量
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {

                // 由小到大排序，那么小的永远在前
                // 如果前面的数比后面的大，则进行交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "次排序结果：" + Arrays.toString(arr));
        }

        // 冒泡排序的时间复杂度为O(n^2)，即两个for循环

    }
}
