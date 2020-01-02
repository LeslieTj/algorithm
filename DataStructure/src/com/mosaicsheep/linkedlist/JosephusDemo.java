package com.mosaicsheep.linkedlist;

public class JosephusDemo {

    public static void main(String[] args) {
        //1. 构建环形链表，并遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        System.out.println("-----------------------------------------------------------");

        //2. 测试小孩出圈
        circleSingleLinkedList.countBoy(1, 2, 5);//2 -> 4 -> 1 -> 5 -> 3
    }
}


//创建一个单向环形链表
class CircleSingleLinkedList{

    //创建一个first节点
    private Boy first = null;


    //创建一个有nums个节点的环形链表
    public void addBoy(int nums){

        if(nums < 1){
            System.out.println("nums的值不正确~");
            return;
        }

        Boy currentBoy = null;//辅助指针，帮助构建环形链表

        //使用for循环创建环形链表
        for(int i = 1; i <= nums; i++){

            Boy boy = new Boy(i);
            if(i == 1){
                first = boy;
                first.setNext(first);//让第一个小孩自己指向自己，构成环
                currentBoy = first;//让currentBoy指向第一个小孩
            }else{

                currentBoy.setNext(boy);
                boy.setNext(first);
                currentBoy = boy;
            }

        }
    }


    //遍历当前环形链表
    public void showBoy(){
        //判断链表是否为空
        if(first == null){
            System.out.println("链表为空~");
        }

        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy currentBoy = first;

        while(true){
            System.out.printf("小孩的编号%d\n", currentBoy.getNo());
            if(currentBoy.getNext() == first){
                break;//已经遍历完毕
            }

            currentBoy = currentBoy.getNext();
        }

    }

    //解决约瑟夫问题，计算小孩出圈顺序
    //startNo：表示从第几个小孩开始数
    //countNum:表示数几下
    //nums:表示最初有多少小孩在圈中
    public void countBoy(int startNo, int countNum, int nums){
        if(first == null){
            System.out.println("没有小孩啊~");
            return;
        }
        if(startNo < 1 || startNo > nums){
            System.out.println("参数输出有误~");
        }

        //创建辅助指针，帮助完成小孩出圈
        Boy helper = first;

        //将helper移动到最后一个节点
        while(true){
            if(helper.getNext() == first){
                break;//说明helper指向了最后一个节点
            }
            helper = helper.getNext();//不能用helper.next，因为next在Boy中private的，不能直接访问
        }

        //小孩报数前，先让first和helper移动到最先报数的小孩的所在位置
        //假设要从第StartNo个小孩开始报数，那么需要移动 StartNo-1 次
        for(int i = 0; i < startNo - 1; i++){
            first = first.getNext();
            helper = helper.getNext();
        }

        //当小孩报数时，让first和helper指针同时移动 countNum - 1 次，然后出圈
        //循环执行，直到圈中只有一个节点
        while(true){
            if(helper == first){
                break;//这时圈中只有一个节点
            }
            //让first和helper同时移动 countNum - 1 次
            for(int i = 0; i < countNum - 1 ; i++){
                first = first.getNext();
                helper = helper.getNext();
            }

            //这时，first指向的节点就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //将first指向的小孩出圈
            first = first.getNext();
            helper.setNext(first);
        }

        System.out.println("最后留在圈中的小孩编号为：" + first.getNo());


    }

}

//创建一个Boy类
class Boy {
    //数据域：
    private int no;//编号
    //指针域：
    private Boy next;//指向下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }


    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}