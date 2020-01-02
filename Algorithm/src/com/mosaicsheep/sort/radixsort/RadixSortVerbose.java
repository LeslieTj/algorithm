package com.mosaicsheep.sort.radixsort;

import java.util.Arrays;

public class RadixSortVerbose {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        RadixSort(arr);
    }

    public static void RadixSort(int[] arr) {

        // 定义一个二维数组，表示10个桶，每个桶都是一个一维数组
        // 桶排序是用空间换时间的，桶可能放不满，但为了防止溢出，必须要arr.length
        int[][] bucket = new int[10][arr.length];

        // 记录0~9每个桶中实际存放了多少数据
        int[] bucketElementCounts = new int[10];

        // 第一轮排序
        // 将元素按照个位数存放进桶中
        for (int i = 0; i < arr.length; i++) {
            int digitOfElement = arr[i] % 10;//取出每个元素的个位
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }

        // 遍历每个桶，并将桶中的元素放入原数组
        int index = 0;
        for (int j = 0; j < bucketElementCounts.length; j++) {
            if (bucketElementCounts[j] != 0) {// 桶中有数据
                for (int k = 0; k < bucketElementCounts[j]; k++) {
                    arr[index++] = bucket[j][k];
                }
            }
            // 每一轮都要将桶"清空"
           bucketElementCounts[j] = 0;
        }

        System.out.println("第一轮排序结果：" + Arrays.toString(arr));
        System.out.println("------------------------------------");

        // 第二轮排序
        // 将元素按照十位数存放进桶中
        for (int i = 0; i < arr.length; i++) {
            int digitOfElement = arr[i] / 10 % 10;//取出每个元素的十位
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }

        // 遍历每个桶，并将桶中的元素放入原数组
        index = 0;
        for (int j = 0; j < bucketElementCounts.length; j++) {
            if (bucketElementCounts[j] != 0) {// 桶中有数据
                for (int k = 0; k < bucketElementCounts[j]; k++) {
                    arr[index++] = bucket[j][k];
                }
            }

            bucketElementCounts[j] = 0;
        }

        System.out.println("第二轮排序结果：" + Arrays.toString(arr));
        System.out.println("------------------------------------");

        // 第三轮排序
        // 将元素按照百位数存放进桶中
        for (int i = 0; i < arr.length; i++) {
            int digitOfElement = arr[i] / 100 % 10;//取出每个元素的百位
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }

        // 遍历每个桶，并将桶中的元素放入原数组
        index = 0;
        for (int j = 0; j < bucketElementCounts.length; j++) {
            if (bucketElementCounts[j] != 0) {// 桶中有数据
                for (int k = 0; k < bucketElementCounts[j]; k++) {
                    arr[index++] = bucket[j][k];
                }
            }

            bucketElementCounts[j] = 0;
        }

        System.out.println("第三轮排序结果：" + Arrays.toString(arr));

    }
}
