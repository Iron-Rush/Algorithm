package cn.czl.string.operate;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author RedRush
 * @date 2020/12/2 9:46
 * @description:
 *      321. 拼接最大数
 *      - 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
 *      现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，
 *      要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 *      - 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 *      - 说明: 请尽可能地优化你算法的时间和空间复杂度。
 *      示例:
 *          输入:
 *              nums1 = [3, 4, 6, 5], nums2 = [9, 1, 2, 5, 8, 3], k = 5
 *          输出:
 *              [9, 8, 6, 5, 3]
 */
public class CreateMaximumNumber_Hard {

    private int[] NUMS1 = {3, 4, 6, 5};
    private int[] NUMS2 = {9, 1, 2, 5, 8, 3};
    private int[] NUMS3 = {6, 7};       // [6,7,6,0,4]
    private int[] NUMS4 = {6, 0, 4};
    private int[] NUMS5 = {8, 6, 9};
    private int[] NUMS6 = {1, 7, 5};
    private int K = 5;
    private int K2 = 3;

    @Test
    public void TestSolution(){
        int[] result = maxNumber(NUMS3, NUMS4, 5);
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void TestMethods(){
        int[] res = getMaxNumber(NUMS2, 3);
        System.out.println(Arrays.toString(res));
    }


    /**
     * 1. 数组生成函数：根据给定 数组 和 规定长度，返回最大的定长数组
     * 2. 合并数组函数：规定长度下，合并出一条当前的最大数组。
     * 3. 比较函数：判断传入的两条数组，大小(从index位置开始比较)
     * 执行用时：21 ms, 在所有 Java 提交中击败了66.14%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了56.01%的用户
     * */
    /**
     * 改用数组提取最大数组。(数组生成函数优化[getMaxNumber2])
     * 执行用时：8 ms, 在所有 Java 提交中击败了97.00%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了64.81%的用户
     * */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        int[] maxResult = new int[k];
        int start = Math.max(0, k - len2), end = Math.min(k, len1);
        for (int i = start; i <= end; i++) {
            int[] arr1 = getMaxNumber(nums1, i);
            int[] arr2 = getMaxNumber(nums2, k - i);
            int[] result = mergeDeque(arr1, arr2, k);
            maxResult = compareArray(result, 0, maxResult, 0) ? result : maxResult;
        }
        return maxResult;
    }

    // 比较当前两个数组，返回true为第一个数组较大，false为第二个数组较大
    private boolean compareArray(int[] arr1, int pos1, int[] arr2, int pos2){
        int len1 = arr1.length, len2 = arr2.length;
        while (pos1 < len1 && pos2 < len2){
            int dist = arr1[pos1++] - arr2[pos2++];
            if (dist != 0){
                return dist > 0;
            }
        }
        return ((len1 - pos1) - (len2 - pos2)) > 0;
    }

    // 根据传入队列，和指定长度，合并出当前最大数组
    private int[] mergeDeque(int[] arr1, int[] arr2, int k){
        int[] res = new int[k];
        int pos = 0, pos1 = 0, pos2 = 0;
        while (pos < k && pos1 < arr1.length && pos2 < arr2.length){
            // 深入比较两个数组大小
            if (compareArray(arr1, pos1, arr2, pos2)){
                res[pos++] = arr1[pos1++];
            }else {
                res[pos++] = arr2[pos2++];
            }
        }
        while (pos < k && pos1 < arr1.length){
            res[pos++] = arr1[pos1++];
        }
        while (pos < k && pos2 < arr2.length){
            res[pos++] = arr2[pos2++];
        }
        return res;
    }

    // 根据传入数组，和截取长度，生成最大数组(双向队列实现)
    private int[] getMaxNumber(int[] nums, int k){
        int del = nums.length - k;
        Deque<Integer> deque = new LinkedList<>();
        for (int num : nums) {      // 如果队列非空，且当前元素比队尾元素大，则移除队尾元素
            while (!deque.isEmpty() && del > 0 && num > deque.peekLast()){
                deque.pollLast();
                del--;
            }
            deque.offerLast(num);
        }
        for (int i = del; i > 0; i--) { // 仍需删除，删除末位
            deque.pollLast();
        }
        int[] result = new int[k];
        int pos = 0;
        while (!deque.isEmpty()){
            result[pos++] = deque.pollFirst();
        }
        return result;
    }

    // 根据传入数组，和截取长度，生成最大数组(数组实现)
    private int[] getMaxNumber2(int[] nums, int k){
        int del = nums.length - k, pos = 0;
        int[] stack = new int[nums.length];
        Deque<Integer> deque = new LinkedList<>();
        for (int num : nums) {      // 如果队列非空，且当前元素比队尾元素大，则移除队尾元素
            while (del > 0 && pos > 0 && num > stack[pos-1]){
                del--;
                pos--;
            }
            stack[pos++] = num;     // 当前元素入栈
        }
        pos -= del;     // 若k>0,仍需删除，删除末位
        return Arrays.copyOfRange(stack, 0, pos);
    }

}
