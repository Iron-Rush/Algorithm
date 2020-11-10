package cn.czl.node.listnode;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/11/10 15:51
 * @description:
 *      2. 两数相加
 *      - 给出两个 非空 的链表用来表示两个非负的整数。
 *      其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 *      - 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *      - 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *      示例：
 *          输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 *          输出：7 -> 0 -> 8
 *          原因：342 + 465 = 807
 */
public class AddTwoNumbers_Normal {

    @Test
    public void TestSolution(){
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
        ListNode res = addTwoNumbers(l1, l2);
        while (res != null){
            System.out.print(res.val);
            res = res.next;
        }
    }

    /**
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.92%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了56.75%的用户
     * */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean flag = false;
        ListNode dummy = new ListNode();
        ListNode tempNode = new ListNode();
        dummy.next = tempNode;
        while (l1 != null || l2 != null || flag){
            int v1, v2, sum;
            v1 = l1 == null ? 0 : l1.val;
            v2 = l2 == null ? 0 : l2.val;
            sum = v1 + v2;
            if (flag){
                flag = false;
                sum++;
            }
            if (sum >= 10){
                flag = true;
                sum %= 10;
            }
            tempNode.next = new ListNode(sum);
            tempNode = tempNode.next;
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }
        return dummy.next.next;
    }
}
