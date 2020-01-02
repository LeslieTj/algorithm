package com.mosaicsheep.kruskalalgorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Spliterator;

/**
 * @author LeslieTang
 * @version 2019/12/30
 */
public class Kruskal {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}
        };

        Kruskal kruskal = new Kruskal(vertex, matrix);

        kruskal.print();

        Edge[] edges = kruskal.getEdges();
        System.out.println("未排序：" + Arrays.toString(edges));
        kruskal.sortEdge(edges);
        System.out.println("排序后：" + Arrays.toString(edges));
        System.out.println("--------------------------------");
        kruskal.kruskalAlgorithm();// 总共12条边，只有6条边入选

    }

    private int edgeNum;// 边数
    private char[] vertex;// 顶点
    private int[][] matrix;// 邻接矩阵
    private static final int INF = Integer.MAX_VALUE;// 使用INF表示两个顶点不能连通

    public Kruskal(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
        // 统计边
        // 遍历上三角
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    this.edgeNum++;
                }
            }
        }
    }

    public void kruskalAlgorithm() {
        int index = 0;// 表示最后结果数组的索引
        int[] ends = new int[edgeNum];// 用于保存 已有的最小生成树中的 每个顶点 在最小生成树中的终点

        // 创建结果数组，保存最后的最小生成树
        Edge[] results = new Edge[edgeNum];

        // 获取图中所有边的集合
        Edge[] edges = getEdges();
        System.out.println("图中边的集合：" + Arrays.toString(edges) + "共" + edges.length + "条边");

        // 按照边的权值大小进行排序
        sortEdge(edges);

        // 遍历edges数组，将边添加到最小生成树时，判断准备加入的边是否构成了回路
        // 如果没有构成回路，就添加，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            // 获取到第i条边的第一个顶点
            int v1 = getPosition(edges[i].start);
            // 获取到第i条边的第二个顶点
            int v2 = getPosition(edges[i].end);

            // 获取v1顶点在当前已有的最小生成树中的终点
            int m = getEnd(ends, v1);
            // 获取v1顶点在当前已有的最小生成树中的终点
            int n = getEnd(ends, v2);
            // 判断这条边加入是否构成回路
            if (m != n) {
                // 设置m在当前已有最小生成树中的终点
                // ends最初是是[0, 0, 0, ..., 0]，原始图中有12条边，因此是12个0
                // 第一次会选取权值最小的E-F边，即v1 = 4, v2 = 5, 根据getEnd方法的逻辑，得到m = 4， n = 5
                ends[m] = n;// [0, 0, 0, 0, 5, 0, ..., 0]
                results[index++] = edges[i];// 将边加入results数组
            }
        }

        // 统计并打印最小生成树
        System.out.println("最小生成树为：" + Arrays.toString(results));


    }

    // 打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%12d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    // 使用冒泡法，对边进行排序处理
    public void sortEdge(Edge[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    Edge temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 根据顶点的值，返回顶点的下标
     *
     * @param ch 顶点，如'A','B'
     * @return 返回ch顶点对应的下标；找不到的话返回-1
     */
    public int getPosition(char ch) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    // 通过matrix邻接矩阵来获取图中的边，放到Edge数组中（形式如[['A','B', 12], ['B', 'F', 7]...]），并遍历该数组
    public Edge[] getEdges() {
        int index = 0;
        Edge[] edges = new Edge[edgeNum];

        // 遍历上三角
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new Edge(vertex[i], vertex[j], matrix[i][j]);
                }
            }
        }

        return edges;
    }

    // 获取下标为i的顶点对应的终点下标，用来判断两个顶点的终点是否相同
    // ends数组记录了各个顶点对应的终点，是在遍历的过程中逐步形成的，不是一步到位的，
    // 比如最开始时，一条边都没有加入，我们没办法直到A的顶点是谁，B的顶点是谁...
    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

}

class Edge {
    char start;// 边的端点
    char end;// 边的另外一个端点
    int weight;// 边的权值

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
