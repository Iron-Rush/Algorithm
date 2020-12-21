package cn.czl.dynamicPlanning.study.primary;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/12/21 9:41
 * @description:
 *      746. 使用最小花费爬楼梯
 *      - 数组的每个索引作为一个阶梯，第 i个阶梯对应着
 *      一个非负数的体力花费值 cost[i](索引从0开始)。
 *      - 每当你爬上一个阶梯你都要花费对应的体力花费值，
 *      然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 *      - 您需要找到达到楼层顶部的最低花费。在开始时，
 *      你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 *      示例 1:
 *          输入: cost = [10, 15, 20]
 *          输出: 15
 *          解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 *      示例 2:
 *          输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 *          输出: 6
 *          解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 *      注意：
 *          1. cost 的长度将会在 [2, 1000]。
 *          2. 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
 */
public class ClimbStairsMinCost_Easy {

    private int[] COST1 = {10, 15, 20};
    private int[] COST2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};

    @Test
    public void TestSoluion(){
        System.out.println(15 == minCostClimbingStairs(COST1));
        System.out.println(6 == minCostClimbingStairs(COST2));
    }

    /**
     * 动态规划     递推公式: dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.68% 的用户
     * 内存消耗： 37.9 MB , 在所有 Java 提交中击败了 93.88% 的用户
     * */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < len; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }
        return Math.min(dp[len-1], dp[len-2]);
    }

    /**
     * 动态规划 - 状态压缩
     *
     * */
    public int minCostClimbingStairs2(int[] cost) {
        int pre1 = cost[0], pre2 = cost[1];
        for(int i = 2; i < cost.length; i++){
            if(i % 2 == 0){
                pre1 = Math.min(pre1, pre2) + cost[i];
            }else{
                pre2 = Math.min(pre1, pre2) + cost[i];
            }
        }
        return Math.min(pre1, pre2);
    }
}
