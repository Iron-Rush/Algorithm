package cn.czl.node.listnode;

/**
 * @author RedRush
 * @date 2020/10/13 17:30
 * @description:
 *      24. 两两交换链表中的节点
 *          给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *          你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
 *      示例 1：
 *          输入：head = [1,2,3,4]     输出：[2,1,4,3]
 *      示例 2：
 *          输入：head = []            输出：[]
 *      示例 3：
 *          输入：head = [1]           输出：[1]
 *      提示：
 *          链表中节点的数目在范围 [0, 100] 内
 *          0 <= Node.val <= 100
 */
public class SwapNodesInPairs_Normal {

    /**
     * 递归遍历
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了99.51%的用户
     * */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newNode = head.next;           // 新节点为当前节点的下一个节点
        head.next = swapPairs(newNode.next);    // 当前节点下一个节点的指针，指向当前节点的下下个节点(递归得到)
        newNode.next = head;                    // 新节点的下一个节点指向当前节点
        return newNode;
    }

    /**
     * 迭代遍历
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了98.40%的用户
     * */
    public ListNode swapPairs2(ListNode head) {
        ListNode resNode = new ListNode();
        resNode.next = head;
        ListNode temp = resNode;
        while (temp.next != null && temp.next.next != null ){
            ListNode next = temp.next;
            ListNode next2 = temp.next.next;
            temp.next = next2;      // 前驱结点的指针，指向下面第二个节点
            next.next = next2.next; // 下面第一个节点的指针，指向下面第二个节点后面节点(下面第三个节点)
            next2.next = next;      // 下面第二个节点的指针，指向下面第一个节点
            temp = next;            // 移动前驱节点
        }
        return resNode.next;
    }


}
