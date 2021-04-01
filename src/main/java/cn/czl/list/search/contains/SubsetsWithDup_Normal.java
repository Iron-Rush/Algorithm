package cn.czl.list.search.contains;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2021/3/31 9:49
 * @description:
 *      90. 子集 II
 *      - 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *      - 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *      示例 1：
 *          输入：nums = [1,2,2]
 *          输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *      示例 2：
 *          输入：nums = [0]
 *          输出：[[],[0]]
 *      提示：
 *          1 <= nums.length <= 10
 *          -10 <= nums[i] <= 10
 */
public class SubsetsWithDup_Normal {

    private int[] NUMS1 = {1,2,2};
    private int[] NUMS2 = {0};
    private int[] NUMS3 = {1,2,3};

    @Test
    public void TestSolution(){
        System.out.println(subsetsWithDup(NUMS1));
//        System.out.println(subsetsWithDup(NUMS2));
//        System.out.println(subsetsWithDup(NUMS3));
    }

    /**
     * 深度优先搜索
     * 每次分为 选择当前数 和 不选择当前数 进行递归。
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 24.70% 的用户
     * */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);      // 排序后便于去重
        dfs(res, new ArrayList<>(), 0, nums.length, nums);
        return res;
    }
    // dfs - 递归函数
    public void dfs(List<List<Integer>> res, List<Integer> list, int index, int len, int[] nums){
        if(index == len){   // [基本情况] 如果游标到达尾部，将当前list拷贝放入res中
            res.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[index]);
        dfs(res, list, index + 1, len, nums); // 选择当前的数
        list.remove(list.size() - 1);   // 回溯 - 将当前添加的数删除
        while (index < len - 1 && nums[index] == nums[index + 1]){ // 去重，不选当前数递归时直接跳过重复数
            index++;    // 所有重复数的可能性在前面递归中均会出现
        }
        dfs(res, list, index + 1, len, nums); // 不选择当前的数
    }

    /**
     * 深度优先搜索2 - 改变去重方式
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 80.40% 的用户
     * */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        dfs(false, 0, nums, res, list);
        return res;
    }
    // dfs - 递归函数2
    void dfs(boolean choosePre, int cur, int[] nums, List<List<Integer>> res, List<Integer> list){
        if(cur == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        dfs(false, cur + 1, nums, res, list);
        if(!choosePre && cur > 0 && nums[cur - 1] == nums[cur]){
            return;     // 如果当前数不是首个数，并且与前一个数相等，则不再进行递归
        }
        list.add(nums[cur]);
        dfs(true, cur + 1, nums, res, list);
        list.remove(list.size() - 1);
    }


    /**
     * 迭代
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 52.97% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 44.00% 的用户
     * */
    public List<List<Integer>> subsetsWithDup3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int mask = 0; mask < (1 << len); mask++) {
            list.clear();
            boolean flag = true;
            for (int i = 0; i < len; i++) {
                if((mask & (1 << i)) != 0){
                    if(i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]){
                        flag = false;
                        break;
                    }
                    list.add(nums[i]);
                }
            }
            if(flag){
                res.add(new ArrayList<>(list));
            }
        }
        return res;
    }
}
