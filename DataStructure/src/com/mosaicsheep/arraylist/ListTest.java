package com.mosaicsheep.arraylist;

public class ListTest {
    public static void main(String[] args) {
        List list = new List(5);

        // 添加元素
        list.insertElement(1,-1);
        list.insertElement(2);
        list.insertElement(-3);
        list.insertElement(-4);
        list.insertElement(5);
        // list.insertElement(-6);
        System.out.println(list.getLength());

        // 遍历List
        list.iterateList();

        System.out.println("-----------------------");

        // 按索引得到元素
        System.out.println(list.getElement(2));

        System.out.println("-----------------------");

        // 删除元素
        list.deleteElement(3);
        System.out.println(list.getLength());
        list.iterateList();

        System.out.println("-----------------------");
    }
}
