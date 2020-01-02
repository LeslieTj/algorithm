package com.mosaicsheep.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 图的创建和显示
 *
 * @author LeslieTang
 * @version 2019/12/24
 */
public class Graph {

    public static void main(String[] args) {
        String[] vertexes = {"A", "B", "C", "D", "E"};

        // 创建图对象
        Graph graph = new Graph(vertexes.length);

        // 添加结点
        for (String s : vertexes) {
            graph.insertVertex(s);
        }

        // 添加边
        graph.insertEdge(0, 1, 1);// A-B
        graph.insertEdge(0, 2, 1);// A-C
        graph.insertEdge(1, 2, 1);// B-C
        graph.insertEdge(1, 3, 1);// B-D
        graph.insertEdge(1, 4, 1);// B-E

        // 显示图
        graph.showGraph();
    }

    private ArrayList<String> vertexes;// 存储顶点
    private int[][] edges;// 存储对应的邻接矩阵
    private int numOfEdges;// 表示边数

    public Graph(int numOfVertex) {
        vertexes = new ArrayList<>(numOfVertex);
        edges = new int[numOfVertex][numOfVertex];
        // numOfEdges = 0; 默认为0
    }

    // 插入结点
    public void insertVertex(String vertex) {
        vertexes.add(vertex);
    }

    // 添加边
    // v1、v2表示顶点在二维数组中的下标
    // weight:要么是1，表示连通；要么是0，表示未连通
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;// 因为是无向图，所以要写两遍，v1 v2的顺序不同
        numOfEdges++;
    }

    // 返回结点的个数
    public int getNumOfVertex() {
        return vertexes.size();
    }

    // 返回边数
    public int getNumOfEdge() {
        return numOfEdges;
    }

    // 返回结点index（下标）对应的数据，比如0 -> "A", 1-> "B"
    public String getValueByIndex(int index) {
        return vertexes.get(index);
    }

    // 返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示图对应的邻接矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
}
