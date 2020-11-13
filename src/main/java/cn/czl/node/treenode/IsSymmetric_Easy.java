package cn.czl.node.treenode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/11/13 16:02
 * @description:
 *      101. 对称二叉树
 *      给定一个二叉树，检查它是否是镜像对称的。
 */
public class IsSymmetric_Easy {
    /**
     * 重组树，递归。->比较
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了17.61%的用户
     * */
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        if (root.right == null && root.left == null){
            return true;
        }
        if (root.right == null || root.left == null){
            return false;
        }
        boolean curFlag = root.left.val == root.right.val;
        TreeNode outNode = new TreeNode(0);
        outNode.left = root.left.left;
        outNode.right = root.right.right;
        boolean outFlag = isSymmetric(outNode);
        TreeNode inNode = new TreeNode(0);
        inNode.left = root.left.right;
        inNode.right = root.right.left;
        boolean inFlag = isSymmetric(inNode);
        return curFlag && outFlag && inFlag;
    }

    /**
     * 借助类，拆开分别同步递归左右分支
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了92.68%的用户
     * */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null){
            return true;
        }
        return check(root.left, root.right);
    }
    public boolean check(TreeNode lNode, TreeNode rNode){
        if (lNode == null && rNode == null){
            return true;
        }
        if (lNode == null || rNode == null){
            return false;
        }
        boolean cur = lNode.val == rNode.val;
        boolean out = check(lNode.left, rNode.right);
        boolean in = check(lNode.right, rNode.left);
        return cur && out && in;
    }

    /**
     * BFS，迭代实现
     * 执行用时：1 ms, 在所有 Java 提交中击败了28.77%的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了18.09%的用户
     * */
    public boolean isSymmetric3(TreeNode root) {
        if (root == null){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()){
            TreeNode lNode = queue.poll();
            TreeNode rNode = queue.poll();
            if(lNode == null && rNode == null){
                continue;
            }
            if ((lNode == null || rNode == null) || lNode.val != rNode.val){
                return false;
            }
            queue.offer(lNode.left);
            queue.offer(rNode.right);
            queue.offer(lNode.right);
            queue.offer(rNode.left);
        }
        return true;
    }
}
