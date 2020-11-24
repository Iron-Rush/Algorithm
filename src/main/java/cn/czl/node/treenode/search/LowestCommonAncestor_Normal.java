package cn.czl.node.treenode.search;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author RedRush
 * @date 2020/11/14 22:27
 * @description:
 *      236. 二叉树的最近公共祖先
 *      - 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 */
public class LowestCommonAncestor_Normal {

    /**
     *
     * 执行用时：7 ms, 在所有 Java 提交中击败了99.84%的用户
     * 内存消耗：40.4 MB, 在所有 Java 提交中击败了91.46%的用户
     * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null){
            return root;
        }
        if (root == p || root == q){
            return root;
        }
        TreeNode lNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rNode = lowestCommonAncestor(root.right, p, q);
        if (lNode != null && rNode != null){
            return root;
        }else if(lNode != null){
            return lNode;
        }else if(rNode != null){
            return rNode;
        }
        return null;
    }

    /**
     * 直接递归，若已找到所求父节点，则终止递归
     * 执行用时：7 ms, 在所有 Java 提交中击败了99.84%的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了80.06%的用户
     * */
    boolean flag = true;
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q){
            return root;
        }
        if (flag){
            TreeNode lNode = lowestCommonAncestor2(root.left, p, q);
            TreeNode rNode = lowestCommonAncestor2(root.right, p, q);
            if (lNode != null && rNode != null){
                flag = false;
                return root;
            }else if(lNode != null){
                return lNode;
            }else if(rNode != null){
                return rNode;
            }
        }
        return null;
    }

    /**
     * dfs,记录全部子节点的父节点
     * 记录p节点到root的路径，获取q节点到root路径同时与p节点路径作比较
     * 执行用时：12 ms, 在所有 Java 提交中击败了15.68%的用户
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了5.66%的用户
     * */
    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null){  // 获取/记录p节点 -> 根节点的路径
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null){  // 获取q节点 -> 根节点的路径，同时与p节点路径作比较
            if(visited.contains(q.val)){
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }
    public void dfs(TreeNode root){     // 存储全部子节点，及其父节点
        if (root.left != null){
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null){
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

}
