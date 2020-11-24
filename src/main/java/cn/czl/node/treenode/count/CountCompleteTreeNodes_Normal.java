package cn.czl.node.treenode.count;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/11/24 9:10
 * @description:
 *      222. 完全二叉树的节点个数
 *      - 给出一个完全二叉树，求出该树的节点个数。
 *      示例：
 *          输入:
 *                  1
 *                 / \
 *                2   3
 *               / \  /
 *              4  5 6
 *          输出: 6
 */
public class CountCompleteTreeNodes_Normal {

    /**
     * dfs,深度优先搜索
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了97.81%的用户
     * */
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        int lCount = countNodes(root.left);
        int rCount = countNodes(root.right);
        return lCount + rCount + 1;
    }

    /**
     * bfs,广度优先搜索
     * 执行用时：7 ms, 在所有 Java 提交中击败了7.97%的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了70.77%的用户
     * */
    public int countNodes2(TreeNode root) {
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null){
            queue.add(root);
            count++;
        }
        while(!queue.isEmpty()){
            TreeNode curNode = queue.poll();
            if(curNode.right != null){
                queue.add(curNode.right);
                count++;
            }
            if(curNode.left != null){
                queue.add(curNode.left);
                count++;
            }
        }
        return count;
    }

    /**
     * 二分查找 + 位运算
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了71.37%的用户
     * */
    public int cuntNodes3(TreeNode root){
        if(root == null){
            return 0;
        }
        int depth = 0;
        TreeNode node = root;
        while (node.left != null){
            node = node.left;
            depth ++;
        }
        int low = 1 << depth, high = (1 << (depth + 1)) - 1;
        while (low < high){
            int mid = (high - low + 1) / 2 + low;
            if(exists(root, depth, mid)){
                low = mid;
            }else {
                high = mid - 1;
            }
        }
        return low;
    }
    boolean exists(TreeNode root, int level, int k){
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0){
            if ((bits & k) == 0){
                node = node.left;
            }else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

    /**
     * 根据二叉树性质，分别探查左右子树深度
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.4 MB, 在所有 Java 提交中击败了98.96%的用户
     * */
    public int countNodes3(TreeNode root){
        if(root == null){
            return 0;
        }
        TreeNode lNode = root.left;
        TreeNode rNode = root.right;
        int lHeight = 0, rHeight = 0;
        while (lNode != null){   // 探查左子树深度
            lNode = lNode.left;
            lHeight++;
        }
        while (rNode != null){  // 探查右子树深度
            rNode = rNode.right;
            rHeight++;
        }
        if (lHeight == rHeight){    // 注意(2<<1) 相当于2^2，lHeight
            return (2 << lHeight) - 1;
        }
        return countNodes3(root.left) + countNodes3(root.right) + 1;
    }

}
