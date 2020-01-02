package com.mosaicsheep.sort.quicksort;

import java.util.Arrays;
// step1 完成以0为基准的左右交换，还没有进行递归
public class QuickSort1 {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};

        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));// [-9, -567, 0, 23, 78, 70]


    }

    public static void quickSort(int[] arr, int left, int right) {
        // 以{-9, 78, 0, 23, -567, 70}为例
        int leftIndex = left;// 即从最左边的arr[0] = -9开始向右找
        int rightIndex = right;// 即从最右边的arr[5] = 70开始向左找
        int pivot = arr[(left + right) / 2];// 中轴，即基准点，在此例中为arr[2] = 0
        int temp = 0;// 用于交换的临时变量

        // 比pivot小的值放到左边，比pivot大的值放到右边
        // leftIndex ->   直到两者碰头   <- rightIndex
        while(leftIndex < rightIndex) {

            // 从pivot的左边开始一直向后找，直到找到大于等于pivot的值，才退出循环
            while(arr[leftIndex] < pivot){
                leftIndex++;
            }// 第一次退出循环时，找到了arr[1] = 78 > 0

            // 从pivot的右边开始一直向前找，直到找到小于等于pivot的值，才退出循环
            while(arr[rightIndex] > pivot){
                rightIndex--;
            }// 第一次退出循环时，找到了arr[4] = -567 < 0

            // 退出上面的两个循环后，如果leftIndex >= rightIndex的话
            // 就说明pivot左边的值全都小于等于pivot，右边全都大于等于pivot
            if(leftIndex >= rightIndex) {
                break;
            }

            // 交换，即，将arr[1]与arr[4]交换
            temp = arr[leftIndex];
            arr[leftIndex] = arr[rightIndex];
            arr[rightIndex] = temp;

            // 交换完后，针对等于pivot的情况，再进行判断
            if(arr[leftIndex] == pivot){
                rightIndex--;
            }
            if(arr[rightIndex] == pivot){
                leftIndex++;
            }

            // 第一次循环结束，此时arr为：
            // {-9, -567 , 0, 23, 78, 70}
            //       ⬆            ⬆
            //     leftIndex   rightIndex

            // 进入下一次循环，会出现这这种情况：
            // {-9, -567 , 0, 23, 78, 70}
            //            ⬆⬆
            //    leftIndex/rightIndex

            // if(leftIndex >= rightIndex) {
            //      break;
            // }
            // 因此直接break

        }

    }
}
