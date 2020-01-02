package com.mosaicsheep.search.sequencesearch;

public class SequenceSearch {
    public static void main(String[] args) {
        int[] arr = {1, 6, 11, -2, 33, 90};

        int index = sequenceSort(arr, 33);
        if (index == -1) {
            System.out.println("Not Found!");
        } else {
            System.out.println("The founded index is " + index);
        }

    }

    public static int sequenceSort(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;// 找到一个满足条件的就返回
            }
        }
        return -1;
    }
}
