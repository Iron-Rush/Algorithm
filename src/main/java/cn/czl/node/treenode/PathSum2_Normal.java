package cn.czl.node.treenode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/9/26 10:55
 * @description:
 *      113. 路径总和 II:
 *          - 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *          - 说明: 叶子节点是指没有子节点的节点。
 *      事例:
 *          给定如下二叉树，以及目标和 sum = 22
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 *          返回:
 *          [
 *             [5,4,11,2],
 *             [5,8,4,5]
 *          ]
 */
public class PathSum2_Normal {

    /**
     * dfs，深度优先搜索
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了9.64%的用户
     * */
    List<List<Integer>> resList = new ArrayList<>();
    List<Integer> tempList = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root != null){
            dfs_findpath(root, sum, 0);
        }
        return resList;
    }
    private void dfs_findpath(TreeNode root, int target, int curSum){
        if (root.left == null && root.right == null){
            if (curSum + root.val == target){
                tempList.add(root.val);
                resList.add(new ArrayList<>(tempList));
                tempList.remove(tempList.size() - 1);
            }
        }else {
            tempList.add(root.val);
            if (root.left != null){
                dfs_findpath(root.left, target, curSum + root.val);
            }
            if (root.right != null){
                dfs_findpath(root.right, target, curSum + root.val);
            }
            tempList.remove(tempList.size() - 1);
        }
    }

}
