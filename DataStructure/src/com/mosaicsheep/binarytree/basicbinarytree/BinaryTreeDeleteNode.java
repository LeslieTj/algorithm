package com.mosaicsheep.binarytree.basicbinarytree;

/*
 * 删除原则：
 * 如果是叶子节点（没有子节点的节点）， 则直接删除该节点；
 * 如果删除的节点是非叶子节点，则删除该子树。
 *
 * 1. 如果当前节点的左子节点不为空，其正好就是我们要删除的节点，就令this.left = null；
 * 2. 否则如果右子节点不为空，且正好是要删除的节点，就令this.right = null;
 * 3. 如果前两步没有删除节点，就需要向左子树进行递归删除
 * 4. 如果第三步也没有删除节点，就向右子树递归删除
 * */
public class BinaryTreeDeleteNode {
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

        binaryTree.preOrder();

        System.out.println("--------------------");
        binaryTree.deleteNode(5);
        binaryTree.preOrder();


    }

}
