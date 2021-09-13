package cn.czl.dynamicPlanning.study.primary;

/**
 * @author RedRush
 * @date 2020/12/10 14:43
 * @description:
 *      198. 打家劫舍
 *      - 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *      - 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *      示例 1：
 *          输入：[1,2,3,1]
 *          输出：4
 *          解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。偷窃到的最高金额 = 1 + 3 = 4 。
 *      示例 2：
 *          输入：[2,7,9,3,1]
 *          输出：12
 *          解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，
 *          接着偷窃 5 号房屋 (金额 = 1)。偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *      提示：
 *      - 0 <= nums.length <= 100
 *      - 0 <= nums[i] <= 400
 */
public class HouseRobber_Easy {

    /**
     * 动态规划1[较为繁琐],nums[i]存储当前位置最大值
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了60.34%的用户
     * */
    public int rob(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return Math.max(nums[0], nums[1]);
        }
        nums[2] += nums[0];
        for(int i = 3; i < nums.length; i++){
            nums[i] += Math.max(nums[i-2], nums[i-3]);
        }
        return Math.max(nums[nums.length-1], nums[nums.length-2]);
    }

    /**
     * 动态规划2，开拓dp数组。dp[i]为，nums[i-1]时最优解
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了44.23%的用户
     * */
    public int rob2(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i+1] = Math.max(dp[i-1] + nums[i], dp[i]);
        }
        return dp[dp.length-1];
    }

    /**
     * 动态规划 - 状态压缩
     * 分别记录前一位最大值，和前两位的最大值
     * 通过比较currMax与prevMax + curVal获得当前位置最优解
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了69.55%的用户
     * */
    public int rob3(int[] nums){
        int currMax = 0, prevMax = 0;
        for (int num : nums) {
            int temp = currMax;
            currMax = Math.max(prevMax + num, currMax);
            prevMax = temp;
        }
        return currMax;
    }

}
