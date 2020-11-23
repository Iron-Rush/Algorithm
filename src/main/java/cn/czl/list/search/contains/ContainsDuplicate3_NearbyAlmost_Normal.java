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
    private int[] NUMS2 = {-2147483648,2147483647};
    private int K2 = 1;
    private int T2 = 1;


    @Test
    public void TestSolution(){
        System.out.println(containsNearbyAlmostDuplicate1(NUMS2, K2, T2));
    }

    /**
     * 二叉搜索树(TreeSet实现的有序集合)
     * 控制树的大小在[i-k, i+k],通过TreeSet获取>=和<=当前数的两个数，
     * 判断两数是否有符合条件的
     * 执行用时：54 ms, 在所有 Java 提交中击败了10.53%的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了39.36%的用户
     * */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];
            Long bigger = treeSet.ceiling(num);   // 获取>=当前数的数字
            Long smaller = treeSet.floor(num);    // 获取<=当前数的数字
            Long max = num + t;
            Long min = num - t;
            if (bigger != null && bigger <= max){
                return true;
            }
            if (smaller != null && smaller >= min){
                return true;
            }
            treeSet.add(num);
            if (treeSet.size() > k){
                treeSet.remove((long)nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 暴力解
     * */
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = Math.max(i-k, 0); j < i; j++) {    // 防止负数下标
                if (Math.abs((long)nums[i] - (long)nums[j]) <= t){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 桶排序
     * 执行用时：25 ms, 在所有 Java 提交中击败了56.05%的用户
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了14.13%的用户
     * */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {// k为下标范围，t为值范围
        if (t < 0) {
            return false;
        }
        HashMap<Long, Long> map = new HashMap<>();
        long width = t + 1; // 一个桶内，数字范围的个数是 t+1
        for (int i = 0; i < nums.length; i++) {
            // 删除窗口左端数字
            if(i > k){
                map.remove(getBucket(nums[i-k-1], width));
            }
            // 获取当前桶号
            long bucket = getBucket(nums[i], width);
            if (map.containsKey(bucket)){
                return true;
            }
            if (map.containsKey(bucket+1) && map.get(bucket+1) - nums[i] < width){
                return true;
            }
            if (map.containsKey(bucket-1) && nums[i] - map.get(bucket-1) < width){
                return true;
            }
            map.put(bucket, (long)nums[i]);
        }
        return false;
    }
    // 获取桶号
    Long getBucket (long num, long width){
        if(num >= 0){   // 通过(数字/差值)得出桶号
            return num / width;
        }else { // 负数，先+1右移，获取桶号，再将桶号-1，以分割正数的桶号。
            return (num + 1) / width-1;
        }
    }

}
