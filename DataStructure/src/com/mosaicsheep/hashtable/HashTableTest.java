package com.mosaicsheep.hashtable;

import java.util.spi.CurrencyNameProvider;

public class HashTableTest {
    public static void main(String[] args) {
        // 创建哈希表
        HashTable hashTable = new HashTable(7);

        Employee employee1 = new Employee(1, "Adele");
        hashTable.add(employee1);// 如果HashTable中不初始化它管理的多条链表的话，会出现空指针异常
        Employee employee2 = new Employee(3, "Chris");
        hashTable.add(employee2);
        Employee employee3 = new Employee(17, "Ed");
        hashTable.add(employee3);

        hashTable.list();

        System.out.println("--------------------------------");

        hashTable.findEmployeeById(1);
        hashTable.findEmployeeById(2);


    }
}

// 管理多条链表
class HashTable {
    private int size;// 表示共有多少条链表
    private EmployeeLinkedList[] employeeLinkedLists;

    public HashTable(int size) {
        this.size = size;
        employeeLinkedLists = new EmployeeLinkedList[size];

        // 分别初始化每一条链表，如果不进行初始化的话，会出现上面说的空指针异常
        for (int i = 0; i < size; i++) {
            employeeLinkedLists[i] = new EmployeeLinkedList();
        }
    }

    // 添加雇员
    public void add(Employee employee) {
        //根据员工的id，得到该员工应该加入到哪条链表
        int employeeLinkedListNo = hashFunction(employee.id);
        // 将employee添加到对应的链表中
        employeeLinkedLists[employeeLinkedListNo].add(employee);
    }

    // 根据输入的id查找员工
    public void findEmployeeById(int id){
        // 使用散列函数，确定到哪条链表查找
        int employeeListNo = hashFunction(id);
        Employee employee = employeeLinkedLists[employeeListNo].findEmployeeById(id);
        if(employee != null){
            System.out.println(String.format("Find employee no.%d in LinkedList no.%d", id, employeeListNo));
        }else{
            System.out.println("Not Found!");
        }
    }

    // 散列函数：取模法
    public int hashFunction(int id) {
        return id % size;
    }

    // 遍历哈希表
    public void list() {
        for (int i = 0; i < size; i++) {
            employeeLinkedLists[i].list(i);
        }
    }


}

class EmployeeLinkedList {
    private Employee head;// 这里的head是直接指向第一个employee的，默认为空，需要向里面添加employee

    // 添加雇员到链表
    // 假定当添加雇员时，id是自增的，id的分配总是从小到大
    // 因此只需要从尾部添加即可
    public void add(Employee employee) {
        if (head == null) {// 添加的是第一个雇员
            head = employee;
            return;
        }

        Employee currentEmployee = head;// 辅助指针，帮助遍历到链表的最后
        while (true) {
            if (currentEmployee.next == null) {
                break;
            }
            currentEmployee = currentEmployee.next;
        }

        currentEmployee.next = employee;
    }

    // 遍历链表
    public void list(int no) {
        if (head == null) {
            System.out.println(String.format("The LinkedList(No.%d) is empty.", no));
            return;
        }

        Employee currentEmployee = head;// 辅助指针
        while (true) {
            System.out.println(String.format("LinkedList No.%d: => id = %d, name = %s", no, currentEmployee.id, currentEmployee.name));

            if (currentEmployee.next == null) {// 防止出现空指针异常
                break;
            } else {
                currentEmployee = currentEmployee.next;
            }
        }
    }

    // 找不到就返回null
    public Employee findEmployeeById(int id) {
        if(head == null){
            return null;
        }
        Employee currentEmployee = head;
        while(true){
            if(currentEmployee.id == id){// 找到了
                break;
            }
            if(currentEmployee.next == null){//遍历当前链表并没有找到该雇员
                currentEmployee = null;
            }
            currentEmployee = currentEmployee.next;
        }

        return currentEmployee;
    }


}

// Employee是链表的节点，每个节点有数据域和指针域
class Employee {
    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
