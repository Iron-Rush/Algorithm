package cn.czl.list;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/9/10 10:03
 * @description:
 *          40.组合总和 II
 *          1. 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *          2. candidates 中的每个数字在每个组合中只能使用一次。
 *          3. 说明：
 *          - 所有数字（包括目标数）都是正整数。
 *          - 解集不能包含重复的组合。
 */
public class CombinationSum2_Normal {

    private int[] nums = new int[]{2,5,2,1,2};
    private int target = 5;

    @Test
    public void TestSolution(){
    System.out.println(combinationSum2(nums, target));
    }

    /**
     * 递归实现dfs
     * 执行用时：5 ms, 在所有 Java 提交中击败了45.03%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了91.79%的用户
     * */
    private List<List<Integer>> resList = new ArrayList<List<Integer>>();
    private List<Integer> tempList = new ArrayList<Integer>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        Boolean[] keys = new Boolean[candidates.length];
        Arrays.fill(keys, true);
        depthFirstSearch(keys, candidates, target);
        return resList;
    }
    public void depthFirstSearch(Boolean[] keys, int[] nums, int target){
        for (int i = 0; i < keys.length; i++) {
            if (target == 0){
                if (!resList.contains(tempList)){
                    resList.add(new ArrayList<>(tempList));
                }
            }
            if (keys[i] == true && nums[i] <= target){
                keys[i] = false;
                tempList.add(nums[i]);
                depthFirstSearch(keys, nums, target - nums[i]);
                keys[i] = true;
                tempList.remove(tempList.size() - 1);
            }else {
                break;
            }
        }
    }
}
