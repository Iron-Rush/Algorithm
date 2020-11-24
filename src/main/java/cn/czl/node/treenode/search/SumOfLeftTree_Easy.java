package cn.czl.node.treenode.search;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/9/19 15:10
 * @description:
 *      404. 左叶子之和
 *          计算给定二叉树的所有左叶子之和。
 *      示例：
 *              3
 *             / \
 *            9  20
 *              /  \
 *             15   7
 *      在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 */
public class SumOfLeftTree_Easy {

    @Test
    public void TestSolution(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(sumOfLeftLeaves(root));
    }

    /**
     * dfs深度优先搜索
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了93.17%的用户
     * */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null){
            return 0;
        }
        int sum = 0;
        sum = dfs(root, sum, false);
        return sum;
    }
    public int dfs(TreeNode curNode, int sum, boolean flag){
        if (curNode.left != null){  // 将现有数字和，下传，进行累加
            sum = dfs(curNode.left, sum, true);
        }
        if (curNode.right != null){
            sum = dfs(curNode.right, sum, false);
        }
        if (flag && curNode.left == null && curNode.right == null){
            sum += curNode.val;
        }
        return sum;
    }

    /**
     * 极简-直接递归
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了28.01%的用户
     * */
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null){
        return 0;
        }
        return sumOfLeftLeaves2(root.left)
                + sumOfLeftLeaves2(root.right)
                + ((root.left != null && root.left.left == null && root.left.right == null) ? root.left.val : 0);
    }

}
