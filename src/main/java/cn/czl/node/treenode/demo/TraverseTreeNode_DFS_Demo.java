package cn.czl.node.treenode.demo;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/9/4 11:20
 * @description: 二叉树的深度优先搜索代码如下
 */
public class TraverseTreeNode_DFS_Demo {

    @Test
    public void TestTraverseTreeNode(){

    }
    /**
     * 递归实现DFS
     * */
    public void treeDFS_Recur(TreeNode root) {
        //当前节点为空直接返回
        if (root == null)
            return;
        //打印当前节点的值
        System.out.println(root.val);
        //然后递归遍历左右子节点
        treeDFS_Recur(root.left);
        treeDFS_Recur(root.right);
    }
    /**
     * 循环实现DFS
     * */
    public static void treeDFS_Loop(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();  // 栈-先进后出
        stack.add(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * Definition for a binary tree node.
     *  */
    public class TreeNode { int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
