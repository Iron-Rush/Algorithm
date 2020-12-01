package cn.czl.node.treenode.verify;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/12/1 17:09
 * @description:
 *      1609. 奇偶树
 *      - 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
 *      - 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
 *          - 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
 *          - 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
 *          - 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
 */
public class EvenOddTree_Normal {

    @Test
    public void TestSolution(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        System.out.println(isEvenOddTree(root));
    }

    /**
     * BFS,广度优先搜索
     * 执行用时：11 ms, 在所有 Java 提交中击败了94.83%的用户
     * 内存消耗：55.1 MB, 在所有 Java 提交中击败了81.40%的用户
     * */
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int deep = 1;
        if (root != null){
            queue.offer(root);
        }
        while (!queue.isEmpty()){
            int size = queue.size();
            int pre = 0;        // pre为该层前一个数
            int rest = deep % 2;// rest 为奇/偶层的余数
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null){
                    queue.offer(curNode.left);
                }
                if (curNode.right != null){
                    queue.offer(curNode.right);
                }
                if (curNode.val % 2 != rest){   // 检验该节点数的奇偶性
                    return false;
                }
                // 检验该节点的递增/减性，该层收个节点无需检验
                if (i != 0 && (curNode.val >= pre && rest == 0) || (curNode.val <= pre && rest == 1)){
                    return false;
                }
                pre = curNode.val;
            }
            deep++;
        }
        return true;
    }
}
