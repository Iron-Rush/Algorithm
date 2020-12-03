package cn.czl.node.listnode.operate;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/12/3 10:27
 * @description:
 *      19. 删除链表的倒数第N个节点
 *      - 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *      示例：
 *          给定一个链表: 1->2->3->4->5, 和 n = 2.
 *          当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *      说明：给定的 n 保证是有效的。
 *      进阶：你能尝试使用一趟扫描实现吗？
 */
public class RemoveNthFromEnd_Normal {

    @Test
    public void TestSolution(){
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        printListNode(head);
        head = removeNthFromEnd3(head, 1);
        printListNode(head);
    }

    /**
     * 借助栈实现
     * 执行用时：1 ms, 在所有 Java 提交中击败了18.90%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了58.85%的用户
     * */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pos = dummy;
        Stack<ListNode> stack = new Stack<>();
        while (pos != null){
            stack.push(pos);
            pos = pos.next;
        }
        while (n > 0){
            stack.pop();
            n--;
        }
        pos = stack.pop();
        pos.next = pos.next.next;
        return dummy.next;
    }

    /**
     * 统计链表长度，再次遍历删除节点
     * 执行用时：1 ms, 在所有 Java 提交中击败了18.90%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了58.85%的用户
     * */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pos = dummy;
        int depth = 0;
        while (pos.next != null){
            pos = pos.next;
            depth++;
        }
        pos = dummy;
        while (n < depth){
            pos = pos.next;
            n++;
        }
        pos.next = pos.next.next;
        return dummy.next;
    }

    /**
     * 双指针同步遍历。通过差值，找到目标节点
     * 执行用时：1 ms, 在所有 Java 提交中击败了18.90%的用户
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了73.19%的用户
     * */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode rPos = dummy;
        ListNode lPos = dummy;
        for (int i = 0; i < n; i++) {
            rPos = rPos.next;
        }
        while (rPos.next != null){
            rPos = rPos.next;
            lPos = lPos.next;
        }
        lPos.next = lPos.next.next;
        return dummy.next;
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
