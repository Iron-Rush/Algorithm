package org.lanqiao.study.treenode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class preorderTraversal{
    public List<Integer> preorderTraversal(TreeNode root){
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(root);
        List<Integer> list = new ArrayList<Integer>();
        while (!nodes.isEmpty()){
            TreeNode current = nodes.pop();
        }

//        list.addAll(nodes.toArray());
        return null;
    }

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
}