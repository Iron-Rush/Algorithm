package cn.czl.node.listnode.unique;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/11/23 17:49
 * @description:
 *      148. 排序链表
 *      - 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *      示例 1：
 *          输入：head = [4,2,1,3]
 *          输出：[1,2,3,4]
 *      示例 2：
 *          输入：head = [-1,5,3,4,0]
 *          输出：[-1,0,3,4,5]
 */
public class SortList_Normal {

    @Test
    public void TestSolution(){
        ListNode head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);

    }

    public ListNode sortList(ListNode head) {
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
