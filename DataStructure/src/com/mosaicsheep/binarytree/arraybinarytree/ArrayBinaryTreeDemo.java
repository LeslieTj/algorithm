package com.mosaicsheep.binarytree.arraybinarytree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};

        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        arrayBinaryTree.preOrder(0);// 根节点是第0个元素
        System.out.println("--------------------------------------");
        arrayBinaryTree.preOrder();

    }


}

class ArrayBinaryTree {
    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    /**
     * 顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("Array is empty!");
        }

        // 输出当前的元素
        System.out.println(array[index]);
        // 向左递归遍历
        if (index * 2 + 1 < array.length) {
            preOrder(2 * index + 1);
        }

        // 向右递归遍历
        if (index * 2 + 2 < array.length) {
            preOrder(2 * index + 2);
        }
    }

    // 重载上面的preOrder方法，不用每次都传0进去，显得更干净一点
    public void preOrder() {
        this.preOrder(0);
    }
}