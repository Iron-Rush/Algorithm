package cn.czl.node.treenode;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @author RedRush
 * @date 2020/9/4 10:45
 * @description:
 */
public class BinaryTreePaths_Easy {

    /**
     * DFS递归实现
     * 执行用时：10 ms,在所有 Java 提交中击败了53.76%的用户
     * 内存消耗：39.7 MB,在所有 Java 提交中击败了94.60%的用户
     * 每一次非空判断浪费时间，可放到递归开始进行非空判断
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
     * DFS递归实现(优化后)
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

    public List<String> binaryTreePathDFS(TreeNode root){
        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        //栈中节点和路径都是成对出现的，路径表示的是从根节点到当前
        //节点的路径，如果到达根节点，说明找到了一条完整的路径
        Stack<Object> stack = new Stack<>();
        //当前节点和路径同时入栈
        stack.push(root);
        stack.push(String.valueOf(root.val));
        while (!stack.isEmpty()) {
            //节点和路径同时出栈
            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();
            //如果是根节点，说明找到了一条完整路径，把它加入到集合中
            if (node.left == null && node.right == null) {
                res.add(path);
            }
            //右子节点不为空就把右子节点和路径压栈
            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }
            //左子节点不为空就把左子节点和路径压栈
            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
        }
        return res;
    }

    /**
     * BFS实现
     * 执行用时：2 ms,在所有 Java 提交中击败了93.97%的用户
     * 内存消耗：39.7 MB,在所有 Java 提交中击败了90.72%的用户
     * */
    public List<String> binaryTreePathBFS(TreeNode root){
        List<String> paths = new ArrayList<>();
        if (root == null){
            return paths;
        }
        // 通过队列实现BFS搜索
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();        // 二叉树遍历队列
        Queue<String> pathQueue = new LinkedList<String>();     // 路径队列
        nodeQueue.offer(root);
        pathQueue.offer(String.valueOf(root.val));
        while (!nodeQueue.isEmpty()){   // 当队列为空时，停止循环
            TreeNode currNode = nodeQueue.poll();
            String path = pathQueue.poll();
            if(currNode.left == null && currNode.right == null){
                paths.add(path.toString());
            }else {
                if (currNode.left != null){
                    nodeQueue.add(currNode.left);
                    pathQueue.add(new StringBuilder(path).append("->").append(currNode.left.val).toString());
                }
                if (currNode.right != null){
                    nodeQueue.add(currNode.right);
                    pathQueue.add(new StringBuilder(path).append("->").append(currNode.right.val).toString());
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


