package com.mosaicsheep.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 在Graph2的基础上，又增加了图的广度优先遍历
 *
 * @author LeslieTang
 * @version 2019/12/24
 */
public class Graph3 {

    public static void main(String[] args) {
        String[] vertexes = {"A", "B", "C", "D", "E"};

        // 创建图对象
        Graph3 graph = new Graph3(vertexes.length);

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

        // 测试广度优先遍历
        graph.bfs();

    }

    private ArrayList<String> vertexes;// 存储顶点
    private int[][] edges;// 存储对应的邻接矩阵
    private int numOfEdges;// 表示边数
    private boolean[] isVisited;// 记录节点是否被访问

    public Graph3(int numOfVertex) {
        vertexes = new ArrayList<>(numOfVertex);
        edges = new int[numOfVertex][numOfVertex];
        isVisited = new boolean[numOfVertex];
        // numOfEdges = 0; 默认为0
    }

    // 对一个节点进行广度优先遍历
    public void bfs(int index) {
        int front;// 表示队列的头节点对应的下标
        int w;// 邻接节点的下标

        // 队列，存放节点访问的顺序
        LinkedList queue = new LinkedList<>();

        // 访问节点
        System.out.println(getValueByIndex(index));
        // 标记为已访问
        isVisited[index] = true;
        // 将节点加入队列
        queue.addLast(index);

        while (!queue.isEmpty()) {// 只要队列不为空，就取出队列的头节点下标
            // 取出队列的头节点下标
            front = (Integer) queue.removeFirst();// removeFirst()返回的是一个Object
            // 得到第一个邻接节点的下标
            w = getFirstNeighbour(front);
            while (w != -1) {// w存在，即可以连通
                if (!isVisited[w]) {// w未被访问过
                    System.out.println(getValueByIndex(w));// 输出
                    isVisited[w] = true;// 标记被访问
                    queue.addLast(w);// 加入队列
                }
                // 去找同一层的下一个邻接节点（w后面的下一个邻接节点)
                w = getNextNeighbour(front, w);
            }

        }
    }


    // 重载bfs，遍历所有节点，都进行广度优先搜索，防止出现不连通图
    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(i);
            }
        }
    }


    // 得到第一个邻接节点的下标
    public int getFirstNeighbour(int index) {
        for (int j = 0; j < vertexes.size(); j++) {
            // edges中0表示未连通，1表示连通
            // 我们是在遍历第index行，参考dsf.png，比如index=0时，
            // 就是在遍历第一行，找A的第一个连通的节点
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbour(int v1, int v2) {
        for (int j = v2 + 1; j < vertexes.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 深度优先遍历
    // index第一次时是0，即初始访问节点
    public void dfs(int index) {
        // 首先访问该节点，并输出
        System.out.println(getValueByIndex(index));

        // 将节点设置为已访问
        isVisited[index] = true;

        // 查找第一个邻接节点
        int w = getFirstNeighbour(index);
        while (w != -1) {// w存在
            if (!isVisited[w]) {// w未被访问
                dfs(w);
            } else {// w已经被访问过，查找邻接节点的下一个邻接节点
                w = getNextNeighbour(index, w);
            }
        }
    }

    // 对dfs进行一个重载，遍历所有的节点，并进行dsf
    // 这样做的原因是为了遍历不连通图
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            // 实际上在本例子中，dsf(0)就能完成遍历，dsf(0)之后，isVisited里面就已经都是true了
            // 之所以要重载，是为了防止出现不连通图
            if (!isVisited[i]) {
                dfs(i);
            }
        }
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
