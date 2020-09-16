package cn.czl.node.treenode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/9/16 9:53
 * @description:
 *      226. 翻转二叉树
 *      示例：
 *      输入：
 *           4
 *         /   \
 *        2     7
 *       / \   / \
 *      1   3 6   9
 *      输出：
 *           4
 *         /   \
 *        7     2
 *       / \   / \
 *      9   6 3   1
 */
public class InvertTree_Easy {
    /**
     * DFS,深度优先搜索实现
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了6.74%的用户
     * */
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return root;
        }
        TreeNode resNode = new TreeNode(root.val);
        dfs_Invert(root, resNode);
        return resNode;
    }
    public void dfs_Invert(TreeNode root, TreeNode pos){
        if (root.left != null){
            pos.right = new TreeNode(root.left.val);
            dfs_Invert(root.left, pos.right);
        }
        if (root.right != null){
            pos.left = new TreeNode(root.right.val);
            dfs_Invert(root.right, pos.left);
        }
    }

    /**
     * 递归回溯，直接交换左右分支
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了31.27%的用户
     * */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null){
            return root;
        }
        TreeNode left = invertTree2(root.right);
        TreeNode right = invertTree2(root.left);
        root.right = right;
        root.left = left;
        return root;
    }

    /**
     * Test-main
     * */
    @Test
    public void TestSolution()
    {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        p.left.left = new TreeNode(4);
        p.left.right = new TreeNode(5);
        p.right.right = new TreeNode(6);

        invertTree(p);
    }
}
