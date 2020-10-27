package cn.czl.node.treenode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/10/27 9:21
 * @description:
 *      144. 二叉树的前序遍历（递归算法很简单，你可以通过迭代算法完成吗？）
 *      输入: [1,null,2,3]
 *         1
 *          \
 *           2
 *          /
 *         3
 *      输出: [1,2,3]
 */
public class PreprderTraversal_Normal {

    @Test
    public void TestSolution(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(7);
        System.out.println(preorderTraversal(root));
    }

    /**
     * 前序遍历-迭代法实现(模版)
     * 浏览顺序：中->左->右
     * 入栈顺序：右->左->中
     * */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null){
            stack.push(root);
        }
        while (!stack.isEmpty()){
            TreeNode curNode = stack.pop();
            if (curNode == null){
                curNode = stack.pop();
                resList.add(curNode.val);
            }else{
                if (curNode.right != null){
                    stack.push(curNode.right);
                }
                if (curNode.left != null){
                    stack.push(curNode.left);
                }
                stack.push(curNode);
                stack.push(null);
            }
        }
        return resList;
    }
}
