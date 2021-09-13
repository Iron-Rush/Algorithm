package cn.czl.dynamicPlanning.study.primary;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RedRush
 * @date 2020/12/10 10:13
 * @description:
 *      70. 爬楼梯
 *      - 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *      - 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *      - 注意：给定 n 是一个正整数。
 *      示例 1：
 *          输入： 2       输出： 2
 *          解释： 有两种方法可以爬到楼顶。
 *      示例 2：
 *          输入： 3       输出： 3
 *          解释： 有三种方法可以爬到楼顶。
 *              1.  1 阶 + 1 阶 + 1 阶
 *              2.  1 阶 + 2 阶
 *              3.  2 阶 + 1 阶
 */
public class ClimbStairs_Easy {

    @Test
    public void TestSolution(){
        for (int i = 0; i < 8; i++) {
            System.out.println(climbStairs(i));
            System.out.println(climbStairs2(i));
            System.out.println(climbStairs3(i));
        }
    }

    /**
     * 记忆化递归
     * 每次可选择 1/2两个台阶。
     * 因此当前情况可由 n-1 和 n-2 两种情况递推得出
     * */
    Map<Integer, Integer> memo = new HashMap<>();
    public int climbStairs(int n) {
        if (n <= 2){
            return n;
        }
        if (memo.containsKey(n)){
            return memo.get(n);
        }
        int ans = climbStairs(n - 1) + climbStairs(n - 2);
        memo.put(n, ans);
        return ans;
    }

    /**
     * 因为每次仅能爬1/2个台阶。因此n的步数由n-1与n-2推导出
     * counter[i] = counter[i-1] + counter[i-2];
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了36.55%的用户
     * */
    public int climbStairs2(int n) {
        if(n <= 0) return 0;
        int[] counter = new int[n+1];
        counter[0] = 1;
        counter[1] = 1;
        for (int i = 2; i <= n; i++) {
            counter[i] = counter[i-1] + counter[i-2];
        }
        return counter[n];
    }

    /**
     * 动态规划 - 状态压缩
     * */
    public int climbStairs3(int n) {
        if(n <= 2){
            return n;
        }
        int cur = 2, pre = 1;
        for (int i = 3; i <= n; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }
}
