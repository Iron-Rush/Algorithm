package cn.czl.dynamicPlanning.backpack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/4/15 10:47
 * @description:
 *      213. 打家劫舍 II
 *      - 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 *          这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 *          同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *      - 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，
 *          能够偷窃到的最高金额。
 *      示例 1：
 *          输入：nums = [2,3,2]
 *          输出：3
 *          解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
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

    private int[] NUMS1 = {2,3,2};
    private int[] NUMS2 = {1,2,3,1};
    private int[] NUMS3 = {1,2,3};

    @Test
    public void TestSolution(){
        System.out.println(rob(NUMS1));
        System.out.println(rob3(NUMS1));
        System.out.println(rob(NUMS2));
        System.out.println(rob3(NUMS2));
        System.out.println(rob(NUMS3));
        System.out.println(rob3(NUMS3));
    }

    /**
     * 动态规划，分别求解[偷第一户，不偷最后一户； 不偷第一户，偷最后一户]的最优解
     * 从两个解中得出最大值
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36 MB , 在所有 Java 提交中击败了 25.10% 的用户
     * */
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        if (len == 1){
            return nums[0];
        }
        int[] dpA = new int[len];   // 0 - n-2
        int[] dpB = new int[len];   // 1 - n-1
        dpA[0] = 0;
        dpB[0] = 0;
        dpA[1] = nums[0];
        dpB[1] = nums[1];
        for (int i = 1; i < len - 1; i++) {
            dpA[i + 1] = Math.max(dpA[i], dpA[i-1] + nums[i]);
            dpB[i + 1] = Math.max(dpB[i], dpB[i-1] + nums[i + 1]);
        }
        return Math.max(dpA[len - 1], dpB[len - 1]);
    }

    /**
     * 深度优先搜索 - 超出时间限制
     * */
    public int rob2(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        if (len == 1){
            return nums[0];
        }
        int a = dfs(nums, 0, len - 1, true);
        int b = dfs(nums, 1, len, true);
        return Math.max(a, b);
    }
    // dfs - 深度优先搜索
    private int dfs (int[] nums, int index, int end, boolean choose){
        if(index >= end){
            return 0;
        }
        int a = dfs(nums, index + 1, end, choose);  // a为不选择当前数，继续搜索
        int b = dfs(nums, index + 1, end, !choose); // b为根据flag判断是否选择当前数，并取反搜索
        if(choose){
            b += nums[index];
        }
        return Math.max(a, b);
    }

    /**
     * 深度优先搜索[记忆优化]
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.8 MB , 在所有 Java 提交中击败了 73.85% 的用户
     * */
    public int rob3(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        if (len == 1){
            return nums[0];
        }
        int[] memory = new int[len + 1];
        Arrays.fill(memory, -1);    // 初始化记忆数组
        int a = dfs2(nums, 0, len - 1, memory);
        Arrays.fill(memory, -1);    // 初始化记忆数组
        int b = dfs2(nums, 1, len, memory);
        return Math.max(a, b);
    }
    // dfs - 深度优先搜索[带字典]
    private int dfs2 (int[] nums, int index, int end, int[] memory){
        if(index >= end){
            return 0;
        }
        if(memory[index] != -1){
            return memory[index];
        }
        int res = Math.max(dfs2(nums, index + 1, end, memory),
                dfs2(nums, index + 2, end, memory)+nums[index]);
        memory[index] = res;
        return res;
    }
}
