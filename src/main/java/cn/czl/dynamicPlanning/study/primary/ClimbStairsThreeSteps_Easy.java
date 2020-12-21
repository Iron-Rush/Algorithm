package cn.czl.dynamicPlanning.study.primary;

/**
 * @author RedRush
 * @date 2020/12/21 10:08
 * @description:
 *      面试题 08.01. 三步问题
 *      - 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
 *      示例1:
 *        输入：n = 3
 *        输出：4
 *      示例2:
 *        输入：n = 5
 *        输出：13
 *      - 提示: n范围在[1, 1000000]之间
 */
public class ClimbStairsThreeSteps_Easy {

    /**
     * 动态规划     dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
     * 执行用时： 33 ms , 在所有 Java 提交中击败了 59.43% 的用户
     * 内存消耗： 44 MB , 在所有 Java 提交中击败了 36.24% 的用户
     * */
    public int waysToStep(int n) {
        if(n <= 2){
            return n == 2 ? 2 : 1;
        }
        long[] dp = new long[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++){
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % 1000000007;
        }
        return (int)dp[n];
    }
}
