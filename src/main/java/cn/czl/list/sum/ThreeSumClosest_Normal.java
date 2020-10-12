package cn.czl.list.sum;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/10/12 15:27
 * @description:
 *      16. 最接近的三数之和
 *          给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 *          找出 nums 中的三个整数，使得它们的和与 target 最接近。
 *          返回这三个数的和。假定每组输入只存在唯一答案。
 *      例：
 *          输入：nums = [-1,2,1,-4], target = 1
 *          输出：2
 *          解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 */
public class ThreeSumClosest_Normal {
    private int[] NUMS = new int[]{-1,2,1,-4};
    private int TARGET = 1;

    @Test
    public void TestSulution(){
        System.out.println(threeSumClosest(NUMS, TARGET));
    }

    /**
     * 双指针
     * 执行用时：8 ms, 在所有 Java 提交中击败了32.41%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了13.70%的用户
     * */
    public int threeSumClosest(int[] nums, int target) {
        int res = Integer.MAX_VALUE;
        int distance = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            int Lpos = i+1, Rpos = nums.length-1;
            while (Lpos < Rpos){
                int sum = nums[i] + nums[Lpos] + nums[Rpos];
                int curdist = Math.abs(target - sum);
                if (curdist < distance){
                    distance = curdist;
                    res = sum;
                }else if (sum > target){
                    Rpos--;
                }else {
                    Lpos++;
                }
            }
        }
        return res;
    }

    /**
     * 双指针，找到答案即返回
     * 执行用时：5 ms, 在所有 Java 提交中击败了96.32%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了98.14%的用户
     * */
    public int threeSumClosest2(int[] nums, int target) {
        int res = Integer.MAX_VALUE;
        int distance = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            int Lpos = i+1, Rpos = nums.length-1;
            while (Lpos < Rpos){
                int sum = nums[i] + nums[Lpos] + nums[Rpos];
                int curdist = Math.abs(target - sum);
                if (curdist < distance){
                    if (curdist == 0){
                        return sum;
                    }
                    distance = curdist;
                    res = sum;
                }
                if (sum > target){
                    Rpos--;
                }else {
                    Lpos++;
                }
            }
        }
        return res;
    }

    /**
     * 双指针+上下界剪枝
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.99%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了98.31%的用户
     * */
    public int threeSumClosest3(int[] nums, int target) {
        int len = nums.length;
        int res = nums[len-3] + nums[len-2] + nums[len-1];
        int way = Math.abs(target - res);
        //当数组仅有三个数或最后三位之和刚好等于target则直接返回
        if (len == 3 || res == target) {
            return res;
        }
        //排序数组，便于后面移动双指针
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            int l = i + 1;
            int r = len - 1;
            //优化代码段	判断上下界，提前结束循环
            int max = nums[i] + nums[r] + nums[r-1];
            int min = nums[i] + nums[l] + nums[l+1];
            if (max < target) {
                int dist = Math.abs(target - max);
                if (dist < way) {
                    way = dist;
                    res = max;
                }
            } else if (min > target) {
                int th = Math.abs(target - min);
                if (th < way) {
                    way = th;
                    res = min;
                }
            }else {
                while (l < r) {
                    int sum = nums[i]+nums[l]+nums[r];
                    int dist = Math.abs(target - sum);
                    if (dist < way) {
                        way = dist;
                        res = sum;
                    }
                    //当前和小于target则左指针右移
                    //当前和大于target则右指针左移
                    //若等于，则直接返回
                    if (sum < target) {
                        l++;
                    } else if (sum > target) {
                        r--;
                    } else {
                        return res;
                    }
                }
            }
        }
        return res;
    }

}
