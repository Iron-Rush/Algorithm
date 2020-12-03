package cn.czl.node.listnode.judge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/10/23 9:22
 * @description:
 *      234. 回文链表   请判断一个链表是否为回文链表。
 *      示例 1:   输入: 1->2        输出: false
 *      示例 2:   输入: 1->2->2->1  输出: true
 */
public class IsPalindrome_Easy {

    @Test
    public void TestSolution(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
//        head.next.next = new ListNode(1);
//        head.next.next.next = new ListNode(1);
//        head.next.next.next.next = new ListNode(1);
        System.out.println(isPalindrome4(head));
    }

    /**
     * 指针 + 栈，同步比较。相遇时结束
     * 执行用时：4 ms, 在所有 Java 提交中击败了28.12%的用户
     * 内存消耗：41.8 MB, 在所有 Java 提交中击败了44.80%的用户
     * */
    public boolean isPalindrome(ListNode head) {
        if (head == null){
            return true;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode rNode = head;
        while (rNode != null){
            stack.add(rNode);
            rNode = rNode.next;
        }
        ListNode lNode = head;
        rNode = stack.pop();
        while (!stack.isEmpty() && lNode != rNode){
             if (lNode.val != rNode.val){
                 return false;
             }
             lNode = lNode.next;
             if (lNode == rNode){
                 break;
             }
             rNode = stack.pop();
        }
        return true;
    }

    /**
     * (数组+双指针)建数组-存储节点值-双指针比较判断
     * 执行用时：4 ms, 在所有 Java 提交中击败了28.12%的用户
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了41.21%的用户
     * */
    public boolean isPalindrome2(ListNode head) {
        // 存储链表数值至数组
        List<Integer> list = new ArrayList();
        ListNode pos = head;
        while (pos != null){
            list.add(pos.val);
            pos = pos.next;
        }
        // 双指针比较判断
        int lPos = 0, rPos = list.size()-1;
        while (lPos < rPos){
            if (!list.get(lPos++).equals(list.get(rPos--))){
                return false;
            }
        }
        return true;
    }

    /**
     * 快慢指针 + 反转 + 比较
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.86%的用户
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了96.76%的用户
     * */
    public boolean isPalindrome3(ListNode head) {
        ListNode pre = null, fast = head, slow = head;
        while (fast != null && fast.next != null){
            ListNode nextTemp = slow.next;
            if (pre != null){
                slow.next = pre;
            }
            pre = slow;
            fast = fast.next.next;
            slow = nextTemp;
        }
        if(fast != null){
            slow = slow.next;
        }
        while (slow != null){
            if (slow.val != pre.val){
                return false;
            }
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.96%的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了81.31%的用户
     * */
    public boolean isPalindrome4(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        ListNode fast = head.next, slow = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode l2 = slow.next;
        if(fast.next != null){
            l2 = l2.next;
        }
        slow.next = null;
        ListNode l1 = reverse(head);
        return compare(l2, l1);
    }

    // 比较两个链表是否一样
    private boolean compare(ListNode l2, ListNode l1) {
        while (l1 != null && l2 != null){
            if (l1.val != l2.val){
                return false;
            }else {
                l1 = l1.next;
                l2 = l2.next;
            }
        }
        return true;
    }

    // 翻转传入链表
    private ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode pos = head;
        while (pos != null){
            ListNode temp = pos.next;
            pos.next = prev;
            prev = pos;
            pos = temp;
        }
        return prev;
    }
}
