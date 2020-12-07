package cn.czl.list.unique;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/12/7 14:16
 * @description:
 *      88. 合并两个有序数组
 *      - 给你两个有序整数数组 nums1 和 nums2，请你将
 *      nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *      说明：
 *          初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 *          你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *      示例：
 *          输入：
 *              nums1 = [1,2,3,0,0,0], m = 3
 *              nums2 = [2,5,6],       n = 3
 *          输出：[1,2,2,3,5,6]
 *      提示：
 *      1. -10^9 <= nums1[i], nums2[i] <= 10^9
 *      2. nums1.length == m + n
 *      3. nums2.length == n
 */
public class MergeTwoSortedArray_Easy {

    private int[] NUMS1 = {1,2,3,0,0,0};
    private int[] NUMS2 = {2,5,6};

    @Test
    public void TestSolution(){
        merge2(NUMS1, 3, NUMS2, 3);
        System.out.println(Arrays.toString(NUMS1));
    }

    /**
     * 合并后，再直接排序
     * 执行用时：1 ms, 在所有 Java 提交中击败了25.29%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了62.51%的用户
     * */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i = 0; i < n; i++){
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    /**
     * 双指针, 从后向前遍历
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了76.36%的用户
     * */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int pos1 = m - 1, pos2 = n - 1, pos = m + n - 1;
        while (pos1 >= 0 && pos2 >= 0){
            if(nums1[pos1] > nums2[pos2]){
                nums1[pos--] = nums1[pos1--];
            }else {
                nums1[pos--] = nums2[pos2--];
            }
        }
        while (pos1 >= 0){
            nums1[pos--] = nums1[pos1--];
        }
        while (pos2 >= 0){
            nums1[pos--] = nums2[pos2--];
        }
    }

}
