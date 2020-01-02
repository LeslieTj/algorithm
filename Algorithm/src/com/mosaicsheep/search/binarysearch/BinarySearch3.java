package com.mosaicsheep.search.binarysearch;


import java.util.ArrayList;

public class BinarySearch3 {
    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89, 1000, 1000, 1234};
        System.out.println(binarySearchRecursive(array, 1000, 0, array.length - 1));
        System.out.println(binarySearchIterative(array, 1000));
        System.out.println(binarySearchFindAll(array, 1000, 0, array.length - 1));
    }

    public static int binarySearchRecursive(int[] array, int x, int left, int right){
        if(left > right){
            // The starting position comes after the final index,
            // so there are actually no elements in the specified
            // range. The value does not occur in this empty list.
            return -1;// base case 1
        }

        // When the array is large enough, this could cause an overflow
        // error if left + right is more than what int can store.
        // int mid = (left + right)/2;
        int mid = left + (right - left)/2;// This can prevent overflow.
        if(array[mid] == x){
            return mid;// base case 2
        }else if( x < array[mid]){
            return binarySearchRecursive(array, x, left, mid -1);
        }else{
            return binarySearchRecursive(array, x, mid + 1, right);
        }

    }

    public static int binarySearchIterative(int[] array, int x){

        int left = 0;
        int right = array.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(array[mid] == x){
                return mid;
            }else if(x < array[mid]){
                right = mid - 1;
            }else{
                left = mid + 1;
            }

        }
        return -1;
    }


    public static ArrayList<Integer> binarySearchFindAll(int[] array, int x, int left, int right){
        if(left > right){
            return new ArrayList<>();
        }

        int mid = left + (right - left)/2;
        if(array[mid] == x){
            ArrayList<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (true) {// scan to the left
                if (temp < 0 || array[temp] != x) {
                    break;
                }
                list.add(temp);
                temp--;
            }

            list.add(mid);

            temp = mid + 1;
            while (true) {// scan to the right
                if (temp > array.length || array[temp] != x) {
                    break;
                }
                list.add(temp++);
            }

            return list;

        }else if( x < array[mid]){
            return binarySearchFindAll(array, x, left, mid -1);
        }else{
            return binarySearchFindAll(array, x, mid + 1, right);
        }

    }


}
