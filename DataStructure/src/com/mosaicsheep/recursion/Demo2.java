package com.mosaicsheep.recursion;

//递归调用机制：阶乘问题
public class Demo2 {
    public static void main(String[] args) {

        System.out.println(factorial(5));
    }

    public static int factorial(int n){

        if(n == 1){
            return 1;
        }else{
            return n * factorial(n-1);
        }
    }
}
