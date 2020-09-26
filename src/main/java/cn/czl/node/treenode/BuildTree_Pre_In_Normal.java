package cn.czl.node.treenode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author RedRush
 * @date 2020/9/25 10:57
 * @description:    前序遍历：中->左->右
 *      105. 从前序与中序遍历序列构造二叉树
 *          根据一棵树的前序遍历与中序遍历构造二叉树。
 *          注意:你可以假设树中没有重复的元素。
 *      例:
 *          前序遍历 preorder = [3,9,20,15,7]
 *          中序遍历 inorder = [9,3,15,20,7]
 *      返回如下的二叉树：
 *                  3
 *                 / \
 *                9  20
 *                  /  \
 *                 15   7
 */
public class BuildTree_Pre_In_Normal {

    private static int[] PREORDER = new int[]{3,9,20,15,7};
    private static int[] INORDER = new int[]{9,3,15,20,7};

    @Test
    public void TestSolution(){
        TreeNode root = buildTree(INORDER, PREORDER);
        dfs(root);
    }

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了73.89%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了37.19%的用户
     * */
    HashMap<Integer, Integer> inMap = new HashMap<>();
    int[] preorder;
    int pos;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        pos = 0;
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i],i);
        }
        return BuildHelper(0,inorder.length-1);
    }
    private TreeNode BuildHelper(int Lpos, int Rpos){
        if (Lpos > Rpos){
            return null;
        }
        int curRootVal = preorder[pos++];
        TreeNode curRoot = new TreeNode(curRootVal);
        int index = inMap.get(curRootVal);
        curRoot.left = BuildHelper(Lpos, index-1);
        curRoot.right = BuildHelper(index+1, Rpos);
        return curRoot;
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
