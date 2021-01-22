package cn.czl.math;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/1/20 9:16
 * @description:
 *      628. 三个数的最大乘积
 *      - 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *      示例 1:
 *          输入: [1,2,3]
 *          输出: 6
 *      示例 2:
 *          输入: [1,2,3,4]
 *          输出: 24
 *      注意:
 *          给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 *          输入的数组中任意三个数的乘积不会超出32位有符号整数的范围
 */
public class MaximumProduct_Easy {

    private int[] NUMS1 = {1,2,3};          // 6
    private int[] NUMS2 = {1,2,3,4};        // 24
    private int[] NUMS3 = {-8,-4,1,2,3};    // 96

    @Test
    public void TestSolution(){
        System.out.println(maximumProduct(NUMS1));
        System.out.println(maximumProduct(NUMS2));
        System.out.println(maximumProduct(NUMS3));
    }

    /**
     * 排序后，比较 最小两个数 和 最大值的乘积 与 最大三个数的乘积
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 69.60% 的用户
     * 内存消耗： 40.2 MB , 在所有 Java 提交中击败了 5.08% 的用户
     * */
    public int maximumProduct(int[] nums) {
        int len = nums.length;
        int result;
        Arrays.sort(nums);
        result = Math.max(nums[0] * nums[1] * nums[len-1], nums[len-1] * nums[len-2] * nums[len-3]);
        return result;
    }

    /**
     *
     * 一次遍历全部数组，记录最大的前三个数，和最小的两个数。
     * 比较 最小两个数与最大数的积 和 最大三个数的积
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 99.13% 的用户
     * 内存消耗： 40 MB , 在所有 Java 提交中击败了 36.84% 的用户
     * */
    public int maximumProduct2(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if(num > max1){
                max3 = max2;
                max2 = max1;
                max1 = num;
            }else if(num > max2){
                max3 = max2;
                max2 = num;
            }else if(num > max3){
                max3 = num;
            }
            if(num < min1){
                min2 = min1;
                min1 = num;
            }else if(num < min2){
                min2 = num;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
}
