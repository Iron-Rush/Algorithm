package cn.czl.node.listnode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/11/13 9:13
 * @description:
 *      328. 奇偶链表
 *      - 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
 *      请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *      - 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，
 *      时间复杂度应为 O(nodes)，nodes 为节点总数。
 *      示例:
 *          输入: 2->1->3->5->6->4->7->NULL
 *          输出: 2->3->6->7->1->5->4->NULL
 *      说明:
 *          - 应当保持奇数节点和偶数节点的相对顺序。
 *          - 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 */
public class OddEvenList_Normal {

    @Test
    public void TestSolution(){
        ListNode root1 = new ListNode(2, new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(7)))))));
        ListNode root2 = new ListNode(2, new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(6, new ListNode(4))))));
        ListNode root3 = new ListNode(2);
        printNode(root2);
        ListNode res = oddEvenList3(root2);
        printNode(root2);
    }

    /**
     * 通过队列存储偶数位节点，先连接奇数位节点，再根据队列在末尾拼接偶数位节点
     * 执行用时：2 ms, 在所有 Java 提交中击败了8.03%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了78.40%的用户
     * */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        Queue<ListNode> nodeQueue = new LinkedList<>();
        ListNode pos = head;
        // 拼接奇数位节点，并存储偶数位节点
        while (pos.next != null){
            nodeQueue.add(pos.next);
            if (pos.next.next != null){
                ListNode temp = pos.next.next;
                pos.next = temp;
                pos = temp;
            }else {
                pos.next = null;
            }
        }
        // 根据队列，在pos末尾拼接偶数位节点
        while (!nodeQueue.isEmpty()){
            ListNode temp = nodeQueue.poll();
            pos.next = temp;
            pos = temp;
        }
        pos.next = null;
        return head;
    }

    /**
     * 两次遍历，获取末尾奇数指针，左右指针同步拼接链表
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了83.82%的用户
     * */
    public ListNode oddEvenList2(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode end = null;    // 末尾偶数位节点，若链长度为奇数则为空，偶数则为最后一个节点
        ListNode rPos = head, lPos = head;
        // 获取最后一个奇数位节点
        while (rPos.next != null){
            if (rPos.next.next == null){
                end = rPos.next;
                rPos.next = null;
            }else {
                rPos = rPos.next.next;
            }
        }
        ListNode flag = rPos;
        // 开始拼接链表
        while (lPos != flag){
            ListNode next = lPos.next;  // 获取下一个偶数节点
            lPos.next = lPos.next.next; // 拼接奇数节点
            lPos = lPos.next;
            rPos.next = next;           // 在末尾拼接偶数节点
            rPos = rPos.next;
        }
        rPos.next = end;
        return head;
    }

    /**
     * 分别拼接 奇数位/偶数位 链表，将 奇数表表尾 指向 偶数表表头
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了84.19%的用户
     * */
    public ListNode oddEvenList3(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode odd = head;        // 奇数节点链表
        ListNode even = head.next;  // 偶数节点链表
        ListNode evenHead = even;   // 偶数节点表头
        while (odd.next != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;        // 拼接链表
        return head;
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
