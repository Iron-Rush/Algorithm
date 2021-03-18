package cn.czl.node.listnode.operate;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/3/18 9:38
 * @description:
 *      92. 反转链表 II
 *      - 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *      - 说明: 1 ≤ m ≤ n ≤ 链表长度。
 *      示例:
 *          输入: 1->2->3->4->5->NULL, m = 2, n = 4
 *          输出: 1->4->3->2->5->NULL
 */
public class ReverseList2_Between_Normal {

    @Test
    public void TestSolution(){
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        printNode(head);
        head = reverseList(head, 3);
//        head = reverseBetween2(head, 2, 4);
        printNode(head);
    }

    /**
     * 递归
     * 先找到翻转起始点，然后翻转该节点开始，前 n-m 个节点
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 21.02% 的用户
     * */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == 1){
            return reverseList(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }
    // 翻转前n个节点
    ListNode tail = null;   // 保存末尾节点
    public ListNode reverseList(ListNode head, int count) {
        if(count == 1){
            tail = head.next;
            return head;
        }
        ListNode reverse = reverseList(head.next, count-1);
        head.next.next = head;  // 将下一个节点的next指针，指向自己
        head.next = tail;       // 将自己的next指针，指向尾部
        return reverse;         // 返回翻转后的头结点
    }

    /**
     * 迭代 - 翻转链表
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36 MB , 在所有 Java 提交中击败了 64.85% 的用户
     * */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < n -m; i++) {    // pre -> cur -> next -> next.next
            next = cur.next;        // 暂存下一个节点
            cur.next = next.next;   // 当前节点的next，指向下下个节点(next.next)
            next.next = pre.next;   // next节点的next指向当前节点(pre.next)
            pre.next = next;        // 前驱头结点的next指向next
            // pre -> next -> cur -> next.next
        }
        return dummy.next;
    }


    // 打印链表
    void printNode(ListNode pos){
        while (pos != null){
            System.out.print(pos.val + "->");
            pos = pos.next;
        }
        System.out.println();
    }
}
