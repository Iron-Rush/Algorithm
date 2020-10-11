package cn.czl.dynamicPlanning.backpack;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/9/20 17:32
 * @description:
 *      416. 分割等和子集
 *          给定一个只包含正整数的非空数组。
 *          是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *      注意:   每个数组中的元素不会超过 100
 *              数组的大小不会超过 200
 *      示例:
 *          输入: [1, 5, 11, 5]
 *          输出: true
 *          解释: 数组可以分割成 [1, 5, 5] 和 [11].
 */
public class CanPartition_01_Normal {

    private static int[] NUMS = new int[]{1,5,11,5};
    @Test
    public void TestSolution(){
        System.out.println(canPartition4(NUMS));
    }

    /**
     * 递归取数(超出时间限制)
     * */
    boolean flag = false;
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int temp : nums) {
            sum += temp;
        }
        if (sum % 2 != 0){
            return false;
        }
        Arrays.sort(nums);
        dfs(nums, 0, sum/2, 0);
        return flag;
    }
    private void dfs(int[] nums, int pos, int target, int current){
        if (!flag){
            if (target > current){
                for (int i = pos; i < nums.length; i++) {
                    if (current + nums[i] <= target && !flag){
                        dfs(nums, i+1, target, current + nums[i]);
                    }else{
                        break;
                    }
                }
            }else if (target == current){
               flag = true;
           }
        }
    }

    /**
     * 动态规划(二维数组)
     * 执行用时：52 ms, 在所有 Java 提交中击败了21.15%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了6.04%的用户
     * */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int temp : nums) {
            sum += temp;
        }
        if (sum % 2 != 0){
            return false;
        }
        int len = nums.length;
        sum = sum/2;
        boolean[][] dp = new boolean[len+1][sum+1];
        for (int i = 0; i <= len ; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j-nums[i-1] < 0){
                    dp[i][j] = dp[i-1][j];  // 如果装不下当前数，则保存前一个容量的值
                }else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[len][sum];
    }

    /**
     * 动态规划(状态压缩-一维数组)
     * 执行用时：31 ms, 在所有 Java 提交中击败了54.88%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了61.50%的用户
     * */
    public boolean canPartition3(int[] nums) {
        int sum = 0;
        for (int temp : nums) {
            sum += temp;
        }
        if (sum % 2 != 0){
            return false;
        }
        int len = nums.length;
        sum = sum/2;
        boolean[] dp = new boolean[sum+1];
        dp[0] = true;
        for (int i = 0; i < len; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j-nums[i] >= 0){
                    dp[j] = dp[j] || dp[j - nums[i]];  // 如果装不下当前数，则保存前一个容量的值
                }
            }
        }
        return dp[sum];
    }

    /**
     * 简易备忘录(可优化)
     * 执行用时：131 ms, 在所有 Java 提交中击败了5.00%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了25.19%的用户
     * */
    public boolean canPartition4(int[] nums) {
        // 常规判定
        int sum = 0;
        for (int temp : nums) {
            sum += temp;
        }
        if (sum % 2 != 0){
            return false;
        }
        sum = sum/2;
        HashSet<Integer> set = new HashSet<Integer>();
        set.add(0);
        // 顺序取数字
        for (int num : nums) {
            // 和既有和操作，key = 0时，说明不取这个数
            List<Integer> tempList = new ArrayList<>();
            for (int temp : set) {
                int current = num + temp;
                // 判断是否满足要求
                if (sum == current){
                    return true;
                }
                tempList.add(current);
//                set.add(current);
            }
            set.addAll(tempList);
        }
        return false;
    }

}
