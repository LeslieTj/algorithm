package com.mosaicsheep.recursion;

public class Maze {
    public static void main(String[] args) {
        //创建二维数组，模拟迷宫
        int[][] maze = new int[8][7];

        //设置外墙，用数字1表示
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        //左右全都置为1
        for (int i = 0; i < 8; i++) {
            maze[i][0] = 1;
            maze[i][6] = 1;
        }

        //设置内墙，用数字1表示
        maze[3][1] = 1;
        maze[3][2] = 1;
        // maze[1][2] = 1;
        // maze[2][2] = 1;// 打开这两条注释，会发现出现了回溯

        // 输出迷宫
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("-------------------------------");

        // 使用递归回溯给小球找路
        // 假设小球从maze[1][1]出发
//        findWay(maze, 1, 1);

        // 看似在"走"迷宫，实际上只是"神游"，在出发点不断自己调用自己，不断压栈。
        // 什么时候开始弹栈呢？规定了两个基线条件(基线条件1和基线条件2)。
        // 不断弹栈到最后，又返回到了起点函数findWay2(maze, 1, 1)
        // 起点函数最终返回一个布尔值，为true则成功找到出口，否则没有找到出口
        System.out.println(findWay2(maze, 1, 1));

        //输出走过的迷宫：
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }


    }

    // 使用递归回溯来给小球找路
    // maze 表示待走的迷宫
    // i,j 表示出发的位置

    // 当maze[i][j]为0时，表示该点还没有走过
    // 当maze[i][j]为2时，表示通路可以走
    // 当maze[i][j]为3时，表示已走过，但走不通

    // 走迷宫策略：下->右->上->左，如果该点走不通，再回溯
    // 如果走到maze[6][5]，即走出迷宫
    public static boolean findWay(int[][] maze, int i, int j) {

        if (maze[6][5] == 2) {// 走出迷宫
            return true;// 基线条件1

        } else {

            if (maze[i][j] == 0) {// 当前点还没走过

                //按照下->右->上->左的策略走
                maze[i][j] = 2;// 假设该点可以走通
                if (findWay(maze, i + 1, j)) {// 向下走
                    return true;
                } else if (findWay(maze, i, j + 1)) {// 向右走
                    return true;
                } else if (findWay(maze, i - 1, j)) {// 向上走
                    return true;
                } else if (findWay(maze, i, j - 1)) {// 向左走
                    return true;
                } else {// 该点没有走通，假设不成立
                    maze[i][j] = 3;
                    return false;// 基线条件2
                }

            } else {// maze[i][j] != 0, 可能是1，2，3
                return false;
            }
        }

    }


    // 修改走迷宫策略：上 -> 右 -> 下 -> 左
    public static boolean findWay2(int[][] maze, int i, int j) {

        if (maze[6][5] == 2) {// 走出迷宫
            return true;

        } else {

            if (maze[i][j] == 0) {// 当前点还没走过

                //按照上->右->下-> 左的策略走
                maze[i][j] = 2;// 假设该点可以走通
                if (findWay2(maze, i - 1, j)) {// 向上走
                    return true;
                } else if (findWay2(maze, i, j + 1)) {// 向右走
                    return true;
                } else if (findWay2(maze, i + 1, j)) {// 向下走
                    return true;
                } else if (findWay2(maze, i, j - 1)) {// 向左走
                    return true;
                } else {// 该点没有走通，假设不成立
                    maze[i][j] = 3;
                    return false;
                }

            } else {// maze[i][j] != 0, 可能是1，2，3
                return false;
            }
        }

    }


}
