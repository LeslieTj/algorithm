package com.mosaicsheep.sort.test;

import com.mosaicsheep.sort.utils.SortUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

public class SortTest {
    public static void main(String[] args) {
        Integer[] arr = new Integer[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }

        test(arr, "冒泡", SortUtils::bubbleSort);
        System.out.println("------------------------------");

        test(arr, "插入", SortUtils::insertSort);
        System.out.println("------------------------------");

        test(arr, "希尔", SortUtils::shellSort);
        System.out.println("------------------------------");

        test(arr, "选择", SortUtils::selectSort);



    }

    public static void test(Integer[] arr, String algorithmName, Consumer<Integer[]> sort){

        Date d1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1str = simpleDateFormat.format(d1);

        System.out.println("排序前的时间：" + d1str);
        System.out.println("开始" + algorithmName + "排序");
        sort.accept(arr);

        Date d2 = new Date();
        String d2str = simpleDateFormat.format(d2);
        System.out.println("排序后的时间：" + d2str);

    }


}
