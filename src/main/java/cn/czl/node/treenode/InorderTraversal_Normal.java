package cn.czl.node.treenode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/9/14 10:29
 * @description:
 *          94.二叉树的中序遍历
 *          - 给定一个二叉树，返回它的中序 遍历。
 *          输入: [1,null,2,3]
 *             1
 *              \
 *               2
 *              /
 *             3
 *          - 输出: [1,3,2]
 *          - 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class InorderTraversal_Normal {

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

        List<Integer> list = inorderTraversal2(p);
        for (int i = 0; i < list.size(); i++)
        {
            System.out.print(list.get(i) + " ");
        }

    }
    /**
     * 中序遍历-常用递归解法
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了20.07%的用户
     * */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<Integer>();
        Recursive(resList, root);
        return resList;
    }
    private void Recursive(List<Integer> resList, TreeNode root){
        if (root != null){
            Recursive(resList, root.left);
            resList.add(root.val);
            Recursive(resList, root.right);
        }
    }

    /**
     * 中序遍历-栈，迭代实现
     * */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stk = new Stack<>();  // 栈-先进后出
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    /**
     * 中序遍历-Morris遍历算法
     * Morris 遍历算法是另一种遍历二叉树的方法，它能将非递归的中序遍历空间复杂度降为 O(1)O(1)。
     * */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
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
