package com.mosaicsheep.queue.arrayqueue.circlearrayqueue;

public class CircleArrayQueueTest {
    public static void main(String[] args) {
        CircleArrayQueue circlearrayqueue = new CircleArrayQueue(5);
        circlearrayqueue.addQueue(5);
        circlearrayqueue.addQueue(4);
        circlearrayqueue.addQueue(3);
        circlearrayqueue.addQueue(2);
//        circlearrayqueue.addQueue(1);//输出结果：队列已满。虽然maxSize是5，但是由于rear预留了一个空间作为约定

        circlearrayqueue.showQueue();

        System.out.println(circlearrayqueue.getQueue());
        System.out.println(circlearrayqueue.getQueue());
        System.out.println(circlearrayqueue.getQueue());
        System.out.println(circlearrayqueue.getQueue());
//        System.out.println(circlearrayqueue.getQueue());//java.lang.RuntimeException: 队列为空，无法获取数据

        circlearrayqueue.addQueue(10);//不同于普通队列，环形队列还可以继续添加
        circlearrayqueue.addQueue(20);
        circlearrayqueue.showQueue();

    }


}
