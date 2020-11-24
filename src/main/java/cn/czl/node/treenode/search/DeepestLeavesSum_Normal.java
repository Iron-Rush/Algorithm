package cn.czl.node.treenode.search;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/11/24 16:33
 * @description:
 *      1302. 层数最深叶子节点的和
 *      - 给你一棵二叉树，请你返回层数最深的叶子节点的和。
 */
public class DeepestLeavesSum_Normal {

    @Test
    public void TestSolution(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(7);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(8);
        System.out.println(deepestLeavesSum2(root));
    }

    /**
     * BFS-广度优先搜索
     * 执行用时：5 ms, 在所有 Java 提交中击败了52.47%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了79.03%的用户
     * */
    public int deepestLeavesSum(TreeNode root) {
        if (root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int curSum = 0;
        while (!queue.isEmpty()){
            int curSize = queue.size();
            curSum = 0;
            for (int i = 0; i < curSize; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null){
                    queue.add(curNode.left);
                }
                if (curNode.right != null){
                    queue.add(curNode.right);
                }
                curSum += curNode.val;
            }
        }
        return curSum;
    }

    /**
     * dfs-深度优先搜索。维护最大深度，和总和两个变量。
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.22%的用户
     * 内存消耗：40.4 MB, 在所有 Java 提交中击败了34.63%的用户
     * */
    int maxDepth, sum;
    public int deepestLeavesSum2(TreeNode root) {
        if (root == null){
            return 0;
        }
        dfs(root, 1);
        return sum;
    }
    void dfs(TreeNode root, int depth){
        if (root.left == null && root.right == null){
            if (depth > maxDepth){
                sum = root.val;
                maxDepth = depth;
            }else if (depth == maxDepth){
                sum += root.val;
            }
            return;
        }
        if (root.left != null){
            dfs(root.left, depth + 1);
        }
        if (root.right != null){
            dfs(root.right, depth + 1);
        }
    }

}
