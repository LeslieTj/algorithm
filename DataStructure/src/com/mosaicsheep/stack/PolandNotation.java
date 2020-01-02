package com.mosaicsheep.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //定义逆波兰表达式
        //(3+4)×5-6 -> 3 4 + 5 × 6
        String suffixExpression = "3 4 + 5 * 6 -";

        List<String> list = getListString(suffixExpression);
        System.out.println("计算的结果为：" + calculate(list));

    }

    //将逆波兰表达式打散，装到ArrayList里。
    public static List<String> getListString(String suffixExpression){

        String[] split = suffixExpression.split(" ");

        List<String> list = new ArrayList<>();

        Arrays.stream(split).forEach(i -> list.add(i));

        return  list;
    }

    public static int calculate(List<String> list){
            Stack<String> stack = new Stack<>();
        for (String s: list) {
            //使用正则表达式，取出数
            //+代表一到多，即匹配多位数
            if(s.matches("\\d+")){
                //如果是数，直接入栈
                stack.push(s);
            }else{
                //如果不是数，则说明是运算符
                //pop出两个数字，运算后入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                if(s.equals("+")){
                    result = num1 + num2;
                }else if(s.equals("-")){
                    result = num1 - num2;

                }else if(s.equals("*")){
                    result = num1 * num2;

                }else if(s.equals("/")){
                    result = num1/num2;
                }else{
                    throw new RuntimeException("运算符有误");
                }

                //将result转成字符串，拼接一个空串即可
                stack.push(result + "");

            }

        }
            return Integer.parseInt(stack.pop());

    }
}
