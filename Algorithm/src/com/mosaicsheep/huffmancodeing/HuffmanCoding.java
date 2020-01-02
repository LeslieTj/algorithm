package com.mosaicsheep.huffmancodeing;

import java.util.*;

/**
 * 思路分析：
 * 1. 创建Node，成员变量包括data（存放数据对应的ascii码，比如a对应97），weight（权值，比如a的权值就是5），left和right
 * 2. 得到"i like like like java do you like a java"对应的byte[]数组
 * 3. 将准备构建霍夫曼树的Node节点放到list中
 * 4. 通过list创建对应的霍夫曼树
 * 5. 生成霍夫曼树对应的霍夫曼编码表，将霍夫曼编码存放在Map<Byte,String>，形式如32=>01, 97=>100, 100=>11000
 * 6. 返回霍夫曼编码压缩后的byte[]数组
 *
 * @author LeslieTang
 * @version 2019/12/17
 */
public class HuffmanCoding {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";

        byte[] bytes = str.getBytes();
        System.out.println("original bytes:" + Arrays.toString(bytes));

        System.out.println("----------------------------------------");

        List<Node> nodes = getNodes(bytes);
        System.out.println(nodes);

        System.out.println("----------------------------------------");

        Node root = createHuffmanTree(nodes);
        preOrder(root);

        System.out.println("----------------------------------------");

        // getCodes(root, "", stringBuilder);
        getCodes(root);
        System.out.println("生成的huffman编码表为：" + huffmanCodes);

        System.out.println("----------------------------------------");

        byte[] zippedBytes = zip(bytes, huffmanCodes);
        System.out.println("zipped bytes:" + Arrays.toString(zippedBytes));
        System.out.println("压缩率为：" + (double) (bytes.length - zippedBytes.length) / bytes.length);
    }

    /**
     * @param nodes
     * @return the root node of Huffman tree
     */
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }

        return nodes.get(0);
    }

    /**
     * @param bytes 字节数组
     * @return 返回一个ArrayList<Node>
     */
    public static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();

        // 使用map存储每个byte出现的次数
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {// map中还没有这个字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count++);
            }
        }

        // 将键值对转成一个Node对象，并加入到nodes中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    // 生成霍夫曼树对应的霍夫曼编码，存放于map中
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    // 在生成霍夫曼编码时，需要去拼接路径，因此定义一个StringBuilder，存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 得到传入的node节点的所有叶子节点的霍夫曼的编码，并放入到huffmanCodes这个map中
     *
     * @param node          传入的节点
     * @param code          路径，左子节点表示0，右子节点表示1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);

        if (node != null) {// 如果node为null，不进行处理

            // 判断当前node是叶子节点还是非叶子节点
            if (node.data == null) {// 非叶子节点
                // 递归处理
                // 向左递归
                getCodes(node.left, "0", stringBuilder2);
                // 向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {// 叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    // 上面方法的重载形式
    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);

        return huffmanCodes;
    }

    /**
     * 返回霍夫曼编码压缩后的byte[]数组，举例来说，我们的原始字符串为：
     * String str = "i like like like java do you like a java";
     * 将其转换成字节数组（byte[] bytes = str.getBytes()）：
     * 01101001 00100000 01101100 01101001 01101011 01100101 00100000 01101100 01101001 01101011 01100101 00100000 01101100 01101001 01101011 01100101 00100000 01101010 01100001 01110110 01100001 00100000 01100100 01101111 00100000 01111001 01101111 01110101 00100000 01101100 01101001 01101011 01100101 00100000 01100001 00100000 01101010 01100001 01110110 01100001
     * 而通过霍夫曼编码后得到的字节数组，会对上面的字节进行压缩
     *
     * @param bytes        原始的字符串对应的byte数组
     * @param huffmanCodes 生成的霍夫曼编码map
     * @return 霍夫曼编码处理后的byte数组
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1. 利用huffmanCodes将bytes转换成霍夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        // stringBuilder转换成byte[]数组，首先确定长度
        int length;
        if (stringBuilder.length() % 8 == 0) {
            length = stringBuilder.length() / 8;
        } else {
            length = stringBuilder.length() / 8 + 1;
        }
        // 创建存储压缩后的byte[]数组
        byte[] result = new byte[length];
        int index = 0; //记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {// 每8位对应一个byte，因此步长为8
            String strByte;
            if (i + 8 > stringBuilder.length()) {// 最后一段可能不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            result[index++] = (byte) Integer.parseInt(strByte, 2);
        }

        return result;
    }


    // 前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树不能遍历！");
        }
    }

}

class Node implements Comparable<Node> {
    Byte data;// 比如'a'对应97，空格(' ')对应32
    int weight;// 权值，字符出现的次数
    Node left;// 左子节点
    Node right;// 右子节点

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node other) {
        return this.weight - other.weight;//从小到大排序
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
