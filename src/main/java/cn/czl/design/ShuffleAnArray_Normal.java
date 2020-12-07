package cn.czl.design;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author RedRush
 * @date 2020/12/7 15:26
 * @description:
 *      384. 打乱数组
 *      - 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 *      - 实现 Solution class:
 *          - Solution(int[] nums) 使用整数数组 nums 初始化对象
 *          - int[] reset() 重设数组到它的初始状态并返回
 *          - int[] shuffle() 返回数组随机打乱后的结果
 *      示例：
 *          输入:
 *          ["Solution", "shuffle", "reset", "shuffle"]
 *          [[[1, 2, 3]], [], [], []]
 *          输出:
 *          [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 */
public class ShuffleAnArray_Normal {

    @Test
    public void TestSolution(){
        int[] nums = {1,2,3};
        Solution solution = new Solution(nums);
        int[] res1 = {};
        for (int i = 0; i < 10; i++) {
            res1 = solution.shuffle();
            System.out.println(Arrays.toString(res1));
        }
    }

    /**
     * 调用随机数库，根据随机数生成下标，交换数字
     * 执行用时：106 ms, 在所有 Java 提交中击败了44.00%的用户
     * 内存消耗：46.9 MB, 在所有 Java 提交中击败了59.71%的用户
     * */
    class Solution {

        private int[] nums = {};
        private int[] origin = {};

        public Solution(int[] nums) {
            this.nums = nums.clone();
            origin = new int[nums.length];
            int pos = 0;
            for (int temp : nums) {
                origin[pos++] = temp;
            }
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            int pos = 0;
            for (int temp : origin) {
                nums[pos++] = temp;
            }
            return nums;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            Random random = new Random();
            for (int i = 0; i < nums.length; i++) {
                swap(nums, i, random.nextInt(nums.length));
            }
            return nums;
        }

        private void swap(int[] nums, int a, int b){
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }

    /**
     * 执行用时：99 ms, 在所有 Java 提交中击败了76.14%的用户
     * 内存消耗：46.9 MB, 在所有 Java 提交中击败了56.55%的用户
     * */
    class Solution2 {

        private int[] nums = {};
        private int[] origin = {};

        Random random = new Random();

        public Solution2(int[] nums) {
            this.origin = nums.clone();
            this.nums = nums;
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            nums = origin.clone();
            return nums;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            for (int i = 0; i < nums.length; i++) {
                swap(nums, i, random.nextInt(nums.length - i) + i);
            }
            return nums;
        }

        private void swap(int[] nums, int a, int b){
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }
}
