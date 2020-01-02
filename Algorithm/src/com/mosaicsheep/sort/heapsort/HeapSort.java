package com.mosaicsheep.sort.heapsort;

import java.util.Arrays;

/**
 * @author LeslieTang
 * @version 2019/12/15
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    /**
     * 对数组进行升序排序
     *
     * @param arr 待排序数组
     */
    public static void heapSort(int[] arr) {
//        // 分步完成第一次大顶堆的调整：
//        adjustHeap(arr, 1, arr.length);
//        System.out.println("第一次:" + Arrays.toString(arr));// 4 9 8 5 6
//        adjustHeap(arr, 0, arr.length);
//        System.out.println("第二次:" + Arrays.toString(arr));// 9 6 8 5 4

        // 1. 一次性完成第一次大顶堆的调整：
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        // System.out.println("调整后的大顶堆数组：" + Arrays.toString(arr));

        // 2. 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端
        // 3. 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤
        // 直到整个序列有序
        int temp;
        for (int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);// 除了第一次调整外，其他情况下还是从顶上开始往下找的，所以i=0
        }
        System.out.println("排序后的数组为：" + Arrays.toString(arr));
    }

    /**
     * 将数组调整成大顶堆数组，比如:
     * arr = {4,6,8,5,9}, i = 1, length = 5  => {4,9,8,5,6}
     * 如果我们再次调用adjustHeap，arr = {4,9,8,5,6}, i = 0, length = 5 => {9,6,8,5,4}
     *
     * @param arr    待调整数组
     * @param i      非叶子节点在数组中的索引
     * @param length 对多少个元素进行调整（每一轮重构时待调整的元素都会减少）
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];// 取出非叶子节点的值，这个非叶子节点是待调整的，因此保存在临时变量temp中

        // 不断地调整左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (arr[k] < arr[k + 1] && k + 1 < length) {// 左子节点小于右子节点
                k++;// k指向右子节点
            }

            if (arr[k] > temp) {// 子节点大于父节点
                arr[i] = arr[k];// 把较大的值赋给当前节点
                i = k;// 让i指向k，继续循环比较
            } else {
                break;// 为什么我们敢break？因为我们是从下往上、从左往右进行调整的，也就是说，我们传入的i已经是最底层的待调整节点（非叶子节点）了
            }

        }
        // 当for循环结束后，我们已经将i为父节点的树的最大值，放在了最顶部
        arr[i] = temp;// 将temp值放在调整后的位置

    }
}
