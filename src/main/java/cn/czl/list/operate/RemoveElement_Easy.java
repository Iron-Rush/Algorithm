package cn.czl.list.operate;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/4/19 9:21
 * @description:
 *      27. 移除元素
 *      - 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，
 *          并返回移除后数组的新长度。
 *      - 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *      - 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *      说明:
 *          为什么返回数值是整数，但输出的答案是数组呢?
 *          请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *      示例 1：
 *          输入：nums = [3,2,2,3], val = 3
 *          输出：2, nums = [2,2]
 *          解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 *      示例 2：
 *          输入：nums = [0,1,2,2,3,0,4,2], val = 2
 *          输出：5, nums = [0,1,4,0,3]
 *          解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 *              注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 *      提示：
 *          0 <= nums.length <= 100
 *          0 <= nums[i] <= 50
 *          0 <= val <= 100
 */
public class RemoveElement_Easy {

    private int[] NUMS1 = {3,2,2,3};
    private int[] NUMS2 = {0,1,2,2,3,0,4,2};

    @Test
    public void TestSolution(){
        System.out.println(removeElement2(NUMS1, 3));
        System.out.println(removeElement2(NUMS2, 2));
    }

    /**
     * 遍历 - 将符合条件的数字插入数组
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.8 MB , 在所有 Java 提交中击败了 94.53% 的用户
     * */
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for(int num : nums){
            if(num != val){
                nums[index++] = num;
            }
        }
        return index;
    }

    /**
     * 双指针 - 左侧数字不符合条件，从右侧取。两指针相遇即停止
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.7 MB , 在所有 Java 提交中击败了 98.43% 的用户
     * */
    public int removeElement2(int[] nums, int val) {
        int l = 0, r = nums.length;
        while (l < r){
            if(nums[l] == val){
                nums[l] = nums[r - 1];
                r--;
            }else {
                l++;
            }
        }
        return r;
    }
}
