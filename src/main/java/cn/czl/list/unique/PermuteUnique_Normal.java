package cn.czl.list.unique;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/9/18 15:14
 * @description:
 *          46. 全排列
 *          给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *      示例:
 *          输入: [1,2,3]
 *          输出:
 *          [
 *            [1,2,3],[1,3,2],[2,1,3],
 *            [2,3,1],[3,1,2],[3,2,1]
 *          ]
 */
public class PermuteUnique_Normal {

    private static int[] nums = new int[]{1,2,3};

    @Test
    public void TestSolution(){
        List<List<Integer>> res = permute(nums);
        for (List<Integer> tempList: res){
            for (int temp : tempList) {
                System.out.print(temp + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了82.89%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了85.77%的用户
     * */
    List<List<Integer>> resLists = new ArrayList<List<Integer>>(); //结果集
    List<Integer> tempList = new ArrayList<Integer>(); // 递归记录数据的数组
    boolean[] lock; // 元素锁(记录哪些元素已被使用)
    public List<List<Integer>> permute(int[] nums) {
        lock = new boolean[nums.length];
        getUnique(nums);
        return resLists;
    }
    private void getUnique(int[] nums){
        // 基本情况：当生成至目标长度，则终止递归
        if (tempList.size() == nums.length){
            resLists.add(new ArrayList<Integer>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!lock[i]){ // 检查当前数是否可用
                // 添加当前数至迭代数组并为该数加锁
                tempList.add(nums[i]);
                lock[i] = true;
                getUnique(nums); // 递归
                // 回溯数组和锁的状态
                tempList.remove(tempList.size() -1);
                lock[i] = false;
            }
        }
    }

}
