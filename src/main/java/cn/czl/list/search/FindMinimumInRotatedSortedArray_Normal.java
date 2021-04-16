package cn.czl.list.search;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/4/8 9:19
 * @description:
 *      153. 寻找旋转排序数组中的最小值
 *      - 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 *          例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 *          - 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 *          - 若旋转 4 次，则可以得到 [0,1,2,4,5,6,7]
 *      - 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为
 *          数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *      - 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，
 *          并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *      示例 1：
 *          输入：nums = [3,4,5,1,2]
 *          输出：1
 *          解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 *      示例 2：
 *          输入：nums = [4,5,6,7,0,1,2]
 *          输出：0
 *          解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 *      示例 3：
 *          输入：nums = [11,13,15,17]
 *          输出：11
 *          解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
 *      提示：
 *          n == nums.length
 *          1 <= n <= 5000
 *          -5000 <= nums[i] <= 5000
 *          nums 中的所有整数 互不相同
 *          nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 */
public class FindMinimumInRotatedSortedArray_Normal {

    /**
     * 择端 - 降序搜索
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38 MB , 在所有 Java 提交中击败了 35.70% 的用户
     * */
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        int pre = nums[0];
        int l = 0, r = len - 1;
        if(nums[l] < nums[r]){
            while(l < len && nums[l] <= pre){
                pre = nums[l++];
            }
        }else{
            while(r >= 0 && nums[r] <= pre){
                pre = nums[r--];
            }
        }
        return pre;
    }

    /**
     * 排序 - 返回最小的
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 5.04% 的用户
     * */
    public int findMin2(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        Arrays.sort(nums);
        return nums[0];
    }

    /**
     * 遍历，取最小值
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 6.84% 的用户
     * */
    public int findMin3(int[] nums) {
        int minVal = Integer.MAX_VALUE;
        for (int num : nums) {
            minVal = Math.min(minVal, num);
        }
        return minVal;
    }

    /**
     * 二分搜索
     * */
    public int findMin4(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high){
            int mid = low + (high - low)/2;
            if(nums[mid] < nums[high]){
                high = mid;
            }else {
                low = mid + 1;
            }
        }
        return nums[low];
    }

}
