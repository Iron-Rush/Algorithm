package cn.czl.dynamicPlanning.calculate;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
        if (amount == 0){   // 递归基本情况
            return 0;
        }
        if (amount < 0){    // 特殊情况(无解)
            return -1;
        }
        // 初始化对比结果的值为极大值，用于与其他计算结果比较，取最小值
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {    // 遍历货币数组，对每个货币进行递归深挖
            int count = coinChange(coins, amount - coin);
            // 若子问题无解，则跳过
            if(count == -1){
                continue;
            }
            res = Math.min(res, 1 + count); // 对比计算结果，取更优解
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
        // 查看是否存在已计算过的对应值
        if (saveMap.containsKey(amount)){
            return saveMap.get(amount);
        }
        if (amount == 0){   // 递归基本情况
            return 0;
        }
        if (amount < 0){    // 特殊情况(无解)
            return -1;
        }
        // 初始化对比结果的值为极大值，用于与其他计算结果比较，取最小值
        int res = Integer.MAX_VALUE;
        for (int coin: coins) { // 遍历货币数组，对每个货币进行递归深挖
            int count = coinChange(coins, amount - coin);
            if (count == -1){   // 若子问题无解，则跳过
                continue;
            }
            res = Math.min(res, 1 + count); // 对比计算结果，取更优解
        }
        // 验证本次计算是否有效，有效则存入备忘录
        saveMap.put(amount, (res == Integer.MAX_VALUE? -1: res));
        return saveMap.get(amount);
    }

    /**
     * dp数组迭代法
     * 执行用时：13 ms, 在所有 Java 提交中击败了91.18%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了74.56%的用户
     * */
    public int coinChange3(int[] coins, int amount) {
        // 初始化dp数组，大小为amount+1
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        // 从零计算出0-amount的全部最优解。i即为当前的amount
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i < coin){  // 子问题无解，跳过
                    continue;
                }
                // 当前位置最优解
                // 即当前位置解 与 当前货币值的货币缺口对应的最优解
                // 中的最小值
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        // 判断当前解是否有效
        return (dp[amount] == amount+1) ? -1 : dp[amount];
    }
}
