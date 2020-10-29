package cn.czl.node.treenode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/10/29 9:14
 * @description:
 *      129. 求根到叶子节点数字之和
 *      - 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，
 *      每条从根到叶子节点的路径都代表一个数字。
 *      - 例如，从根到叶子节点路径 `1->2->3` 代表数字 123。
 *      - 计算从根到叶子节点生成的所有数字之和。
 *      - 说明: 叶子节点是指没有子节点的节点。
 *      示例 1:
 *          输入: [1,2,3]
 *              1
 *             / \
 *            2   3
 *          输出: 25（12+13=25）
 */
public class SumRootToLeafNumbers_Normal {
    @Test
    public void TestSolution(){
//        TreeNode treeNode = new TreeNode(1);
//        treeNode.left = new TreeNode(2);
//        treeNode.right = new TreeNode(3);
        TreeNode treeNode = new TreeNode(4);
//        treeNode.left = new TreeNode(9);
//        treeNode.right = new TreeNode(0);
//        treeNode.left.left = new TreeNode(5);
//        treeNode.left.right = new TreeNode(1);
        System.out.println(sumNumbers(treeNode));
    }

    /**
     * 深度优先搜索(递归)
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了96.58%的用户
     * */
    public int sumNumbers(TreeNode root) {
        if (root == null){
            return 0;
        }
        return dfs(root, 0);
    }
    public int dfs(TreeNode root, int preSum){
        if (root.left == null && root.right == null){
            return root.val + preSum*10;
        }
        int lSum = 0, rSum = 0;
        if (root.left != null){
            lSum = dfs(root.left, root.val + preSum*10);
        }
        if (root.right != null){
            rSum = dfs(root.right, root.val + preSum*10);
        }
        return lSum+rSum;
    }

    /**
     * BFS,广度优先搜索实现
     * 执行用时：1 ms, 在所有 Java 提交中击败了31.04%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了88.05%的用户
     * */
    public int sumNumbers2(TreeNode root){
        if(root == null){
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> numQueue = new LinkedList<>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        while (!nodeQueue.isEmpty()){
            TreeNode curNode = nodeQueue.poll();
            int curVal = numQueue.poll();
            if (curNode.left == null && curNode.right == null){
                sum += curVal;
            }else {
                if (curNode.left != null){
                    nodeQueue.offer(curNode.left);
                    numQueue.offer(curVal*10 + curNode.left.val);
                }
                if (curNode.right != null){
                    nodeQueue.offer(curNode.right);
                    numQueue.offer(curVal*10 + curNode.right.val);
                }
            }
        }
        return sum;
    }

}
