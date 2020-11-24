package cn.czl.node.listnode.unique;

import org.junit.jupiter.api.Test;

import java.util.List;

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
        ListNode res = sortList3(head);

        while (res != null){
            System.out.print(res.val + "->");
            res = res.next;
        }
    }

    /**
     * 插入排序
     * 执行用时：748 ms, 在所有 Java 提交中击败了5.30%的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了65.27%的用户
     * */
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

    /**
     * 归并排序(递归实现),分割链表 -> 从下向上合并链表
     * 执行用时：6 ms, 在所有 Java 提交中击败了74.37%的用户
     * 内存消耗：46.8 MB, 在所有 Java 提交中击败了24.04%的用户
     * */
    public ListNode sortList2(ListNode head) {
        if(head == null || head.next == null){  // 递归基本情况
            return head;
        }
        // 快慢指针，分割链表
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;
        ListNode lNode = sortList2(head);   // 获取排序后的左链表
        ListNode rNode = sortList2(head2);  // 获取排序后的右链表
        ListNode dummy = new ListNode();    // 设置虚拟表头，合并链表
        ListNode pos = dummy;
        while (lNode != null && rNode != null){
            if (lNode.val < rNode.val){
                pos.next = lNode;
                lNode = lNode.next;
            }else {
                pos.next = rNode;
                rNode = rNode.next;
            }
            pos = pos.next;
        }
        pos.next = lNode == null ? rNode : lNode;
        return dummy.next;
    }

    /**
     * 归并排序(迭代实现 - 从底至顶直接合并)
     * 执行用时：10 ms, 在所有 Java 提交中击败了32.25%的用户
     * 内存消耗：46.6 MB, 在所有 Java 提交中击败了37.92%的用户
     * */
    public ListNode sortList3(ListNode head) {
        ListNode dummy = new ListNode(0, head);// 初始化虚拟节点
        int length = getListLength(head);   // 计算head长度
        for (int subLen = 1; subLen < length; subLen <<= 1) {
            ListNode prev = dummy;
            ListNode pos = dummy.next;
            while (pos != null){
                ListNode head1 = pos;   // 拆分第一段长为sublen的链表
                for (int i = 1; i < subLen && pos != null && pos.next != null; i++) {
                    pos = pos.next;
                }
                ListNode head2 = pos.next;// 拆分第二段长为sublen的链表
                pos.next = null;    // 断开两段链表
                pos = head2;
                for (int i = 1; i < subLen && pos != null && pos.next != null; i++) {
                    pos = pos.next;
                }
                ListNode next = null;   // 断开第二段链表
                if(pos != null){
                    next = pos.next;
                    pos.next = null;
                }
                pos = next;
                // 合并两段链表
                ListNode merged = mergeTwoList(head1, head2);
                prev.next = merged;
                while (prev.next != null){
                    prev = prev.next;
                }
            }
        }
        return dummy.next;
    }
    // 合并两个链表
    ListNode mergeTwoList(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode();
        ListNode pos = dummy;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                pos.next = l1;
                l1 = l1.next;
            }else {
                pos.next = l2;
                l2 = l2.next;
            }
            pos = pos.next;
        }
        pos.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
    // 计算链表长度
    int getListLength(ListNode head){
        int count = 0;
        while (head != null){
            head = head.next;
            count++;
        }
        return count;
    }

}
