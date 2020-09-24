package cn.czl.node.treenode.bst;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/9/24 9:32
 * @description:
 *      501. 二叉搜索树中的众数
 *          给定一个有相同值的二叉搜索树（BST），
 *          找出 BST 中的所有众数（出现频率最高的元素）。
 *      例：  给定 BST [1,null,2,2]
 *             1
 *              \
 *               2
 *              /
 *             2
 *          返回[2].
 *          提示：如果众数超过1个，不需考虑输出顺序
 */
public class FindModeBST_Easy {

    @Test
    public void TestSolution(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(13);
        root.right.left = new TreeNode(13);
        int[] res = (findMode(root));
        for (int temp : res) {
            System.out.print(temp + ",");
        }
    }

    /**
     * 强行修改能用的- -，
     * 执行用时：2 ms, 在所有 Java 提交中击败了39.20%的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了5.24%的用户
     * */
    int cur = 0, max = 0, count = 0,prev = 0;
    List<Integer> list = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        if (root == null){
            return new int[0];
        }
        prev = root.val;
        HashMap<Integer, Integer> map = new HashMap<>();
        inorderTraversal(map, root);
        if (max == cur){
            list.add(prev);
        }else if (cur > max){
            list.clear();
            list.add(prev);
        }
        int res[] = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    private void inorderTraversal(HashMap<Integer, Integer> map, TreeNode root){
        if (root == null){
            return;
        }
        inorderTraversal(map, root.left);
        if (map.containsKey(root.val)){
            map.put(root.val, map.get(root.val)+1);
            cur++;
        }else {
            map.put(root.val, 1);
            if (max == cur){
                count++;
                list.add(prev);
            }else if (cur > max){
                list.clear();
                list.add(prev);
                max = cur;
                count = 1;
            }
            cur = 1;
            prev = root.val;
        }
        inorderTraversal(map, root.right);
    }

    /**
     * 减去非必要变量。因为BST为递增树
     * 通过判断当前数与前一个数大小是否相同以统计数字出现频率
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了37.99%的用户
     * */
    int prevNum = 0, maxCount = 0, curCount = 0;
    List<Integer> listMark = new ArrayList<>();
    public int[] findMode2(TreeNode root) {
        if (root == null){
            return new int[0];
        }
        prevNum = root.val;
        inorderTraversal(root);
        if (maxCount == curCount){
            listMark.add(prevNum);
        }else if (curCount > maxCount){
            return new int[]{prevNum};
        }
        int res[] = new int[listMark.size()];
        for (int i = 0; i < listMark.size(); i++) {
            res[i] = listMark.get(i);
        }
        return res;
    }
    private void inorderTraversal(TreeNode root){
        if (root == null){
            return;
        }
        inorderTraversal(root.left);
        if (root.val == prevNum){
            curCount++;
        }else {
            if (maxCount == curCount){
                listMark.add(prevNum);
            }else if (curCount > maxCount){
                listMark.clear();
                listMark.add(prevNum);
                maxCount = curCount;
            }
            curCount = 1;
            prevNum = root.val;
        }
        inorderTraversal(root.right);
    }


    /**
     * 抽取代码
     * 执行用时：1 ms, 在所有 Java 提交中击败了63.03%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了44.76%的用户
     * */
//    int prevNum = 0, maxCount = 0, curCount = 0;
//    List<Integer> listMark = new ArrayList<>();
    public int[] findMode3(TreeNode root) {
        if (root == null){
            return new int[0];
        }
        prevNum = root.val;
        inorderTraversal2(root);
        if (maxCount == curCount){
            listMark.add(prevNum);
        }else if (curCount > maxCount){
            return new int[]{prevNum};
        }
        int res[] = new int[listMark.size()];
        for (int i = 0; i < listMark.size(); i++) {
            res[i] = listMark.get(i);
        }
        return res;
    }
    private void inorderTraversal2(TreeNode root){
        if (root == null){
            return;
        }
        inorderTraversal2(root.left);
        updateNum(root.val);
        inorderTraversal2(root.right);
    }
    private void updateNum(int nodeNum){
        if (nodeNum == prevNum){
            curCount++;
        }else {
            if (maxCount == curCount){
                listMark.add(prevNum);
            }else if (curCount > maxCount){
                listMark.clear();
                listMark.add(prevNum);
                maxCount = curCount;
            }
            curCount = 1;
            prevNum = nodeNum;
        }
    }
}
