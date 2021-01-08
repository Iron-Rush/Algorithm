package cn.czl.list.search.stock;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/12/28 8:08
 * @description:
 *      188. 买卖股票的最佳时机 IV
 *      - 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *      - 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *      - 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *      示例 1：
 *          输入：k = 2, prices = [2,4,1]
 *          输出：2
 *          解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *      示例 2：
 *          输入：k = 2, prices = [3,2,6,5,0,3]
 *          输出：7
 *          解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *               随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *      提示：
 *      1. 0 <= k <= 10^9
 *      2. 0 <= prices.length <= 1000
 *      3. 0 <= prices[i] <= 1000
 */
public class MaxProfit4_Hard {

    private int[] PRICES1 = {2,4,1};
    private int[] PRICES2 = {3,2,6,5,0,3};
    private int[] PRICES3 = {3,3,5,0,0,3,1,4};
    private int[] PRICES4 = {1,2,4,2,5,7,2,4,9,0};

    @Test
    public void TestSolution(){
        System.out.println(maxProfit(2, PRICES4));
    }

    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        k = Math.min(k, n / 2);
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        buy[0] = -prices[0];
        sell[0] = 0;
        for (int i = 1; i <= k; ++i) {
            buy[i] = sell[i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            buy[0] = Math.max(buy[0], sell[0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                buy[j] = Math.max(buy[j], sell[j] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sell).max().getAsInt();
    }

    public int maxProfit2(int k, int[] prices) {
        int profit = 0, len = prices.length;
        int low = prices[0], high = -1;
        PriorityQueue<Integer> upNum = new PriorityQueue<>();
        PriorityQueue<Integer> downNum = new PriorityQueue<>();
        for (int i = 1; i < len; i++) {
            if(prices[i] > prices[i-1]){
                if(i == len-1 || prices[i+1] <= prices[i]){
                    upNum.add(prices[i] - low);
                    if(high >= 0){
                        downNum.add(high - low);
                    }
                    high = prices[i];
                }
            }else {
                low = prices[i];
            }
        }
        while (upNum.size() > k){
            int minUp = upNum.peek();
            int minDown = downNum.peek();
            if(minUp <= minDown){

            }else {

            }
        }
        while (!upNum.isEmpty()){
            profit += upNum.poll();
        }
        return profit;
    }
}
