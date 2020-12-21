package cn.czl.list.search.stock;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/12/17 9:29
 * @description:
 *      714. 买卖股票的最佳时机含手续费
 *      - 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；
 *      非负整数 fee 代表了交易股票的手续费用。
 *      - 你可以无限次地完成交易，但是你每笔交易都需要付手续费。
 *      如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *      - 返回获得利润的最大值。
 *      -  注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *      示例 1:
 *          输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 *          输出: 8
 *          解释:
 *          - 在此处买入 prices[0] = 1
 *          - 在此处卖出 prices[3] = 8
 *          - 在此处买入 prices[4] = 4
 *          - 在此处卖出 prices[5] = 9
 *          - 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *      注意:
 *      1. 0 < prices.length <= 50000.
 *      2. 0 < prices[i] < 50000.
 *      3. 0 <= fee < 50000.
 */
public class BuyAndSellStockWithTransactionFee_Normal {

    private int[] PRICES1 = {1,3,2,8,4,9};
    private int FEE = 2;
    @Test
    public void TestSolution(){
        System.out.println(maxProfit2(PRICES1, FEE));
    }

    /**
     * 贪心算法     将手续费直接算入买入价钱中。
     * 如果哪天价格要比自己成本高，卖了，同时将这天价格更新为自己的成本。
     * 如果日后哪天有更高的价钱，可以以这天的价格为成本，叠加利润
     * 遇见更低的成本，则改选为在这一天买入。相当于重新开始计算一波利润
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 99.77% 的用户
     * 内存消耗： 47.5 MB , 在所有 Java 提交中击败了 82.36% 的用户
     * */
    public int maxProfit(int[] prices, int fee) {
        int profit = 0;
        int buyPrice = prices[0] + fee;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > buyPrice){
                profit += prices[i] - buyPrice;
                buyPrice = prices[i];
            }else if(buyPrice > prices[i] + fee){
                buyPrice = prices[i] + fee;
            }
        }
        return profit;
    }

    /**
     * 动态规划 row0模拟当前卖出最大利润，row1模拟当前买入最大利润
     * 因此 -> dp[i][0] = max(dp[i-1][0], dp[i-1][0] + prices[i] - fee)
     *      -> dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     * 执行用时： 26 ms , 在所有 Java 提交中击败了 10.36% 的用户
     * 内存消耗： 47.7 MB , 在所有 Java 提交中击败了 58.15% 的用户
     * */
    public int maxProfit2(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;           // 当前无收益
        dp[0][1] = -prices[0];  // 第一天买入
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    /**
     * 动态规划，状态压缩
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 99.77% 的用户
     * 内存消耗： 47.6 MB , 在所有 Java 提交中击败了 74.21% 的用户
     * */
    public int maxProfit3(int[] prices, int fee) {
        int profit = 0;
        int money = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int preProfit = profit;
            profit = Math.max(profit, money + prices[i] - fee);
            money = Math.max(money, preProfit - prices[i]);
        }
        return profit;
    }
}
