package com.mosaicsheep.dynamicprogramming;

/**
 * 利用动态规划算法求解背包问题
 *
 * @author LeslieTang
 * @version 2019/12/27
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};// 物品的重量
        int[] val = {1500, 3000, 2000};// 物品的价值， 这里的val[i]就是笔记中公式里的v[i]
        int m = 4;// 背包的容量
        int n = w.length;// 物品的个数

        // 创建二维数组，v[i][j]表示在前i个物品中能够装入容量为j的
        // 背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        // 初始化第一行和第一列，因为默认为0，因此可以不用写
//        for (int i = 0; i < v.length; i++) {
//            v[i][0] = 0;// 将第一列设置为0
//        }
//        for (int i = 0; i < v[0].length; i++) {
//            v[0][i] = 0;// 将第一行设置为0
//        }

        // 动态规划
        for (int i = 1; i < v.length; i++) {// i = 1开始，不处理全为0的第一行
            for (int j = 1; j < v[0].length; j++) {// 不处理全为0的第一列
                if (w[i - 1] > j) {// 因为程序i是从1开始的，因此我们笔记中公式中的w[i]要修改为w[i-1]
                    v[i][j] = v[i - 1][j];
                } else {// 因为程序i是从1开始的，因此我们笔记中公式中的w[i]要修改为w[i-1],val[i]要修改为val[i-1]
                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                }
            }
        }

        // 输出表格
        for(int i = 0; i< v.length; i++){
            for(int j = 0; j < v[0].length; j++){
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
    }
}
