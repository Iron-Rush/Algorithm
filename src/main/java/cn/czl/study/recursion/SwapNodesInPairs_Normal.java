package cn.czl.study.recursion;

/**
 * @author RedRush
 * @date 2021/3/19 14:04
 * @description:
 *      24. 两两交换链表中的节点
 *      - 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *      - 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *      示例 1：
 *          输入：head = [1,2,3,4]
 *          输出：[2,1,4,3]
 *      示例 2：
 *          输入：head = []
 *          输出：[]
 *      示例 3：
 *          输入：head = [1]
 *          输出：[1]
 *      提示：
 *          链表中节点的数目在范围 [0, 100] 内
 *          0 <= Node.val <= 100
 */
public class SwapNodesInPairs_Normal {

    /**
     * 直接递归
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 27.10% 的用户
     * */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    /**
     * 迭代法
     * */
    public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pos = dummy;
        while (pos.next != null && pos.next.next != null){
            ListNode temp = pos.next.next;
            pos.next.next = temp.next;
            temp.next = pos.next;
            pos.next = temp;
            pos = temp.next;
        }
        return dummy.next;
    }

    public ListNode swapPairs3(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pos = dummy;
        while (pos.next != null && pos.next.next != null){
            ListNode next1 = pos.next;
            ListNode next2 = pos.next.next;
            next1.next = next2.next;
            next2.next = next1;
            pos.next = next2;
            pos = next1;
        }
        return dummy.next;
    }
}
