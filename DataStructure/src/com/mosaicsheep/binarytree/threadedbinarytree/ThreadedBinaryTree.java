package com.mosaicsheep.binarytree.threadedbinarytree;

// 创建线索化二叉树ThreadedBinaryTree，
// 在com.mosaicsheep.binarytree.basicbinarytree.BinaryTree的基础上，
// 增加了线索化功能。
public class ThreadedBinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 为了实现线索化，需要创建一个指向 当前节点的前驱节点 的指针
    // 在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    /**
     * 遍历线索化二叉树
     */
    public void threadedList(){
        //定义变量，临时存储当前遍历的节点
        HeroNode node = root;// 从root开始往下找

        while(node != null){
            // 循环找到leftType == 1的结点，第一个找到就是8结点
            // 后面随着遍历而变化，因为当leftType == 1时，说明该节点是按照线索化处理后的有效结点
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }
            // 打印当前这个结点
            System.out.println(node);
            // 如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1){
                // 获取当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的结点
            node = node.getRight();
        }
    }

    /**
     * 对二叉树进行中序线索化:
     * 1. 先线索化左子树
     * 2. 线索化当前节点
     * 3. 再线索化右子树
     *
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node) {

        // 如果node == null，不能线索化
        if (node == null) {
            return;
        }

        // 1. 先线索化左子树
        threadedNodes(node.getLeft());

        // 2. 线索化当前节点
        // 2.1 处理当前节点的前驱节点
        if (node.getLeft() == null) {
            // 让当前节点的左指针指向前驱节点
            node.setLeft(pre);

            // 修改当前节点的左指针的类型，指向前驱节点
            // 以8节点为例，8节点的.left为null，8节点的.leftType = 1
            // 即，8节点向左指向了前驱节点，这个前驱节点为空
            node.setLeftType(1);
        }

        // 2.2 处理当前节点的后继节点
        // 处理后继节点实际上是用"下一次"来处理的
        // 以节点8为例，当下一次node指向节点3的时候，这时候pre就指向了节点8
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);// 指向当前节点（在这个例子里就是节点3）
            pre.setRightType(1);
        }

        pre = node;// 每处理一个节点后，让当前节点是下一个节点的前驱节点


        // 3. 再线索化右子树
        threadedNodes(node.getRight());


    }

    // 重载上面的threadedNodes方法
    public void threadedNodes() {
        this.threadedNodes(root);
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
            } else {
                root.deleteNode(no);
            }

        } else {
            System.out.println("The tree is empty!");
        }
    }

}

// 创建HeroNode节点， 增加了leftType、rightType以及它们的getter、setter
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;// 默认null
    private HeroNode right;// 默认null

    // 如果leftType == 0, 则表示指向的是左子树；
    // 如果leftType == 1， 则表示指向的是前驱节点。
    // 如果rightType == 0, 则表示指向的是右子树；
    // 如果rightType == 1， 则表示指向的是前驱节点。
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

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

        if (this.right != null) {// 向右子树递归删除
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
