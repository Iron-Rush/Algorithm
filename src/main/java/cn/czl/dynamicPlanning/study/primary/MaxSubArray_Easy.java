package cn.czl.dynamicPlanning.study.primary;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/12/10 13:55
 * @description:
 *      53. 最大子序和
 *      - 给定一个整数数组 nums ，找到一个具有最大和的连续子数组
 *      （子数组最少包含一个元素），返回其最大和。
 *      示例:
 *          输入: [-2,1,-3,4,-1,2,1,-5,4]
 *          输出: 6
 *          解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *      进阶: 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class MaxSubArray_Easy {

    @Test
    public void TestSolution(){
        int[] NUMS = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray2(NUMS));
    }

    /**
     * 当前总和<=0时，则可中断本次累计。
     * 每次检测更新当前最大值。
     * 执行用时：1 ms, 在所有 Java 提交中击败了94.13%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了57.01%的用户
     * */
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE, curSum = 0;
        for (int num : nums) {
            if(curSum > 0){
                curSum += num;
            }else {
                curSum = num;
            }
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

    /**
     * 动态规划，dp[i]为当前位结尾的数组的最大的和
     * 执行用时：1 ms, 在所有 Java 提交中击败了94.13%的用户
     * 内存消耗：38.4 MB , 在所有 Java 提交中击败了71.75%的用户
     * */
    public int maxSubArray2(int[] nums) {
        int max = Integer.MIN_VALUE;
        for(int i = 1; i < nums.length; i++){
            nums[i] = Math.max(nums[i-1] + nums[i], nums[i]);
            max = Math.max(max, nums[i]);
        }
        return max;
    }

}
