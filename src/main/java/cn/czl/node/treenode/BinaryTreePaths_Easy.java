package cn.czl.node.treenode;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/9/4 10:45
 * @description:
 */
public class BinaryTreePaths_Easy {

    /**
     * 执行用时：10 ms,在所有 Java 提交中击败了53.76%的用户
     * 内存消耗：39.7 MB,在所有 Java 提交中击败了94.60%的用户
     * */
    List<String> strList = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null){
            return strList;
        }
        StringBuilder stringBuilder = new StringBuilder();
        pathBuilder(root, stringBuilder);
        return strList;
    }

    public void pathBuilder(TreeNode node, StringBuilder stringBuilder){
        if (node.left == null && node.right == null){
            stringBuilder.append(node.val);
            strList.add(stringBuilder.toString());
            return;
        }
        if (node.left != null){
            StringBuilder sbTemp = new StringBuilder();
            sbTemp.append(stringBuilder);
            sbTemp.append(node.val + "->");
            pathBuilder(node.left, sbTemp);
        }
        if (node.right != null){
            StringBuilder sbTemp = new StringBuilder();
            sbTemp.append(stringBuilder);
            sbTemp.append(node.val + "->");
            pathBuilder(node.right, sbTemp);
        }
    }

    /**
     * 优化后
     * 执行用时：1 ms,在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.2 MB,在所有 Java 提交中击败了14.75%的用户
     * */
    List<String> res = new ArrayList<>();
    public List<String> binaryTreePaths2(TreeNode root) {
        if(root == null){
            return res;
        }
        getTree(root,new StringBuilder());
        return res;
    }

    private void getTree(TreeNode root, StringBuilder sb) {
        if(root == null){
            return;
        }
        sb.append(root.val);
        if(root.left == null && root.right == null){
            res.add(sb.toString());
            return;
        }
        sb.append("->");
        getTree(root.left,new StringBuilder(sb));
        getTree(root.right,new StringBuilder(sb));
    }

    /**
     * BFS实现
     * */
    public List<String> binaryTreePaths3(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        if (root == null) {
            return paths;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<String> pathQueue = new LinkedList<String>();

        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();
            if (node.left == null && node.right == null) {
                paths.add(path);
            } else {
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    pathQueue.offer(new StringBuffer(path).append("->").append(node.left.val).toString());
                }

                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    pathQueue.offer(new StringBuffer(path).append("->").append(node.right.val).toString());
                }
            }
        }
        return paths;
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


