package cn.czl.node.treenode;

import java.util.ArrayList;
import java.util.List;

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
     * Definition for a binary tree node.
     *  */
    public class TreeNode { int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}


