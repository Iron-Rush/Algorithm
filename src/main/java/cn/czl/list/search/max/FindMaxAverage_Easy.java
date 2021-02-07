package cn.czl.list.search.max;

/**
 * @author RedRush
 * @date 2021/2/4 11:05
 * @description:
 *      643. 子数组最大平均数 I
 *      - 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 *      示例：
 *          输入：[1,12,-5,-6,50,3], k = 4
 *          输出：12.75
 *          解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *      提示：
 *          1 <= k <= n <= 30,000。
 *          所给数据范围 [-10,000，10,000]。
 */
public class FindMaxAverage_Easy {


    /**
     * 根据滑动窗口内当前总和，计算出指定长度内最大和
     * 根据最大和得出最大平均数
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42.8 MB , 在所有 Java 提交中击败了 27.89% 的用户
     * */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for(int i = 0; i < k; i++){
            sum += nums[i];
        }
        int res = sum;
        for(int i = k; i < nums.length; i++){
            sum = sum + nums[i] - nums[i-k];
            res = Math.max(sum, res);
        }
        return res/(double)k;
    }
}
