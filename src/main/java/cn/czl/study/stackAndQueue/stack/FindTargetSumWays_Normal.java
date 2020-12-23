package cn.czl.study.stackAndQueue.stack;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/12/23 10:02
 * @description:
 *          494. 目标和
 *          - 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。
 *          现在你有两个符号 + 和 -。对于数组中的任意一个整数，
 *          你都可以从 + 或 -中选择一个符号添加在前面。
 *          - 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *          示例：
 *              输入：nums: [1, 1, 1, 1, 1], S: 3
 *              输出：5
 *              解释：
 *                  -1+1+1+1+1 = 3
 *                  +1-1+1+1+1 = 3
 *                  +1+1-1+1+1 = 3
 *                  +1+1+1-1+1 = 3
 *                  +1+1+1+1-1 = 3
 *              一共有5种方法让最终目标和为3。
 *          提示：
 *              1. 数组非空，且长度不会超过 20 。
 *              2. 初始的数组的和不会超过 1000 。
 *              3. 保证返回的最终结果能被 32 位整数存下。
 */
public class FindTargetSumWays_Normal {

    private int[] NUMS1 = {1,1,1,1,1};
    private int[] NUMS2 = {25,29,23,21,45,36,16,35,13,39,44,15,16,14,45,23,50,43,9,15};
    private int S1 = 3;
    private int S2 = 32;

    @Test
    public void TestSolution(){
        System.out.println(findTargetSumWays2(NUMS2, S2));
    }

    /**
     * BFS-广度优先搜索   [队列实现]
     * 超出时间限制
     * */
    public int findTargetSumWays(int[] nums, int S) {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(nums[0]);
        queue.offer(-nums[0]);
        for(int i = 1; i < nums.length; i++){
            int a1 = nums[i], a2 = -a1;
            int curSize = queue.size();
            for (int j = 0; j < curSize; j++) {
                int cur = queue.poll();
                queue.offer(cur + a1);
                queue.offer(cur + a2);
            }
        }
        while(!queue.isEmpty()){
            if(queue.poll() == S){
                count++;
            }
        }
        return count;
    }


    /**
     * DFS-深度优先搜索
     * 执行用时： 663 ms , 在所有 Java 提交中击败了 15.53% 的用户
     * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 68.13% 的用户
     * */
    public int findTargetSumWays2(int[] nums, int S) {
        return dfs_helper(nums, S, 0);
    }
    int dfs_helper(int[] nums, int target, int index){
        if(index == nums.length){
            return target == 0 ? 1 : 0;
        }
        int curNum = nums[index++];
        int res = 0;
        res += dfs_helper(nums, target + curNum, index);
        res += dfs_helper(nums, target - curNum, index);
        return res;
    }

    /**
     * DFS-深度优先搜索   [记忆化递归]
     * 执行用时： 119 ms , 在所有 Java 提交中击败了 43.33% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 5.04% 的用户
     * */
    HashMap<String, Integer> memory = new HashMap<>();
    public int findTargetSumWays3(int[] nums, int S) {
        return dfs_helper2(nums, S, 0);
    }
    int dfs_helper2(int[] nums, int target, int index){
        if(index == nums.length){
            return target == 0 ? 1 : 0;
        }
        String key = index + "|" + target;
        if(memory.containsKey(key)){
            return memory.get(key);
        }
        int curNum = nums[index++];
        int res = 0;
        res += dfs_helper2(nums, target + curNum, index);
        res += dfs_helper2(nums, target - curNum, index);
        memory.put(key, res);
        return res;
    }

    /**
     * 动态规划
     * 执行用时： 11 ms , 在所有 Java 提交中击败了 66.05% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 21.32% 的用户
     * */
    public int findTargetSumWays4(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if(Math.abs(S) > Math.abs(sum)) return 0;
        int width = sum * 2 + 1;
        int height = nums.length;
        int[][] dp = new int[height][width];
        // 初始化
        if(nums[0] == 0){
            dp[0][sum] = 2;
        }else {
            dp[0][sum + nums[0]] = 1;
            dp[0][sum - nums[0]] = 1;
        }
        for (int i = 1; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // 边界
                int l = (j - nums[i] >= 0 ? j - nums[i] : 0);
                int r = (j + nums[i] < width ? j + nums[i] : 0);
                dp[i][j] = dp[i-1][l] + dp[i-1][r];
            }
        }
        return dp[height - 1][sum + S];
    }

    /**
     * 动态规划
     * 推出 sum(A) = (target + sum(nums)) / 2，
     * 也就是把原问题转化成：nums 中存在几个子集 A，
     * 使得 A 中元素的和为 (target + sum(nums)) / 2
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 83.33% 的用户
     * 内存消耗： 38 MB , 在所有 Java 提交中击败了 38.92% 的用户
     * */
    public int findTargetSumWays5(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 这两种情况不可能存在合法的子集划分
        if(sum < S || (sum + S) % 2 == 1){
            return 0;
        }
        return subsets(nums, (sum + S)/2);
    }
    // 转化为背包问题，用nums装满sum的方法数
    int subsets(int[] nums, int sum){
        int n = nums.length;
        int[][] dp = new int[n+1][sum+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if(j >= nums[i-1]){ // 两种选择的结果之和
                    dp[i][j] = dp[i-1][j] + dp[i-1][j - nums[i-1]];
                }else {     // 背包空间不足，只能选择不装i
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][sum];
    }
    // 转化为背包问题-状态压缩
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 89.47% 的用户
     * 内存消耗： 36.3 MB , 在所有 Java 提交中击败了 61.05% 的用户
     * */
    int subsets2(int[] nums, int sum){
        int n = nums.length;
        int[] dp = new int[sum+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = sum; j >= 0; j--) {
                if(j >= nums[i-1]){
                    dp[j] = dp[j] + dp[j - nums[i-1]];
                }else {
                    dp[j] = dp[j];
                }
            }
        }
        return dp[sum];
    }
}
