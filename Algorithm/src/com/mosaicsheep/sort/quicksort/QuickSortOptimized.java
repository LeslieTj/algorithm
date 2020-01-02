package com.mosaicsheep.sort.quicksort;


import java.util.Arrays;
// average: O(nlog_n)
// worst case: O(n^2) 即，当pivot永远都选在第一个元素时
public class QuickSortOptimized {
    public static void main(String[] args) {
        int[] arr = {9, 2, 6, 4, 3, 5, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));


        //         pivot
        //           ⬇
        // {9, 2, 6, 4, 3, 5, 1}
        //  ⬆                ⬆
        // left              right

        //         pivot
        //           ⬇
        // {1, 2, 6, 4, 3, 5, 9}
        //  ⬆                ⬆
        // left              right

        //         pivot
        //           ⬇
        // {1, 2, 6, 4, 3, 5, 9}
        //        ⬆    ⬆
        //       left  right

        //         pivot
        //           ⬇
        // {1, 2, 3, 4, 6, 5, 9}
        //        ⬆    ⬆
        //       left  right

        //         pivot
        //           ⬇
        // {1, 2, 3, 4, 6, 5, 9}
        //          ⬆⬆
        //       left right
        // 在这一步时，虽然也进行了交换，但是由于arr[left] = arr[right]
        // 所以相当于没有交换

        //         pivot
        //           ⬇
        // {1, 2, 3, 4, 6, 5, 9}
        //        ⬆    ⬆
        //       right left
        // 此时 index = 4
        // 以上为partition()的作用域
        // 在quickSort()的作用域里，left仍然为0，right仍然为(arr.length - 1)


    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = arr[(left + right) / 2];
        int index = partition(arr, left, right, pivot);

        quickSort(arr, left, index - 1);
        quickSort(arr, index, right);

    }

    public static int partition(int[] arr, int left, int right, int pivot) {
        while (left <= right) {
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }

        return left;

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

