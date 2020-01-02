package com.mosaicsheep.linkedlist;

import java.util.Stack;

//Step 3.进行测试
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode h1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode h2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode h3 = new HeroNode(3, "林冲", "豹子头");

        //添加节点到链表（单纯地从尾部添加)
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(h1);
        singleLinkedList.add(h2);
        singleLinkedList.add(h3);
        singleLinkedList.list();

        System.out.println("---------------------------------------------");

        //按照编号添加节点到链表
        singleLinkedList.addByOrder(h1);

        singleLinkedList.add(new HeroNode(4, "吴用", "智多星"));
        singleLinkedList.list();

        System.out.println("---------------------------------------------");

        //修改节点信息
        HeroNode newh1 = new HeroNode(1, "晁盖", "托塔天王");
        singleLinkedList.update(newh1);
        singleLinkedList.list();

        System.out.println("---------------------------------------------");

        //删除节点
        singleLinkedList.delete(4);
        singleLinkedList.delete(3);
        singleLinkedList.delete(2);
        singleLinkedList.delete(1);
        singleLinkedList.list();//输出结果：链表为空

        System.out.println("---------------------------------------------");

        singleLinkedList.add(h1);
        singleLinkedList.add(h2);
        singleLinkedList.add(h3);
        singleLinkedList.list();

        //得到单链表节点个数(不包含头节点)
        System.out.println("有效节点个数 = " + singleLinkedList.getLength(singleLinkedList.getHead()));

        System.out.println("---------------------------------------------");

        //得到倒数第3个节点
        HeroNode result = singleLinkedList.findNode(3, singleLinkedList.getHead());
        System.out.println(result);

        System.out.println("---------------------------------------------");

        //单链表的反转(改变了链表本身的结构)
        SingleLinkedList.reverseList(singleLinkedList.getHead());
        System.out.println("反转后的链表为：");
        singleLinkedList.list();

        System.out.println("---------------------------------------------");

        //逆序打印单链表(使用栈结构)
        singleLinkedList.reversePrint(singleLinkedList.getHead());

    }
}


//Step 2. 定义SingleLinkedList管理英雄
class SingleLinkedList{
    //初始化头节点，头节点数据域为空
    private HeroNode head = new HeroNode(0, "","");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //方式1：单纯地从尾部进行添加
    public void add(HeroNode heroNode){
        //头节点head是不能动的，因此将它赋给一个临时变量temp
        //从头节点开始向后遍历，直到找到链表最后一个节点(即next指向null的节点)
        HeroNode temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //当退出while循环时，temp就成了链表的最后一个节点
        //这时，temp.next就是即将添加的节点
        temp.next = heroNode;

    }

    //方式2：根据排名将英雄插入到指定位置
    public void addByOrder(HeroNode heroNode){
        //头节点head是不能动的，因此将它赋给一个临时变量temp
        //从头节点开始向后遍历，找到要添加位置的前一个节点
        HeroNode temp = head;
        boolean flag = false;//标识添加的编号是否存在，默认为false
        while (true){
            //如果temp.next为null，说明链表已经到最后，无论找没找到都要break
            //在这种情况下，应该在链表最后添加英雄
            if(temp.next == null){
                break;
            }

            if(temp.next.no > heroNode.no){
                break;//这种情况下，heroNode应该在temp和temp.next之间，即，应该在temp的后面插入英雄
            }else if(temp.next.no == heroNode.no){
                flag = true;
                break;
            }

            temp = temp.next;
        }

        //判断flag的值
        //如果编号已经存在，则不进行添加，否则就进行添加
        if(flag){
            System.out.println("准备插入英雄的编号已经存在~");
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }


    //遍历输出整个链表
    public void list(){
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }
        HeroNode temp = head.next;
        while(true){
            //判断链表是否已经到最后
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }

    //修改节点的信息
    //根据编号no来修改，因为编号是不能改的
    public void update(HeroNode newheroNode){
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;//标识是否找到该节点
        while(true){
            if(temp == null){
                break;//链表已经遍历完了都没有找到
            }

            if(temp.no == newheroNode.no){
                flag = true;//找到了
                break;
            }

            temp = temp.next;
        }

        //根据flag判断是否找到要修改的节点
        if(flag){
            temp.name = newheroNode.name;
            temp.nickName = newheroNode.nickName;
        }else{
            System.out.println("没有找到编号为" + newheroNode.no + "的英雄。");
        }

    }

    //从单向链表中删除节点
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;//标识是否找到待删除的节点
        while(true){
            if(temp.next == null){
                break;//遍历到链表最后也没找到待删除的节点
            }

            if(temp.next.no == no){
                flag = true;
                break;//这时temp是待删除节点的前一个节点
            }

            temp = temp.next;
        }

        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.println("没有找到编号为" + no + "的节点，未进行删除操作~");
        }


    }

    //获取单链表的节点的个数(不包含头节点)
    //需要把单链表的头节点传进去，从头节点开始向后数
    public static int getLength(HeroNode head){
        if(head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode current = head.next;
        while(current != null ){
            length++;
            current = current.next;
        }

        return length;

    }

    //查找单链表的倒数第k个节点
    //index指的是倒数第index个节点
    //先从头到尾遍历一遍，得到链表的length(当然也可以直接调用getLength方法）
    //然后再从头节点遍历，找到第(length-index)个节点
    public HeroNode findNode(int index, HeroNode head){

        if(head.next == null){
            System.out.println("链表为空");
            return null;//链表是空的
        }
        //第一次遍历得到链表的length，这里直接调用了getLength()方法
        int length = getLength(head);

        //第二次遍历，找到第(length-index)个节点
        if(index <= 0 || index > length){
            return null;
        }

        HeroNode current = head.next;
        for(int i = 0; i < length - index; i++){
            current = current.next;

        }
        return current;

    }

    //将单链表反转
    public static void reverseList(HeroNode head){
        if (head.next == null || head.next.next == null){
            return;//链表为空，或者链表只有一个节点，那么无需反转，直接返回
        }

        //定义一个辅助指针
        HeroNode current = head.next;
        HeroNode next = null;//指向当前节点(即current)的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //从头遍历原来的链表，取出节点，放在新的链表reverseHead的最前端
        while(current != null){

            next = current.next;//指向当前节点(即current)的下一个节点
            current.next = reverseHead.next;//将current的下一个节点指向新的链表的头部
            reverseHead.next = current;//将current连接到新的链表上
            current = next;//让current后移

        }

        //将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;


    }

    //利用栈结构实现单链表的逆序打印
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return;//空链表不能打印
        }
        //创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode current = head.next;
        while(current != null){
            stack.push(current);
            current = current.next;
        }

        //出栈
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }

    }

}


//Step 1. 定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
    //数据域：
    public int no;
    public String name;
    public String nickName;
    //指针域：
    public HeroNode next;//指向下一个节点


    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }


//    @Override
//    public String toString() {
//        return "HeroNode{" +
//                "no=" + no +
//                ", name='" + name + '\'' +
//                ", nickName='" + nickName + '\'' +
//                ", next=" + next +
//                '}';
//    }

    //不要像上面的toString方法那样，将next也返回出来
    //否则在打印链表时，会把指针域里的对象也打印出来
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}