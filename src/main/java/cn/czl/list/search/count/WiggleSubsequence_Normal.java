package cn.czl.list.search.count;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/12/12 14:53
 * @description:
 *      376. 摆动序列
 *      - 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 *      - 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 *      - 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 *      示例 1:
 *          输入: [1,7,4,9,2,5]
 *          输出: 6
 *          解释: 整个序列均为摆动序列。
 *      示例 2:
 *          输入: [1,17,5,10,13,15,10,5,16,8]
 *          输出: 7
 *          解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
 *      示例 3:
 *          输入: [1,2,3,4,5,6,7,8,9]
 *          输出: 2
 *      进阶:  你能否用 O(n) 时间复杂度完成此题?
 */
public class WiggleSubsequence_Normal {
    private int[] NUMS1 = {1,7,4,9,2,5};
    private int[] NUMS2 = {1,17,5,10,13,15,10,5,16,8};
    private int[] NUMS3 = {1,2,3,4,5,6,7,8,9};
    private int[] NUMS4 = {3,3,2,5};

    @Test
    public void TestSolution(){
        System.out.println(wiggleMaxLength2(NUMS1));     // 6
        System.out.println(wiggleMaxLength2(NUMS2));     // 7
        System.out.println(wiggleMaxLength2(NUMS3));     // 2
        System.out.println(wiggleMaxLength2(NUMS4));
    }

    /**
     * 设置flag为所需增长方向，当所需增长方向 × 当前增长方向 > 0时，
     * 说明 所需增长方向 与 当前增长方向一致，count计数
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了42.13%的用户
     * */
    public int wiggleMaxLength(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int count = 1, pre = nums[0], flag = 1; // flag : -1为负值， 1为正值
        for(int num : nums){
            int cur = (num - pre) * flag;
            if(cur > 0){
                flag = -flag;
                count++;
            }else if(count == 1 && cur < 0){    // 首次默认为正增长，此块为首次负增长兜底
                count++;
            }
            pre = num;
        }
        return count;
    }

    /**
     * 动态规划-如果当前为增长，则以下降为基数算出当前值。
     * 这样，同一增长区间内，不会重复计数
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了 51.50% 的用户
     * */
    public int wiggleMaxLength2(int[] nums) {
        if(nums == null || nums.length <= 1){
            return nums.length;
        }
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i] - nums[i-1];
            if(cur > 0){    // 当改变方向时，up/down才会增长。
                up = down + 1;
            }
            if(cur < 0){
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

}
