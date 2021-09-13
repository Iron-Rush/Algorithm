package cn.czl.dynamicPlanning.study.primary;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RedRush
 * @date 2021/8/26 9:47
 * @description:
 *      1137. 第 N 个泰波那契数
 *      - 泰波那契序列 Tn 定义如下：
 *      - T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *      - 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *      示例 1：
 *          输入：n = 4
 *          输出：4
 *          解释：
 *              T_3 = 0 + 1 + 1 = 2
 *              T_4 = 1 + 1 + 2 = 4
 *      示例 2：
 *          输入：n = 25
 *          输出：1389537
 *      提示：
 *           0 <= n <= 37
 *          答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 */
public class NThTribonacciNumber_Easy {

    @Test
    public void TestSolution(){
        System.out.println(tribonacci2(25));
        System.out.println(tribonacci3(25));
    }

    /**
     * 记忆化递归
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.3 MB , 在所有 Java 提交中击败了 25.75% 的用户
     * */
    Map<Integer, Integer> map = new HashMap<>();
    public int tribonacci(int n) {
        if(n <= 2){
            return n == 0 ? 0 : 1;
        }
        if(map.containsKey(n)){
            return map.get(n);
        }
        int ans = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
        map.put(n, ans);
        return ans;
    }


    /**
     * 动态规划 - dp[]数组
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.4 MB , 在所有 Java 提交中击败了 5.01% 的用户
     * */
    public int tribonacci2(int n) {
        if(n <= 2){
            return n == 0 ? 0 : 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        return dp[n];
    }

    /**
     * 动态规划 - 状态压缩
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.4 MB , 在所有 Java 提交中击败了 5.01% 的用户
     * */
    public int tribonacci3(int n) {
        if (n <= 2) {
            return n == 0 ? 0 : 1;
        }
        int cur = 1;
        int pre1 = 1, pre2 = 0;
        for (int i = 3; i <= n; i++) {
            int next = cur + pre1 + pre2;
            pre2 = pre1;
            pre1 = cur;
            cur = next;
        }
        return cur;
    }
}
