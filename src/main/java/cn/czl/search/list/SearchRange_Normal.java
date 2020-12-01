package cn.czl.search.list;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/9/29 9:40
 * @description:
 *      34. 在排序数组中查找元素的第一个和最后一个位置
 *          - 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 *          找出给定目标值在数组中的开始位置和结束位置。
 *          - 你的算法时间复杂度必须是 O(log n) 级别。
 *          - 如果数组中不存在目标值，返回 [-1, -1]。
 *      示例 1:
 *          输入: nums = [5,7,7,8,8,10], target = 8
 *          输出: [3,4]
 *      示例 2:
 *          输入: nums = [5,7,7,8,8,10], target = 6
 *          输出: [-1,-1]
 */
public class SearchRange_Normal {

    private int TARGET1 = 6;
    private int TARGET2 = 0;
    private int TARGET3 = 0;
    private int[] NUMS1 = new int[]{5,6,6,8,8,10};
    private int[] NUMS2 = new int[]{2};
    private int[] NUMS3 = {1,2,3};
    @Test
    public void TestSolution(){
        int[] res = searchRange4(NUMS1, TARGET1);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 垃圾代码= =,各种边界处理不妥
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了66.55%的用户
     * */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        int Lpos = 0, Rpos = nums.length-1, len = nums.length;
        if (Lpos == Rpos && nums[0] == target){
            return new int[]{0,0};
        }
        while (Lpos <= Rpos){
            int index = (Lpos + Rpos)/2;
            if (nums[index] > target){
                Rpos = index;
            }else if (nums[index] < target){
                Lpos = index;
            }else {
                Lpos = index;
                Rpos = index;
                while ((Lpos - 1 >= 0) && (nums[Lpos - 1] == target)){
                    Lpos--;
                }
                while ((Rpos + 1 < len) && (nums[Rpos + 1] == target)){
                    Rpos++;
                }
                if (Lpos != Rpos){
                    result[0] = Lpos;
                    result[1] = Rpos;
                    break;
                }else {
                    break;
                }
            }
            if (Lpos + 1 == Rpos){
                if (nums[Rpos] == target){
                    result[0] = Rpos;
                    result[1] = Rpos;
                }
                if (nums[Lpos] == target){
                    result[0] = Lpos;
                    result[1] = Lpos;
                }
                break;
            }
        }
        return result;
    }

    /**
     * 双指针，二分查找[两指针相邻即结束]
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了99.34%的用户
     * */
    public int[] searchRange2(int[] nums, int target) {
        int[] result = new int[]{-1, -1};   // 初始化结果数组，左右指针
        int Lpos = 0, Rpos = nums.length-1, len = nums.length;
        while (Lpos + 1 < Rpos){    // [两指针相邻即停止循环]以防止两指针相邻，因无解导致死循环
            int index = (Lpos + Rpos)/2;
            if (nums[index] > target){
                Rpos = index;
            }else if (nums[index] < target){
                Lpos = index;
            }else {
                Lpos = index;   // nums[index] == target
                Rpos = index;   // 向左右寻找边界
                while ((Lpos - 1 >= 0) && (nums[Lpos - 1] == target)){
                    Lpos--;
                }
                while ((Rpos + 1 < len) && (nums[Rpos + 1] == target)){
                    Rpos++;
                }
                break;
            }
        }
        // 当判断是否存在解，并为结果数组赋值
        if (Rpos >= 0 && (nums[Lpos] == target || nums[Rpos] == target)){
            result[0] = nums[Lpos] == target ? Lpos : Rpos;
            result[1] = nums[Rpos] == target ? Rpos : Lpos;
        }
        return result;
    }

    /**
     * 二分查找，两次查找，分别搜索左右边界
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了99.06%的用户
     * */
    public int[] searchRange3(int[] nums, int target){
        int leftIndex = binarySearch(nums, target, true);
        int rightIndex = binarySearch(nums, target, false) - 1;
        if (leftIndex <= rightIndex && rightIndex < nums.length && nums[leftIndex] == target && nums[rightIndex] == target){
            return new int[]{leftIndex, rightIndex};
        }
        return new int[]{-1,-1};
    }

    // 二分查找，lower为true寻找左边界，false寻找右边界
    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            System.out.println("flag:" + lower + ",mid:" + mid + ",left:" + left + ",right" + right);
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了95.06%的用户
     * */
    public int[] searchRange4(int[] nums, int target) {
        int left = getLeftEdge(nums, target);
        int right = getRightEdge(nums, target);
        if(left <= right && right < nums.length && nums[left] == target && nums[right] == target){
            return new int[]{left, right};
        }
        return new int[]{-1, -1};
    }
    int getLeftEdge(int[] nums, int target){
        int left = 0, right = nums.length - 1, ans = nums.length;
        while(left <= right){
            int mid = (right + left) / 2;
            if(nums[mid] >= target){
                right = mid - 1;
                ans = mid;
            }else{
                left = mid + 1;
            }
        }
        System.out.println("left" + right + "," + ans);
        return ans;
    }
    int getRightEdge(int[] nums, int target){
        int left = 0, right = nums.length - 1, ans = nums.length;
        while(left <= right){
            int mid = (right + left) / 2;
            if(nums[mid] > target){
                right = mid - 1;
                ans = mid;
            }else{
                left = mid + 1;
            }
        }
        System.out.println("right" + right + "," + ans);
        return ans-1;
    }

    @Test
    public void TestBinarySearch(){
        int[] nums = {1,3,3,5,6};
        int target = 2, result = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right){  // 查找左侧第一个<=target的
            int mid = (left + right)/2;
            if (nums[mid] >= target){
                right = mid - 1;
                result = mid;
            }else {
                left = mid + 1;
            }
        }
        System.out.println(result);
    }

}
