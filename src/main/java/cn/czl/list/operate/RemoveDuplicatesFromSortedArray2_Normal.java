package cn.czl.list.operate;

/**
 * @author RedRush
 * @date 2021/4/6 11:48
 * @description:
 *      80. 删除有序数组中的重复项 II
 *      - 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，
 *        使每个元素 最多出现两次 ，返回删除后数组的新长度。
 *      - 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *      说明：
 *          为什么返回数值是整数，但输出的答案是数组呢？
 *          请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *      你可以想象内部操作如下:
 *          * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 *           int len = removeDuplicates(nums);
 *          * // 在函数里修改输入数组对于调用者是可见的。
 *          * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 *           for (int i = 0; i < len; i++) {
 *              print(nums[i]);
 *           }
 *      示例 1：
 *          输入：nums = [1,1,1,2,2,3]
 *          输出：5, nums = [1,1,2,2,3]
 *          解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 *               不需要考虑数组中超出新长度后面的元素。
 *      示例 2：
 *          输入：nums = [0,0,1,1,1,1,2,3,3]
 *          输出：7, nums = [0,0,1,1,2,3,3]
 *          解释：函数应返回新长度 length = 7,
 *               并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 *               不需要考虑数组中超出新长度后面的元素。
 *      提示：
 *          1 <= nums.length <= 3 * 10^4
 *          -10^4 <= nums[i] <= 10^4
 *          nums 已按升序排列
 */
public class RemoveDuplicatesFromSortedArray2_Normal {

    /**
     * 双指针
     * 每次向后两位探测，如果与当前右侧指针所指数相等，则执行去重逻辑，左侧指针复制两个目标值
     * 若不等，则左侧指针复制右侧指针的值。
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 57.50% 的用户
     * */
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int l = 0, r = 0;
        while(r < len){
            int next = nums[r];
            int index = r + 2;
            if(index < len && next == nums[index]){     // 当前位置可去重
                while(index < len && next == nums[index]){
                    index++;
                }
                r = index;
                nums[l++] = next;
                nums[l++] = next;
            }else{  // 当前位置不可去重
                nums[l++] = nums[r++];
            }

        }
        return l;
    }

    /**
     * i代表插入位置，遍历数组
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 80.95% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 64.64% 的用户
     * */
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if(i < 2 || num > nums[i - 2]){
                nums[i++] = num;
            }
        }
        return i;
    }
}
