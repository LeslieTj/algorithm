package com.mosaicsheep.sort.radixsort;

import java.util.Arrays;

public class RadixSortNormal {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        RadixSort(arr);
    }

    public static void RadixSort(int[] arr) {

        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];
        int maxDigit = getMaxDigit(arr);// 确定循环的轮次
        int index;

        for (int i = 0; i < maxDigit; i++) {

            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / (int) Math.pow(10, i) % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }

                bucketElementCounts[k] = 0;
            }

            System.out.println("第" + (i + 1) + "轮排序结果：" + Arrays.toString(arr));
        }

    }

    // 得到数组中元素的最大位数
    public static int getMaxDigit(int[] arr) {

        int max = getMaxElement(arr);// 得到数组中的最大元素
        int maxDigit = (max + "").length();// 得到最大元素的位数

        return maxDigit;

    }

    // 得到数组中的最大元素
    public static int getMaxElement(int[] arr) {

        int max = arr[0];// 先假设第一个元素最大
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

}
