package cn.czl.search.list;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2022/2/15 16:07
 * @description:
 *      540. 有序数组中的单一元素
 *      - 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 *      - 请你找出并返回只出现一次的那个数。
 *      - 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 *      示例 1:
 *          输入: nums = [1,1,2,3,3,4,4,8,8]
 *          输出: 2
 *      示例 2:
 *          输入: nums =  [3,3,7,7,10,11,11]
 *          输出: 10
 *      提示:
 *          1 <= nums.length <= 10^5
 *          0 <= nums[i] <= 10^5
 */
public class SingleElementInSortedArray_Normal {

    private int[] NUMS1 = {1,1,2,2,3,5,5,8,8};
    private int[] NUMS2 = {1,2,2,3,3,4,4};
    private int[] NUMS3 = {1,1,2,2,3,3,4,4,5};
    @Test
    public void TestSolution(){
        System.out.println(singleNonDuplicate2(NUMS1));
        System.out.println(singleNonDuplicate2(NUMS2));
        System.out.println(singleNonDuplicate2(NUMS3));
    }
    /**
     * 遍历搜索
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 26.71% 的用户
     * 内存消耗： 47.1 MB , 在所有 Java 提交中击败了 19.67% 的用户
     * */
    public int singleNonDuplicate(int[] nums) {
        int len = nums.length;
        int i = 1;
        for(; i < len; i+=2){
            if(nums[i-1] != nums[i]){
                break;
            }
        }
        return nums[i-1];
    }

    /**
     * 二分搜索 [根据二段性寻找目标数]
     * */
    public int singleNonDuplicate2(int[] nums) {
        int len = nums.length;
        int low = 0, high = len - 1;
        while(low < high){
            int mid = (low + high) >>> 1;
            if(mid % 2 == 0){
                if(mid + 1 < len && nums[mid] == nums[mid + 1]){
                    low = mid + 1;
                }else{
                    high = mid;
                }
            }else{
                if(mid - 1 >= 0 && nums[mid] == nums[mid - 1]){
                    low = mid + 1;
                }else{
                    high = mid;
                }
            }
        }
        return nums[high];
    }
}
