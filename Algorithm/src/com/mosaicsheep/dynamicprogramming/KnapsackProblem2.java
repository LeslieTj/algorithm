package com.mosaicsheep.dynamicprogramming;

/**
 * 利用动态规划算法求解背包问题，对KnapsackProblem.java进行一些完善
 *
 * @author LeslieTang
 * @version 2019/12/27
 */
public class KnapsackProblem2 {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};// 物品的重量
        int[] val = {1500, 3000, 2000};// 物品的价值， 这里的val[i]就是笔记中公式里的v[i]
        int m = 4;// 背包的容量
        int n = w.length;// 物品的个数

        // v[i][j]表示在前i个物品中能够装入容量为j的
        // 背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        // path数组用来记录放入商品对情况
        int[][] path = new int[n + 1][m + 1];


        // 动态规划
        for (int i = 1; i < v.length; i++) {// i = 1开始，不处理全为0的第一行
            for (int j = 1; j < v[0].length; j++) {// 不处理全为0的第一列
                if (w[i - 1] > j) {// 因为程序i是从1开始的，因此我们笔记中公式中的w[i]要修改为w[i-1]
                    v[i][j] = v[i - 1][j];
                } else {// 因为程序i是从1开始的，因此我们笔记中公式中的w[i]要修改为w[i-1],val[i]要修改为val[i-1]
                    // v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    // 为了记录商品存放到背包的情况，我们不能直接使用上面的公式，而要使用if-else:
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }

                }
            }
        }

        // 输出表格
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        // 输入最后放入的是哪些商品
        // 下面这样输出太冗余：
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[0].length; j++) {
//                if (path[i][j] == 1) {
//                    System.out.printf("第%d个商品放入到背包\n", i);
//                }
//            }
//        }


        int i = path.length - 1; // path行的最大下标
        int j = path[0].length - 1;// path列的最大下标
        while (i > 0 && j > 0) {// 从path数组的最后开始走
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];// i这个物品所占的重量是w[i-1]，需要调整背包的剩余容量
            }
            i--;
        }
    }
}
