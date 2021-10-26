package cn.czl.list.search.max;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author RedRush
 * @date 2021/10/26 11:03
 * @description:
 *      128. 最长连续序列
 *      - 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *      - 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *      示例 1：
 *          输入：nums = [100,4,200,1,3,2]
 *          输出：4
 *          解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *      示例 2：
 *          输入：nums = [0,3,7,2,5,8,4,6,0,1]
 *          输出：9
 *      提示：
 *          0 <= nums.length <= 10^5
 *          -10^9 <= nums[i] <= 10^9
 */
public class LongestConsecutiveSequence_Normal {

    private int[] NUMS1 = {100,4,200,1,3,2};
    private int[] NUMS2 = {0,3,7,2,5,8,4,6,0,1};
    @Test
    public void TestSolution(){
        System.out.println(longestConsecutive2(NUMS1));
        System.out.println(longestConsecutive(NUMS2));
        System.out.println(longestConsecutive2(NUMS2));
    }

    /**
     * HashSet去重，通过循环判断set中是否存在+1元素，
     * 判断当前数字的起始的最长连续序列
     * 执行用时： 13 ms , 在所有 Java 提交中击败了 86.50% 的用户
     * 内存消耗： 53.4 MB , 在所有 Java 提交中击败了 39.75% 的用户
     * */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        int ans = 0;
        for(int num : set){
            if(!set.contains(num - 1)){
                int idx = num + 1;
                while(set.contains(idx)){
                    idx++;
                }
                ans = Math.max(ans, idx - num);
            }
        }
        return ans;
    }

    /**
     * 排序后遍历
     * 执行用时： 11 ms , 在所有 Java 提交中击败了 95.39% 的用户
     * 内存消耗： 44.4 MB , 在所有 Java 提交中击败了 94.55% 的用户
     * */
    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length < 1)    return 0;
        Arrays.sort(nums);
        int ans = 1;
        int curCount = 1;
        for(int i = 1; i < nums.length; i++){
            if (nums[i-1] == nums[i]){
                continue;
            }
            if(nums[i-1] == nums[i] - 1){
                curCount++;
                ans = Math.max(ans, curCount);
            }else{
                curCount = 1;
            }
        }
        return ans;
    }
}
