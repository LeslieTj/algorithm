package com.mosaicsheep.huffmancodeing.refactored;

/**
 * byte类型转二进制字符串的代码
 *
 * @author LeslieTang
 * @version 2019/12/18
 */
public class ByteToBinaryString {
    public static void main(String[] args) {

        byte b = -99;
        System.out.println(Integer.toBinaryString(b));// 11111111111111111111111110011101
        String str = Integer.toBinaryString(b | 256);
        System.out.println(str.substring(str.length() - 8));// 10011101，恰好是-99的补码形式


        System.out.println("------------------------------------------------------------");

        b = 99;
        System.out.println(Integer.toBinaryString(b));// 1100011
        str = Integer.toBinaryString(b | 256);
        System.out.println(str.substring(str.length() - 8));// 01100011，恰好是99的补码形式,且恰好补足了8位
    }
}
