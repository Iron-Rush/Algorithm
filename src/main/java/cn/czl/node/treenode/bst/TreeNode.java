package cn.czl.node.treenode.bst;

/**
 * @author RedRush
 * @date 2020/9/15 10:31
 * @description:
 *          Definition for a binary tree node.
 */
public class TreeNode { int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
        }
}
