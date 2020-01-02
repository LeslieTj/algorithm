package com.mosaicsheep.sort.selectsort;

import java.util.Arrays;

public class SelectSortVerbose {

    public static void main(String[] args) {
        // 将arr由小到大排序
        int[] arr = {101, 34, 119, 1};

        int minIndex;// minIndex用来**暂时**存放最小值的索引
        int min;// min用来**暂时**存放最小值，即arr[minIndex] = min

        // 第一轮排序
        // 假定当前arr的第一个元素是最小的
        minIndex = 0;
        min = arr[0];
        // 进行循环，找到最小值与最小值的索引，暂时存放在arr[minIndex]中
        for (int i = 0; i < arr.length; i++) {
            if (min > arr[i]) {// 第一个元素不是最小的，重置最小值
                minIndex = i;
                min = arr[i];

            }
        }

        if (minIndex != 0) {
            // 将最小值放在第一个位置(arr[0])
            arr[minIndex] = arr[0];// 假定minIndex = 1，那么需要将arr[1]和arr[0]交换
            arr[0] = min;
        }


        System.out.println("第一轮排序结果：" + Arrays.toString(arr));

        System.out.println("---------------------------------------");

        // 第二轮排序
        // 假定当前arr的第二个元素是最小的
        minIndex = 1;
        min = arr[1];
        // 进行循环，找到最小值与最小值的索引，暂时存放在arr[minIndex]中
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {// 第一个元素不是最小的，重置最小值
                minIndex = i;
                min = arr[i];

            }
        }

        if (minIndex != 1) {
            // 将最小值放在第二个位置(arr[1])
            arr[minIndex] = arr[1];
            arr[1] = min;
        }

        System.out.println("第二轮排序结果：" + Arrays.toString(arr));

        System.out.println("---------------------------------------");

        // 第三轮排序
        // 假定当前arr的第三个元素是最小的
        minIndex = 2;
        min = arr[2];
        // 进行循环，找到最小值与最小值的索引，暂时存放在arr[minIndex]中
        for (int i = 2; i < arr.length; i++) {
            if (min > arr[i]) {// 第一个元素不是最小的，重置最小值
                minIndex = i;
                min = arr[i];

            }
        }

        if(minIndex != 2){
            // 将最小值放在第三个位置(arr[2])
            arr[minIndex] = arr[2];
            arr[2] = min;
        }

        System.out.println("第三轮排序结果：" + Arrays.toString(arr));

    }
}
