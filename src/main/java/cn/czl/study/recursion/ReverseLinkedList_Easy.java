package cn.czl.study.recursion;

/**
 * @author RedRush
 * @date 2021/3/19 16:19
 * @description:
 *      206. 反转链表
 *          - 反转一个单链表。
 *      示例:
 *          输入: 1->2->3->4->5->NULL
 *          输出: 5->4->3->2->1->NULL
 *      进阶: 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class ReverseLinkedList_Easy {

    /**
     * 递归法
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 5.08% 的用户
     * */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;  // 将下一个节点的指针指向自已
        head.next = null;       // 自己的指针指向末尾
        return newHead;
    }


    /**
     * 迭代
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 40.26% 的用户
     * */
    public ListNode reverseList2(ListNode head) {
        ListNode pos = head;
        ListNode pre = null;
        while(pos != null){
            ListNode next = pos.next;
            pos.next = pre;
            pre = pos;
            pos = next;
        }
        return pre;
    }
}
