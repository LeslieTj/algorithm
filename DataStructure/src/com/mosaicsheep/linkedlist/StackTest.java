package com.mosaicsheep.linkedlist;

import java.util.Stack;

//演示栈的基本使用
public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack();

        //入栈
        stack.add("Jack");
        stack.add("Tom");
        stack.add("Jerry");

        //出栈
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

}
