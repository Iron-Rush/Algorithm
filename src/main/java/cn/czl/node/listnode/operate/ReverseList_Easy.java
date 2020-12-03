package cn.czl.node.listnode.operate;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/12/3 11:07
 * @description:
 *      206. 反转链表
 *      - 反转一个单链表。
 *      示例:
 *          输入: 1->2->3->4->5->NULL
 *          输出: 5->4->3->2->1->NULL
 *      - 进阶: 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class ReverseList_Easy {

    @Test
    public void TestSolution(){
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        printListNode(head);
        head = reverseList3(head);
        printListNode(head);
    }

    /**
     * 递归实现
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了45.90%的用户
     * */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode reverse = reverseList(head.next);
        head.next.next = head;  // head.next此时指向翻转后链表的末尾
        head.next = null;       // 要将翻转后链表末尾的指针指向head,且head为新尾部
        return reverse;
    }

    /**
     * 迭代，新建链表实现翻转
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了64.40%的用户
     * */
    public ListNode reverseList2(ListNode head) {
        ListNode pos = null;
        while(head != null){
            ListNode next = new ListNode(head.val, pos);
            head = head.next;
            pos = next;
        }
        return pos;
    }

    /**
     * 迭代，改变指针，实现翻转
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了59.49%的用户
     * */
    public ListNode reverseList3(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;  // 暂存下一个节点
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    // 打印链表函数
    void printListNode(ListNode head){
        while (head != null && head.next != null){
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println(head.val);
    }
}
