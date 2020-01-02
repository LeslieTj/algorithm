package com.mosaicsheep.huffmancodeing.refactored;

import java.util.*;

/**
 * 在HuffmanCoding的基础上，封装一个huffmanZip方法以及unzip方法
 * 对于unzip，思路如下：
 * 1. 先将霍夫曼编码、压缩后的byte数组转换成对应的二进制字符串（byteToBitString方法）
 * 2. 根据霍夫曼编码表，将对应的二进制字符串转换成原始的字符串
 *
 * @author LeslieTang
 * @version 2019/12/17
 */
public class HuffmanCodingRefactored {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        byte[] zippedBytes = huffmanZip(bytes);

        // 在使用byteToBitString方法时，对于超过8位的二进制字符串，要截取成8位，对于不足8位的，要补足8位
        for (byte b : zippedBytes) {
            System.out.println(b + " -> " + Integer.toBinaryString(b));
        }

        System.out.println(Arrays.toString(zippedBytes));

        byte[] decodedBytes = decode(huffmanCodes, zippedBytes);
        System.out.println("Original String:" + new String(decodedBytes));


    }

    /**
     * @param huffmanCodes 霍夫曼编码后的对照表
     * @param huffmanBytes 霍夫曼编码、压缩处理后的得到的字节数组
     * @return 原始字符串对应的数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1. 先得到huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; ) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);// 判断是不是最后一个字节
            stringBuilder.append(byteToBitString(!flag, b));// 如果是最后一个字节，不需要补高位，比如加入最后一个字节是28，对应二进制是11100，这时候我们不需要在前面补足0变成00011100了，而是直接拼接上11100就好了
        }
        // 把字符串按照制定的霍夫曼编码进行解码
        // 需要把霍夫曼编码表进行调换，因为要反向查询
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        // 创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        // 开始扫描stringBuilder这个字符串，stringBuilder形式如：1010100010111...
        for (int i = 0; i < stringBuilder.length(); i++) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                // 取出一个'1'或'0'
                String key = stringBuilder.substring(i, i + count);// i不动，让count移动，直到匹配到一个字符
                b = map.get(key);
                if (b == null) {//说明还没匹配到
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count; //i直接移动到count的位置，继续向下扫描
        }

        // 当for循环结束后，list中就存放了所有的字符，将list中的数据放入byte数组并返回
        byte[] result = new byte[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        return result;
    }


    /**
     * 将byte转换成二进制的字符串
     *
     * @param flag true表示需要补高位，false表示不需要补高位，如果是最后一个字节，不需要补高位，比如加入最后一个字节是28，对应二进制是11100，这时候我们不需要在前面补足0变成00011100了
     * @param b    待转换的字符串
     * @return 转换成的二进制字符串(是按照补码返回的)
     */
    public static String byteToBitString(boolean flag, byte b) {
        int temp = b;// 将b转换成int，并且保存在临时变量中
        if (flag) {
            temp |= 256;// 采用按位或256 1 0000 0000 | 0000 0001 = 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;// 最后一个字节不需要补高位
        }
    }

    /**
     * @param bytes 原始字符串对应的字节数组
     * @return 霍夫曼编码、压缩后的字节数组
     */
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node root = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(root);
        return zip(bytes, huffmanCodes);
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
