package cn.czl.dynamicPlanning.backpack;

import org.junit.jupiter.api.Test;

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
        System.out.println(canPartition(NUMS));
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int temp : nums) {
            sum += temp;
        }
        if (sum % 2 != 0){
            return false;
        }
        int len = nums.length;
        boolean[][] dp = new boolean[len][sum/2];

        return dp[len-1][len-1];
    }
}
