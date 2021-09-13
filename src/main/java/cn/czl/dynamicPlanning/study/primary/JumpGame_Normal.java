package cn.czl.dynamicPlanning.study.primary;

/**
 * @author RedRush
 * @date 2021/8/28 14:30
 * @description:
 *      55. 跳跃游戏
 *      - 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *      - 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *      - 判断你是否能够到达最后一个下标。
 *      示例 1：
 *          输入：nums = [2,3,1,1,4]
 *          输出：true
 *          解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *      示例 2：
 *          输入：nums = [3,2,1,0,4]
 *          输出：false
 *          解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *      提示：
 *          1 <= nums.length <= 3 * 104
 *          0 <= nums[i] <= 105
 */
public class JumpGame_Normal {

    /**
     * 贪心，获取每个位置可以走到的最远距离
     * 如果某个位置<=0，则无法继续前进
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 52.44% 的用户
     * 内存消耗： 40 MB , 在所有 Java 提交中击败了 6.47% 的用户
     * */
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int cur = nums[0];
        for (int i = 1; i < len; i++) {
            if (cur == 0){
                return false;
            }
            cur = Math.max(--cur, nums[i]);
        }
        return true;
    }

    /**
     * 迭代条件优化
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 96.93% 的用户
     * 内存消耗： 39.9 MB , 在所有 Java 提交中击败了 20.68% 的用户
     * */
    public boolean canJump2(int[] nums) {
        int len = nums.length;
        int cur = nums[0], i = 1;
        for(; i < len && cur > 0; i++){
            cur = Math.max(--cur, nums[i]);
        }
        return i == len;
    }
}
