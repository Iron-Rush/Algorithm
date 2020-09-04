package cn.czl.search.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/9/4 11:27
 * @description: 二叉树的广度优先搜索代码如下
 */
public class TraverseTreeNode_BFS_Demo {

    public static void levelOrder(TreeNode tree) {
        if (tree == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);//相当于把数据加入到队列尾部
        while (!queue.isEmpty()) {
            //poll方法相当于移除队列头部的元素
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
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
