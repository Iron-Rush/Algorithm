package cn.czl.math;

/**
 * @author RedRush
 * @date 2020/12/8 17:19
 * @description:
 *      268. 丢失的数字
 *      - 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 *      - 进阶：你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 *      示例 1：
 *          - 输入：nums = [3,0,1]
 *          - 输出：2
 *      示例 2：
 *          - 输入：nums = [0,1]
 *          - 输出：2
 *      示例 3：
 *          - 输入：nums = [9,6,4,2,3,5,7,0,1]
 *          - 输出：8
 *      提示：
 *      - n == nums.length
 *      - 1 <= n <= 104
 *      - 0 <= nums[i] <= n
 *      - nums 中的所有数字都 独一无二
 */
public class MissingNumber_Easy {

    /**
     * 布尔数组记录nums中已有数字，返回布尔数组中未改变的下标
     * 执行用时：1 ms, 在所有 Java 提交中击败了54.95%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了95.90%的用户
     * */
    public int missingNumber(int[] nums) {
        boolean[] flags = new boolean[nums.length+1];
        for (int num : nums) {
            flags[num] = true;
        }
        for (int i = 0; i < flags.length; i++) {
            if(!flags[i]){
                return i;
            }
        }
        return 0;
    }

    /**
     * 等差数列求和，对总和与nums中数字进行减法运算。最后剩余即为所求
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了70.49%的用户
     * */
    public int missingNumber2(int[] nums) {
        // 首项：0, 末项：nums.length, 项数：nums.length + 1
        int sum = (nums.length * (nums.length + 1))/2;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }

    /**
     * 位运算[异或运算（XOR）]
     * 对一个数进行两次完全相同的异或运算会得到原来的数，
     * 因此可以通过异或运算找到缺失的数字。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了57.11%的用户
     * */
    public int missingNumber3(int[] nums) {
        // 首项：0, 末项：nums.length, 项数：nums.length + 1
        int basic = nums.length;
        for (int i = 0; i < nums.length; i++) {
            basic = basic ^ i ^ nums[i];
        }
        return basic;
    }
}
