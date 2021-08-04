package cn.czl.list.search.max;

import com.sun.org.apache.bcel.internal.generic.I2F;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/8/3 14:23
 * @description:
 *      581. 最短无序连续子数组
 *      - 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，
 *          如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *      - 请你找出符合题意的 最短 子数组，并输出它的长度。
 *      示例 1：
 *          输入：nums = [2,6,4,8,10,9,15]
 *          输出：5
 *          解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *      示例 2：
 *          输入：nums = [1,2,3,4]
 *          输出：0
 *      示例 3：
 *          输入：nums = [1]
 *          输出：0
 *      提示：
 *          1 <= nums.length <= 10^4
 *          -10^5 <= nums[i] <= 10^5
 *      进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
 */
public class ShortestUnsortedContinuousSubarray_Normal {

    private int[] NUMS1 = {2,6,4,8,10,9,15};
    private int[] NUMS2 = {1,2,3,4};
    private int[] NUMS3 = {1};

    @Test
    public void TestSolution(){
        System.out.println(findUnsortedSubarray3(NUMS1));
        System.out.println(findUnsortedSubarray3(NUMS2));
        System.out.println(findUnsortedSubarray3(NUMS3));
    }

    /**
     * 双指针 + 排序1
     * 与排序后数组作比较，分别获取两侧首个不同数字下标
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 60.08% 的用户
     * 内存消耗： 39.7 MB , 在所有 Java 提交中击败了 53.27% 的用户
     * */
    public int findUnsortedSubarray(int[] nums) {
        if (judge(nums)){
            return 0;
        }
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        int l = 0, r = nums.length - 1;
        while (nums[l] == sorted[l]){
            l++;
        }
        while (nums[r] == sorted[r]){
            r--;
        }
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != sorted[i]){
//                l = i;
//                break;
//            }
//        }
//        for (int i = nums.length - 1; i >= 0; i--) {
//            if (nums[i] != sorted[i]){
//                r = i;
//                break;
//            }
//        }
        return r - l + 1;
    }
    // 判断当前数组是否为升序数组
    private boolean judge(int[] nums){
        if (nums == null || nums.length < 1){
            return true;
        }
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < pre){
                return false;
            }else{
                pre = nums[i];
            }
        }
        return true;
    }

    /**
     * 双指针 + 排序2
     * 与排序后数组作比较，分别获取两侧首个不同数字下标
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 60.08% 的用户
     * 内存消耗： 39.6 MB , 在所有 Java 提交中击败了 65.46% 的用户
     * */
    public int findUnsortedSubarray2(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        int len = nums.length;
        int l = 0, r = nums.length - 1;
        while (r >= 0 && nums[r] == sorted[r]){
            r--;
        }
        if (r == -1){    // 如果右指针全部比对完，则说明两个数组一样，直接返回。
            return 0;
        }
        while (l < len && nums[l] == sorted[l]){
            l++;
        }
        return r - l + 1;
    }

    /**
     * 双指针 + 线性扫描
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.6 MB , 在所有 Java 提交中击败了 62.27% 的用户
     * */
    public int findUnsortedSubarray3(int[] nums) {
        if (nums == null || nums.length < 2){
            return 0;
        }
        int len = nums.length;
        int lpos = 0, rpos = 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int curL = i, curR = len - i - 1;       // 左右当前扫描下标
            if (max > nums[curL]){                  // 如果左侧下标数，不满足升序规则，则更新排序终止位置
                lpos = curL;
            }
            if (min < nums[curR]){                  // 如果右侧下标数，不满足降序规则，则更新排序起始位置
                rpos = curR;
            }
            min = Math.min(min, nums[curR]);        // 更新当前最大值、最小值
            max = Math.max(max, nums[curL]);
        }
        return rpos == lpos ? 0 : lpos - rpos + 1;
    }

}
