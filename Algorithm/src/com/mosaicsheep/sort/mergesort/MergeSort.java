package com.mosaicsheep.sort.mergesort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};

        // 归并排序需要一个额外的空间temp
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);

        System.out.println(Arrays.toString(arr));


    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 向左递归分解
            mergeSort(arr, left, mid, temp);
            // 向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            // 分解到底，开始合并、回溯
            merge(arr, left, mid, right, temp);

        }
    }


    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left;// 初始化i
        int j = mid + 1;// 初始化j
        int t = 0;// 指向temp数组的当前索引

        // 先把左右两边的数据（这时候已经是有序的了）按照规则填充到temp中
        // 直到左右两边的有序数列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                // temp[t] = arr[i];
                // t++;
                // i++;
                temp[t++] = arr[i++];

            } else {
                temp[t++] = arr[j++];
            }
        }

        // 把有剩余数据一边的数据全部填充到temp中去
        while (i <= mid) {// 左边的有序序列还有剩余元素
            temp[t++] = arr[i++];
        }
        while (j <= right) {// 右边的有序序列还有剩余元素
            temp[t++] = arr[j++];
        }

        // 将temp数据重新拷贝到arr里
        // 并不是每次都拷贝8个，每次合并时拷贝的temp数组长度不一样
        t = 0;
        int tempLeft = left;
        // 第一次合并时，tempLeft = 0，right = 1
        // 第二次合并时，tempLeft = 2，right = 3
        // 第三次合并时，tempLeft = 0，right = 3
        // 最后一次合并时，tempLeft = 0，right = 7
        System.out.println("tempLeft = " + tempLeft + " right = " + right);
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }


    }
}
