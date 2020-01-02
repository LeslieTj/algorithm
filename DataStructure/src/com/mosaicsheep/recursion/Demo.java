package com.mosaicsheep.recursion;

//递归调用机制:打印问题
public class Demo {
    public static void main(String[] args) {
        test1(4);
        System.out.println("-----------------");
        test2(4);
    }

    public static void test1(int n){
        if(n > 2){
            test1(n-1);
        }
        System.out.println("n=" + n);

    }


    public static void test2(int n){
        if(n > 2){
            test2(n-1);
        }else {
            System.out.println("n=" + n);
        }
    }
}
