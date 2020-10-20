package cn.czl.node.listnode;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/10/20 10:57
 * @description:
 *      876. 链表的中间结点
 *          - 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 *          - 如果有两个中间结点，则返回第二个中间结点。
 */
public class FindMiddleNode_Easy {

    @Test
    public void TestSolution(){
//        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))));
        ListNode middel = middleNode(head);
        while (middel != null){
            System.out.print(middel.val + "->");
            middel = middel.next;
        }
    }

    /**
     * 快慢指針
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了93.61%的用户
     * */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 存入数组->根据下标获取
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了99.85%的用户
     * */
    public ListNode middleNode2(ListNode head){
        ListNode[] list = new ListNode[100];
        int len = 0;
        while (head != null){
            list[len++] = head;
            head = head.next;
        }
        return list[len/2];
    }

    /**
     * 先遍历->统计长度->二次遍历，获取n/2个节点
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了99.72%的用户
     * */
    public ListNode middleNode3(ListNode head){
        int len = 0, count = 0;
        ListNode pos = head;
        while (pos != null){
            pos = pos.next;
            len ++;
        }
        pos = head;
        while (count < len/2){
            pos = pos.next;
            count++;
        }
        return pos;
    }

}
