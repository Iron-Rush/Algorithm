package cn.czl.node.treenode.bst;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2021/4/25 13:10
 * @description:
 *      897. 递增顺序搜索树
 *      - 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，
 *          使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *      示例 1：
 *          输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 *          输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 *      示例 2：
 *          输入：root = [5,1,7]
 *          输出：[1,null,5,null,7]
 *      提示：
 *          树中节点数的取值范围是 [1, 100]
 *          0 <= Node.val <= 1000
 *          通过次数36,783提交次数49,48
 */
public class IncreasingBST_Easy {

    @Test
    public void TestSolution(){

    }

    /**
     * 迭代遍历 - 生成树
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 16.09% 的用户
     * 内存消耗： 36 MB , 在所有 Java 提交中击败了 59.97% 的用户
     * */
    public TreeNode increasingBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode dummy = new TreeNode(1);
        TreeNode head = dummy;
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode curNode = stack.pop();
            if(curNode != null){
                if(curNode.right != null){
                    stack.add(curNode.right);
                }
                stack.add(curNode);
                stack.add(null);
                if(curNode.left != null){
                    stack.add(curNode.left);
                }
            }else{
                TreeNode next = new TreeNode(stack.pop().val);
                head.right = next;
                head = head.right;
            }
        }
        return dummy.right;
    }

    /**
     * 递归遍历 [深度优先搜索] - 将结果添加至数组，根据数组生成树
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.8 MB , 在所有 Java 提交中击败了 89.38% 的用户
     * */
    public TreeNode increasingBST2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        TreeNode dummy = new TreeNode(1);
        TreeNode curNode = dummy;
        for(int val : ans){
            curNode.right = new TreeNode(val);
            curNode = curNode.right;
        }
        return dummy.right;
    }
    // 深度优先搜索
    private void dfs(TreeNode node, List<Integer> ans){
        if(node == null){
            return;
        }
        dfs(node.left, ans);
        ans.add(node.val);
        dfs(node.right, ans);
    }

    /**
     * 递归遍历 - 直接生成树
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.7 MB , 在所有 Java 提交中击败了 98.43% 的用户
     * */
    TreeNode cur;
    public TreeNode increasingBST3(TreeNode root) {
        TreeNode dummy = new TreeNode(0);
        cur = dummy;
        dfs(root);
        return dummy.right;
    }
    // 深度优先搜索
    private void dfs(TreeNode node){
        if(node == null){
            return;
        }
        dfs(node.left);
        node.left = null;
        cur.right = node;
        cur = node;
        dfs(node.right);
    }
}
