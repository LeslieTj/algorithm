package com.mosaicsheep.binarytree.basicbinarytree;

/*
 * 前序遍历：
 * 先输出当前节点（初始的时候为root节点）
 * 如果左子节点不为空，则递归继续前序遍历
 * 如果右子节点不为空，则递归继续前序遍历
 *
 * 中序遍历：
 * 如果左子节点不为空，则递归继续中序遍历
 * 先输出当前节点（root节点）
 * 如果右子节点不为空，则递归继续中序遍历
 *
 * 后序遍历：
 * 如果左子节点不为空，则递归继续后序遍历
 * 如果右子节点不为空，则递归继续后序遍历
 * 先输出当前节点（root节点）
 *
 * */
public class BinaryTreeTraversal {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode heroNode1 = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "吴用");
        HeroNode heroNode3 = new HeroNode(3, "卢俊义");
        HeroNode heroNode4 = new HeroNode(4, "林冲");

        binaryTree.setRoot(heroNode1);
        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);
        heroNode3.setRight(heroNode4);

        binaryTree.preOrder();

        System.out.println("-------------------------------");

        binaryTree.infixOrder();

        System.out.println("-------------------------------");

        binaryTree.postOrder();// 2 -> 4 -> 3 -> 1
    }
}
