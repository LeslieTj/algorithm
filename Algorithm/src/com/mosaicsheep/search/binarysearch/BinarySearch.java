package com.mosaicsheep.search.binarysearch;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};// a sorted array
        System.out.println(binarySearch(arr, 0, arr.length - 1, 10));

    }

    public static int binarySearch(int[] arr, int left, int right, int findValue) { // findValue: the value to be found

        if (left > right){
            return -1;// base case 2：寻找一个不存在的元素，容易出现死循环，因此要加上这一个退出条件
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (findValue > midValue){// recursion to the right
           return binarySearch(arr, mid + 1, right, findValue);
        }else if(findValue < midValue){// recursion to the left
            return binarySearch(arr, left, mid - 1, findValue);
        }else {// findValue = midValue
            return mid;// base case 1: 找到了就结束递归
        }



    }
}
