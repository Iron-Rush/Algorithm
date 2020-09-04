package cn.czl.node.treenode;

/**
 * @author RedRush
 * @date 2020/9/4 11:20
 * @description: 二叉树的深度优先搜索代码如下
 */
public class TraverseTreeNode_DFS_Demo {
    public void treeDFS(TreeNode root) {
        //当前节点为空直接返回
        if (root == null)
            return;
        //打印当前节点的值
        System.out.println(root.val);
        //然后递归遍历左右子节点
        treeDFS(root.left);
        treeDFS(root.right);
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
