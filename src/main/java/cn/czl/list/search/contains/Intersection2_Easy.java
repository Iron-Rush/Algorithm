package cn.czl.list.search.contains;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/11/19 14:12
 * @description:
 *      350. 两个数组的交集 II
 *      - 给定两个数组，编写一个函数来计算它们的交集。
 *      示例 1：
 *          输入：nums1 = [1,2,2,1], nums2 = [2,2]
 *          输出：[2,2]
 *      示例 2:
 *          输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *          输出：[4,9]
 *      说明：
 *          输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 *          我们可以不考虑输出结果的顺序。
 */
public class Intersection2_Easy {

    /**
     * 存储其中一个数组的 值-个数 至map
     * 再遍历另一个数组与map进行比较
     * 执行用时：3 ms, 在所有 Java 提交中击败了82.55%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了54.71%的用户
     * */
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> memory = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            memory.put(nums1[i], memory.getOrDefault(nums1[i], 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (memory.containsKey(nums2[i])){
                list.add(nums2[i]);
                int count = memory.get(nums2[i]) -1;
                if (count == 0){
                    memory.remove(nums2[i]);
                }else {
                    memory.put(nums2[i], count);
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 基于map实现-优化
     * 执行用时：3 ms, 在所有 Java 提交中击败了82.55%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了81.35%的用户
     * */
    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length){
            return intersect2(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int temp : nums1){
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        int[] res = new int[nums1.length];
        int pos = 0;
        for(int temp : nums2){
            if (map.containsKey(temp)){
                res[pos++] = temp;
                int count = map.get(temp) - 1;
                if(count == 0){
                    map.remove(temp);
                }else {
                    map.put(temp, count);
                }
            }
        }
        return Arrays.copyOfRange(res, 0 ,pos);
    }

    /**
     * 排序后，双指针取交集
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了56.50%的用户
     * */
    public int[] intersect3(int[] nums1, int[] nums2) {
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int pos1 = 0, pos2 = 0, pos = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        while (pos1 < nums1.length && pos2 < nums2.length){
            if (nums1[pos1] > nums2[pos2]){
                pos2++;
            }else if (nums1[pos1] < nums2[pos2]){
                pos1++;
            }else {
                res[pos++] = nums1[pos1++];
                pos2++;
            }
        }
        return Arrays.copyOfRange(res, 0, pos);
    }

}
