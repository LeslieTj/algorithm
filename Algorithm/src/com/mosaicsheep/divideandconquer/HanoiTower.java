package com.mosaicsheep.divideandconquer;

/**
 * 使用分治算法求解汉诺塔问题
 *
 * @author LeslieTang
 * @version 2019/12/26
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    public static void hanoiTower(int numOfPile, char a, char b, char c) {
        if (numOfPile == 1) {// 如果只有一个盘
            System.out.println(a + "->" + c);
        } else {
            hanoiTower(numOfPile - 1, a, c, b);// 中间借助c，将上层的所有盘都从a移到b
            System.out.println(a + "->" + c);// 将最下面的盘从a移动到c
            hanoiTower(numOfPile - 1, b, a, c);// 中间借助a，将b塔所有盘都移到c
        }
    }


}
