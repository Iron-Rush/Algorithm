package cn.czl.list.slidingWindow;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/2/19 10:03
 * @description:
 *      1004. 最大连续1的个数 III
 *      - 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 *      - 返回仅包含 1 的最长（连续）子数组的长度。
 *      示例 1：
 *          输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 *          输出：6
 *          解释： [1,1,1,0,0,1,1,1,1,1,1]
 *      示例 2：
 *          输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 *          输出：10
 *          解释： [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 *          粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 *      提示：
 *          1 <= A.length <= 20000
 *          0 <= K <= A.length
 *          A[i] 为 0 或 1
 */
public class MaxConsecutiveOnesiii_Normal {

    private int[] A1 = {1,1,1,0,0,0,1,1,1,1,0};
    private int[] A2 = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
    private int K1 = 2;
    private int K2 = 3;

    @Test
    public void TestSolution(){
        System.out.println(longestOnes(A1, K1));
        System.out.println(longestOnes(A2, K2));
    }

    /**
     * 滑动窗口，根据窗口中0的个数，维护窗口大小
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 94.07% 的用户
     * 内存消耗： 39.9 MB , 在所有 Java 提交中击败了 14.94% 的用户
     * */
    public int longestOnes(int[] A, int K) {
        int curMax = 0, curZero = 0;
        int len = A.length;
        int l = 0, r = 0;
        while (r < len){
            if (A[r++] == 0){
                curZero++;
                while (curZero > K){
                    if(A[l] == 0){
                        curZero--;
                    }
                    l++;
                }
            }
            curMax = Math.max(curMax, r - l);
        }
        return curMax;
    }

    /**
     * 二分查找
     * 执行用时： 68 ms , 在所有 Java 提交中击败了 5.49% 的用户
     * 内存消耗： 40.7 MB , 在所有 Java 提交中击败了 5.27% 的用户
     * */
    public int longestOnes2(int[] A, int K) {
        int curMax = 0, len = A.length;
        int[] P = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            P[i] = P[i-1] + (1 - A[i-1]);
        }
        for (int r = 0; r < len; r++) {
            int l = binarySearch(P, P[r + 1] - K);
            curMax = Math.max(curMax, r - l + 1);
        }
        return curMax;
    }
    // 二分搜索
    public int binarySearch(int[] arr, int target){
        int l = 0, r = arr.length - 1;
        while (l < r){
            int mid = (r - l) / 2 + l;
            if(arr[mid] < target){
                l = mid + 1;
            }else {
                r = mid;
            }
        }
        return l;
    }

}
