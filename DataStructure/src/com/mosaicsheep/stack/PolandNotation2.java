package com.mosaicsheep.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//中缀表达式转后缀表达式
public class PolandNotation2 {
    public static void main(String[] args) {

        //1. 将中缀表达式 1+((2+3)×4)-5 转换为list(ArrayList)
        // 即 [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpression = toInfixExpressionList(expression);
        System.out.println(infixExpression);

        System.out.println("----------------------------------------------------");

        //2. 将中缀表达式对应的list转换为后缀表达式对应的list
        List<String> suffixExpression = parseSuffixExpression(infixExpression);
        System.out.println(suffixExpression);


    }


    //1.将中缀表达式转换成对应的list
    public static List<String> toInfixExpressionList(String str){

        List<String> list = new ArrayList<>();

        int i = 0;//用于遍历中缀表达式字符串的指针
        String s;//对多位数的拼接
        char c;//每遍历到一个字符，就赋给c

        do{
            //如果c是一个非数字，直接添加进list
            //如果是数字，则要考虑多位数
            if((c = str.charAt(i)) < 48 || (c = str.charAt(i)) > 57){
                list.add(c + "");//将c转成字符串，放入list
                i++;
            }else{
                s = "";
                while( i < str.length() && (c = str.charAt(i)) >= 48 && (c = str.charAt(i)) <= 57){
                    s += c;//拼接
                    i++;
                }

                list.add(s);
            }
        }while(i < str.length());

        return list;
    }


    //2.将中缀list转为后缀list
    public static List<String> parseSuffixExpression(List<String> list){
        //定义符号栈和数字栈：
        Stack<String> s1 = new Stack<>();//符号栈
        //Stack<String> s2 = new Stack<>();//数字栈

        //在整个转换过程中，s2始终没有出栈(pop)操作，并且，后面将会逆序输出数字栈中的数字
        //因此，使用栈结构比较麻烦，直接使用ArrayList：
        List<String> s2 = new ArrayList<>();

        //遍历传进来的中缀list
        for (String s: list) {

            if(s.matches("\\d+")){
                //如果是数字，直接加入s2
                s2.add(s);
            }else if(s.equals("(")){
                //如果是左括号，则直接压入s1
                s1.push(s);
            }else if(s.equals(")")){
                // 如果是右括号，则依次弹出s1栈顶的运算符，并压入s2，
                // 直到遇到左括号为止，此时将这一对括号丢弃
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将)丢弃
            }else{
                //当s的优先级小于等于s1栈顶的运算符，将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符比较。
                while (s1.size() != 0 &&
                        Operation.getPriorityValue(s1.peek()) >= Operation.getPriorityValue(s)) {
                    s2.add(s1.pop());
                }
                //将s压入栈顶
                s1.push(s);

            }


        }

        //将s1中剩余的运算符依次弹出并加入s2：
        while(s1.size() != 0){
            s2.add(s1.pop());
        }

        return s2;

    }

}

//编写一个Operation类，返回一个运算符对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回优先级：
    public static int getPriorityValue(String operation){
        int result = 0;

        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                //System.out.println("不存在该运算符");
                break;
        }

        return result;
    }


}