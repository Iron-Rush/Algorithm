package cn.czl.node.treenode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/9/15 10:26
 * @description:
 *          迭代法遍历二叉树
 *          前序遍历/中序遍历/后序遍历
 */
public class IterationTraversalTreeNode_Demo {

    /**
     * 前序遍历-迭代法实现(模版)
     * 浏览顺序：中->左->右
     * 入栈顺序：右->左->中
     * */
    public List<Integer> preorderTraversalDemo(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null){
            stack.push(root);
        }
        while (!stack.isEmpty()){
//            TreeNode tempNode = stack.peek();
            TreeNode tempNode = stack.pop();
            if (tempNode != null){
//                stack.pop();        // 将该节点弹出，避免重复操作，下面再将右左中节点添加到栈中
                if (tempNode.right != null){    // 添加右节点
                    stack.push(tempNode.right);
                }
                stack.push(tempNode);          // 添加中节点
                stack.push(null);        // 中节点访问过，但未处理，做标记(单独添加中间节点，通过null识别)
                if (tempNode.left != null){    // 添加左节点
                    stack.push(tempNode.left);
                }
            }else {
//                stack.pop();                   // 弹出空节点
                tempNode = stack.pop();        // 重新取出栈中元素
//                tempNode = stack.peek();
//                stack.pop();
                res.add(tempNode.val);         // 加入到数组
            }
        }
        return res;
    }

    /**
     * 中序遍历-迭代法实现(模版)
     * 浏览顺序：左->中->右
     * 入栈顺序：右->中->左
     * */
    public List<Integer> inorderTraversalDemo(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null){
            stack.push(root);
        }
        while (!stack.isEmpty()){
            TreeNode tempNode = stack.pop();    // 弹出节点，判断该节点是否为空
            if (tempNode != null){              // 避免重复操作，下面再将右中左节点添加到栈中
                if (tempNode.right != null){    // 添加右节点
                    stack.push(tempNode.right);
                }
                stack.push(tempNode);          // 添加中节点
                stack.push(null);        // 中节点访问过，但未处理，做标记(单独添加中间节点，通过null识别)
                if (tempNode.left != null){    // 添加左节点
                    stack.push(tempNode.left);
                }
            }else {
                tempNode = stack.pop();        // 重新取出栈中元素
                res.add(tempNode.val);         // 加入到数组
            }
        }
        return res;
    }

    /**
     * 后序遍历-迭代法实现(模版)
     * 浏览顺序：左->右->中
     * 入栈顺序：中->右->左
     * */
    public List<Integer> postorderTraversalDemo(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null){
            stack.push(root);
        }
        while (!stack.isEmpty()){
            TreeNode tempNode = stack.pop();    // 弹出节点，判断该节点是否为空
            if (tempNode != null){              // 避免重复操作，下面再将中右左节点添加到栈中
                stack.push(tempNode);          // 添加中节点
                stack.push(null);        // 中节点访问过，但未处理，做标记(单独添加中间节点，通过null识别)
                if (tempNode.right != null){    // 添加右节点
                    stack.push(tempNode.right);
                }
                if (tempNode.left != null){    // 添加左节点
                    stack.push(tempNode.left);
                }
            }else {
                tempNode = stack.pop();        // 重新取出栈中元素
                res.add(tempNode.val);         // 加入到数组
            }
        }
        return res;
    }

    /**
     * Test-main
     * */
    @Test
    public void TestSolution()
    {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        p.left.left = new TreeNode(4);
        p.left.right = new TreeNode(5);
        p.right.right = new TreeNode(6);

        List<Integer> list = postorderTraversalDemo(p);
        for (int i = 0; i < list.size(); i++)
        {
            System.out.print(list.get(i) + " ");
        }

    }
}
