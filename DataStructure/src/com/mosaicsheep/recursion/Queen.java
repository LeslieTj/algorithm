package com.mosaicsheep.recursion;

public class Queen {
    // num: 皇后的个数
    int num = 8;

    // 存放皇后的位置，比如，arr = { 0, 4, 7, 5, 2, 6, 1, 3}
    // arr[0] = 0 表示将皇后放在第一行第一列
    // arr[1] = 4 表示将皇后放在第二行第五列
    // ...
    int[] arr;

    static int count = 0;// count用来统计一共有多少种解法

    public Queen(){
        this.arr =  new int[num];
    }

    // 放置第n个皇后
    public void check(int n){
        if(n == num){ // 基线条件，即 n = 8，n是从0开始的，当n = 8 时，已经放好8个皇后了
            print();
            return;
        }

        // 从第1列开始摆放皇后，判断是否冲突
        for(int i = 0; i < num; i ++){
            // 第一次进入循环时，i = 0
            // arr[n] = 0 表示先把当前的这个皇后n放到该行的第1列(第n行第1列)
            arr[n] = i;
            if(judge(n)){ // 不冲突
                // 接着放第 n + 1 个皇后
                check(n + 1);
            }

            // 如果冲突，就开启下一次循环，即i++，将当前这个皇后n放到该行的第2列...
        }

    }

    // 当放置第n个皇后时(第n行)，检测该皇后是否和前面已经摆放的皇后冲突
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {// i从第一行到第(n-1)行

            if (arr[i] == arr[n]) {// 在同一列(arr[x]表示，第x行 第arr[x]列 存放皇后)
                return false;
            }
            if (Math.abs(n - i) == Math.abs(arr[n] - arr[i])) { // 行差等于列差，即在一条斜线上
                return false;
            }
        }

        return true;
    }

    // 输出皇后的位置(即遍历输出形如`arr = { 0, 4, 7, 5, 2, 6, 1, 3}`的数组)
    public void print() {
        count++;// 统计一共有多少种解法
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
