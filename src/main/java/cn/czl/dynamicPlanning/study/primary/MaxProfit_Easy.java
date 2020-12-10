package cn.czl.dynamicPlanning.study.primary;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/12/10 10:35
 * @description:
 *      121. 买卖股票的最佳时机
 *      - 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *      - 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *      - 注意：你不能在买入股票前卖出股票。
 *      示例 1:
 *          输入: [7,1,5,3,6,4]       输出: 5
 *          解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *          注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *      示例 2:
 *          输入: [7,6,4,3,1]
 *          输出: 0
 */
public class MaxProfit_Easy {

    @Test
    public void TestSolution(){
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit2(prices));
    }

    /**
     * 1. 首先想记录购买当天能获得的最大利润，那么我就需要知道今天以前的，历史最低价。
     * 2. 根据这个当天的最大利润，可以维护一个理论上的最大利润值。
     * 3. 根据每天的股价，可以维护一个今天以前的(历史)最低价
     * 4. 如果今天的价钱要比记录中的历史最低价还要低，那么今天必不可能是卖出日，
     * 维护历史最低价与维护理论最大利润是互斥的。
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.55%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了71.36%的用户
     * */
    public int maxProfit(int[] prices) {
        int maxProfit = 0, curMin = Integer.MAX_VALUE;
        for (int price : prices) {
            if(price < curMin){
                curMin = price;
            }else{
                maxProfit = Math.max(maxProfit, price-curMin);
            }
        }
        return maxProfit;
    }

    /**
     * 维护一个单调栈
     * 执行用时：7 ms, 在所有 Java 提交中击败了21.65%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了86.27%的用户
     * */
    public int maxProfit2(int[] prices) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int price : prices) {
            while (!stack.isEmpty() && stack.peek() > price){
                stack.pop();
            }
            stack.push(price);
            result = Math.max(result, price - stack.firstElement());
        }
        return result;
    }
}
