package cn.czl.dynamicPlanning.calculate;

/**
 * @author RedRush
 * @date 2021/3/2 9:33
 * @description:
 *      303. 区域和检索 - 数组不可变
 *      - 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 *      - 实现 NumArray 类：
 *          - NumArray(int[] nums) 使用数组 nums 初始化对象
 *          - int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，
 *          包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 *      示例：
 *          输入：["NumArray", "sumRange", "sumRange", "sumRange"]
 *                [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 *          输出：[null, 1, -1, -3]
 *          解释：
 *              NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 *              numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 *              numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 *              numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 *      提示：
 *          0 <= nums.length <= 10^4
 *          -10^5 <= nums[i] <= 10^5
 *          0 <= i <= j < nums.length
 *          最多调用 10^4 次 sumRange 方法
 */
public class RangeSumArray_Easy {
}
/**
 * 基础方法，每次遍历求和
 * 执行用时： 80 ms , 在所有 Java 提交中击败了 16.18% 的用户
 * 内存消耗： 41.5 MB , 在所有 Java 提交中击败了 26.07% 的用户
 * */
class NumArray1 {

    private int[] NUMS;
    private int len;
    public NumArray1(int[] nums) {
        this.NUMS = nums;
        this.len = nums.length;
    }

    public int sumRange(int i, int j) {
        if(i > j || i < 0 || j >= len){
            return 0;
        }
        int sum = 0;
        for(; i <= j; i++){
            sum += NUMS[i];
        }
        return sum;
    }
}

/**
 * 动态规划，从前向后累加，计算出从0至每位上的和，根据差值得出每个区间的和
 * 执行用时： 9 ms , 在所有 Java 提交中击败了 99.94% 的用户
 * 内存消耗： 41.2 MB , 在所有 Java 提交中击败了 85.11% 的用户
 * */
class NumArray2 {

    private int[] dp;
    private int len;
    public NumArray2(int[] nums) {
        this.len = nums.length;
        dp = new int[len + 1];
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            dp[i + 1] = sum;
        }
    }

    public int sumRange(int i, int j) {
        if(i > j || i < 0 || j > len){
            return 0;
        }
        return dp[j + 1] - dp[i];
    }
}