package cn.czl.node.listnode.operate;

/**
 * @author RedRush
 * @date 2020/12/18 9:56
 * @description:
 *      83. 删除排序链表中的重复元素
 *      - 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *      示例 1:
 *          输入: 1->1->2
 *          输出: 1->2
 *      示例 2:
 *          输入: 1->1->2->3->3
 *          输出: 1->2->3
 */
public class DeleteDuplicatesFromSortedList_Easy {


    /**
     * 递归删除重复节点。[先递归删除，再处理当前节点]
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.6 MB , 在所有 Java 提交中击败了 93.88% 的用户
     * */
    public ListNode deleteDuplicates(ListNode head) {
        if(head != null && head.next != null){
            deleteDuplicates(head.next);
            if(head.val == head.next.val){
                head.next = head.next.next;
            }
        }
        return head;
    }

    /**
     * 迭代 - 删除重复节点。
     * [如果当前节点与下一节点val相同，则next指针指向下下个节点；否则游标后移]
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.9 MB , 在所有 Java 提交中击败了 47.73% 的用户
     * */
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode cur = head;
        while(cur != null && cur.next != null){
            if(cur.val == cur.next.val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return head;
    }
}
