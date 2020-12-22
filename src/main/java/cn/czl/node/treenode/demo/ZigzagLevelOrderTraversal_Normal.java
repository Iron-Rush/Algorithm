package cn.czl.node.treenode.demo;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/12/22 9:24
 * @description:
 *      103. 二叉树的锯齿形层序遍历
 *      - 给定一个二叉树，返回其节点值的锯齿形层序遍历。
 *      （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *      - 例如：
 *      给定二叉树 [3,9,20,null,null,15,7],
 *              3
 *             / \
 *            9  20
 *              /  \
 *             15   7
 *      返回锯齿形层序遍历如下：
 *            [ [3],
 *              [20,9],
 *              [15,7]]
 */
public class ZigzagLevelOrderTraversal_Normal {
    @Test
    public void TestSolution(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> result = zigzagLevelOrder2(root);
        System.out.println(result);
    }


    /**
     * 双向队列     根据flag决定读取/添加节点顺序
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.42% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 68.51% 的用户
     * */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if(root != null){
            deque.add(root);
        }
        boolean flag = true;    // flag为true，从左向右遍历，false为从右向左遍历
        while (!deque.isEmpty()){
            int curSize = deque.size();
            List<Integer> temp = new LinkedList<>();
            Deque<TreeNode> next = new LinkedList<>();
            for (int i = 0; i < curSize; i++) {
                if (flag){
                    TreeNode curNode = deque.pollLast();
                    temp.add(curNode.val);
                    if(curNode.left != null){
                        next.addFirst(curNode.left);
                    }
                    if(curNode.right != null){
                        next.addFirst(curNode.right);
                    }
                }else {
                    TreeNode curNode = deque.pollFirst();
                    temp.add(curNode.val);
                    if(curNode.right != null){
                        next.addLast(curNode.right);
                    }
                    if(curNode.left != null){
                        next.addLast(curNode.left);
                    }
                }
            }
            deque = next;
            result.add(temp);
            flag = !flag;
        }
        return result;
    }

    /**
     * 直接使用栈实现
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.42% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 65.73% 的用户
     * */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root != null){
            stack.add(root);
        }
        boolean flag = true;    // flag为true，从左向右遍历，false为从右向左遍历
        while (!stack.isEmpty()){
            List<Integer> temp = new LinkedList<>();
            Stack<TreeNode> next = new Stack<>();
            while (!stack.isEmpty()){
                TreeNode curNode = stack.pop();
                temp.add(curNode.val);
                if (flag){
                    if(curNode.left != null){
                        next.add(curNode.left);
                    }
                    if(curNode.right != null){
                        next.add(curNode.right);
                    }
                }else {
                    if(curNode.right != null){
                        next.add(curNode.right);
                    }
                    if(curNode.left != null){
                        next.add(curNode.left);
                    }
                }
            }
            stack = next;
            result.add(temp);
            flag = !flag;
        }
        return result;
    }

    /**
     * BFS-层序遍历后，翻转奇数层
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.42% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 72.90% 的用户
     * */
    public List<List<Integer>> zigzagLevelOrder3(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        boolean flag = false;
        if(root != null){
            queue.offer(root);
        }
        while (!queue.isEmpty()){
            int curSize = queue.size();
            List<Integer> curList = new ArrayList<>();
            for (int i = 0; i < curSize; i++) {
                TreeNode curNode = queue.poll();
                curList.add(curNode.val);
                if(curNode.left != null){
                    queue.offer(curNode.left);
                }
                if(curNode.right != null){
                    queue.offer(curNode.right);
                }
            }
            if(flag){
                Collections.reverse(curList);
            }
            flag = !flag;
            result.add(curList);
        }
        return result;
    }
}
