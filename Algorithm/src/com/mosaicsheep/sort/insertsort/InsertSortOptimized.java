package com.mosaicsheep.sort.insertsort;
import java.util.Arrays;

public class InsertSortOptimized {
    public static void main(String[] args) {
        // 对arr由小到大进行排序
        int[] arr = {101, 34, 119, 1, -1, 35};

       for(int i = 1; i < arr.length; i++){
           int insertValue = arr[i];
           int insertIndex = i - 1;

           while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
               arr[insertIndex + 1] = arr[insertIndex];
               insertIndex--;
           }

           // 优化
           if(insertIndex + 1 != i){// insertIndex == i的话，说明待插入的元素根本就没挪窝儿
               arr[insertIndex + 1] = insertValue;
           }


           System.out.println("第" + i + "轮排序结果：" + Arrays.toString(arr));
       }

    }
}
