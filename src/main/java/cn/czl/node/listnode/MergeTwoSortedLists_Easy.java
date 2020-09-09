package cn.czl.node.listnode;

/**
 * @author RedRush
 * @date 2020/9/8 18:10
 * @description:
 *      21.合并两个有序链表
 *      - 输入：1->2->4, 1->3->4
 *      - 输出：1->1->2->3->4->4
 */
public class MergeTwoSortedLists_Easy {
    /**
     * 比较并抽取节点存至新链表中
     * 执行用时：1 ms, 在所有 Java 提交中击败了63.55%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了35.16%的用户
     * */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode listNodeHead = new ListNode(-1);   // 初始化链表头
        ListNode prev = listNodeHead;           // 初始化链表指针
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                prev.next = l1;
                l1 = l1.next;
            }else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        // 循环结束后，最多应有一个链表未被合并完，将指针指向未合并完成链表
        prev.next = l1 == null ? l2 : l1;
        return listNodeHead.next;
    }

    /**
     * 递归实现
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了28.96%的用户
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

    public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
