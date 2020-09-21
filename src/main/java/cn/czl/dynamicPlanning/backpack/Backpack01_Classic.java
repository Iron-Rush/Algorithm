package cn.czl.dynamicPlanning.backpack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/9/18 23:52
 * @description:
 *          0-1 背包问题
 *          给你一个可装载重量为W的背包和N个物品，
 *          每个物品有重量和价值两个属性。
 *          其中第i个物品的重量为wt[i]，价值为val[i]，
 *          现在让你用这个背包装物品，最多能装的价值是多少？
 *      - 输入:
 *          N = 3, W = 4
 *          wt = [2, 1, 3]
 *          val = [4, 2, 3]
 *      - 输出: 6
 */
public class Backpack01_Classic {
    private static int N = 3, W = 4;
    private static int[] wt = new int[]{2,1,3};
    private static int[] val = new int[]{4,2,3};

    @Test
    public void TestSolution(){
        System.out.println(backpack01(4,3,wt,val));
        System.out.println(backpack01_Reduce(4,3,wt,val));
    }
    /**
     * dp二维数组
     * 判断当前物品，装/不装进背包
     * */
    public int backpack01(int weight, int n, int[] wt, int[] val){
        int[][] dp = new int[n+1][weight+1];    // 从背包容量为0开始，向上层递推最优解
        for (int i = 1; i <= n; i++) {          // 背包物品容量递推层
            for (int w = 1; w <= weight; w++) { // 背包物品重量递推层
                if (w - wt[i-1] < 0){   // 若当前背包塞不下，则不装入背包(则该位置为前一个背包物品容量的解)
                    dp[i][w] = dp[i-1][w];
                }else {                 // 择优，判断是否装入背包
                    dp[i][w] = Math.max(dp[i-1][w-wt[i-1]] + val[i-1],
                                        dp[i-1][w]);
                }
            }
        }
        return dp[n][weight];
    }
    /**
     * dp状态压缩：一维数组
     * */
    public int backpack01_Reduce(int weight, int n, int[] wt, int[] val){
        int[] dp = new int [weight + 1];    // 缓存当前状态数组(否则循环运算时，会读取到本次循环的值)
        for (int i = 1; i <= n; i++){
            int[] preList = Arrays.copyOf(dp, weight+1);
            for (int w = 1; w <= weight; w++) {
                if (w >= wt[i-1]){
                    dp[w] = Math.max(preList[w - wt[i-1]] + val[i-1], preList[w]);
                }
            }
        }
        return dp[weight];
    }
}
