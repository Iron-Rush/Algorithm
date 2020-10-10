package cn.czl.linkedList.Cycle;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

/**
 * @author RedRush
 * @date 2020/10/10 13:36
 * @description:
 *      141. 环形链表: 给定一个链表，判断链表中是否有环。
 *      如果链表中存在环，则返回 true 。 否则，返回 false 。
 */
public class LinkedListHasCycle_Easy {

    @Test
    public void TestSolution(){
        ListNode root = new ListNode(3);
        root.next = new ListNode(2);
        root.next.next = new ListNode(0);
        root.next.next.next = new ListNode(-4);
//        root.next.next.next.next = root.next;
        System.out.println(hasCycle(root));
    }

    /**
     * 通过HashSet判断，是否已存在相同元素
     * 执行用时：4 ms, 在所有 Java 提交中击败了22.24%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了24.14%的用户
     * */
    public boolean hasCycle(ListNode head) {
        HashSet set = new HashSet();
        while (head != null){       // 若head为null，说明遍历至链尾，则不为循环链表
            if (set.add(head)){     // 通过将当前节点存入set，判断set中是否已有重复节点
                head = head.next;
            }else {
                return true;
            }
        }
        return false;
    }

    /**
     * 快慢指针判断(双指针)
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了89.48%的用户
     * */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null){
            return false;
        }
        ListNode fastNode = head.next;
        ListNode slowNode = head;
        // 若两指针相遇，则说明快指针追上慢指针(套圈)，则存在循环
        while (slowNode != fastNode){
            // 若快指针抵达null，则说明存在链为，则不存在循环
            if (fastNode == null || fastNode.next == null){
                return false;
            }
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        return true;
    }

}
