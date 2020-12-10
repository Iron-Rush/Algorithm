package cn.czl.dynamicPlanning.study.primary;

/**
 * @author RedRush
 * @date 2020/12/10 10:48
 * @description:
 *      122. 买卖股票的最佳时机 II
 *      - 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *      - 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *      - 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *      示例 1:
 *       输入: [7,1,5,3,6,4]
 *       输出: 7
 */
public class MaxProfit2_Easy {

    /**
     * 动态规划，基于之前的利润，得出当前的最大利润
     * 执行用时：2 ms, 在所有 Java 提交中击败了30.12%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了51.47%的用户
     * */
    public int maxProfit(int[] prices) {
        int[] profits = new int[prices.length];
        int pre = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - pre;
            pre = prices[i];
            profits[i] = profit > 0 ? profits[i-1] + profit : profits[i-1];
        }
        return profits[profits.length-1];
    }
}
