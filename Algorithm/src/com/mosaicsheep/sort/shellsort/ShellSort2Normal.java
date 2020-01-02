package com.mosaicsheep.sort.shellsort;

import java.util.Arrays;

// 希尔排序(移动法)
// 即，分组后，对每组数据进行插入排序
public class ShellSort2Normal {
    public static void main(String[] args) {
        // 由小到大进行排序
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {// 按增量(gap)分组，并逐步缩小增量

            // 从第gap个元素开始，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {

                // 下面是插入排序的逻辑：

                int insertIndex = i;// 保存待插入元素的索引
                int insertValue = arr[insertIndex];// 保存待插入元素的值

                // 开始给待插入元素找位置：
                if (arr[insertIndex] < arr[insertIndex - gap]) {// 待插入元素也是向前去比对，但是不是和前一个比，还要有步长

                    while (insertIndex - gap >= 0 && insertValue < arr[insertIndex - gap]) {// 如果insertIndex - gap >=0，说明还可以继续向前比对
                        // 移动待插入元素
                        arr[insertIndex] = arr[insertIndex - gap];
                        insertIndex -= gap;

                    }

                    // 退出while循环后，这时候已经找到待插入元素的位置
                    arr[insertIndex] = insertValue;
                }

            }
            System.out.println("第" + (++count)+"轮排序结果：" + Arrays.toString(arr));
        }


    }
}
