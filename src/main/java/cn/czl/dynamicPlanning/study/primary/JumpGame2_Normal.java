package cn.czl.dynamicPlanning.study.primary;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/8/28 14:53
 * @description:
 *      45. 跳跃游戏 II
 *      - 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 *      - 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *      - 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *      - 假设你总是可以到达数组的最后一个位置。
 *      示例 1:
 *          输入: nums = [2,3,1,1,4]
 *          输出: 2
 *          解释: 跳到最后一个位置的最小跳跃数是 2。
 *               从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *      示例 2:
 *          输入: nums = [2,3,0,1,4]
 *          输出: 2
 *      提示:
 *          1 <= nums.length <= 10^4
 *          0 <= nums[i] <= 1000
 */
public class JumpGame2_Normal {

    private int[] NUMS1 = {2,3,1,1,4};
    private int[] NUMS2 = {2,3,0,1,4};
    private int[] NUMS3 = {1,2};
    private int[] NUMS4 = {1,1,1,1};


    @Test
    public void TestSolution(){
        System.out.println(jump(NUMS1));
        System.out.println(jump(NUMS2));
        System.out.println(jump(NUMS3));
        System.out.println(jump(NUMS4));
    }
    /**
     * 贪心算法 - 正向查找可以抵达的最远位置
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 61.38% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 42.91% 的用户
     * */
    public int jump(int[] nums) {
        int len = nums.length;
        if (len == 1){
            return 0;
        }
        int l = 0, r = nums[0];
        int count = 1;
        while (r < len - 1 && r > 0){
            int max = 0;
            while (l <= r){
                max = Math.max(max, nums[l] + l - r);
                l++;
            }
            count++;
            l = r;
            r += max;
        }
        return count;
    }


    /**
     * 贪心算法 - 正向查找可以抵达的最远位置2
     * 递推逻辑优化
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.18% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 53.45% 的用户
     * */
    public int jump2(int[] nums) {
        int len = nums.length;
        int count = 0;
        int end = 0, maxPos = 0;
        for (int i = 0; i < len - 1; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == end){
                end = maxPos;
                count++;
            }
        }
        return count;
    }
    
}
