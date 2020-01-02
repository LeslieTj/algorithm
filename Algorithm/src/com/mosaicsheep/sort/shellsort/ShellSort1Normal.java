package com.mosaicsheep.sort.shellsort;

import java.util.Arrays;

// 希尔排序(交换式)
public class ShellSort1Normal {
    public static void main(String[] args) {
        // 由小到大进行排序
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int temp = 0;// 用于交换的临时变量
        int count = 0;

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {// 最外层循环是用来缩小增量，进行分组的

            for (int i = gap; i < arr.length; i++) {

                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }

                }
            }
            System.out.println("第" + (++count) + "轮排序结果：" + Arrays.toString(arr));
        }


    }
}
