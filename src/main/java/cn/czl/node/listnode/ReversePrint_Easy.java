package cn.czl.node.listnode;

import cn.czl.node.treenode.TraverseTreeNode_DFS_Demo;

import javax.swing.tree.TreeNode;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/9/8 9:51
 * @description:
 *      剑指 Offer 06. 从尾到头打印链表
 *      输入：head = [1,3,2]
 *      输出：[2,3,1]
 */
public class ReversePrint_Easy {
    /**
     * Int栈，效率偏低
     * 执行用时：2 ms, 在所有 Java 提交中击败了43.79%的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了87.70%的用户
     * */
    public int[] reversePrint(ListNode head) {
        if(head == null){
            return new int[0];
        }
        Stack<Integer> stack = new Stack<>();  // 栈-先进后出
        int size = 0;
        while (true){
            stack.push(head.val);
            size++;
            if(head.next != null){
                head = head.next;
            }else{
                break;
            }
        }
        int res[] = new int[size];
        for(int i = 0; i < size; i++){
            res[i] = stack.pop();
        }
        return res;
    }

    /**
     * ListNode栈,高效
     * 执行用时：1 ms, 在所有 Java 提交中击败了73.92%的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了22.33%的用户
     * */
    public int[] reversePrint2(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }
        return print;
    }

    /**
     * 递归重组
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了9.68%的用户
     * */
    public int[] reversePrint3(ListNode head) {
        int cout = length(head);
        int[] res = new int[cout];
        reversePrintHelper(head, res, cout - 1);
        return res;
    }
    //计算链表的长度
    public int length(ListNode head) {
        int cout = 0;
        ListNode dummy = head;
        while (dummy != null) {
            cout++;
            dummy = dummy.next;
        }
        return cout;
    }
    // 递归重新排序，存储至数组res
    public void reversePrintHelper(ListNode head, int[] res, int index) {
        if (head == null)
            return;
        reversePrintHelper(head.next, res, index - 1);
        res[index] = head.val;
    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

