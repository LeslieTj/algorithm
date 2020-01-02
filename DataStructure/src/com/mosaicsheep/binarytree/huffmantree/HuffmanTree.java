package com.mosaicsheep.binarytree.huffmantree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author LeslieTang
 * @version 2019/12/16
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树不能遍历！");
        }
    }

    public static Node createHuffmanTree(int[] arr) {
        // 为了操作方便，先遍历arr，将arr的每个元素构建成一个Node
        // 然后将Node放到ArrayList中去
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {// 只要nodes里面还有大于1个的元素，就要继续进行，直到最后只剩下一个根结点
            // 从小到大排序
            Collections.sort(nodes);

            // 取出根结点权值最小的两颗二叉树,组成一颗新的二叉树,
            // 这棵新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            // 从ArrayList中删除left/right，添加parent
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);

        }

        return nodes.get(0);// 返回最后的一个根结点


    }

}

// 创建结点类
class Node implements Comparable<Node> {
    int value;// 结点的权值
    Node left;// 指向左子节点
    Node right;// 指向右子节点

    public Node(int value) {
        this.value = value;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node other) {
        return this.value - other.value;// 从小到大进行排序
    }
}
