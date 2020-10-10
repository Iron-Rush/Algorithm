package cn.czl.linkedList.Cycle;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author RedRush
 * @date 2020/10/10 14:35
 * @description:
 *      142. 环形链表 II
 *          给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *          说明：不允许修改给定的链表。
 */
public class LinkedListHasCycle2_Normal {

    @Test
    public void TestSolution(){
        ListNode root = new ListNode(3);
        root.next = new ListNode(2);
//        root.next.next = new ListNode(0);
//        root.next.next.next = new ListNode(-4);
//        root.next.next.next.next = root.next;
        System.out.println(detectCycle2(root));
    }

    /**
     * HashSet判断是否存在已重复节点(若存在，则当前重复节点即为第一个循环节点)
     * 执行用时：4 ms, 在所有 Java 提交中击败了24.40%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了5.09%的用户
     * */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();
        while (head != null){
            if (set.add(head)){
                head = head.next;
            }else {
                return head;
            }
        }
        return head;
    }

    /**
     * 快慢指针(双指针) + 距离推算
     * slow = a + b; fast = a + 2b + c; fast = 2slow;
     * a + 2b + c = 2a + 2b; a = c;
     * => fast/slow相遇后,从head开始与slow同步移动至相遇，即为所求入环点。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了89.81%的用户
     * */
    public ListNode detectCycle2(ListNode head) {
        ListNode slowNode = head, fastNode = head;  // 定义快慢指针
        while (fastNode != null){   // 若停止循环，则说明不存在换，停止循环返回null
            if (fastNode.next == null){
                break;
            }
            fastNode = fastNode.next.next;  // 移动双指针
            slowNode = slowNode.next;
            if (slowNode == fastNode){      // 若两指针相遇，则存在环。
                ListNode posNode = head;    // 此时从head->入环点的距离，等于相遇点到入环点距离
                while (posNode != slowNode){// 同步移动pos和slow，直至相遇
                    posNode = posNode.next;
                    slowNode = slowNode.next;
                }
                return posNode;     // 此时pos/slow即为所求
            }
        }
        return null;
    }

}
