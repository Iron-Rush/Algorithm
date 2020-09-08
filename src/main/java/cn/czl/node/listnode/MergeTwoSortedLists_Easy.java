package cn.czl.node.listnode;

/**
 * @author RedRush
 * @date 2020/9/8 18:10
 * @description:
 */
public class MergeTwoSortedLists_Easy {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode();
        while (l1.next != null && l2.next != null){
            int v1 = l1.val;
            int v2 = l2.val;
            if (v1 < v2){
                listNode.val = v1;

            }
        }
        return listNode;
    }

    public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
