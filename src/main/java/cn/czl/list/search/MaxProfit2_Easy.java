package cn.czl.list.search;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/11/11 15:43
 * @description:
 *      122. 买卖股票的最佳时机 II
 *      - 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *      - 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *      - 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *      示例 1:
 *          输入: [7,1,5,3,6,4]   输出: 7
 *          解释: 在第 2 天（股票价格 = 1）的时候买入，
 *              在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *              随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出,
 *              这笔交易所能获得利润 = 6-3 = 3
 */
public class MaxProfit2_Easy {

    private int[] PRICES1 = new int[]{7,1,5,3,6,4};
    private int[] PRICES2 = new int[]{1,2,3,4,5};
    private int[] PRICES3 = new int[]{7,6,4,3,1};

    @Test
    public void TestSolution(){
        System.out.println(maxProfit(PRICES3));
    }

    /**
     * 今天比明天便宜就买。第二天就卖
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.43%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了68.26%的用户
     * */
    public int maxProfit(int[] prices) {
        int profit = 0, temp = prices[0];
        for (int rPos = 1; rPos < prices.length; rPos++) {
            if (prices[rPos] > temp){
                profit += prices[rPos] - temp;
            }
            temp = prices[rPos];
        }
        return profit;
    }
}
