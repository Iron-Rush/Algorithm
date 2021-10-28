package cn.czl.dynamicPlanning.study.primary;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author RedRush
 * @date 2021/3/22 11:00
 * @description:
 *      300. 最长递增子序列
 *      - 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *      - 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *      示例 1：
 *          输入：nums = [10,9,2,5,3,7,101,18]
 *          输出：4
 *          解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *      示例 2：
 *          输入：nums = [0,1,0,3,2,3]
 *          输出：4
 *      示例 3：
 *          输入：nums = [7,7,7,7,7,7,7]
 *          输出：1
 *      提示：
 *          1 <= nums.length <= 2500
 *          -10^4 <= nums[i] <= 10^4
 *      进阶：
 *          你可以设计时间复杂度为 O(n2) 的解决方案吗？
 *          你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */
public class LongestIncreasingSubsequence_Normal {

    private int[] NUMS1 = {10,9,2,5,3,7,101,18};
    private int[] NUMS2 = {0,1,0,3,2,3};
    private int[] NUMS3 = {7,7,7,7,7,7,7,7};
    private int[] NUMS4 = {1,3,6,7,9,4,10,5,6};


    @Test
    public void TestSolution(){
//        System.out.println(lengthOfLIS4(NUMS1));     // 4
//        System.out.println(lengthOfLIS4(NUMS2));     // 4
//        System.out.println(lengthOfLIS4(NUMS3));     // 1
        System.out.println(lengthOfLIS4(NUMS4));     // 6
    }

    /**
     * 记忆化递归 - 借助HashMap记录递归结果
     * 执行用时： 472 ms , 在所有 Java 提交中击败了 5.43% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 5.32% 的用户
     * */
    HashMap<Integer, Integer> memory;
    public int lengthOfLIS(int[] nums) {
        memory = new HashMap<>();
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, helper(nums, i));
        }
        return res;
    }
    // 递归函数
    int helper(int[] nums, int i){
        if(memory.containsKey(i)){
            return memory.get(i);
        }
        int a = 1;
        for (int j = 0; j < i; j++) {
            if(nums[j] < nums[i]){
                a = Math.max(a, helper(nums, j) + 1);
            }
        }
        memory.put(i, a);
        return a;
    }


    /**
     * 记忆化递归 - 借助数组记录递归结果
     * 执行用时： 123 ms , 在所有 Java 提交中击败了 5.43% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 57.65% 的用户
     * */
    int[] arr;
    public int lengthOfLIS2(int[] nums) {
        int res = 0;
        arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) { // 比较以每个数字为结尾情况下的最优解
            res = Math.max(res, helper2(nums, i));
        }
        return res;
    }
    // 递归函数
    int helper2(int[] nums, int i){
        if (arr[i] != 0){
            return arr[i];
        }
        int a = 1;
        for (int j = 0; j < i; j++) {
            if(nums[j] < nums[i]){
                a = Math.max(a, helper2(nums, j) + 1);
            }
        }
        arr[i] = a;
        return a;
    }

    /**
     * 动态规划
     * 执行用时： 76 ms , 在所有 Java 提交中击败了 33.88% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 40.54% 的用户
     * */
    public int lengthOfLIS3(int[] nums) {
        int len = nums.length;
        if(len == 0){
            return 0;
        }
        int ans = 1;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]); // dp[i]为以当前下标结尾下的最优解
        }
        return ans;
    }

    /**
     * 贪心 + 二分查找
     * 新建数组，保存当前最长上升子序列
     * 遍历目标数组，如果当前数大于新建数组的尾部，则插入到新数组尾部
     *             否则用它覆盖比它大的元素中最小的那个
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 94.03% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 56.81% 的用户
     * */
    public int lengthOfLIS4(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int ans = 1;
        int[] arr = new int[len + 1];
        arr[ans] = nums[0];
        for(int num : nums){
            if(num > arr[ans]){     // 如果当前数字 > 拼接数组尾部元素，则插入至数组尾部
                arr[++ans] = num;
            }else {         // 否则用它覆盖比当前数字大的数字中的最小的数字
                int l = 1, r = ans, pos = 0;    // 如果找不到，则说有数均比nums[i]大，此时更新arr[1]，因此pos初始化为0
                while (l <= r){
                    int mid = (l + r) / 2;
                    if(arr[mid] < num){
                        pos = mid;
                        l = mid + 1;
                    }else {
                        r = mid - 1;
                    }
                }
                arr[pos + 1] = num;
            }
        }
        return ans;
    }

}
