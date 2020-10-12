package cn.czl.node.treenode.bst;

import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author RedRush
 * @date 2020/10/12 10:21
 * @description:
 *      530. 二叉搜索树的最小绝对差
 *          给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 */
public class GetMinDiffBST_Easy {

    @Test
    public void TestSolution(){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        System.out.println(getMinimumDifference(root));
    }

    /**
     * dfs深度优先搜索
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了38.44%的用户
     * */
    int minDiff = Integer.MAX_VALUE;
    int pre = -1;
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return minDiff;
    }
    private void dfs(TreeNode curNode){
        if (curNode == null){
            return;
        }
        dfs(curNode.left);
        if (pre == -1){
            pre = curNode.val;
        }else {
            minDiff = Math.min(minDiff, curNode.val - pre);
            pre = curNode.val;
        }
        dfs(curNode.right);
    }
}
