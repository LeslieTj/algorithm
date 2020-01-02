package com.mosaicsheep.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode2 h1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 h2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 h3 = new HeroNode2(3, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(h1);
        doubleLinkedList.add(h2);
        doubleLinkedList.add(h3);

        doubleLinkedList.list();

        System.out.println("----------------------------------------------------");

        //修改节点信息
        HeroNode2 newh2 = new HeroNode2(2, "公孙胜", "入云龙");

        doubleLinkedList.update(newh2);

        doubleLinkedList.list();

        System.out.println("----------------------------------------------------");

        //删除节点
        doubleLinkedList.delete(1);
        doubleLinkedList.list();


    }
}

class DoubleLinkedList{

    private HeroNode2 head = new HeroNode2(0,"", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历输出整个链表
    //实现逻辑与单链表相同
    public void list(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while(true){
            //判断链表是否已经到最后
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }

    //从尾部添加新节点
    public void add(HeroNode2 heroNode2){
        HeroNode2 temp = head;
        while(true){
            if (temp.next == null){
                break;//找到链表的最后一个节点
            }
            temp = temp.next;
        }

        //形成双向互指
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    //删除节点
    //对于双向节点，可以直接找到要删除的节点，进行自我删除
    public void delete(int no){

        if (head.next == null){
            System.out.println("链表为空，不能进行删除操作~");
        }

        HeroNode2 temp = head.next;
        boolean flag = false;//标识是否找到待删除节点
        while(true){
            if (temp == null){
                break;//遍历到链表最后也没有找到
            }
            if(temp.no == no){
                //找到待删除节点
                flag = true;
                break;
            }
            temp = temp.next;

        }
        if (flag){
            temp.pre.next = temp.next;
            if (temp.next != null){
                //如果是最后一个节点，就不需要执行这句话，否则会出空指针异常
                temp.next.pre = temp.pre;
            }

        }else{
            System.out.println("没有找到要删除的节点");
        }
    }


    //修改节点的信息
    //实现逻辑与单向链表一样
    public void update(HeroNode2 newheroNode){
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }

        HeroNode2 temp = head.next;
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


}




class HeroNode2{

    //数据域：
    public int no;
    public String name;
    public String nickName;
    //指针域：
    public HeroNode2 next;//指向下一个节点，默认为null
    public HeroNode2 pre;//指向前一个节点，默认为null


    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
