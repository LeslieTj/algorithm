package com.mosaicsheep.recursion;

public class QueenTest {
    public static void main(String[] args) {
        Queen queen = new Queen();

        // 先放第一个皇后，然后不断调用内部机制，如果不冲突，再放第二个皇后...
        queen.check(0);
        System.out.println("一共有" + queen.count + "种解法。");
    }
}
