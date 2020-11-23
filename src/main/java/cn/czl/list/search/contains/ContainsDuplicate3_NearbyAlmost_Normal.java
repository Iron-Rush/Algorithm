package cn.czl.list.search.contains;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/11/17 17:09
 * @description:
 *      220. 存在重复元素 III
 *      - 在整数数组 nums 中，是否存在两个下标 i 和 j，
 *      使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，
 *      且满足 i 和 j 的差的绝对值也小于等于 ķ 。
 *      - 如果存在则返回 true，不存在返回 false。
 */
public class ContainsDuplicate3_NearbyAlmost_Normal {

    private int[] NUMS1 = {1,5,9,1,5,9};
    private int K1 = 2;
    private int T1 = 3;

    @Test
    public void TestSolution(){
        System.out.println(containsNearbyAlmostDuplicate(NUMS1, K1, T1));
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long bigger = treeSet.ceiling((long)nums[i]);   // 获取>=当前数的数字
            if (bigger != null && bigger <= nums[i] + t){
                return true;
            }
            Long smaller = treeSet.floor((long)nums[i]);    // 获取<=当前数的数字
            if (smaller != null && smaller >=nums[i] - t){
                return true;
            }
            treeSet.add((long)nums[i]);
            if (treeSet.size() > k){
                treeSet.remove(nums[i - k]);
            }
        }
        return false;
    }

}
