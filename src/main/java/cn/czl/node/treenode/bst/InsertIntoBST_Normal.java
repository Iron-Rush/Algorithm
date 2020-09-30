package cn.czl.node.treenode.bst;

import org.junit.jupiter.api.Test;
import sun.reflect.generics.tree.Tree;

/**
 * @author RedRush
 * @date 2020/9/30 9:32
 * @description:
 *      701. 二叉搜索树中的插入操作
 *          - 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
 *          返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
 *          - 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。
 *          你可以返回任意有效的结果。
 *          - 例, 给定二叉搜索树:
 *                  4
 *                 / \
 *                2   7
 *               / \
 *              1   3
 *          和 插入的值: 5
 */
public class InsertIntoBST_Normal {

    @Test
    public void TestSolution(){
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7));
//        dfs(root);
        root = insertIntoBST2(root, 5);
        dfs(root);
    }

    /**
     * 递归实现
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了62.75%的用户
     * */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null){
            return new TreeNode(val);
        }
        if (root.val > val){
            root.left = insertIntoBST(root.left, val);
        }else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    /**
     * 迭代实现
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了51.78%的用户
     * */
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        TreeNode insertNode = new TreeNode(val);
        TreeNode parent = root, child = root;
        while (child != null){
            parent = child;
            child = parent.val > val ? parent.left : parent.right;
        }
        if (parent == null){
            return insertNode;
        }else if (val < parent.val){
            parent.left = insertNode;
        }else{
            parent.right = insertNode;
        }
        return root;
    }

    void dfs(TreeNode root){
        if (root == null){
            return;
        }
        dfs(root.left);
        System.out.print(root.val+",");
        dfs(root.right);
    }
}
