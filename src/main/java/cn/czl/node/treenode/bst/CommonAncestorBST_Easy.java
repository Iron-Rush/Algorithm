package cn.czl.node.treenode.bst;

/**
 * @author RedRush
 * @date 2020/9/27 9:27
 * @description:
 *          235. 二叉搜索树的最近公共祖先
 *              给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *              最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 *              最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
 *              的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class CommonAncestorBST_Easy {

    /**
     * 直接递归：因为是二叉搜索树，若当前节点介于二值中间(包括其中某值)
     * 则当前节点即为所求节点。(即：寻找 p<=target<=q )
     * 执行用时：6 ms, 在所有 Java 提交中击败了99.70%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了88.48%的用户
     * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        if (root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }

}
