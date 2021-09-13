package cn.czl.dynamicPlanning.study.primary;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/8/27 13:31
 * @description:
 *      213. 打家劫舍 II
 *      - 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *      - 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *      示例 1：
 *          输入：nums = [2,3,2]
 *          输出：3
 *          解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋
 *              （金额 = 2）, 因为他们是相邻的。
 *      示例 2：
 *          输入：nums = [1,2,3,1]
 *          输出：4
 *          解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *               偷窃到的最高金额 = 1 + 3 = 4 。
 *      示例 3：
 *          输入：nums = [0]
 *          输出：0
 *      提示：
 *          1 <= nums.length <= 100
 *          0 <= nums[i] <= 1000
 */
public class HouseRobber2_Normal {

    private int[] NUMS1 = {2,3,2};      // 3
    private int[] NUMS2 = {1,2,3,1};    // 4

    @Test
    public void TestSolution(){
        System.out.println(rob4(NUMS1));
        System.out.println(rob3(NUMS1));
        System.out.println(rob4(NUMS2));
        System.out.println(rob3(NUMS2));
    }

    /**
     * dfs - 深度优先搜索 [超出时间限制]
     * */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int len = nums.length;
        int a = dfs(nums, 0, len - 1);
        int b = dfs(nums, 1, len);
        return Math.max(a, b);
    }
    // dfs 递归函数
    private int dfs(int[] nums, int idx, int end){
        if (idx >= end){
            return 0;
        }
        int a = dfs(nums, idx + 2, end) + nums[idx];
        int b = dfs(nums, idx + 1, end);
        return Math.max(a, b);
    }

    /**
     * dfs + 备忘录
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.6 MB , 在所有 Java 提交中击败了 84.19% 的用户
     * */
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        int len = nums.length;
        int[] memo = new int[len + 1];
        Arrays.fill(memo, -1);
        int a = dfs(nums, 0, len - 1, memo);
        Arrays.fill(memo, -1);
        int b = dfs(nums, 1, len, memo);
        return Math.max(a, b);
    }
    // dfs 递归函数
    private int dfs(int[] nums, int idx, int end, int[] memo){
        if (idx >= end){
            return 0;
        }
        if (memo[idx] != -1){
            return memo[idx];
        }
        int a = dfs(nums, idx + 2, end, memo) + nums[idx];
        int b = dfs(nums, idx + 1, end, memo);
        int ans = Math.max(a, b);
        memo[idx] = ans;
        return ans;
    }

    /**
     * 动态规划 - dp[]数组
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.5 MB , 在所有 Java 提交中击败了 93.87% 的用户
     * */
    public int rob3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int len = nums.length;
        int dp[] = new int[len + 1];
        dp[1] = nums[0];
        for (int i = 1; i < len - 1; i++) {
            dp[i + 1] = Math.max(dp[i], dp[i - 1] + nums[i]);
        }
        int max = dp[len - 1];
        Arrays.fill(dp, 0);
        dp[2] = nums[1];
        for (int i = 2; i < len; i++) {
            dp[i + 1] = Math.max(dp[i], dp[i - 1] + nums[i]);
        }
        return Math.max(max, dp[len]);
    }

    /**
     * 动态规划 - 状态压缩 + 解法封装
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.8 MB , 在所有 Java 提交中击败了 48.97% 的用户
     * */
    public int rob4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int len = nums.length;
        int a = robAction(nums, 0, len - 1);
        int b = robAction(nums, 1, len);
        return Math.max(a, b);
    }
    private int robAction(int[] nums, int start, int end){
        int preMax = 0, curMax = nums[start];
        for (int i = start + 1; i < end; i++) {
            int max = Math.max(curMax, preMax + nums[i]);
            preMax = curMax;
            curMax = max;
        }
        return curMax;
    }
}
