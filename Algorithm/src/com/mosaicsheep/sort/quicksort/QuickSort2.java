package com.mosaicsheep.sort.quicksort;

import java.util.Arrays;

// step2 在step1的基础上进行递归
public class QuickSort2 {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};

        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        // 不进行递归的输出结果：
        // [-9, -567, 0, 23, 78, 70]
        // 只进行向左递归的输出结果：
        // [-567, -9, 0, 23, 78, 70]
        // 只进行向右递归的输出结果：
        // [-9, -567, 0, 23, 70, 78]

        // 最终输出结果：
        // [-567, -9, 0, 23, 70, 78]

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

        // 出上面的循环时：
        // left                  right
        //  ⬇                     ⬇
        // {-9, -567 , 0, 23, 78, 70}
        //            ⬆⬆
        //    leftIndex/rightIndex



        // 如果leftIndex == rightIndex，必须leftIndex++，rightIndex--，否则会出现栈溢出
        if (leftIndex == rightIndex) {
            leftIndex++;
            rightIndex--;
        }

        // 此时：
        // left                  right
        //  ⬇                     ⬇
        // {-9, -567 , 0, 23, 78, 70}
        //       ⬆        ⬆
        //    rightIndex leftIndex

        //向左递归
        if (left < rightIndex) {
            quickSort(arr, left, rightIndex);
        }
        // 向右递归
        if (right > leftIndex) {
            quickSort(arr, leftIndex, right);
        }


    }
}
