package com.mosaicsheep.search.fibonaccisearch;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;//因为需要用到斐波那契数列，在初始化时，先给数列一个20的容量

    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibSearchIterative(array, 8));
    }

    public static int fibSearchIterative(int[] array, int x) {// x是需要查找的值
        int low = 0;
        int high = array.length - 1;
        int mid = 0;

        int k = 0;// F(k) - 1 中的k
        int[] fib = getFibIterative();

        while (high > fib[k] - 1) {// 拿到k，使得array的长度正好等于 F(k), 即索引值high正好等于F(k) - 1
            k++;
        }

        // fib[k] 的值可能大于array的长度，因此将array复制到一个长度为fib[k]的数组
        // 不足的部分会用0进行填充
        // {1, 8, 10, 89, 1000, 1234} => {1, 8, 10, 89, 1000, 1234, 0, 0, 0, ...}
        int[] temp = Arrays.copyOf(array, fib[k]);

        // {1, 8, 10, 89, 1000, 1234, 0, 0, 0, ...} => {1, 8, 10, 89, 1000, 1234, 1234, 1234, 1234, ...}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = array[high];
        }

        while (low <= high) {// 只要low小于high，就能一直查找
            mid = low + fib[k - 1] - 1;
            if (x < temp[mid]) {// 向左边查找
                high = mid - 1;
                // 全部元素 = 前面的元素 + 后面的元素
                // fib[k] = f[k-1] + f[k-2]
                // 因为前面有k-1个元素，所以可以继续拆分、查找
                k--;
            } else if (x > temp[mid]) {// 向右边查找
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                }else{
                    return high;// {1, 8, 10, 89, 1000, 1234, 1234, 1234, 1234, ...} 后面的值都是high，mid大于high的话就返回high
                }
            }
        }

        return -1;

    }

    public static int[] getFibIterative() {
        int[] fib = new int[maxSize];

        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        return fib;
    }

}
