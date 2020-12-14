package cn.czl.study.stackAndQueue.queue;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/12/12 23:18
 * @description:
 *      279. 完全平方数
 *      - 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *      示例 1:
 *          输入: n = 12
 *          输出: 3
 *          解释: 12 = 4 + 4 + 4.
 *      示例 2:
 *          输入: n = 13
 *          输出: 2
 *          解释: 13 = 4 + 9.
 */
public class NumSquares_Normal {

    @Test
    public void TestSolution(){
        System.out.println(numSquares(1));
    }

    /**
     * bfs-广度优先搜索[未优化]      Queue实现
     * 执行用时： 219 ms , 在所有 Java 提交中击败了 11.74% 的用户
     * 内存消耗： 255.8 MB , 在所有 Java 提交中击败了 5.02% 的用户
     * */
    public int numSquares(int n) {
        List<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        int step = 0;
        for (int i = 1; i * i <= n; i++) {
            list.add(i * i);
        }
        queue.offer(n);
        while (!queue.isEmpty() && n != 0){
            step++;
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                int val = queue.poll();
                for (int temp : list) {
                    int ans = val - temp;
                    if(ans > 0){
                        queue.offer(ans);
                    }else if(ans == 0){
                        return step;
                    }else{
                        break;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * bfs-广度优先搜索           Set实现
     * 执行用时： 42 ms , 在所有 Java 提交中击败了 63.68% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 24.37% 的用户
     * */
    public int numSquares2(int n) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int step = 0;
        set.add(n);
        for (int i = 1; i * i <= n; i++) {
            list.add(i * i);
        }
        while (set.size() > 0){
            step++;
            Set<Integer> nextSet = new HashSet<>();
            for (int val : set) {
                for (int square : list) {
                    int ans = val - square;
                    if(ans > 0){
                        nextSet.add(ans);
                    }else if(ans == 0){
                        return step;
                    }else {
                        break;
                    }
                }
            }
            set = nextSet;
        }
        return -1;
    }

    /**
     * 动态规划     选择抵达每个数的最优步数。
     * 执行用时： 45 ms , 在所有 Java 提交中击败了 43.68% 的用户
     * 内存消耗： 37.6 MB , 在所有 Java 提交中击败了 74.33% 的用户
     * */
    public int numSquares3(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int squareMax = (int) Math.sqrt(n) + 1;
        int[] squareArr = new int[squareMax];
        for (int i = 1; i < squareMax; i++) {
            squareArr[i] = i * i;
        }
        for (int i = 0; i <= n; i++) {
            for (int sq = 1; sq < squareMax; sq++) {
                if(i < squareArr[sq]){
                    break;
                }
                dp[i] = Math.min(dp[i], dp[i - squareArr[sq]] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 数学运算     三平方定理
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.5 MB , 在所有 Java 提交中击败了 98.07% 的用户
     * */
    public int numSquares4(int n){
        while (n % 4 == 0){
            n /= 4;
        }
        if(n % 8 == 7){
            return 4;
        }
        if(isSquare(n)){
            return 1;
        }
        for (int i = 1; i * i <= n; i++) {
            if(isSquare(n - i * i)){
                return 2;
            }
        }
        return 3;
    }
    // 判断当前数是否为完全平方数
    private boolean isSquare(int n){
        int sq = (int)Math.sqrt(n);
        return n == sq * sq;
    }

}
