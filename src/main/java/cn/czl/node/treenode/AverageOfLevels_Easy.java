package cn.czl.node.treenode;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/9/12 10:04
 * @description:
 *          637.二叉树的层平均值
 *          给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 * 输入：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出：[3, 14.5, 11]
 */
public class AverageOfLevels_Easy {
    /**
     * BFS:广度优先搜索，粗糙版
     * 执行用时：5 ms, 在所有 Java 提交中击败了9.47%的用户
     * 内存消耗：41.3 MB, 在所有 Java 提交中击败了87.94%的用户
     * */
    public List<Double> averageOfLevels(TreeNode root) {
        int count = 1, NextCount = 0, Lpos = 0, Rpos = 2;
        double avg = 0;
        List<Double> avgList = new ArrayList<Double>();
        List<Double> tempList = new ArrayList<Double>();
        if (root == null){
            return avgList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            if (Lpos == Rpos){
                double sum = 0;
                for (double temp : tempList) {
                    sum += temp;
                }
                avg = sum/count;
                tempList.clear();
                avgList.add(avg);
                avg = 0;
                Lpos = Rpos;
                Rpos += (NextCount*2);
                count = NextCount;
                NextCount = 0;
            }
            TreeNode curNode = queue.poll();
            tempList.add((double)curNode.val);
            if (curNode.left != null){
                NextCount ++;
                queue.add(curNode.left);
            }
            if (curNode.right != null){
                NextCount++;
                queue.add(curNode.right);
            }
            Lpos += 2;
        }
        double sum = 0;
        for (double temp : tempList) {
            sum += temp;
        }
        avg = sum/count;
        avgList.add(avg);
        return avgList;
    }

    /**
     * BFS,第一次优化
     * 执行用时：6 ms, 在所有 Java 提交中击败了5.30%的用户
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了56.87%的用户
     * */
    public List<Double> averageOfLevels2(TreeNode root) {
        int count = 1, NextCount = 0, Lpos = 0, Rpos = 1, level = 0;
        double avg = 0;
        List<Double> avgList = new ArrayList<Double>();
        if (root == null){
            return avgList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            if (Lpos == Rpos){
                //avg = avgList.get(level)/count;
                avgList.set(level,avgList.get(level)/count);
                Rpos += NextCount;
                count = NextCount;
                NextCount = 0;
                level++;
            }
            TreeNode curNode = queue.poll();
            if (level == avgList.size()){
                avgList.add((double)curNode.val);
            }else {
                avgList.set(level,avgList.get(level) + (double)curNode.val);
            }
            if (curNode.left != null){
                NextCount ++;
                queue.add(curNode.left);
            }
            if (curNode.right != null){
                NextCount++;
                queue.add(curNode.right);
            }
            Lpos ++;
        }
        avgList.set(level,avgList.get(level)/count);
        return avgList;
    }


    /**
     * BFS,第二次优化
     * 执行用时：3 ms, 在所有 Java 提交中击败了73.65%的用户
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了64.73%的用户
     * */
    public List<Double> averageOfLevels3(TreeNode root) {
        int count = 1, NextCount = 0, pos = 1;
        double sum = 0;
        List<Double> avgList = new ArrayList<Double>();
        if (root == null){
            return avgList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            if (pos == 0){
                avgList.add(sum/count);
                pos = NextCount;
                count = NextCount;
                NextCount = 0;
                sum = 0;
            }
            TreeNode curNode = queue.poll();
            sum += (double)curNode.val;
            if (curNode.left != null){
                NextCount ++;
                queue.add(curNode.left);
            }
            if (curNode.right != null){
                NextCount++;
                queue.add(curNode.right);
            }
            pos--;
        }
        avgList.add(sum/count);
        return avgList;
    }

    /**
     * BFS,三次优化。内层循环遍历该层
     * 执行用时：3 ms, 在所有 Java 提交中击败了73.65%的用户
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了74.37%的用户
     * */
    public List<Double> averageOfLevels4(TreeNode root) {
        List<Double> averages = new ArrayList<Double>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            averages.add(sum / size);
        }
        return averages;
    }

    /**
     * DFS,深度优先搜索
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.17%的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了5.09% 的用户
     * */
    public List<Double> averageOfLevels5(TreeNode root) {
        List<Integer> counts = new ArrayList<Integer>();
        List<Double> sums = new ArrayList<Double>();
        dfs(root, 0, counts, sums);
        List<Double> averages = new ArrayList<Double>();
        int size = sums.size();
        for (int i = 0; i < size; i++) {
            averages.add(sums.get(i) / counts.get(i));
        }
        return averages;
    }
    public void dfs(TreeNode root, int level, List<Integer> counts, List<Double> sums) {
        if (root == null) {
            return;
        }
        if (level < sums.size()) {
            sums.set(level, sums.get(level) + root.val);
            counts.set(level, counts.get(level) + 1);
        } else {
            sums.add(1.0 * root.val);
            counts.add(1);
        }
        dfs(root.left, level + 1, counts, sums);
        dfs(root.right, level + 1, counts, sums);
    }

    /**
     * DFS,优化版
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.17%的用户
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了53.71%的用户
     * */
    List<Integer> counts = new ArrayList<Integer>();
    List<Double> sums = new ArrayList<Double>();
    public List<Double> averageOfLevels6(TreeNode root) {
        List<Double> result = new ArrayList<Double>();
        dfs2(root, 0);
        int size = counts.size();
        for(int i = 0; i < size; i++){
            result.add(sums.get(i)/counts.get(i));
        }
        return result;
    }
    public void dfs2(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level < sums.size()) {
            sums.set(level, sums.get(level) + root.val);
            counts.set(level, counts.get(level) + 1);
        } else {
            sums.add(1.0 * root.val);
            counts.add(1);
        }
        dfs2(root.left, level + 1);
        dfs2(root.right, level + 1);
    }

    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }
}
