package cn.czl.node.treenode.search;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/9/22 14:53
 * @description:
 *      124.二叉树中的最大路径和
 *          - 给定一个非空二叉树，返回其最大路径和。
 *          - 本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *      示例 1：
 *          输入：[1,2,3]
 *          输出：6
 *             1
 *            / \
 *           2   3
 *      示例 2：
 *          输入：[-10,9,20,null,null,15,7]
 *          输出：42
 *             -10
 *             / \
 *            9  20
 *              /  \
 *             15   7
 */
public class MaxPathSum_Hard {

    @Test
    public void TestSolution(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(maxPathSum(root));
    }

    /**
     * 中序遍历(递归实现)-记录当前最佳路径；返回当前最长分支
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.90%的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了66.42%的用户
     * */
    int sum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs_FindPath(root);
        return sum;
    }
    private int dfs_FindPath(TreeNode root){
        if (root == null){
            return 0;
        }
        int leftVal = Math.max(dfs_FindPath(root.left), 0);
        int rightVal = Math.max(dfs_FindPath(root.right), 0);
        sum = Math.max(sum, leftVal + rightVal + root.val);
        return Math.max(rightVal, leftVal) + root.val;
    }

//    public int maxPathSum2(TreeNode root) {
//        Stack<TreeNode> stack = new Stack<>();
//        stack.add(root);
//        while (!stack.isEmpty()){
//            TreeNode tempNode = stack.pop();
//            if (tempNode == null){
//                tempNode = stack.pop();
//
//            }else {
//
//            }
//        }
//        return sum;
//    }
}
