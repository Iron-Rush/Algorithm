package cn.czl.list.sum;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/1/28 10:01
 * @description:
 *      724. 寻找数组的中心索引
 *      - 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
 *      - 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 *      - 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 *      示例 1：
 *          输入：nums = [1, 7, 3, 6, 5, 6]
 *          输出：3
 *          解释：索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，
 *              与右侧数之和 (5 + 6 = 11) 相等。同时, 3 也是第一个符合要求的中心索引。
 *      示例 2：
 *          输入：nums = [1, 2, 3]
 *          输出：-1
 *          解释：数组中不存在满足此条件的中心索引。
 *      说明：
 *          nums 的长度范围为 [0, 10000]。
 *          任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。
 */
public class PivotIndex_Easy {

    private int[] NUMS1 = {1, 7, 3, 6, 5, 6};   // 3
    private int[] NUMS2 = {-1,-1,0,1,1,0};      // 5
    private int[] NUMS3 = {-1,-1,-1,0,1,1};     // 0

    @Test
    public void TestSolution(){
        System.out.println(pivotIndex(NUMS1));
        System.out.println(pivotIndex(NUMS2));
        System.out.println(pivotIndex(NUMS3));
    }

    /**
     * 统计两侧和，从左向右逐渐比较左侧和 与 右侧和
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 30.52% 的用户
     * */
    public int pivotIndex(int[] nums) {
        int len = nums.length;
        if(len <= 2){
            return len == 1 ? 0 : -1;
        }
        int rSum = 0;
        for(int num : nums){
            rSum += num;
        }
        int lSum = 0, index = nums[0];
        rSum = rSum - lSum - index;
        for(int i = 1; i < len; i++){
            if(lSum == rSum){
                return i-1;
            }
            lSum += index;
            index = nums[i];
            rSum -= index;
        }
        return lSum == 0 ? len-1 : -1;
    }

    /**
     * 判断逻辑优化
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39 MB , 在所有 Java 提交中击败了 77.94% 的用户
     * */
    public int pivotIndex2(int[] nums) {
        int len = nums.length;
        int rSum = 0, lSum = 0, index = 0;
        for(int num : nums){
            rSum += num;
        }
        for(int i = 0; i < len; i++){
            lSum += index;
            index = nums[i];
            rSum -= index;
            if(lSum == rSum){
                return i;
            }
        }
        return -1;
    }
}
