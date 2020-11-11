package cn.czl.list.operate;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/11/11 16:04
 * @description:
 *      189. 旋转数组
 *      给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *      示例 1:
 *          输入: [1,2,3,4,5,6,7] 和 k = 3
 *          输出: [5,6,7,1,2,3,4]
 *      解释:
 *          向右旋转 1 步: [7,1,2,3,4,5,6]
 *          向右旋转 2 步: [6,7,1,2,3,4,5]
 *          向右旋转 3 步: [5,6,7,1,2,3,4]
 *      要求使用空间复杂度为 O(1) 的 原地 算法。
 */
public class RotateArray_Normal {

    private int[] NUMS1 = new int[]{1,2,3,4,5,6,7};
    private int K1 = 3;
    private int[] NUMS2 = new int[]{-1,-100,3,99};
    private int K2 = 2;

    @Test
    public void TestSolution(){
        rotate(NUMS2, K2);
        System.out.println(Arrays.toString(NUMS2));
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了87.91%的用户
     * */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        int len = nums.length, count = 0, start = 0;
        while (count < len){    // 记录交换位置的次数，n个同学一共需要换n次
            int cur = start;
            int pre = nums[cur];
            do{
                int next = (cur + k) % len;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                cur = next;
                count++;
            }while (start != cur);
            start++;
        }
    }
}
