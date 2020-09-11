package cn.czl.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/9/11 10:44
 * @description:
 *          216.组合总和 III
 *          1. 找出所有相加之和为 n 的 k 个数的组合。
 *          组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *          2. 说明：
 *          - 所有数字都是正整数。
 *          - 解集不能包含重复的组合。
 *          输入: k = 3, n = 7 - 输出: [[1,2,4]]
 *          输入: k = 3, n = 9 - 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum3_Normal {
    private static int k = 3;
    private static int n = 9;

    @Test
    public void TestSolution(){
        System.out.println(combinationSum3(k, n));
    }


    /**
     * 递归实现dfs，初始化静态数组nums
     * 执行用时：0 ms, 在所有 Java 提交中击败了99.97%的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了24.09%的用户
     * */
    private List<List<Integer>> resList = new ArrayList<List<Integer>>();
    private List<Integer> tempList = new ArrayList<Integer>();
    private static int[] nums = new int[]{1,2,3,4,5,6,7,8,9};
    private int size;
    public List<List<Integer>> combinationSum3(int k, int n) {
        size = k;
        depthFirstSearch(0, n);
        return resList;
    }
    private void depthFirstSearch(int pos, int target){
        if (target == 0){
            if (tempList.size() == size){
                resList.add(new ArrayList<>(tempList));
            }
            return;
        }
        for (int i = pos; i < 9; i++) {
            tempList.add(nums[i]);
            depthFirstSearch(i+1, target - nums[i]);
            tempList.remove(tempList.size()-1);
        }
    }


    /**
     * 递归实现dfs,省略目标数组，使用循环中i替代
     * 执行用时：1 ms, 在所有 Java 提交中击败了69.81%的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了24.09%的用户
     * */
//    private List<List<Integer>> resList = new ArrayList<List<Integer>>();
//    private List<Integer> tempList = new ArrayList<Integer>();
//    private int size;
    public List<List<Integer>> combinationSum3_2(int k, int n) {
        size = k;
        depthFirstSearch(1, n);
        return resList;
    }
    private void depthFirstSearch2(int pos, int target){
        if (target == 0){
            if (tempList.size() == size){
                resList.add(new ArrayList<>(tempList));
            }
            return;
        }
        for (int i = pos; i < 10; i++) {
            tempList.add(i);
            depthFirstSearch2(i+1, target - i);
            tempList.remove(tempList.size()-1);
        }
    }

}
