package cn.czl.study.dataStructure.linkedList;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/1/14 15:17
 * @description:
 *      单链表
 */
public class SingleLinkedList {

    @Test
    public void TestSolution(){
        RankNode node = new RankNode();
        node.add(new Node(4));
        node.add(new Node(5));
        node.add(new Node(3));
        node.print();
        node.add(new Node(1));
        node.add(new Node(10));
        node.print();
        node.add(new Node(5));
        node.print();
        node.add(new Node(2));
        node.print();
    }
}
class RankNode{
    // 初始化头结点
    private Node head = new Node(0, "", "");
    // 按照顺序添加节点至单向链表
    public void add(Node node){
        Node temp = head;
        while (temp.next != null && temp.next.no < node.no){
            temp = temp.next;
        }
        Node next = temp.next;
        temp.next = node;
        node.next = next;
    }
    // 打印链表
    public void print(){
        Node temp = head.next;
        while (temp.next != null){
            System.out.print(temp.no + "->");
            temp = temp.next;
        }
        System.out.println(temp.no);
    }
}

// 定义节点
class Node{
    public int no;
    public String name;
    public String nickname;
    public Node next;
    // 构造器
    public Node(int no){
        this.no = no;
    }
    public Node(int no, String name, String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    @Override
    public String toString() {
        return "RankNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
