package com.mosaicsheep.sort.selectsort;

import java.util.Arrays;
// 选择排序的时间复杂度为O(n^2)，即两个for循环。
public class SelectSortNormal {

    public static void main(String[] args) {
        // 将arr由小到大排序
        int[] arr = {101, 34, 119, 1};

        int minIndex;// minIndex用来**暂时**存放最小值的索引
        int min;// min用来**暂时**存放最小值，假定当前arr的第一个元素是最小的

        for (int i = 0; i < arr.length - 1; i++) {
            // 第i轮排序
            // 假定当前arr的第i个元素是最小的
            minIndex = i;
            min = arr[i];
            // 进行循环，找到最小值与最小值的索引，暂时存放在arr[minIndex]中
            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {// 第一个元素不是最小的，重置最小值
                    minIndex = j;
                    min = arr[j];

                }
            }

            if (minIndex != i) {
                // 将最小值放在第一个位置(arr[0])
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

            System.out.println("第" + (i + 1) + "轮排序结果：" + Arrays.toString(arr));
        }


    }
}