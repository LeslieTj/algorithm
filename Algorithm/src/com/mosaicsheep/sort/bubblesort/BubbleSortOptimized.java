package com.mosaicsheep.sort.bubblesort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

// 冒泡排序优化：
// 如果在某轮排序中，元素的交换一次都没有发生，那么可以提前结束冒泡排序
public class BubbleSortOptimized {
    public static void main(String[] args) {
        // 将arr由小到大排序
        int[] arr = {3, 9, -1, 10, 20};

        int temp = 0;// 用作交换时的临时变量
        boolean flag = false;// 标识是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {

                // 由小到大排序，那么小的永远在前
                // 如果前面的数比后面的大，则进行交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "次排序结果：" + Arrays.toString(arr));
            if (!flag) { // 一次交换都没有发生
                break;
            } else {
                flag = false;// 将flag重新置为false
            }
        }


    }


}
