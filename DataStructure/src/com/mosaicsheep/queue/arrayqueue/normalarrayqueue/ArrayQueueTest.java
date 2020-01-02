package com.mosaicsheep.queue.arrayqueue.normalarrayqueue;


//用数组实现普通队列
public class ArrayQueueTest {
    public static void main(String[] args) {
        ArrayQueue arrayqueue = new ArrayQueue(5);
        arrayqueue.addQueue(5);
        arrayqueue.addQueue(4);
        arrayqueue.addQueue(3);
        arrayqueue.addQueue(2);
        arrayqueue.addQueue(1);
//      arrayqueue.addQueue(0);//队列已满，不能往里继续添加

        arrayqueue.showQueue();

        System.out.println(arrayqueue.getQueue());
        System.out.println(arrayqueue.getQueue());
        System.out.println(arrayqueue.getQueue());
        System.out.println(arrayqueue.getQueue());
        System.out.println(arrayqueue.getQueue());
//      System.out.println(arrayqueue.getQueue());//java.lang.RuntimeException: 队列为空，无法获取数据

        arrayqueue.addQueue(20);//输出结果：队列已满，即，普通队列数组只能使用一次

    }

}
