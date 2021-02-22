package cn.czl.list.slidingWindow;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RedRush
 * @date 2021/2/20 9:15
 * @description:
 *      697. 数组的度
 *      - 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 *      - 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *      示例 1：
 *          输入：[1, 2, 2, 3, 1]
 *          输出：2
 *          解释：
 *              输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 *              连续子数组里面拥有相同度的有如下所示:
 *              [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 *              最短连续子数组[2, 2]的长度为2，所以返回2.
 *      示例 2：
 *          输入：[1,2,2,3,1,4,2]
 *          输出：6
 *      提示：
 *          nums.length 在1到 50,000 区间范围内。
 *          nums[i] 是一个在 0 到 49,999 范围内的整数。
 */
public class FindShortestSubArray_Easy {

    private int[] NUMS1 = {1, 2, 2, 3, 1};  // 2
    private int[] NUMS2 = {1,2,2,3,1,4,2};  // 6

    @Test
    public void TestSolution(){
        System.out.println(findShortestSubArray(NUMS1));
        System.out.println(findShortestSubArray(NUMS2));
    }

    /**
     * 先获取频率最高的数字，再分别找高频数字的最短子数组
     * 执行用时： 50 ms , 在所有 Java 提交中击败了 11.91% 的用户
     * 内存消耗： 44 MB , 在所有 Java 提交中击败了 6.92% 的用户
     * */
    public int findShortestSubArray(int[] nums) {
        int minLen = nums.length;
        int maxCount = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            int cur = map.getOrDefault(n, 0) + 1;
            map.put(n, cur);
            maxCount = Math.max(maxCount, cur);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == maxCount){
                minLen = Math.min(minLen, getLength(nums, entry.getKey()));
            }
        }
        return minLen;
    }
    // 获取包含全部target的最短子数组长度
    int getLength(int[] nums, int target){
        int l = 0, r = nums.length - 1;
        while (nums[l] != target){
            l++;
        }
        while (nums[r] != target){
            r--;
        }
        return r - l + 1;
    }

    /**
     * map记录每个数字，对应的数量，起始位置，终止位置
     * 遍历map，对比出现数量的同时，通过起始、终止位置得出子数组长度
     * 执行用时： 19 ms , 在所有 Java 提交中击败了 82.03% 的用户
     * 内存消耗： 42.9 MB , 在所有 Java 提交中击败了 33.80% 的用户
     * */
    public int findShortestSubArray2(int[] nums) {
        int minLen = 0, maxCount = 0;
        Map<Integer, int[]> map = new HashMap<>();  // int[] 0表示数量，1表示起始位置，2表示结束位置
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if(map.containsKey(num)){
                int[] arr = map.get(num);
                arr[0]++;
                arr[2] = i;
            }else {
                map.put(num, new int[]{1, i, i});
            }
        }
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            if(maxCount < arr[0]){
                maxCount = arr[0];
                minLen = arr[2] - arr[1] + 1;
            }else if(maxCount == arr[0]){
                minLen = Math.min(minLen, arr[2] - arr[1] + 1);
            }
        }
        return minLen;
    }
}
