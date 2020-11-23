package cn.czl.list.search.contains;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author RedRush
 * @date 2020/11/17 16:11
 * @description:
 *      219. 存在重复元素 II
 *      - 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 *      使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 *      示例 1:
 *          输入: nums = [1,2,3,1], k = 3     输出: true
 *      示例 2:
 *          输入: nums = [1,0,1,1], k = 1     输出: true
 *      示例 3:
 *          输入: nums = [1,2,3,1,2,3], k = 2 输出: false
 */
public class ContainsDuplicate2_Nearby_Easy {

    private int[] NUMS1 = new int[]{1,2,3,1};   // true
    private int K1 = 3;
    private int[] NUMS2 = new int[]{1,1};   // true
    private int K2 = 2;
    private int[] NUMS3 = new int[]{1,2,3,1,2,3};// false
    private int K3 = 2;

    @Test
    public void TestSolution(){
        System.out.println(containsNearbyDuplicate(NUMS3, K3));
    }

    /**
     * 遍历，存入HashMap，若存在相同值的数则判断下标是否符合要求
     * 若不符合要求则更新map中的下标。
     * 执行用时：10 ms, 在所有 Java 提交中击败了65.42%的用户
     * 内存消耗：44 MB, 在所有 Java 提交中击败了34.99%的用户
     * */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int pos = 0; pos < nums.length; pos++) {
            if(!map.containsKey(nums[pos])){
                int index = map.get(nums[pos]);
                if(pos - index <= k){
                    return true;
                }
            }
            map.put(nums[pos], pos);
        }
        return false;
    }

    /**
     * 维护一个HashSet
     * 执行用时：10 ms, 在所有 Java 提交中击败了65.42%的用户
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了70.73%的用户
     * */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k){
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
