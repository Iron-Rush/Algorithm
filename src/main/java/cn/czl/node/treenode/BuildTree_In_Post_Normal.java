package cn.czl.node.treenode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RedRush
 * @date 2020/9/25 9:25
 * @description:
 *      106. 从中序与后序遍历序列构造二叉树
 *          根据一棵树的中序遍历与后序遍历构造二叉树。
 *          注意：你可以假设树中没有重复的元素。
 *      例：
 *          中序遍历 inorder = [9,3,15,20,7]
 *          后序遍历 postorder = [9,15,7,20,3]
 *      返回如下的二叉树：
 *              3
 *             / \
 *            9  20
 *              /  \
 *             15   7
 */
public class BuildTree_In_Post_Normal {

    private static int[] INORDER = new int[]{9,3,15,20,7};
    private static int[] POSTORDER = new int[]{9,15,7,20,3};

    @Test
    public void TestSolution(){
        TreeNode root = buildTree(INORDER, POSTORDER);
        dfs(root);
    }

    /**
     * 根据后序遍历获取当前的root节点，中序遍历根据root节点分割左右子树
     * 执行用时：2 ms, 在所有 Java 提交中击败了97.53%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了52.26%的用户
     * */
    int pos;
    int[] postorder;
    Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
    public TreeNode buildTree(int[] inorder, int[] postorder){
        this.postorder = postorder;
        pos = postorder.length - 1; // 从后序遍历的最后一个元素开始
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return BuildHelper(0, pos);
    }

    private TreeNode BuildHelper(int leftPos, int rightPos){
        if (leftPos > rightPos){
            return null;
        }
        int curRootVal = postorder[pos--];          // 取后序遍历的最后元素作为当前树根节点
        TreeNode root = new TreeNode(curRootVal);   // 初始化当前树
        int index = inorderMap.get(curRootVal);     // 根据当前根节点的值，从中序遍历中分割子树
        root.right = BuildHelper(index+1, rightPos);
        root.left = BuildHelper(leftPos, index - 1);
        return root;
    }

    // 前序遍历
    private void dfs(TreeNode node){
        if (node == null){
            return;
        }
        System.out.println(node.val);
        dfs(node.left);
        dfs(node.right);
    }
}
