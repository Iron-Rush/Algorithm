package cn.czl.list.search;

/**
 * @author RedRush
 * @date 2021/4/9 10:49
 * @description:
 *      154. 寻找旋转排序数组中的最小值 II
 *      - 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，
 *          得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 *          - 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 *          - 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 *      - 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为
 *          数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *      - 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，
 *          并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *      示例 1：
 *          输入：nums = [1,3,5]
 *          输出：1
 *      示例 2：
 *          输入：nums = [2,2,2,0,1]
 *          输出：0
 *      提示：
 *          n == nums.length
 *          1 <= n <= 5000
 *          -5000 <= nums[i] <= 5000
 *          nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 */
public class FindMinimumInRotatedSortedArray2_Hard {

    /**
     * 二分搜索
     * 如果中间点 > 最右侧，说明最小值位于 中间点 - 右侧 之间
     * 如果中间点 < 最右侧，说明最小值位于 左侧 - 中间点 之间
     * 若中间点 == 最右侧， 最右侧左移一次。
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 5.16% 的用户
     * */
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while(low < high){
            int pos = low + (high - low) / 2;
            if(nums[pos] < nums[high]){
                high = pos;
            }else if(nums[pos] > nums[high]){
                low = pos + 1;
            }else{
                high -= 1;
            }
        }
        return nums[low];
    }
}
