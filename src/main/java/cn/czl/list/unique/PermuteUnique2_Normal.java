package cn.czl.list.unique;

import cn.czl.search.bfs.TraverseTreeNode_BFS_Demo;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/9/18 11:35
 * @description:
 *          47. 全排列 II
 *          给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *      示例:
 *          输入: [1,1,2]
 *          输出:
 *          [
 *            [1,1,2],
 *            [1,2,1],
 *            [2,1,1]
 *          ]
 */
public class PermuteUnique2_Normal {

    private static int[] nums = new int[]{1,1,2};

    @Test
    public void TestSolution(){
        List<List<Integer>> res = permuteUnique(nums);
        for (List<Integer> tempList: res){
            for (int temp : tempList) {
                System.out.print(temp + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 递归回溯 + 元素锁
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了96.56%的用户
     * */
    List<List<Integer>> resLists = new ArrayList<List<Integer>>(); //结果集
    List<Integer> tempList = new ArrayList<Integer>(); // 递归记录数据的数组
    boolean[] lock; // 元素锁(记录哪些元素已被使用)
    public List<List<Integer>> permuteUnique(int[] nums) {
//        if (nums.length == 0){
//            return resLists;
//        }
        lock = new boolean[nums.length];
        Arrays.sort(nums);
        getUnique(nums, 0);
        return resLists;
    }
    private void getUnique(int[] nums, int pos){
        // 基本情况：当生成至目标长度，则终止递归
        if (pos == nums.length){
            resLists.add(new ArrayList<Integer>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!lock[i]){ // 检查当前数是否可用
                // 添加当前数至迭代数组并为该数加锁
                tempList.add(nums[i]);
                lock[i] = true;
                getUnique(nums, pos + 1); // 递归
                // 回溯数组和锁的状态
                tempList.remove(tempList.size() -1);
                lock[i] = false;
                // 向后去重
                while (i+1 < nums.length && nums[i] == nums[i+1]){
                    i++;
                }
            }
        }
    }

}
