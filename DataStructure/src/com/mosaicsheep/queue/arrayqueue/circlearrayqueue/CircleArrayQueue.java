package com.mosaicsheep.queue.arrayqueue.circlearrayqueue;

// 用数组实现环形队列
public class CircleArrayQueue {

    private int maxSize;// 数组的最大容量
    private int front;// front指向队列的第一个元素，即arr[front]就是队列的第一个元素，front的初始值为0
    private int rear;// rear指向队列最后一个元素的后一个位置，空出一个空间作为一个约定，rear的初始值为0
    private int[] arr;// 用于存放数据，模拟队列

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
//        因为front和rear的初始值都是0，下面两句可写可不写，int类型默认初始化就为0
//        this.front = 0;
//        this.rear = 0;
    }

    //判断队列是否已满员
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        arr[rear] = n;
        //将rear后移，不能直接(rear + 1)，因为可能会数组越界
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，即出队列
    //队列先进先出(先到食堂的人先打饭)，即从front开始获取数据
    public int getQueue() {
        if (isEmpty()) {
            //如果为空，就抛出异常
            throw new RuntimeException("队列为空，无法获取数据");
        }
        //front直接指向队列的第一个元素
        //1. 先将front对应的值保留到一个临时变量
        //2. 将front后移
        //3. 将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }


    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，无法获取数据");
            return;
        }
        //从front开始遍历
        for (int i = front; i < front + effectiveSize(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }

    }

    //求当前队列有效数据的个数
    public int effectiveSize() {
        return (rear + maxSize - front) % maxSize;
    }


}