package com.mosaicsheep.sort.utils;

public class SortUtils {
    private SortUtils() {
    }

    public static void bubbleSort(Integer[] arr) {

        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }

    }

    public static void insertSort(Integer[] arr) {

        for (int i = 1; i < arr.length; i++) {

            int insertValue = arr[i];
            int insertIndex = i - 1;

            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {

                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            arr[insertIndex + 1] = insertValue;

        }
    }

    public static void quickSort(Integer[] arr, int left, int right) {

        int leftIndex = left;
        int rightIndex = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;

        while (leftIndex < rightIndex) {

            while (arr[leftIndex] < pivot) {
                leftIndex++;
            }
            while (arr[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex >= rightIndex) {
                break;
            }

            temp = arr[leftIndex];
            arr[leftIndex] = arr[rightIndex];
            arr[rightIndex] = temp;

            if (arr[leftIndex] == pivot) {
                rightIndex--;
            }
            if (arr[rightIndex] == pivot) {
                leftIndex++;
            }

        }

        if (leftIndex == rightIndex) {
            leftIndex++;
            rightIndex--;
        }
        if (left < rightIndex) {
            quickSort(arr, left, rightIndex);
        }
        if (right > leftIndex) {
            quickSort(arr, leftIndex, right);
        }

    }

    public static void selectSort(Integer[] arr) {

        int minIndex;
        int min;

        for (int i = 0; i < arr.length - 1; i++) {

            minIndex = i;
            min = arr[i];

            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];

                }
            }

            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

        }

    }

    public static void shellSort(Integer[] arr){

        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {

            for (int i = gap; i < arr.length; i++) {

                int insertIndex = i;
                int insertValue = arr[insertIndex];

                if (arr[insertIndex] < arr[insertIndex - gap]) {

                    while (insertIndex - gap >= 0 && insertValue < arr[insertIndex - gap]) {// 如果insertIndex - gap >=0，说明还可以继续向前比对
                        arr[insertIndex] = arr[insertIndex - gap];
                        insertIndex -= gap;

                    }

                    arr[insertIndex] = insertValue;
                }

            }
        }

    }


}
