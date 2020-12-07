package cn.czl.node.treenode.bst;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/12/7 13:42
 * @description:
 *      108. 将有序数组转换为二叉搜索树
 *      - 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *      - 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *       示例:
 *          给定有序数组: [-10,-3,0,5,9],
 *          一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *            0
 *           / \
 *         -3   9
 *         /   /
 *       -10  5
 */
public class SortedArrayToBST_Easy {

    private int[] ARRAY1 = {-10, -3, 0, 5, 9};

    @Test
    public void TestSolution(){
        TreeNode root = sortedArrayToBST(ARRAY1);
        printTreeNode(root);
    }

    /**
     * 取数组中点为当前节点，对左右分别递归生成子树
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了89.44%的用户
     * */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0 , nums.length-1);
    }

    TreeNode helper(int[] nums, int start, int end){
        if(start == end){
            return new TreeNode(nums[start]);
        }
        if(start > end){
            return null;
        }
        int cur = (start + end)/2;
        TreeNode root = new TreeNode(nums[cur]);
        root.left = helper(nums, start, cur - 1);
        root.right = helper(nums, cur + 1, end);
        return root;
    }

    void printTreeNode(TreeNode root){
        if (root != null){
            System.out.print(root.val + ",");
            printTreeNode(root.left);
            printTreeNode(root.right);
        }
    }
}
