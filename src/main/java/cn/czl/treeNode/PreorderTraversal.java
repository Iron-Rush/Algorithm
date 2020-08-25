package cn.czl.treeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/8/25 11:41
 */
public class PreorderTraversal {
    public List<Integer> preorderTraversal(PreorderTraversal.TreeNode root){
        Stack<PreorderTraversal.TreeNode> nodes = new Stack<>();
        nodes.push(root);
        List<Integer> list = new ArrayList<Integer>();
        while (!nodes.isEmpty()){
            PreorderTraversal.TreeNode current = nodes.pop();
        }

//        list.addAll(nodes.toArray());
        return null;
    }

    public class TreeNode{
        int val;
        PreorderTraversal.TreeNode left;
        PreorderTraversal.TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
}
