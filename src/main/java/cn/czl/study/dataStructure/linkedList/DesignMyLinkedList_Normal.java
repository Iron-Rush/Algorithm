package cn.czl.study.dataStructure.linkedList;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/3/22 16:06
 * @description:
 *      707. 设计链表
 *      - 设计链表的实现。您可以选择使用单链表或双链表。
 *      - 单链表中的节点应该具有两个属性：val 和 next。
 *          val 是当前节点的值，next 是指向下一个节点的指针/引用。
 *      - 如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。
 *          假设链表中的所有节点都是 0-index 的。
 */
public class DesignMyLinkedList_Normal {

    @Test
    public void TestSolution(){
        MyLinkedList2 linkedList = new MyLinkedList2();
        linkedList.addAtHead(1);
        linkedList.addAtTail(100);
        linkedList.addAtIndex(-100, 2018);
        linkedList.addAtIndex(2, 3);
        for (int i = 0; i < 4; i++) {
            System.out.println(linkedList.get(i));
        }
        linkedList.deleteAtIndex(2);
        linkedList.deleteAtIndex(0);
        for (int i = 0; i < 2; i++) {
            System.out.println(linkedList.get(i));
        }
    }
}

/**
 * 存储结构为双向链表，保存head/tail两个哑结点。
 * 根据index大小，决定从首/尾获取目标节点
 * 构建(根据index，获取目标节点前一个节点)的方法
 * 执行用时： 10 ms , 在所有 Java 提交中击败了 85.46% 的用户
 * 内存消耗： 39.1 MB , 在所有 Java 提交中击败了 53.80% 的用户
 * */
class MyLinkedList {

    class Node{
        int val;
        Node next;
        Node pre;
        Node(){}
        Node(int v){
            val = v;
        }
    }

    Node head;
    Node tail;
    int size;

    // 初始化链表
    public MyLinkedList() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        size = 0;
    }

    // 根据索引，返回对应值。若索引非法，return -1
    public int get(int index) {
        if(index < 0 || index >= size){
            return -1;
        }
        Node node = getPreNode(index);
        return node.next.val;
    }

    // 在链表头部插入数据
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    // 在链表尾部插入数据
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    // 在指定索引位置插入数据，如果索引为负，则在头部插入
    public void addAtIndex(int index, int val) {
        if(index > size){
            return;
        }
        if(index < 0){
            index = 0;
        }
        Node preNode = getPreNode(index);
        Node temp = new Node(val);
        temp.next = preNode.next;
        temp.pre = preNode;
        preNode.next = temp;
        temp.next.pre = temp;
        size++;
    }

    // 根据索引删除数据，如果索引非法则不执行删除
    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size){
            return;
        }
        Node preNode = getPreNode(index);
        preNode.next = preNode.next.next;
        preNode.next.pre = preNode;
        size--;
    }

    // 获取指定index的前一个节点
    private Node getPreNode(int index){
        if(index < size / 2){
            Node preNode = head;
            for (int i = 0; i < index; i++) {
                preNode = preNode.next;
            }
            return preNode;
        }else {
            Node preNode = tail;
            for (int i = 0; i < size - index + 1; i++) {
                preNode = preNode.pre;
            }
            return preNode;
        }
    }
}

/**
 * 单链表实现
 * 执行用时： 13 ms , 在所有 Java 提交中击败了 62.60% 的用户
 * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 42.41% 的用户
 * */
class MyLinkedList2 {
    class Node{
        int val;
        Node next;
        Node(int v){
            val = v;
        }
    }

    int size;
    Node head;

    // 初始化链表
    public MyLinkedList2(){
        size = 0;
        head = new Node(0);
    }

    // 根据索引，返回对应值。若索引非法，return -1
    public int get(int index) {
        if(index < 0 || index >= size){
            return -1;
        }
        Node node = getPreNode(index);
        return node.next.val;
    }

    // 在链表头部插入数据
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    // 在链表尾部插入数据
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    // 在指定索引位置插入数据，如果索引为负，则在头部插入
    public void addAtIndex(int index, int val) {
        if(index > size){
            return;
        }
        if(index < 0){
            index = 0;
        }
        Node preNode = getPreNode(index);
        Node temp = new Node(val);
        temp.next = preNode.next;
        preNode.next = temp;
        size++;
    }

    // 根据索引删除数据，如果索引非法则不执行删除
    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size){
            return;
        }
        Node preNode = getPreNode(index);
        preNode.next = preNode.next.next;
        size--;
    }

    // 获取指定index的前一个节点
    Node getPreNode(int index){
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

}