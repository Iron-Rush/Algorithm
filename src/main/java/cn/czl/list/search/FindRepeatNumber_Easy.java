package cn.czl.list.search;

import java.util.HashSet;
import java.util.Set;

/**
 * @author RedRush
 * @date 2022/2/23 16:06
 * @description:
 *      剑指 Offer 03. 数组中重复的数字
 *      - 找出数组中重复的数字。
 *      - 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 *          数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 *          请找出数组中任意一个重复的数字。
 *      示例 1：
 *          输入：[2, 3, 1, 0, 2, 5, 3]
 *          输出：2 或 3
 *          限制：2 <= n <= 100000
 */
public class FindRepeatNumber_Easy {

    /**
     * 数字作下标归位
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 78.04% 的用户
     * 内存消耗： 48.6 MB , 在所有 Java 提交中击败了 23.51% 的用户
     * */
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while(nums[i] != i){
                if(nums[i] == nums[nums[i]]){
                    return nums[i];
                }
                swap(nums, nums[i], i);
            }
        }
        return -1;
    }
    // 交换数组中两个数字位置
    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * HashSet记录
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 53.62% 的用户
     * 内存消耗： 51.3 MB , 在所有 Java 提交中击败了 5.00% 的用户
     * */
    public int findRepeatNumber2(int[] nums) {
        int len = nums.length;
        Set<Integer> set = new HashSet<>(len);
        for(int num : nums){
            if(!set.add(num)){
                return num;
            }
        }
        return -1;
    }

    /**
     * 数组记录
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 78.04% 的用户
     * 内存消耗： 48.9 MB , 在所有 Java 提交中击败了 16.89% 的用户
     * */
    public int findRepeatNumber3(int[] nums) {
        int len = nums.length;
        boolean[] memo = new boolean[len];
        for(int num : nums){
            if(memo[num])   return num;
            memo[num] = true;
        }
        return -1;
    }
}
