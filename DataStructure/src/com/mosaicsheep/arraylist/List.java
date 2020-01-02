package com.mosaicsheep.arraylist;


//利用数组构建List
public class List {

    private int[] arr;// 数组，用来存放List元素
    private int maxSize;// 线性表的最大存储容量，即数组的长度
    private int length;// 线性表的当前长度

    // 数组的长度：存放线性表的存储空间的长度，是固定不变的；
    // 线性表的长度：线性表中的元素的个数，是随着线性表插入和删除而变化的
    // 线性表的长度 <= 数组的长度


    public List(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.length = 0;
    }

    // 按索引获取List中的元素
    public int getElement(int index){

        if(isEmpty()){
            throw new RuntimeException("The list is empty!");
        }

        if(index > this.length || index < 1){
           throw new RuntimeException("Index Out Of Bound!");
        }

        return arr[index - 1];

    }

    // 从尾部向List中添加元素
    public void insertElement(int element){

        if(isFull()){
            System.out.println("The list is full!");
            return;
        }
        arr[length] = element;
        length++;

    }

    // 按索引向List中添加元素
    // index: 插入的位置
    public void insertElement(int index, int element){

        if(isFull()){
        System.out.println("The list is full!");
            return;
        }

        length++;

        if(index > length || index < 1){
            System.out.println("Index Out Of Bound!");
            return;
        }

        for(int i = length - 2; i >= index - 1; i--){
            arr[i+1] = arr[i];
        }

        arr[index - 1] = element;

    }


    // 删除元素
    public void deleteElement(int index){
        if(isEmpty()){
            throw new RuntimeException("The list is empty!");
        }
        if(index < 1 || index > length){
            throw new RuntimeException("Index Out Of Bound!");
        }
        for(int i = index-1; i < length -1; i++){
            arr[i] = arr[i+1];
        }
        length--;
    }

    // 遍历输出整个List
    public void iterateList(){
        if(isEmpty()){
            throw new RuntimeException("The list is empty!");
        }
        for(int i = 0; i < length; i++){
            System.out.println("List["+ (i+1)+"] = " + arr[i]);
        }

    }

    // 判断List是否为空
    public boolean isEmpty(){
        return this.length == 0;
    }

    // 判断List是否已满
    public boolean isFull(){
        return this.length == this.maxSize;
    }

    // 得到List当前长度
    public int getLength(){
        return this.length;
    }


}




