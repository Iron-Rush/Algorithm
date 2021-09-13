package cn.czl.dynamicPlanning.study.primary;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2021/8/27 14:43
 * @description:
 *      740. 删除并获得点数
 *      - 给你一个整数数组 nums ，你可以对它进行一些操作。
 *      - 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 *      - 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *      示例 1：
 *          输入：nums = [3,4,2]
 *          输出：6
 *          解释：
 *              删除 4 获得 4 个点数，因此 3 也被删除。
 *              之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 *      示例 2：
 *          输入：nums = [2,2,3,3,3,4]
 *          输出：9
 *          解释：
 *              删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 *              之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 *              总共获得 9 个点数。
 *      提示：
 *          1 <= nums.length <= 2 * 10^4
 *          1 <= nums[i] <= 10^4
 */
public class DeleteAndEarn_Normal {

    private int[] NUMS1 = {2,2,3,3,3,4};

    @Test
    public void TestSolution(){
        System.out.println(deleteAndEarn3(NUMS1));
    }

    /**
     * 动态规划
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 29.02% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 5.04% 的用户
     * */
    public int deleteAndEarn(int[] nums) {
        if (nums == null){
            return 0;
        }
        int[] count = new int[10001];
        for (int num : nums) {
            count[num] += num;
        }
        int[] dp = new int[10001];
        dp[0] = 0;
        dp[1] = count[1];
        for (int i = 2; i < count.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + count[i]);
        }
        return dp[dp.length - 1];
    }

    /**
     * 动态规划 - 直接套用打家劫舍模版
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.9 MB , 在所有 Java 提交中击败了 89.01% 的用户
     * */
    public int deleteAndEarn2(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int MaxVal = 0;
        for(int num : nums){
            MaxVal = Math.max(MaxVal, num);
        }
        int[] scores = new int[MaxVal + 1];
        for(int num : nums){
            scores[num] += num;
        }
        return rob(scores);
    }
    // 打家劫舍模版
    private int rob(int[] nums){
        int len = nums.length;
        int pre = 0, curMax = nums[0];
        for (int i = 1; i < len; i++) {
            int max = Math.max(pre + nums[i], curMax);
            pre = curMax;
            curMax = max;
        }
        return curMax;
    }

    /**
     * 使用HashMap计数 + 动态规划
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 18.93% 的用户
     * 内存消耗： 38 MB , 在所有 Java 提交中击败了 81.82% 的用户
     * */
    public int deleteAndEarn3(int[] nums) {
        int maxVal = 0;
        Map<Integer, Integer> counter = new HashMap<>();
        for(int num : nums){
            counter.put(num, counter.getOrDefault(num, 0) + num);
            maxVal = Math.max(maxVal, num);
        }
        int pre = 0, curMax = 0;
        for (int i = 1; i <= maxVal; i++) {
            int max = Math.max(curMax, pre + counter.getOrDefault(i, 0));
            pre = curMax;
            curMax = max;
        }
        return curMax;
    }

    /**
     * 对连续区间进行dp运算
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 18.93% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 22.43% 的用户
     * */
    public int deleteAndEarn4(int[] nums) {
        int len = nums.length, ans = 0;
        Arrays.sort(nums);
        List<Integer> sum = new ArrayList<>();
        sum.add(nums[0]);
        int size = 1;
        for (int i = 1; i < len; i++) {
            int val = nums[i];
            if (val == nums[i-1]){
                sum.set(size-1, sum.get(size-1) + val);
            }else if (val == nums[i-1] + 1){
                sum.add(val);
                size++;
            }else {
                ans += rob(sum);
                sum.clear();
                sum.add(val);
                size = 1;
            }
        }
        ans += rob(sum);
        return ans;
    }
    // 打家劫舍模版[List<>版]
    public int rob(List<Integer> nums){
        int len = nums.size();
        int pre = 0, curMax = nums.get(0);
        for (int i = 1; i < len; i++) {
            int max = Math.max(curMax, pre + nums.get(i));
            pre = curMax;
            curMax = max;
        }
        return curMax;
    }
}
