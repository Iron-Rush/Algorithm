package cn.czl.node.treenode.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2021/11/4 13:56
 * @description:
 *      938. 二叉搜索树的范围和
 *      - 给定二叉搜索树的根结点 root，返回值位于范围 [low, high]
 *          之间的所有结点的值的和。
 *      示例 1：
 *          输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
 *          输出：32
 *      示例 2：
 *          输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 *          输出：23
 *      提示：
 *          树中节点数目在范围 [1, 2 * 10^4] 内
 *          1 <= Node.val <= 10^5
 *          1 <= low <= high <= 10^5
 *          所有 Node.val 互不相同
 */
public class RangeSumBST_Easy {

    /**
     * 递归 叠加
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 46.6 MB , 在所有 Java 提交中击败了 31.22% 的用户
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        int ans = 0;
        int cur = root.val;
        if (cur < low) {
            ans = rangeSumBST(root.right, low, high);
        } else if (cur > high) {
            ans = rangeSumBST(root.left, low, high);
        } else {
            ans = cur;
            ans += rangeSumBST(root.left, low, high);
            ans += rangeSumBST(root.right, low, high);
        }
        return ans;
    }

    /**
     * 借助队列 - 迭代
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 19.60% 的用户
     * 内存消耗： 43.7 MB , 在所有 Java 提交中击败了 97.71% 的用户
     */
    public int rangeSumBST2(TreeNode root, int low, int high) {
        if (root == null) return 0;
        int ans = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode curNode = queue.poll();
            if(curNode == null){
                continue;
            }
            int curVal = curNode.val;
            if (curVal < low){
                queue.offer(curNode.right);
            }else if (curVal > high){
                queue.offer(curNode.left);
            }else{
                ans += curVal;
                queue.offer(curNode.left);
                queue.offer(curNode.right);
            }
        }
        return ans;
    }
}