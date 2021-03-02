package cn.czl.list.slidingWindow;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author RedRush
 * @date 2021/2/24 13:20
 * @description:
 *      239. 滑动窗口最大值
 *      - 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 *      你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *      - 返回滑动窗口中的最大值。
 *      示例 1：
 *          输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 *          输出：[3,3,5,5,6,7]
 *          解释：
 *           滑动窗口的位置                最大值
 *           ---------------               -----
 *           [1  3  -1] -3  5  3  6  7       3
 *            1 [3  -1  -3] 5  3  6  7       3
 *            1  3 [-1  -3  5] 3  6  7       5
 *            1  3  -1 [-3  5  3] 6  7       5
 *            1  3  -1  -3 [5  3  6] 7       6
 *            1  3  -1  -3  5 [3  6  7]      7
 *      示例 2：
 *          输入：nums = [1], k = 1
 *          输出：[1]
 *      示例 3：
 *          输入：nums = [1,-1], k = 1
 *          输出：[1,-1]
 *      示例 4：
 *          输入：nums = [9,11], k = 2
 *          输出：[11]
 *      示例 5：
 *          输入：nums = [4,-2], k = 2
 *          输出：[4]
 *      提示：
 *          1 <= nums.length <= 10^5
 *          -10^4 <= nums[i] <= 10^4
 *          1 <= k <= nums.length
 */
public class SlidingWindowMaximum_Hard {

    private int[] NUMS1 = {1,3,-1,-3,5,3,6,7};
    private int[] NUMS2 = {1};
    private int[] NUMS3 = {1, -1};
    private int[] NUMS4 = {9, 11};

    @Test
    public void TestSolution(){
        System.out.println(Arrays.toString(maxSlidingWindow(NUMS1, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow(NUMS2, 1)));
        System.out.println(Arrays.toString(maxSlidingWindow(NUMS3, 1)));
        System.out.println(Arrays.toString(maxSlidingWindow(NUMS4, 2)));
    }

    
    public int[] maxSlidingWindow(int[] nums, int k) {
        /**
         * @Author: RedRush
         * @Date:   2021/2/26 9:41
         * @param nums
         * @param k
         * @Return: int[]
         * @description:
         */
        int len = nums.length;
        if(len <= 1 || k == 1){
            return nums;
        }
        int max = Integer.MIN_VALUE;
        int[] res = new int[len - k + 1];
        for (int i = 0; i < k; i++) {
            max = Math.max(max, nums[i]);
        }
        int index = 0;
        res[index++] = max;
        for (int i = k; i < len; i++) {
            if(nums[i-k] == max){
                max = nums[i-k+1];
                for (int j = i-k+1; j < i+1; j++) {
                    max = Math.max(max, nums[j]);
                }
            }
            max = Math.max(max, nums[i]);
            res[index++] = max;
        }
        return res;
    }
}
