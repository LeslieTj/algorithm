package com.mosaicsheep.stack;

//这个代码有bug
public class Calculator {
    public static void main(String[] args) {
        String expression = "7-6/2-2";//有bug！！！！！
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        int index = 0;//用于扫描
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = "";//用于拼接多位数


        //扫描入栈
        while(true){
            //依次得到expression的每一个字符
            ch = expression.substring(index, index+1).charAt(0);
            //判断ch是符号还是数字
            if(operStack.isOper(ch)){
                //判断当前符号栈是否为空
                if(!operStack.isEmpty()){
                    //如果符号栈有操作符，就比较优先级
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.cal(num1, num2, oper);
                        //把运算结果入数栈
                        numStack.push(result);
                        //把当前操作符入符号栈
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }else{
                    //如果为空直接入栈
                    operStack.push(ch);
                }
            }else{
                //如果是数，判断是单位数还是多位数
                keepNum += ch;

                if(index == expression.length()- 1){
                    numStack.push(Integer.parseInt(keepNum));//如果ch已经是expression的最后一位，就直接入栈
                }else{
                    //判断下一个字符是不是数字，是数字的话就继续扫描
                    //注意：是看后一位，不是index++
                    if(operStack.isOper(expression.substring(index+1, index+2).charAt(0))){
                        //如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }

            //让index+1，并判断是否扫描到expression最后
            index++;
            if(index >= expression.length()){
                break;
            }
        }

        //扫描完毕，开始运算
        while(true){
            //如果符号栈为空，数栈中这时只有一个数字，该数字即为最终结果
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1, num2, oper);
            numStack.push(result);
        }

        System.out.println("表达式" + expression + "的最终结果为：" + numStack.pop());

    }
}



//先用数组构建一个栈结构
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//用数组模拟栈
    private int top = -1;//top表示栈顶，初值为-1


    //返回运算符的优先级
    //优先级由程序员来确定，用数字表示，数字越大，优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;
        }
    }

    //判断是运算符还是数字
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper){
        int result = 0;
        switch(oper){
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;//注意顺序
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
        }
        return result;
    }

    //返回当前栈顶的值，但不将栈顶pop出来
    public int peek(){
        return stack[top];
    }


    //以下的方法都和ArrayStack一样：

    public ArrayStack2(int maxSize) {
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
            throw new RuntimeException("栈为空~没有数据出栈~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈，从栈顶往下遍历
    public void list(){
        if(isEmpty()){
            System.out.println("栈空~没有数据");
            return;
        }

        for(int i = top; i >= 0; i--){
            System.out.println(stack[i]);
        }
    }



}