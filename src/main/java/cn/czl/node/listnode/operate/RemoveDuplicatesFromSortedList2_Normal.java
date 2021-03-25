package cn.czl.node.listnode.operate;

/**
 * @author RedRush
 * @date 2021/3/25 13:27
 * @description:
 *      82. 删除排序链表中的重复元素 II
 *      - 存在一个按升序排列的链表，给你这个链表的头节点 head ，
 *        请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 *      - 返回同样按升序排列的结果链表。
 *      示例 1：
 *          输入：head = [1,2,3,3,4,4,5]
 *          输出：[1,2,5]
 *      示例 2：
 *          输入：head = [1,1,1,2,3]
 *          输出：[2,3]
 *      提示：
 *          - 链表中节点数目在范围 [0, 300] 内
 *          - -100 <= Node.val <= 100
 *          题目数据保证链表已经按升序排列
 */
public class RemoveDuplicatesFromSortedList2_Normal {


    /**
     * 判断当前节点后面是否有重复的，
     * 如果存在则一次性向后去重
     * 否则，将前驱有效节点next指针指向该节点
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 7.38% 的用户
     * */
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = dummy.next;  // 游标
        ListNode pre = dummy;       // 存储前一个有效节点
        while (cur != null && cur.next != null){
            if(cur.val == cur.next.val){    // 存在重复节点的话，则移动游标跳过此段链表
                int curVal = cur.val;
                while (cur != null && cur.val == curVal){
                    cur = cur.next;
                }
            }else {
                pre.next = cur;
                pre = cur;
                cur = cur.next;
            }
        }
        pre.next = cur;     // 处理最后一个节点。如果末尾节点为重复节点，则此时cur已为null
        return dummy.next;
    }

    /**
     * 递归解
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 10.64% 的用户
     * */
    public ListNode deleteDuplicates(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        if(head.val != head.next.val){
            head.next = deleteDuplicates(head.next);
        }else {
            int curVal = head.val;
            while (head != null && head.val == curVal){
                head = head.next;
            }
            // if(head != null){
                head = deleteDuplicates(head);
            // }
        }
        return head;
    }
}
