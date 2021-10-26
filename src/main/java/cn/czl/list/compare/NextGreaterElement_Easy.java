package cn.czl.list.compare;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2021/10/26 14:00
 * @description:
 *      496. 下一个更大元素 I
 *      - 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 *      - 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
 *      - nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。
 *          如果不存在，对应位置输出 -1 。
 *      示例 1:
 *          输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 *          输出: [-1,3,-1]
 *          解释:
 *              对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 *              对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 *              对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 *      示例 2:
 *          输入: nums1 = [2,4], nums2 = [1,2,3,4].
 *          输出: [3,-1]
 *          解释:
 *              对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 *              对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 *      提示：
 *          1 <= nums1.length <= nums2.length <= 1000
 *          0 <= nums1[i], nums2[i] <= 10^4
 *          nums1和nums2中所有整数 互不相同
 *          nums1 中的所有整数同样出现在 nums2 中
 *      进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
 */
public class NextGreaterElement_Easy {

    /**
     * 逐位搜索
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 91.45% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 38.39% 的用户
     * */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = getNextGreaterElement(nums2, nums1[i]);
        }
        return ans;
    }
    // 获取cur后面，下一个更大的数字
    private int getNextGreaterElement(int[] nums, int cur){
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            if (flag){
                if (nums[i] > cur){
                    return nums[i];
                }
            }else {
                if (nums[i] == cur){
                    flag = true;
                }
            }
        }
        return -1;
    }

    /**
     * 单调栈 + HashMap
     * 通过单调栈获取前一个大于当前数字的数
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 91.45% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 28.46% 的用户
     * */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            int num = nums2[i];
            while (!stack.isEmpty() && num >= stack.peek()){
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        int len = nums1.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
}
