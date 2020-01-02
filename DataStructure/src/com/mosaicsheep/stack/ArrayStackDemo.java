package com.mosaicsheep.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        stack.list();

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();

    }

}

//用数组模拟栈：
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//用数组模拟栈
    private int top = -1;//top表示栈顶，初值为-1


    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }


    //判断栈空
    public boolean isEmpty(){
        return  top == -1;
    }

    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈已满~");
            return;
        }
        top++;
        stack[top] = value;
    }


    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空，没有数据出栈~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈，从栈顶往下遍历
    public void list(){
        if(isEmpty()){
            System.out.println("栈为空，没有数据~");
            return;
        }

        for(int i = top; i >= 0; i--){
            System.out.println(stack[i]);
        }
    }
}