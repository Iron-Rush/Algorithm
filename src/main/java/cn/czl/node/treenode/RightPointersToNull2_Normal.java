package cn.czl.node.treenode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/10/15 12:20
 * @description:
 *      117. 填充每个节点的下一个右侧节点指针 II
 *          - 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 *          如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *          - 初始状态下，所有 next 指针都被设置为 NULL。
 */
public class RightPointersToNull2_Normal {

    /**
     * 把每层当作链表处理
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了99.84%的用户
     * */
    public Node connect(Node root) {
        if (root == null){
            return root;
        }
        Node curRoot = root;        // 当前层链表
        while (curRoot != null){
            Node dummy = new Node();// 下一层链表表头
            Node pos = dummy;       // 下一层链表指针
            while (curRoot != null){// 根据当前层链表的左右分支，生成下一层链表
                if (curRoot.left != null){
                    pos.next = curRoot.left;
                    pos = pos.next;
                }
                if (curRoot.right != null){
                    pos.next = curRoot.right;
                    pos = pos.next;
                }
                curRoot = curRoot.next;
            }
            curRoot = dummy.next;
        }
        return root;
    }

    /**
     * BFS-广度优先搜索
     * 执行用时：2 ms, 在所有 Java 提交中击败了56.26%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了99.92%的用户
     * */
    public Node connect2(Node root){
        if (root == null){
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curNode = queue.poll();
                if (i != size-1){
                    curNode.next = queue.peek();
                }
                if (curNode.left != null){
                    queue.add(curNode.left);
                }
                if (curNode.right != null){
                    queue.add(curNode.right);
                }
            }
        }
        return root;
    }

}
