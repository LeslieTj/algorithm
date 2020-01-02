package com.mosaicsheep.binarytree.binarysorttree;


/**
 * @author LeslieTang
 * @version 2019/12/19
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环地添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.infixOrder();// 中序遍历：1，2, 3，5，7，9，10，12

        System.out.println("------------------------------");

        // 测试删除叶子节点
        //binarySortTree.deleteNode(2);

        // 测试删除有两棵子树的节点
        binarySortTree.deleteNode(10);
        binarySortTree.infixOrder();

        System.out.println("----------------------------------------");

        int[] arr2 = {10, 1};
        binarySortTree = new BinarySortTree();
        // 循环地添加节点到二叉排序树
        for (int i = 0; i < arr2.length; i++) {
            binarySortTree.add(new Node(arr2[i]));
        }
        binarySortTree.infixOrder();// 中序遍历：1，10

        // 现在我要删除10节点
        binarySortTree.deleteNode(10);// 会报空指针异常，因为10节点已经是root节点了，它的parent是空的
        // 因此，当要删除的节点有只有一棵子树时，要加一个逻辑判断：if (parent != null)... else...
        binarySortTree.infixOrder();
    }


}

// 创建二叉排序树
class BinarySortTree {
    private Node root;

    // 删除节点
    public void deleteNode(int value) {
        if (root == null) {
            return;
        } else {
            Node target = search(value);// 查找待删除的节点
            if (target == null) {// 没有找到待删除节点
                return;
            }

            // 这时候我们可以找到待删除节点了，如果我们发现整个二叉树只有一个节点，那就直接删除该节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            Node parent = searchParent(value);// 查找待删除节点的父节点
            if (target.left == null && target.right == null) {// 待删除的节点是叶子节点
                // 判断target是parent的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (target.right != null && target.left != null) {// 待删除的节点有两棵子树
                int minValue = deleteRightTreeMin(target.right);
                target.value = minValue;
            } else {// 待删除节点只有一棵子树


                // 确定待删除节点有左子节点还是右子节点
                if (target.left != null) {// 确定待删除节点有左子节点
                    if (parent != null) {
                        if (parent.left.value == value) {// 待删除节点是父节点的左子节点
                            parent.left = target.left;
                        } else {
                            parent.right = target.left;
                        }
                    } else {
                        root = target.left;
                    }

                } else {// 确定待删除节点有右子节点

                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = target.right;
                        } else {
                            parent.right = target.right;
                        }
                    } else {
                        root = target.right;
                    }

                }


            }

        }
    }

    // 删除右子树中值最小的节点，并返回这个节点的值
    // node是右子树的根节点
    public int deleteRightTreeMin(Node node) {
        Node temp = node;
        // 循环查找左子节点，就会找到最小值
        while (temp.left != null) {
            temp = temp.left;
        }
        deleteNode(temp.value);// 找到最小值后把它删掉
        return temp.value;
    }

    // 查找要删除待节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找待删除节点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    // 添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("空树不能遍历！");
        }
    }
}

// 创建Node节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }


    /**
     * 查找节点（用于查找待删除节点）
     *
     * @param value 待查找节点的值
     * @return 待查找节点，没找到的话返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value <= this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);// 向左子树递归查找
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);// 向右子树递归查找
        }
    }

    /**
     * 查找待删除节点的父节点
     *
     * @param value 待删除节点的值
     * @return 待删除节点的父节点，没有的话返回null
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value <= this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value > this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }


    /**
     * 采用递归的形式，按照二叉排序树的要求添加节点
     *
     * @param node 待添加的节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value <= this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);// 递归地向左子树添加
            }
        } else {// i.e., node.value > this.value
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);// 递归地向右子树添加
            }
        }
    }

    /**
     * 中序遍历二叉树
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}