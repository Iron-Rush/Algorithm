package cn.czl.node.treenode.bst;

import org.junit.jupiter.api.Test;

import java.util.*;
/**
 * * * * * * * * * * * * *
 * BST-二叉搜索树是一棵空树，或者是具有下列性质的二叉树：
 * 1. 若它的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
 * 2. 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
 * 3. 它的左、右子树也分别为二叉搜索树。
 * 由这样的性质我们可以发现，二叉搜索树的中序遍历是一个单调递增的有序序列。如果我们反序地中序遍历该二叉搜索树，即可得到一个单调递减的有序序列。
 * * * * * * * * * * * * *
 * */
/**
 * @author RedRush
 * @date 2020/9/21 14:04
 * @description:
 *      538.把二叉搜索树转换为累加树
 *          给定一个二叉搜索树（Binary Search Tree），
 *          把它转换成为累加树（Greater Tree)，
 *          使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *      输入: 原始二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *      输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 */
public class ConvertBST_Easy {

    @Test
    public void TestSolution(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        root = convertBST2(root);

        DFS(root);
        System.out.println(numList);
    }

    /**
     * 深度优先搜索，提取全部元素。为每个元素求出对应的和
     * 再次遍历，将求和后的数字替换原数
     * 执行用时：11 ms, 在所有 Java 提交中击败了10.37%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了39.06%的用户
     * */
    List<Integer> numList = new ArrayList<Integer>();
    HashMap<Integer, Integer> resultMap = new HashMap<>();
    public TreeNode convertBST(TreeNode root) {
        DFS(root);
//        numList.sort(Comparator.naturalOrder());    // 元素进行升序排列
        numList.sort(Comparator.reverseOrder());    // 元素进行降序排序
        System.out.println(numList.toString());
        resultMap.put(numList.get(0), numList.get(0));
        for (int i = 1; i < numList.size(); i++) {
            resultMap.put(numList.get(i), numList.get(i) + resultMap.get(numList.get(i-1)));
        }
        System.out.println(resultMap);
        return GreaterTree(root);
    }
    // 深度优先搜索：将节点存入数组
    private void DFS(TreeNode curNode){
        if (curNode == null){
            return;
        }
        numList.add(curNode.val);
        DFS(curNode.right);
        DFS(curNode.left);
    }
    // 根据map，将计算好的结果替换原节点
    private TreeNode GreaterTree(TreeNode node){
        node.val = resultMap.get(node.val);
        if (node.right != null){
            node.right = GreaterTree(node.right);
        }
        if (node.left != null){
            node.left = GreaterTree(node.left);
        }
        return node;
    }

    /**
     * 迭代实现-反中序遍历：  遍历顺序：右->中->左
     * 从右向左累加，并替换当前节点值
     * 执行用时：4 ms, 在所有 Java 提交中击败了10.37%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了39.42%的用户
     * */
    public TreeNode convertBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        int sum = 0;
        if (root != null){
            stack.push(root);
        }
        while (!stack.isEmpty()){
            TreeNode curNode = stack.pop();
            if (curNode == null){
                curNode = stack.pop();
                System.out.println(curNode.val);
                sum = curNode.val + sum;
                curNode.val = sum;
                System.out.println(curNode.val);
            }else {
                if (curNode.left != null){
                    stack.push(curNode.left);
                }
                stack.push(curNode);
                stack.push(null);
                if (curNode.right != null){
                    stack.push(curNode.right);
                }
            }
        }
        return root;
    }

    /**
     * 递归实现-反中序遍历：  遍历顺序：右->中->左
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.82%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了68.78%的用户
     * */
    public TreeNode convertBST3(TreeNode root) {
        traversal(root, 0);
        return root;
    }
    private int traversal(TreeNode curNode, int sum){
        if (curNode == null){
            return sum;
        }
        sum = traversal(curNode.right, sum);
        sum += curNode.val;
        curNode.val = sum;
        sum = traversal(curNode.left, sum);
        return sum;
    }

    /**
     * morris遍历
     * 1.如果当前节点的右子节点为空，处理当前节点，并遍历当前节点的左子节点；
     * 2.如果当前节点的右子节点不为空，找到当前节点右子树的最左节点（该节点为当前节点中序遍历的前驱节点）；
     *      2.1如果最左节点的左指针为空，将最左节点的左指针指向当前节点，遍历当前节点的右子节点；
     *      2.2如果最左节点的左指针不为空，将最左节点的左指针重新置为空（恢复树的原状），处理当前节点，并将当前节点置为其左节点；
     * 重复步骤 1 和步骤 2，直到遍历结束。
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.82%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了44.09%的用户
     * */
    public TreeNode convertBST4_morris(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        while (node != null) {
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            } else {
                TreeNode succ = getSuccessor(node);
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                } else {
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }
        return root;
    }

    public TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }
}
