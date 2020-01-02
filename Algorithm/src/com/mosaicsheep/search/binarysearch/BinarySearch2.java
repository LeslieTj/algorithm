package com.mosaicsheep.search.binarysearch;

import java.util.ArrayList;
import java.util.List;

// 当数组中有多个相同的数值时，将所有的数值都查到，并返回索引
// 注意：有序数组一样的值都集中在一起了，比如下面数组中的1000都集中在一起了
public class BinarySearch2 {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 1000));
        System.out.println(binarySearch(arr, 0, arr.length - 1, 11));

    }

    public static List<Integer> binarySearch(int[] arr, int left, int right, int findValue) {

        if (left > right) {
            return new ArrayList<>();// 返回空的list
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (findValue > midValue) {
            return binarySearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return binarySearch(arr, left, mid - 1, findValue);
        } else {
            List<Integer> list = new ArrayList<>();

            // 向mid索引值的左边扫描，将所有满足1000的元素的下标都加入到list中
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findValue) {
                    break;
                }
                list.add(temp);
                temp--;
            }

            list.add(mid);// 扫描完左边的，再放中间的，然后再扫描右边的

            // 向mid索引值的右边扫描，将所有满足1000的元素的下标都加入到list中
            temp = mid + 1;
            while (true) {
                if (temp > arr.length || arr[temp] != findValue) {
                    break;
                }
                list.add(temp++);
            }

            return list;

        }


    }
}
