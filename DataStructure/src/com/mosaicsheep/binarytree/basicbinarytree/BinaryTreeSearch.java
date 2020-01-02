package com.mosaicsheep.binarytree.basicbinarytree;

/*
 * 前序查找：
 * 先判断当前节点（初始的时候为root节点）是否为要查找的节点，如果是要找的节点，则返回当前节点
 * 如果不是要找的节点，则判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
 * 如果左递归没有找到节点，继续判断当前节点的右子节点是否为空，如果不为空，则继续向右递归查找
 *
 * 中序查找：
 * 如果当前节点的左子节点不为空，则递归继续前序查找，如果找到则返回
 * 如果没有找到，则和当前节点（root节点）继续进行比较，如果找到则返回
 * 如果没有找到，则继续右递归查找，如果找到就返回，如果没找到就返回null
 *
 * 后序查找：
 * 先判断当前节点的左子节点是否为空，不为空则向左递归查找
 * 判断当前节点的右子节点是否为空，如果不为空，则右递归查找
 * 最后判断当前节点（root节点），如果找到则返回，找不到返回null
 *
 * */
public class BinaryTreeSearch {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode heroNode1 = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "吴用");
        HeroNode heroNode3 = new HeroNode(3, "卢俊义");
        HeroNode heroNode4 = new HeroNode(4, "林冲");
        HeroNode heroNode5 = new HeroNode(5, "关羽");

        binaryTree.setRoot(heroNode1);
        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);
        heroNode3.setRight(heroNode4);
        heroNode3.setLeft(heroNode5);

        HeroNode resultNode = binaryTree.preOrderSearch(4);
        if(resultNode != null){
            System.out.println("Founded Hero:" + resultNode);
        }else {
            System.out.println("Not Found!");
        }


        System.out.println("-------------------------------");

        resultNode = binaryTree.infixOrderSearch(15);
        if(resultNode != null){
            System.out.println("Founded Hero:" + resultNode);
        }else {
            System.out.println("Not Found!");
        }

    }
}

