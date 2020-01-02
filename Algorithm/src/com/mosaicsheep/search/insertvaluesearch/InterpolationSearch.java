package com.mosaicsheep.search.insertvaluesearch;

public class InterpolationSearch {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i + 1;
        }

        System.out.println(interpolationSearch(array, 89, 0, array.length - 1));


    }

    public static int interpolationSearch(int[] array, int x, int left, int right) {
        int count = 0;
        System.out.println("第" + (++count) + "次查找");

        if (left > right || x < array[0] || x > array[array.length - 1]) {// 如果没有后两个条件，可能会出现mid越界
            return -1;
        }

        int mid = left + (right - left) * (x - array[left]) / (array[right] - array[left]);

        if (array[mid] == x) {
            return mid;
        } else if (x < array[mid]) {
            return interpolationSearch(array, x, left, mid - 1);
        } else {
            return interpolationSearch(array, x, mid + 1, right);
        }

    }
}
