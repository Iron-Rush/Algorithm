package cn.czl.math;

/**
 * @author RedRush
 * @date 2021/1/20 9:45
 * @description:
 *      152. 乘积最大子数组
 *      - 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组
 *      （该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *      示例 1:
 *          输入: [2,3,-2,4]
 *          输出: 6
 *          解释: 子数组 [2,3] 有最大乘积 6。
 *      示例 2:
 *          输入: [-2,0,-1]
 *          输出: 0
 *          解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class MaximumProductSubarray_Normal {

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 87.91% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 42.80% 的用户
     * */
    public int maxProduct(int[] nums) {
        int prevMin = nums[0], prevMax = nums[0], res = nums[0];
        int temp1 = 0, temp2 = 0;
        for (int i = 1; i < nums.length; i++) {
            temp1 = nums[i] * prevMax;
            temp2 = nums[i] * prevMin;
            prevMax = Math.max(Math.max(temp1, temp2), nums[i]);
            prevMin = Math.min(Math.min(temp1, temp2), nums[i]);
            res = Math.max(res, prevMax);
        }
        return res;
    }
}
