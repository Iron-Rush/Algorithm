package cn.czl.node.listnode;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/11/10 16:36
 * @description:
 *      445. 两数相加 II
 *      - 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
 *      它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *      - 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *      - 进阶： 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *      示例：
 *          输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 *          输出：7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbers2_Normal {

    @Test
    public void TestSolution(){
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode res = addTwoNumbers2(l1, l2);
        while (res != null){
            System.out.print(res.val);
            res = res.next;
        }
    }

    /**
     * 通过栈倒置原链表中的数组，在通过栈弹出，计算-生成新链表
     * 执行用时：4 ms, 在所有 Java 提交中击败了79.33%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了86.80%的用户
     * */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1, temp2 = l2;
        ListNode sumNode = new ListNode();
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        boolean flag = false;
        while (temp1 != null){
            stack1.add(temp1);
            temp1 = temp1.next;
        }
        while (temp2 != null){
            stack2.add(temp2);
            temp2 = temp2.next;
        }
        while (!stack1.isEmpty() || !stack2.isEmpty() || flag){
            int sum = 0;
            int v1 = stack1.isEmpty() ? 0 : stack1.pop().val;
            int v2 = stack2.isEmpty() ? 0 : stack2.pop().val;
            sum = v1 + v2;
            if (flag){
                flag = false;
                sum++;
            }
            if (sum >= 10){
                flag = true;
                sum %= 10;
            }
            ListNode curNode = new ListNode(sum);
            ListNode temp = sumNode.next;
            curNode.next = temp;
            sumNode.next = curNode;
        }
        return sumNode.next;
    }

    /**
     * 通过栈倒置原链表中的数组，在通过栈弹出，计算-生成新链表(新节点生成，传递指针优化)
     * 执行用时：4 ms, 在所有 Java 提交中击败了79.33%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了86.80%的用户
     * */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode temp1 = l1, temp2 = l2;
        ListNode sumNode = null;
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        boolean flag = false;
        while (temp1 != null){
            stack1.add(temp1);
            temp1 = temp1.next;
        }
        while (temp2 != null){
            stack2.add(temp2);
            temp2 = temp2.next;
        }
        while (!stack1.isEmpty() || !stack2.isEmpty() || flag){
            int sum = 0;
            int v1 = stack1.isEmpty() ? 0 : stack1.pop().val;
            int v2 = stack2.isEmpty() ? 0 : stack2.pop().val;
            sum = v1 + v2;
            if (flag){
                flag = false;
                sum++;
            }
            if (sum >= 10){
                flag = true;
                sum %= 10;
            }
            ListNode curNode = new ListNode(sum);
            curNode.next = sumNode;
            sumNode = curNode;
        }
        return sumNode;
    }
}
