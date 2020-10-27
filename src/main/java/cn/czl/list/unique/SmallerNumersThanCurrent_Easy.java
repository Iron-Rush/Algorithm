package cn.czl.list.unique;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RedRush
 * @date 2020/10/26 18:02
 * @description:
 *      1365. 有多少小于当前数字的数字
 *          - 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 *          - 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 *          - 以数组形式返回答案。
 *      示例 1：
 *          输入：nums = [8,1,2,2,3]   输出：[4,0,1,1,3]
 *          解释：
 *              对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 *              对于 nums[1]=1 不存在比它小的数字。
 *              对于 nums[2]=2 存在一个比它小的数字：（1）。
 *              对于 nums[3]=2 存在一个比它小的数字：（1）。
 *              对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 */
public class SmallerNumersThanCurrent_Easy {

    /**
     * 拷贝数组并排序，通过map记录数组对应的位置。
     * 执行用时：4 ms, 在所有 Java 提交中击败了73.11%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了90.00%的用户
     * */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        Map<Integer, Integer> memory = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) {
            if (!memory.containsKey(sorted[i])){
                memory.put(sorted[i], i);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = memory.get(nums[i]);
        }
        return nums;
    }

}
