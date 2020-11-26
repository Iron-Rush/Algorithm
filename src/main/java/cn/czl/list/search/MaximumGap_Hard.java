package cn.czl.list.search;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * @author RedRush
 * @date 2020/11/26 14:28
 * @description:
 *      164. 最大间距
 *      - 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 *      - 如果数组元素个数小于 2，则返回 0。
 *      示例 :
 *          输入: [3,6,9,1]
 *          输出: 3
 *          解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 *      - 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 *      - 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 */
public class MaximumGap_Hard {

    private int[] NUMS = {};

    @Test
    public void TestSolution(){

    }

    /**
     * 排序后，再遍历，寻找最大间隔
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.72%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了47.83%的用户
     * */
    public int maximumGap(int[] nums) {
        if(nums.length <= 1){
            return 0;
        }
        Arrays.sort(nums);
        int max = -1, prev = nums[0];
        for(int i = 1; i < nums.length; i++){
            int dist = nums[i] - prev;
            max = Math.max(max, dist);
            prev = nums[i];
        }
        return max;
    }

    /**
     * 排序后，再遍历，寻找最大间隔2
     * 先比较，再赋值
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.72%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了47.83%的用户
     * */
    public int maximumGap2(int[] nums) {
        if(nums.length <= 1){
            return 0;
        }
        Arrays.sort(nums);
        int max = -1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] - nums[i-1] > max) {
                max = nums[i] - nums[i-1];
            }
        }
        return max;
    }

    /**
     * 桶排序
     * 执行用时：4 ms, 在所有 Java 提交中击败了41.08%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了92.27%的用户
     * */
    public int maximumGap3(int[] nums) {
        if(nums.length <= 1){
            return 0;
        }
        int len = nums.length;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        if (max == min){    // 最大值与最小值相等时，间隔必都为0
            return 0;
        }
        int[] bucketMin = new int[len - 1];
        int[] bucketMax = new int[len - 1];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, -1);
        // 计算桶的平均间隔
        int interval = (int)Math.ceil((double)(max - min) / (len-1));
        for (int num : nums) {
            if (num == min || num == max){
                continue;
            }
            int ID = (num - min) / interval;
            bucketMax[ID] = Math.max(bucketMax[ID], num);
            bucketMin[ID] = Math.min(bucketMin[ID], num);
        }
        int maxGap = 0, preMax = min;
        for (int i = 0; i < bucketMax.length; i++) {
            if (bucketMax[i] == -1){
                continue;
            }
            maxGap = Math.max(bucketMin[i] - preMax, maxGap);
            preMax = bucketMax[i];
        }
        maxGap = Math.max(max - preMax, maxGap);
        return maxGap;
    }
}
