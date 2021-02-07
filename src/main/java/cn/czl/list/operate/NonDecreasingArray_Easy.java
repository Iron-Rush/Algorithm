package cn.czl.list.operate;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author RedRush
 * @date 2021/2/7 10:07
 * @description:
 *      665. 非递减数列
 *      - 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，
 *          该数组能否变成一个非递减数列。
 *      - 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，
 *          总满足 nums[i] <= nums[i + 1]。
 *      示例 1:
 *          输入: nums = [4,2,3]
 *          输出: true
 *          解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 *      示例 2:
 *          输入: nums = [4,2,1]
 *          输出: false
 *          解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *      说明：
 *          1 <= n <= 10 ^ 4
 *          - 10 ^ 5 <= nums[i] <= 10 ^ 5
 */
public class NonDecreasingArray_Easy {

    private int[] NUMS1 = {4,2,3};
    private int[] NUMS2 = {4,2,1};
    private int[] NUMS3 = {1,2,1,2,3};
    private int[] NUMS4 = {1,2,3,1,2,3};
    private int[] NUMS5 = {3,4,1,2};

    @Test
    public void TestSolution(){
        System.out.println(checkPossibility(NUMS1));
        System.out.println(checkPossibility(NUMS2));
        System.out.println(checkPossibility(NUMS3));
        System.out.println(checkPossibility(NUMS4));
        System.out.println(checkPossibility(NUMS5));
    }

    /**
     * [多次遍历] 遇到不合法数字后，分别尝试更改，并判断更改后是否合法
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.54% 的用户
     * 内存消耗： 39.9 MB , 在所有 Java 提交中击败了 38.63% 的用户
     * */
    public boolean checkPossibility(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            int a = nums[i], b = nums[i + 1];
            if(a > b){
                nums[i] = b;
                if(isSorted(nums)){
                    return true;
                }
                nums[i] = a;
                nums[i+1] = a;
                return isSorted(nums);
            }
        }
        return true;
    }
    // 判断数组是否为 非递减数列
    boolean isSorted(int[] nums){
        for (int i = 0; i < nums.length-1; i++) {
            if(nums[i] > nums[i+1]){
                return false;
            }
        }
        return true;
    }

    public boolean checkPossibility2(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length-1; i++) {
            int a = nums[i], b = nums[i+1];
            if(a > b){
                count++;
                if(count > 1){
                    return false;
                }
                if(i > 0 && b < nums[i-1]){
                    nums[i+1] = a;
                }
            }
        }
        return true;
    }

}
