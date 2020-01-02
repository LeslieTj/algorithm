package com.mosaicsheep.binarytree.basicbinarytree;

// 创建二叉树
public class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("The binary tree is null.");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("The binary tree is null.");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("The binary tree is null.");
        }
    }

    // 前序查找
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    // 中序查找
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    // 后序查找
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    // 删除节点
    public void deleteNode(int no) {
        if (root != null) {

            if (root.getNo() == no) {
                root = null;
            }else {
                root.deleteNode(no);
            }

        }else {
            System.out.println("The tree is empty!");
        }
    }

}

// 先创建节点，仍以水浒英雄为例
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;// 默认null
    private HeroNode right;// 默认null

    // 前序遍历
    public void preOrder() {
        System.out.println(this);// 先输出父节点
        // 向左子树递归
        if (this.left != null) {
            this.left.preOrder();
        }
        // 向右子树递归
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 向左子树递归
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);// 输出父节点

        // 向右子树递归
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        // 向左子树递归
        if (this.left != null) {
            this.left.postOrder();
        }
        // 向右子树递归
        if (this.right != null) {
            this.right.postOrder();
        }

        System.out.println(this);// 输出父节点
    }


    // 前序查找（按编号）
    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {// 比较当前节点
            return this;
        }

        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.preOrderSearch(no);
        }
        if (resultNode != null) {//说明左递归找到了
            return resultNode;
        }

        if (this.right != null) {
            resultNode = this.right.preOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        } else {
            return null;
        }

    }

    // 中序查找（按编号）
    public HeroNode infixOrderSearch(int no) {

        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.infixOrderSearch(no);
        }
        if (resultNode != null) {//说明左递归找到了
            return resultNode;
        }

        if (this.no == no) {// 比较当前节点
            return this;
        }

        if (this.right != null) {
            resultNode = this.right.infixOrderSearch(no);
        }
        return resultNode;// 不用再判断了，直接返回就可以了

    }

    // 后序查找（按编号）
    public HeroNode postOrderSearch(int no) {

        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.infixOrderSearch(no);
        }
        if (resultNode != null) {//说明左递归找到了
            return resultNode;
        }

        if (this.right != null) {
            resultNode = this.right.infixOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }

        if (this.no == no) {// 比较当前节点
            return this;
        } else {
            return null;
        }
    }

    // 删除节点
    public void deleteNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        if (this.left != null) {// 向左子树递归删除
            this.left.deleteNode(no);
        }

        if(this.right != null) {// 向右子树递归删除
            this.right.deleteNode(no);
        }
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

