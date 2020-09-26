package cn.czl.node.treenode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/9/25 10:27
 * @description:
 *      104.二叉树的最大深度
 *      二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 */
public class MaxDepth_Easy {
    @Test
    public void TestSolution(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(maxDepth3(root));
    }

    /**
     * dfs择优递归
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了29.73%的用户
     * */
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int Rdepth = maxDepth(root.right);
        int Ldepth = maxDepth(root.left);
        return (Rdepth > Ldepth ? Rdepth : Ldepth)+1;
    }
    /**
     * dfs精简版
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了20.72%的用户
     * */
    public int maxDepth2(TreeNode root) {
        if (root == null){
            return 0;
        }
        return (Math.max(maxDepth2(root.right), maxDepth2(root.left)))+1;
    }
    /**
     * bfs广度优先搜索
     * 执行用时：1 ms, 在所有 Java 提交中击败了21.24%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了21.58%的用户
     * */
    public int maxDepth3(TreeNode root) {
        if (root == null){
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode tempNode = queue.poll();
                if (tempNode.right != null){
                    queue.add(tempNode.right);
                }
                if (tempNode.left != null){
                    queue.add(tempNode.left);
                }
            }
            depth++;
        }
        return depth;
    }
}
