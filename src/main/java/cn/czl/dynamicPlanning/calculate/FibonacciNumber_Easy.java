package cn.czl.dynamicPlanning.calculate;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/9/17 13:53
 * @description:
 *          509.斐波那契数
 *          - 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。
 *          该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *          - F(0) = 0,   F(1) = 1
 *          - F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 *          - 输入：4  - 输出：3
 *          - 解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
 */
public class FibonacciNumber_Easy {

    private static int N = 4;

    @Test
    public void TestSoltion(){
        System.out.println(fib2(N));
    }
    /**
     * 暴力递归
     * 执行用时：7 ms, 在所有 Java 提交中击败了28.44%的用户
     * 内存消耗：35.7 MB, 在所有 Java 提交中击败了27.28%的用户
     * */
    public int fib(int N) {
        if(N == 0){
            return 0;
        }
        if(N == 1 || N == 2){
            return 1;
        }
        return fib(N-1) + fib(N-2);
    }

    /**
     * 递归(带备忘录)，自顶向下求解
     * 每一次计算结果存入备忘录中，如果备忘录中存在，则直接返回
     * 可以有效避免重复计算(空间换时间)
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了6.13%的用户
     * */
    public int fib2(int N){
        if (N < 1){
            return 0;
        }
        int[] dict = new int[N+1];  // 初始化备忘录，默认为0
        return fibTool(dict, N);
    }
    private int fibTool(int[] dict, int n){
        // 基本情况
        if (n == 1 || n == 2){
            return 1;
        }
        // 如果备忘录中已存入数，则返回已计算的值
        if (dict[n] != 0){
            return dict[n];
        }
        // 若备忘录中无，则递归计算，存入备忘录
        dict[n] = fibTool(dict, n - 1) + fibTool(dict, n - 2);
        return dict[n];
    }

    /**
     * 迭代(dp数组),自底向上求解
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了68.45%的用户
     * */
    public int fib3_dp1(int N){
        if (N < 1){
            return 0;
        }
        if (N <= 2){
            return 1;
        }
        int[] dp = new int[N + 1];
        // 基本情况
        dp[1] = dp[2] = 1;
        // 根据基本情况，从下向上求解塔顶N的值
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }

    /**
     * 迭代(dp数组)优化,自底向上求解
     * 省略dp数组存储，优化空间。仅保留与迭代相关的N-1和N-2，用于计算当前值
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了74.96%的用户
     * */
    public int fib3_dp2(int N){
        if (N < 1){
            return 0;
        }
        if (N == 1 || N == 2){
            return 1;
        }
        int prev = 1, curr = 1;
        for (int i = 3; i <= N ; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }

}
