package cn.czl.node.treenode;

import java.util.HashMap;

/**
 * @author RedRush
 * @date 2020/9/25 13:48
 * @description:
 */
public class BuildTree_Pre_Post_Normal {

    int[] pre;
    HashMap<Integer, Integer> postMap = new HashMap<>();
    public TreeNode TreeBuilder(int[] pre, int[]post){
        this.pre = pre;
        for (int i = 0; i < post.length; i++) {
            postMap.put(post[i], i);
        }
        return BuildHelper(0, pre.length-1, 0, post.length-1);
    }
    private TreeNode BuildHelper(int preLpos, int preRpos, int postLpos, int postRpos){
        if (preLpos > preRpos || postLpos > postRpos){
            return null;
        }
        int curRootVal = pre[preLpos];
        TreeNode curRoot = new TreeNode(curRootVal);
        int index = postMap.get(curRootVal);
        curRoot.left = BuildHelper(preLpos + 1, preLpos+1+index-postLpos, postLpos, index);
        curRoot.right = BuildHelper(preLpos + 1 + 1 + index - postLpos, preRpos, index + 1, postRpos + 1);
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
