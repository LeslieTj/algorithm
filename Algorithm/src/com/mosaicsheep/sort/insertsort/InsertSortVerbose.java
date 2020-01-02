package com.mosaicsheep.sort.insertsort;

import java.util.Arrays;

public class InsertSortVerbose {
    public static void main(String[] args) {
        // 对arr由小到大进行排序
        int[] arr = {101, 34, 119, 1};

        // 第一轮排序
        //  {101, 34, 119, 1} => {101}  {34, 119, 1}
        // 准备将无序表中的第一个元素(即34)插入到有序表中

        int insertValue = arr[1];// 保存待插入元素的值，即34
        int insertIndex = 1 - 1;// 保存待插入元素的插入位置(即arr[1]前一个元素的下标，34要从后往前插入有序表)

        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            // 因为是从后往前比对，insertIndex >= 0 保证不越界
            // insetValue < arr[insertIndex] 说明待插入的数34还没有找到插入位置

            arr[insertIndex + 1] = arr[insertIndex];// 到这一步，{101, 101, 119, 1}，待插入的值(insertValue)为34
            insertIndex--;// 到这一步，insertIndex = -1，退出循环
        }

        // 退出while循环时，说明插入的位置已经找到，insertIndex + 1就是要插入的位置
        arr[insertIndex+1] = insertValue;

        System.out.println("第一轮排序结果：" + Arrays.toString(arr));
        System.out.println("-------------------------------------------------");

        // 第二轮排序
        //  {34, 101} {119, 1}
        // 准备将无序表中的第一个元素(即34)插入到有序表中

        insertValue = arr[2];// 保存待插入元素的值，即119
        insertIndex = 2 - 1;// 保存待插入元素的插入位置(即arr[2]前一个元素的下标，34要从后往前插入有序表)

        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            // 因为是从后往前比对，insertIndex >= 0 保证不越界
            // insetValue < arr[insertIndex] 说明待插入的数119还没有找到插入位置

            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        // 退出while循环时，说明插入的位置已经找到，insertIndex + 1就是要插入的位置
        arr[insertIndex+1] = insertValue;

        System.out.println("第二轮排序结果：" + Arrays.toString(arr));
        System.out.println("-------------------------------------------------");

        // 第三轮排序
        //  {34, 101, 119} {1}
        // 准备将无序表中的第一个元素(即34)插入到有序表中

        insertValue = arr[3];// 保存待插入元素的值，即1
        insertIndex = 3 - 1;// 保存待插入元素的插入位置(即arr[3]前一个元素的下标，34要从后往前插入有序表)

        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            // 因为是从后往前比对，insertIndex >= 0 保证不越界
            // insetValue < arr[insertIndex] 说明待插入的数1还没有找到插入位置

            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        // 退出while循环时，说明插入的位置已经找到，insertIndex + 1就是要插入的位置
        arr[insertIndex+1] = insertValue;

        System.out.println("第三轮排序结果：" + Arrays.toString(arr));
    }
}
