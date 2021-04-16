package cn.czl.list.search.contains;

/**
 * @author RedRush
 * @date 2021/4/7 9:36
 * @description:
 *      81. 搜索旋转排序数组 II
 *      - 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 *      - 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 *      - 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 *      示例 1：
 *          输入：nums = [2,5,6,0,0,1,2], target = 0
 *          输出：true
 *      示例 2：
 *          输入：nums = [2,5,6,0,0,1,2], target = 3
 *          输出：false
 *      提示：
 *          1 <= nums.length <= 5000
 *          -10^4 <= nums[i] <= 10^4
 *          题目数据保证 nums 在预先未知的某个下标上进行了旋转
 *          -10^4 <= target <= 10^4
 */
public class SearchInRotatedSortedArray2_Normal {

    /**
     * 判断target在翻转点的左侧/右侧
     * 再选择从 首/尾 搜索
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 88.78% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 59.09% 的用户
     * */
    public boolean search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        if(target == nums[l] || target == nums[r]){
            return true;
        }else if(target > nums[l]){
            while(l < r && nums[l] <= nums[l + 1]){
                if(nums[++l] == target){
                    return true;
                }
            }
        }else{
            while(l < r && nums[r] >= nums[r - 1]){
                if(nums[--r] == target){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 二分搜索
     * */
    public boolean search2(int[] nums, int target) {
        int len = nums.length;
        if(len == 0){
            return false;
        }
        if(len == 1){
            return nums[0] == target;
        }
        int l = 0, r = len - 1;
        while (l <= r){
            int mid = (l + r) / 2;
            if(nums[mid] == target){
                return true;
            }
            if(nums[l] == nums[mid] && nums[r] == nums[mid]){
                l++;
                r--;
            }else if(nums[l] <= nums[mid]){
                if(nums[l] <= target && target < nums[mid]){
                    r = mid - 1;
                }else {
                    l = mid + 1;
                }
            }else {
                if(nums[mid] < target && target <= nums[len - 1]){
                    l = mid + 1;
                }else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }

}
