package cn.czl.node.listnode;

import java.util.List;

/**
 * @author RedRush
 * @date 2020/11/20 16:36
 * @description:
 *      147. 对链表进行插入排序
 *      插入排序算法：
 *          1. 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 *          2. 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 *          3. 重复直到所有输入数据插入完为止。
 */
public class InsertionSortList_Normal {

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.70%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了14.04%的用户
     * */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode curSorted = head, curPos = head.next;
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        while (curPos != null){
            if (curSorted.val <= curPos.val){
                curSorted = curSorted.next;
            }else {
                ListNode pre = dummyNode;
                while (pre.next.val <= curPos.val){
                    pre = pre.next;
                }
                curSorted.next = curPos.next;
                curPos.next = pre.next;
                pre.next = curPos;
            }
            curPos = curSorted.next;
        }
        return dummyNode.next;
    }
}
