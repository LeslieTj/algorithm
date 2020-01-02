package com.mosaicsheep.sort.insertsort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSortTest {


    public static void main(String[] args) {
        // 测试插入排序法的速度O(n^2)
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }

        Date d1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1str = simpleDateFormat.format(d1);
        System.out.println("排序前的时间：" + d1str);

        insertSort(arr);

        Date d2 = new Date();
        String d2str = simpleDateFormat.format(d2);
        System.out.println("排序后的时间：" + d2str);
    }

    public static int[] insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {

            int insertValue = arr[i];
            int insertIndex = i - 1;

            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {

                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            arr[insertIndex + 1] = insertValue;

        }
        return arr;
    }
}
