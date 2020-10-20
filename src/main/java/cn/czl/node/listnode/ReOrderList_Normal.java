package cn.czl.node.listnode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/10/20 9:29
 * @description:
 *      143. 重排链表
 *          - 给定一个单链表 L：L0→L1→…→Ln-1→Ln
 *          将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *          - 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *      示例1：
 *          给定链表 1->2->3->4,重新排列为 1->4->2->3.
 *      示例2：
 *          给定链表 1->2->3->4->5,重新排列为 1->5->2->4->3.
 */
public class ReOrderList_Normal {

    @Test
    public void TestSolution(){
//        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3)));
        reorderList2(root);
        while (root != null){
            System.out.print(root.val + "->");
            root = root.next;
        }
    }

    /**
     * 通过栈，获取右侧节点，并与左侧节点交互设置指针
     * 执行用时：4 ms, 在所有 Java 提交中击败了28.37%的用户
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了98.72%的用户
     * */
    public void reorderList(ListNode head){
        if (head == null || head.next == null){
            return;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode pos = head;
        while (pos != null){
            stack.add(pos);
            pos = pos.next;
        }
        ListNode Lnode = head, L2 = head.next;
        ListNode Rnode = stack.pop();
        while (Lnode != Rnode){
            if (Rnode == L2.next){
                Lnode.next = Rnode;
                Rnode.next = L2;
                L2.next = null;
                break;
            }
            if(Rnode == L2){
                L2.next = null;
                break;
            }
            Lnode.next = Rnode;
            Rnode.next = L2;
            Lnode = L2;
            L2 = L2.next;
            Rnode = stack.pop();
        }
    }
    /**
     * 栈获取右侧节点，左侧设置前驱节点，交替设置指针(逻辑优化版)
     * 执行用时：3 ms, 在所有 Java 提交中击败了36.63%的用户
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了99.77%的用户
     * */
    public void reorderList2(ListNode head){
        if (head == null || head.next == null){
            return;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode pos = head;
        int count = 0;
        while (pos != null){
            stack.add(pos);
            pos = pos.next;
            count ++;
        }
        ListNode Lnode = head, L2 = head.next;
        ListNode Rnode = stack.pop();
        while (L2 != Rnode && L2.next != Rnode){
            Lnode.next = Rnode;
            Rnode.next = L2;
            Lnode = L2;
            L2 = L2.next;
            Rnode = stack.pop();
        }
        if (count % 2 == 1){
            Lnode.next = Rnode;
            Rnode.next = L2;
        }
        L2.next = null;
    }

    /**
     * 链表节点存入数组，通过数组下标访问节点，并设置指针
     * 执行用时：4 ms, 在所有 Java 提交中击败了28.37%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了100.00%的用户
     * */
    public void reorderList3(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    /**
     * 查找链表中点 + 右侧链表逆序 + 合并链表
     * 执行用时：2 ms, 在所有 Java 提交中击败了79.06%的用户
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了96.38%的用户
     * */
    public void reorderList4(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);    // 获取链表中点
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;                    // 为左侧链表设置末尾
        l2 = reverseList(l2);               // 根据中点，反转右侧链表
        mergeList(l1, l2);                  // 合并两条链表
    }
    // 通过快慢指针获取链表中点
    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    // 反转链表
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
    // 合并链表
    private void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;
            l1.next = l2;
            l1 = l1_tmp;
            l2.next = l1;
            l2 = l2_tmp;
        }
    }


}