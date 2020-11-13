package cn.czl.node.treenode;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/11/13 17:06
 * @description:
 *      112. 路径总和
 *      - 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，
 *      这条路径上所有节点值相加等于目标和。
 *      - 说明: 叶子节点是指没有子节点的节点。
 */
public class HasPathSum_Easy {

    @Test
    public void TestSolution(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(hasPathSum(root, 0));
    }

    /**
     * 递归，自顶向下解决问题
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了84.97%的用户
     * */
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            if(sum == root.val){
                return true;
            }else{
                return false;
            }
        }
        boolean lFlag = false, rFlag = false;
        int rest = sum - root.val;
        if(root.left != null){
            lFlag = hasPathSum(root.left, rest);
        }
        if(root.right != null){
            rFlag = hasPathSum(root.right, rest);
        }
        return lFlag || rFlag;
    }
}
