package cn.czl.node.listnode;

/**
 * @author RedRush
 * @date 2020/10/13 17:41
 * @description:
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
