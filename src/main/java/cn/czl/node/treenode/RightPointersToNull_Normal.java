
package cn.czl.node.treenode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/10/15 9:28
 * @description:
 *      116. 填充每个节点的下一个右侧节点指针
 *          - 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。
 *          - 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 *          如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *          - 初始状态下，所有 next 指针都被设置为 NULL。
 */
public class RightPointersToNull_Normal {


    /**
     * 递归实现
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了97.66%的用户
     * */
    public Node connect(Node root) {
        if(root == null || root.right == null){
            return root;
        }
        root.left.next = root.right;    // 设置左分支的next指针
        Node next = root.next;          // 获取当前的next指针
        // 若当前next指针为空，则说明当前节点位于该层的最右侧，该节点的右分支next也指向空
        // 否则，右指针next指针指向，当前节点->next节点->左分支
        root.right.next = next == null ? null : next.left;
        connect(root.right);    // 先关联左/右分支一样
        connect(root.left);
        return root;
    }

    /**
     * BFS,广度优先搜索
     * 执行用时：3 ms, 在所有 Java 提交中击败了45.62%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了95.66%的用户
     * */
    public Node connect2(Node root){
        Queue<Node> queue = new LinkedList<>();
        if(root != null){
            queue.add(root);
        }
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

    /**
     * 把每层看作链表处理
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了99.83%的用户
     * */
    public Node connect3(Node root){
        if (root == null){
            return root;
        }
        Node curNode = root;    // 每层的链表
        while (curNode != null){
            Node dummy = new Node();    // 每层前端添加哑节点
            Node preNode = dummy;       // 访问下一层的前一个节点
            // 通过遍历当前层的子节点，实现联结下一层的节点
            while (curNode != null && curNode.left != null){
                preNode.next = curNode.left;
                preNode = preNode.next;
                preNode.next = curNode.right;
                preNode = preNode.next;
                curNode = curNode.next;     // 继续访问当前层下一个节点
            }
            curNode = dummy.next;       // 获取下一层起始节点
        }
        return root;
    }

}
