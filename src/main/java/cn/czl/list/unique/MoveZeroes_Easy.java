package cn.czl.list.unique;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/11/19 9:33
 * @description:
 *      283. 移动零
 *      - 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，
 *      同时保持非零元素的相对顺序。
 *      - 说明:
 *      1. 必须在原数组上操作，不能拷贝额外的数组。
 *      2. 尽量减少操作次数。
 *      - 示例:
 *          输入: [0,1,0,3,12]
 *          输出: [1,3,12,0,0]
 */
public class MoveZeroes_Easy {

    private int[] NUMS1 = {0,1,0,3,12};
    private int[] NUMS2 = {0,1,0};

    @Test
    public void TestSolution(){
        moveZeroes(NUMS2);
        System.out.println(Arrays.toString(NUMS2));
    }

    /**
     * 双指针，右指针负责寻找非零数，左指针负责指向下一位需要交换的数字。
     * 如果左指针！=右指针，说明两指针间存在0，
     * 而左指针必指向零0，右指针指向左指针后的第一个非零数，因此交换两数即可。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了62.40%的用户
     * */
    public void moveZeroes(int[] nums) {
        int rPos = 0, lPos = 0;
        while (rPos < nums.length){
            if (nums[rPos] == 0){
                while (rPos < nums.length && nums[rPos] == 0){
                    rPos++;
                }
            }
            if (lPos != rPos && rPos < nums.length){
                swap(nums, lPos, rPos);
            }
            rPos++;
            lPos++;
        }
    }

    /**
     * 双指针简化版
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了62.01%的用户
     * */
    public void moveZeroes2(int[] nums) {
        int rPos = 0, lPos = 0;
        while (rPos < nums.length) {
            if(nums[rPos] != 0){
                if (rPos != lPos){
                    swap(nums, rPos, lPos);
                }
                lPos++;
            }
            rPos++;
        }
    }

    /**
     * 交换数组中的两位数字
     * */
    void swap(int[] nums, int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

}