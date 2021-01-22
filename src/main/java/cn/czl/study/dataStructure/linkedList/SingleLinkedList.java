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
//        RankNode node = new RankNode();
//        node.add(new Node(4));
//        node.add(new Node(5));
//        node.add(new Node(3));
//        node.print();
//        node.add(new Node(1));
//        node.add(new Node(10));
//        node.print();
//        node.add(new Node(5));
//        node.print();
//        node.add(new Node(2));
//        node.print();
//        HeroRankNode heroNode = new HeroRankNode();
//        heroNode.add(new Node(5, "周树人", "鲁迅"));
//        heroNode.add(new Node(4, "张晓棠", "巴金"));
//        heroNode.add(new Node(1, "万家宝", "曹禺"));
//        heroNode.add(new Node(2, "谢婉莹", "冰心"));
//        heroNode.add(new Node(9, "全家乐", "娃哈哈"));
//        heroNode.print();
//        heroNode.update(new Node(9, "全家乐", "娃哈哈"));
//        heroNode.del(1);
//        heroNode.del(10);
//        heroNode.print();
        Node head = new Node(1);
        Node pos = head;
        for (int i = 2; i < 6; i++) {
            pos.next = new Node(i);
            pos = pos.next;
        }
        System.out.println(getValidNode(head));
        Node target = findLastIndex(head, 8);
        System.out.println(target);
    }

    /**
     * 获取链表有效节点个数
     * */
    public int getValidNode(Node node){
        int count = 0;
        Node dummy = new Node(0);
        dummy.next = node;
        while (dummy.next != null){
            dummy = dummy.next;
            count++;
        }
        return count;
    }
    /**
     * 获取链表倒数第n个节点
     * */
    public Node findLastIndex(Node head, int index){
        if(index <= 0){
            return null;
        }
        int size = getValidNode(head);
        int count = size - index;
        if(count < 0){
            return null;
        }else {
            Node cur = head;
            while (count != 0){
                cur = cur.next;
                count--;
            }
            return cur;
        }
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
        node.next = temp.next;
        temp.next = node;
//        Node next = temp.next;
//        temp.next = node;
//        node.next = next;
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

class HeroRankNode{
    // 初始化头结点
    private Node head = new Node(0, "", "");

    // 根据排名添加指定英雄
    public void add(Node node){
        Node temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no > node.no){
                break;
            }else if(temp.next.no == node.no){
                flag = true;    // 编号存在
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.println("链表中已存在该排名英雄，无法插入");
        }else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    // 根据编号-修改节点的信息
    public void update(Node node){
        Node temp = head.next;
        while (temp != null && temp.no < node.no){
            temp = temp.next;
        }
        if(temp != null && temp.no == node.no){
            temp.name = node.name;
            temp.nickname = node.nickname;
        }else {
            System.out.println("不存在该节点");
        }
    }

    // 根据编号删除节点
    public void del(int no){
        Node temp = head;
        while (temp.next != null && temp.next.no != no){
            temp = temp.next;
        }
        if(temp.next != null && temp.next.no == no){
            temp.next = temp.next.next;
        }else {
            System.out.println("不存在该节点");
        }
    }

    // 打印链表
    public void print(){
        Node temp = head.next;
        if(temp == null){
            System.out.println("链表为空");
        }else {
            while (temp != null){
                System.out.println(temp);
                temp = temp.next;
            }
        }
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
