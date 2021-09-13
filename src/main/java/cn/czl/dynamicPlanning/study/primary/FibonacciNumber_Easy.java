package cn.czl.dynamicPlanning.study.primary;

/**
 * @author RedRush
 * @date 2021/8/25 17:05
 * @description:
 *      509. 斐波那契数
 *      - 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。
 *          该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *          F(0) = 0，F(1) = 1
 *          F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 *      给你 n ，请计算 F(n) 。
 */
public class FibonacciNumber_Easy {

    /**
     * 递归
     * 执行用时： 13 ms , 在所有 Java 提交中击败了 5.15% 的用户
     * 内存消耗： 35.3 MB , 在所有 Java 提交中击败了 14.78% 的用户
     * */
    public int fib(int N){
        if(N <= 1){
            return N;
        }
        return fib(N - 1) + fib(N - 2);
    }

    /**
     * 动态规划 - dp[]数组
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.3 MB , 在所有 Java 提交中击败了 12.60% 的用户
     * */
    public int fib2(int N){
        if(N <= 1){
            return N;
        }
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= N; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }

    /**
     * 动态规划 - 状态压缩
     * */
    public int fib3(int N){
        if(N <= 1){
            return N;
        }
        int pre1 = 0, pre2 = 1;
        int cur = 0;
        for(int i = 2; i <= N; i++){
            cur = pre1 + pre2;
            pre1 = pre2;
            pre2 = cur;
        }
        return cur;
    }
}
