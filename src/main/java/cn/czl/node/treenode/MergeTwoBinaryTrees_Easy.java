package cn.czl.node.treenode;

import cn.czl.node.treenode.demo.TraverseTreeNode_DFS_Demo;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/9/23 9:31
 * @description:
 *      617.合并二叉树
 *          1.给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *          2.你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，
 *          那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 *      输入:
 *          	Tree 1                     Tree 2
 *                    1                         2
 *                   / \                       / \
 *                  3   2                     1   3
 *                 /                           \   \
 *                5                             4   7
 *      输出:
 *          合并后的树:
 *          	     3
 *          	    / \
 *          	   4   5
 *          	  / \   \
 *          	 5   4   7
 */
public class MergeTwoBinaryTrees_Easy {

    @Test
    public void TestSolution(){
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(3);
        t1.left.left = new TreeNode(5);
        t1.right = new TreeNode(2);
        TreeNode t2 = new TreeNode(2);
        t2.left = new TreeNode(1);
        t2.right = new TreeNode(3);
        t2.left.right = new TreeNode(4);
        t2.right.right = new TreeNode(7);
        TreeNode root = mergeTrees(t1, t2);
        DFS(root);
    }

    /**
     * DFS-递归合并树至t1
     * 执行用时：1 ms, 在所有 Java 提交中击败了63.86%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了25.72%的用户
     * */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 != null && t2 !=null){
            t1.val = t1.val + t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
        }else {
            t1 = t1 == null ? t2 : t1;
        }
        return t1;
    }

    /**
     * DFS-遇见空则返回另一树分支，否则合并两树分支
     * 执行用时：1 ms, 在所有 Java 提交中击败了63.86%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了30.69%的用户
     * */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees2(t1.left, t2.left);
        merged.right = mergeTrees2(t1.right, t2.right);
        return merged;
    }

    /**
     * BFS-左右枝分开处理
     * 执行用时：2 ms, 在所有 Java 提交中击败了7.28%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了56.69%的用户
     * */
    public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null){
            return t1 == null ? t2 : t1;
        }
        TreeNode curNode = new TreeNode(t1.val + t2.val);
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue1.offer(t1);
        queue2.offer(t2);
        queue.offer(curNode);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node = queue.poll(), node1 = queue1.poll(), node2 = queue2.poll();
            TreeNode left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
            if (left1 != null && left2 != null) {
                TreeNode left = new TreeNode(left1.val + left2.val);
                node.left = left;
                queue.offer(left);
                queue1.offer(left1);
                queue2.offer(left2);
            }else{
                node.left = left1 == null ? left2 : left1;
            }
            if (right1 != null && right2 != null) {
                TreeNode right = new TreeNode(right1.val + right2.val);
                node.right = right;
                queue.offer(right);
                queue1.offer(right1);
                queue2.offer(right2);
            }else {
                node.right = right1 == null ? right2 : right1;
            }
        }
        return curNode;
    }

    private void DFS(TreeNode root) {
        if (root == null)
            return;
        System.out.println(root.val);
        DFS(root.left);
        DFS(root.right);
    }
}
