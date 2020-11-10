package cn.czl.list.unique;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/11/10 9:36
 * @description:
 *      31. 下一个排列
 *      - 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *      - 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *      - 必须原地修改，只允许使用额外常数空间。
 *      例子：
 *          1,2,3 → 1,3,2
 *          3,2,1 → 1,2,3
 *          1,1,5 → 1,5,1
 */
public class NextPermutation_Normal {

    private int[] NUMS1 = new int[]{1,2,3};
    private int[] NUMS2 = new int[]{3,2,1};
    private int[] NUMS3 = new int[]{1,1,2,3,4,5};

    @Test
    public void TestSolution(){
        nextPermutation(NUMS1);
        System.out.println(Arrays.toString(NUMS1));
//        dfs_getAllPermutation(3);
    }

    /**
     * 第一次，从右到左进行扫描，找到第一个违背递增趋势的数字-PartitionNumber
     * 第二次，从右到左进行扫描，找到第一个大于PartitionNumber的数字-ChangeNumber
     * 交换 ChangeNumber 和 PartitionNumber
     * 反转 PartitionNumber 右侧的数。
     * (PartitionNumber 右侧的排列已经是严格的从大到小排列了,
     * 如此反转之后,可以保证,新的排列组成的数的增长幅度在所有的可能中最小.)
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.72%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了89.04%的用户
     * */
    public void nextPermutation(int[] nums) {
        int lPos = nums.length-2;
        while (lPos >= 0 && nums[lPos] >= nums[lPos + 1]){
            lPos--;
        }
        if (lPos >= 0){
            int rPos = nums.length-1;
            while (rPos >= 0 && nums[lPos] >= nums[rPos]){
                rPos--;
            }
            swap(nums, rPos, lPos);
        }
        reverse(nums, lPos+1, nums.length-1);
    }

    // 反转数组中,first-end位置的元素(包含end)
    void reverse(int[] array, int first, int end){
        while (first < end){
            swap(array, first++, end--);
        }
    }
    // 交换数组中的两元素
    void swap(int[] arr, int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
    // 根据n，生成长度为n的数组
    void dfs_getAllPermutation(int n){
        int[] array = new int[n];
        for (int i = 0; i < n;) {
            array[i++] = i;
        }
        dfs_permute(array, 0);
    }
    // 递归，生成数组全排列
    void dfs_permute(int[] nums, int pos){
        if (pos == nums.length){
            System.out.println(Arrays.toString(nums));
        }else {
            for (int i = pos; i < nums.length;i++) {
                swap(nums, pos, i);
                dfs_permute(nums, pos + 1);
                swap(nums, pos, i);
            }
        }
    }
}
