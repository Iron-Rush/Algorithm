package cn.czl.node.treenode;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2021/11/8 14:51
 * @description:
 *      107. 二叉树的层序遍历 II
 *      - 给定一个二叉树，返回其节点值自底向上的层序遍历。
 *       （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *      例如：
 *          给定二叉树 [3,9,20,null,null,15,7],
 *              3
 *             / \
 *            9  20
 *              /  \
 *             15   7
 *          返回其自底向上的层序遍历为：
 *          [ [15,7],
 *            [9,20],
 *            [3]]
 */
public class LevelOrderBottom_Normal {

    /**
     * 每层遍历结果放入List,将List放入栈，从底层向上层遍历
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 94.77% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 51.16% 的用户
     * */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null)   return ans;
        Stack<List<TreeNode>> stack = new Stack<>();
        List<TreeNode> l = new LinkedList<>();
        l.add(root);
        stack.push(l);
        while (!stack.isEmpty()){
            List<TreeNode> curList = stack.peek();
            List<TreeNode> nextList = new LinkedList<>();
            for(TreeNode node : curList){
                if (node.left != null)  nextList.add(node.left);
                if (node.right != null)  nextList.add(node.right);
            }
            if(nextList.size() == 0)    break;
            stack.push(nextList);
        }
        while (!stack.isEmpty()) {
            List<TreeNode> nodeList = stack.pop();
            List<Integer> list = new LinkedList<>();
            for(TreeNode node : nodeList){
                list.add(node.val);
            }
            ans.add(list);
        }
        return ans;
    }

    /**
     * 将每层遍历结果插入到结果集首位
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 94.77% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 74.05% 的用户
     * */
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null)   return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> list = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                list.add(treeNode.val);
                if(treeNode.left != null)   queue.offer(treeNode.left);
                if(treeNode.right != null)   queue.offer(treeNode.right);
            }
            ans.add(0, list);
        }
        return ans;
    }
}
