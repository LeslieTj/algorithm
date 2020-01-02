package com.mosaicsheep.queue.arrayqueue.normalarrayqueue;

//用数组实现普通队列
public class ArrayQueue {

    private int maxSize;//数组的最大容量
    private int front;//队首
    private int rear;//队尾
    private int[] arr;//用于存放数据，模拟队列

    //创造队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = -1;//front指向队首的前一个位置（类似于链表中头节点-首节点）
        this.rear = -1;//rear直接指向队尾

    }

    //判断队列是否已满员
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    //想象排队的场景，加数据是从队尾增加，即从rear开始加
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    //获取队列的数据，即出队列
    //队列先进先出(先到食堂的人先打饭)，即从front开始获取数据
    public int getQueue() {
        if (isEmpty()) {
            //如果为空，就抛出异常
            throw new RuntimeException("队列为空，无法获取数据");// 这里有时间可以自己重构，自定义异常，extends RuntimeException
        }
        front++;
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，无法获取数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }

    }

}
