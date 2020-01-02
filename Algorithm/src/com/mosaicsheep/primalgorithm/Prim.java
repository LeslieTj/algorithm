package com.mosaicsheep.primalgorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author LeslieTang
 * @version 2019/12/29
 */
public class Prim {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;

        /*  邻接矩阵如下：
         *      A    B  C    D      E      F    G
         * A {10000, 5, 7, 10000, 10000, 10000, 2}
         * B {5, 10000, 10000, 9, 10000, 10000, 3}
         * C {7, 10000, 10000, 10000, 8, 10000, 10000}
         * D {10000, 9, 10000, 10000, 10000, 4, 10000}
         * E {10000, 10000, 8, 10000, 10000, 5, 4}
         * F {10000, 10000, 10000, 4, 5, 10000, 6}
         * G {2, 3, 10000, 10000, 4, 6, 10000}
         *
         * 其中数据10000表示节点之间是不连通的
         */
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };

        Graph graph = new Graph(vertex);

        MST mst = new MST();
        mst.createGraph(graph, vertex, data, weight);
        mst.showGraph(graph);

        System.out.println("----------------------");

        mst.prim(graph, 0);// 从A顶点开始生成，权值总和为25
        System.out.println("----------------------");

        mst.prim(graph, 1);// 从B顶点开始生成，权值总和仍为25


    }
}

// 创建最小生成树
class MST {
    /**
     * 创建一个图对象
     *
     * @param graph  Graph对象
     * @param vertex 图的顶点个数
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(Graph graph, int vertex, char[] data, int[][] weight) {
        for (int i = 0; i < vertex; i++) {
            graph.data[i] = data[i];// 遍历节点存放节点数据
            for (int j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];// 遍历邻接矩阵存放边（权值）
            }
        }
    }

    /**
     * 显示图的邻接矩阵
     *
     * @param graph
     */
    public void showGraph(Graph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 利用普里姆算法求最小生成树
     *
     * @param graph
     * @param v     从图的哪个顶点开始生成，比如要从A顶点开始生成，A顶点对应的下标为0，传入的v就是0
     */
    public void prim(Graph graph, int v) {
        int[] visited = new int[graph.vertex];// 标记被访问顶点
        visited[v] = 1;

        // h1和h2记录两个顶点的下标（往后看就明白了，h1记录被访问顶点下标，h2记录未被访问顶点下标）
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;// 将miniWeight初始化成较大的数值

        for (int k = 1; k < graph.vertex; k++) {// 如果有n个顶点，生成n-1个边

            // 确定每一次生成的子图，和哪个顶点的距离最近
            for (int i = 0; i < graph.vertex; i++) {// 遍历已经访问过的顶点，visited[i] == 1

                for (int j = 0; j < graph.vertex; j++) {// 遍历还没有被访问过的顶点，visited[j] == 0

                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            // 找到权值最小的边
            System.out.println(graph.data[h1] + "-" + graph.data[h2] + ":权值为" + minWeight);
            visited[h2] = 1;// 标记为已访问节点

            // 重置miniWeight
            minWeight = 10000;
        }
    }
}

class Graph {
    int vertex;// 表示图的节点个数
    char[] data;// 存放节点数据
    int[][] weight;// 邻接矩阵，存放边（权值）

    public Graph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }


}
