package cn.czl.study.search;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2022/2/23 16:58
 * @description:
 *      剑指 Offer 53 - I. 在排序数组中查找数字 I
 *      - 统计一个数字在排序数组中出现的次数。
 *      示例 1:
 *          输入: nums = [5,7,7,8,8,10], target = 8
 *          输出: 2
 *      示例 2:
 *          输入: nums = [5,7,7,8,8,10], target = 6
 *          输出: 0
 *      提示：
 *          - 0 <= nums.length <= 10^5
 *          - -10^9 <= nums[i] <= 10^9
 *          - nums 是一个非递减数组
 *          - -10^9 <= target <= 10^9
 */
public class FindNumInSortedArray_Easy {

    private int[] ARR1 = {5,5,7,7,7,8,8,8,10,10};
    @Test
    public void TestSolution(){
        System.out.println(search(ARR1, 4));
        System.out.println(search3(ARR1, 4));
        System.out.println(search(ARR1, 8));
        System.out.println(search3(ARR1, 8));
        System.out.println(search(ARR1, 10));
        System.out.println(search3(ARR1, 10));
        System.out.println(search(ARR1, 20));
        System.out.println(search3(ARR1, 20));
    }

    /**
     * 二分搜索target + 线性扫描
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 44.2 MB , 在所有 Java 提交中击败了 13.47% 的用户
     * */
    public int search(int[] nums, int target) {
        int len = nums.length;
        int l = 0, r = len - 1;
        while(l <= r){
            int mid = (l + r) >>> 1;
            if(nums[mid] > target){
                r = mid - 1;
            }else if(nums[mid] < target){
                l = mid + 1;
            }else{
                l = mid;
                r = mid;
                while(r < len && nums[r] == target) r++;
                while(l >= 0 && nums[l] == target)  l--;
                return r - l - 1;
            }
        }
        return 0;
    }

    /**
     * 分別二分搜索左/右边界
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 44 MB , 在所有 Java 提交中击败了 23.14% 的用户
     * */
    public int search2(int[] nums, int target) {
        int lRange = searchLeftRange(nums, target);
        int rRange = searchRightRange(nums, target);
        if(lRange != -1 && rRange != -1){
            return rRange - lRange + 1;
        }
        return 0;
    }
    // 搜索最左侧 == target的下标
    private int searchLeftRange(int[] nums, int target){
        int len = nums.length;
        int l = 0, r = len - 1;
        while(l <= r){
            int mid = (l + r) >>> 1;
            if(nums[mid] > target){
                r = mid - 1;
            }else if(nums[mid] < target){
                l = mid + 1;
            }else {
                if(mid == 0 || nums[mid-1] != target){
                    return mid;
                }else{
                    r = mid-1;
                }
            }
        }
        return -1;
    }
    // 搜索最右侧 == target的下标
    private int searchRightRange(int[] nums, int target){
        int len = nums.length;
        int l = 0, r = len - 1;
        while (l <= r){
            int mid = (l + r) >>> 1;
            if(nums[mid] > target){
                r = mid - 1;
            }else if(nums[mid] < target){
                l = mid + 1;
            }else {
                if(mid == len-1 || nums[mid + 1] != target){
                    return mid;
                }else{
                    l = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 分別二分搜索左/右边界 边界整合优化
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 44 MB , 在所有 Java 提交中击败了 23.14% 的用户
     * */
    public int search3(int[] nums, int target) {
        int len = nums.length;
        if(len == 0)    return 0;
        int rRange = -1, lRange = -1;
        // 二分搜索左边界
        int l = 0, r = len - 1;
        while (l <= r){
            int mid = l + r >> 1;
            if(nums[mid] >= target){
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        if(l == len || nums[l] != target)   return 0;
        lRange = l;
        // 二分搜索右边界
        l = 0;
        r = len - 1;
        while (l <= r){
            int mid = l + r >> 1;
            if(nums[mid] <= target){
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        rRange = r;
        return rRange - lRange + 1;
    }

    @Test
    public void TestMethod(){
        System.out.println(searchRange(ARR1, 5));
        System.out.println(searchRange(ARR1, 10));
    }
    // 二分搜索
    private int searchRange(int[] arr, int target){
        int len = arr.length;
        int l = 0, r = len - 1;
        while (l <= r){
            int mid = l + r >>> 1;
            if(arr[mid] >= target){
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        System.out.println("left = " + l);
        System.out.println("right = " + r);
        return l;
    }
}
