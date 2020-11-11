package cn.czl.list.search;

/**
 * @author RedRush
 * @date 2020/11/11 15:31
 * @description:
 *      26. 删除排序数组中的重复项
 *      - 给定一个排序数组，你需要在 原地 删除重复出现的元素，
 *      使得每个元素只出现一次，返回移除后数组的新长度。
 *      - 不要使用额外的数组空间，你必须在 原地 修改输入数组
 *      并在使用 O(1) 额外空间的条件下完成。
 *      示例：
 *          给定数组 nums = [1,1,2],
 *          函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *          你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveDuplicatesFromSortedArray_Easy {

    public int removeDuplicates(int[] nums) {
        int count = 0, lPos = 0;
        for(int rPos = 0; rPos < nums.length; rPos++){
            while(rPos < nums.length-1 && nums[rPos] == nums[rPos+1]){
                rPos++;
            }
            nums[lPos++] = nums[rPos];
            count++;
        }
        return count;
    }

    public int removeDuplicates2(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

}
