package com.mosaicsheep.binarytree.threadedbinarytree;

/**
 * @author LeslieTang
 * @version 2019/10/28
 */
public class ThreadedBinaryTreeTest {

    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "Jay");
        HeroNode node2 = new HeroNode(3, "JJ");
        HeroNode node3 = new HeroNode(6, "David");
        HeroNode node4 = new HeroNode(8, "Faye");
        HeroNode node5 = new HeroNode(10, "Sally");
        HeroNode node6 = new HeroNode(14, "Leslie");

        root.setLeft(node2);
        root.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);

        // 线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        // 以节点10做测试
        HeroNode leftNode = node5.getLeft();// 拿到前驱节点，即节点3
        System.out.println("10号节点的前驱节点是" + leftNode);
        System.out.println("10号节点的后继节点是" + node5.getRight());

        System.out.println("------------------------------------");
        // 线索化遍历
        System.out.println("线索化遍历结果：");
        threadedBinaryTree.threadedList();
    }


}

