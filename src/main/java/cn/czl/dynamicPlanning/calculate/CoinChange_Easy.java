package cn.czl.dynamicPlanning.calculate;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author RedRush
 * @date 2020/9/17 23:30
 * @description:
 *          322.零钱兑换
 *          - 给定不同面额的硬币 coins 和一个总金额 amount。
 *          编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 *          如果没有任何一种硬币组合能组成总金额，返回 -1。
 *      示例:
 *          - 输入: coins = [1, 2, 5], amount = 11
 *          - 输出: 3
 *          - 解释: 11 = 5 + 5 + 1
 */
public class CoinChange_Easy {

    private int[] coins = new int[]{1,2,5};
    private int amount = 11;

    @Test
    public void TestSolution(){
        System.out.println(coinChange2(coins, amount));
}
    /**
     * 暴力递归解法
     * ===超时===
     * */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }
        if (amount < 0){
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int count = coinChange(coins, amount - coin);
            // 子问题无解，则跳过
            if(count == -1){
                continue;
            }
            res = Math.min(res, 1 + count);
        }
        return ((res == Integer.MAX_VALUE)? -1: res);
    }

    /**
     * 递归(带备忘录)
     * 执行用时：204 ms, 在所有 Java 提交中击败了5.00%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了5.00%的用户
     * */
    private HashMap<Integer, Integer> saveMap = new HashMap();
    public int coinChange2(int[] coins, int amount) {
        if (saveMap.containsKey(amount)){
            return saveMap.get(amount);
        }
        if (amount == 0){
            return 0;
        }
        if (amount < 0){
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (int coin: coins) {
            int count = coinChange(coins, amount - coin);
            if (count == -1){
                continue;
            }
            res = Math.min(res, 1 + count);
        }
        saveMap.put(amount, (res == Integer.MAX_VALUE? -1: res));
        return saveMap.get(amount);
    }

}
