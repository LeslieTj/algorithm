package com.mosaicsheep.sparsearray;

//以棋盘为例，进行二维数组和稀疏数组的互相转换
public class SparseArrayRefactoring {

    public static void main(String[] args) {
        //创建原始二维数组
        //0表示没有棋子，1表示黑子，2表示白子
        int[][] arr = new int[5][6];
        arr[1][2] = 1;
        arr[2][3] = 2;
        listArray(arr);

        System.out.println("--------------------------------");

        int[][] sparseArray = fromNormalArrayToSpareArray(arr);
        listArray(sparseArray);

        System.out.println("--------------------------------");

        int[][] arr1 = fromSpareArrayToNormalArray(sparseArray);
        listArray(arr1);

        System.out.println("--------------------------------");

    }


    // 遍历输出二维数组
    public static void listArray(int[][] arr) {
        for (int[] row : arr) {
            for (int data : row) {
                System.out.print(data + "\t");

            }
            System.out.println();
        }
    }

    // 将二维数组转换为稀疏数组
    public static int[][] fromNormalArrayToSpareArray(int[][] arr) {

        int sum = 0;
        for (int[] row : arr) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }

            }
        }

        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = arr.length;//原始二维数组的行数
        sparseArr[0][1] = arr[0].length;// 元素二维数组的列数，这里认为二维数组是工整的，每行列数相等
        sparseArr[0][2] = sum;


        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];

                }
            }
        }


        return sparseArr;

    }

    // 将稀疏数组转为普通二维数组
    public static int[][] fromSpareArrayToNormalArray(int[][] sparseArr) {

        int[][] normalArray = new int[sparseArr[0][0]][sparseArr[0][1]];


        for (int i = 1; i < sparseArr.length; i++) {
            normalArray[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        return normalArray;

    }


}

