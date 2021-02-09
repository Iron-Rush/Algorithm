package cn.czl.list.slidingWindow;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/2/8 9:35
 * @description:
 *      978. 最长湍流子数组
 *      - 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 *          - 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 *          - 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 *      - 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 *      - 返回 A 的最大湍流子数组的长度。
 *      示例 1：
 *          输入：[9,4,2,10,7,8,8,1,9]
 *          输出：5
 *          解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 *      示例 2：
 *          输入：[4,8,12,16]
 *          输出：2
 *      示例 3：
 *          输入：[100]
 *          输出：1
 *      提示：
 *          1 <= A.length <= 40000
 *          0 <= A[i] <= 10^9
 */
public class MaxTurbulenceSize_Normal {

    private int[] ARR1 = {9,4,2,10,7,8,8,1,9};
    private int[] ARR2 = {4,8,12,16};

    @Test
    public void TestSolution(){
        System.out.println(maxTurbulenceSize(ARR1));
        System.out.println(maxTurbulenceSize(ARR2));
    }

    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 87.95% 的用户
     * 内存消耗： 41.8 MB , 在所有 Java 提交中击败了 64.53% 的用户
     * */
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        if(len <= 1){
            return len;
        }
        int max = 1;
        int oddLen = 1, evenLen = 1;
        for (int i = 1; i < arr.length; i++) {
            int a = arr[i-1], b = arr[i];
            if(a > b){
                oddLen = evenLen + 1;
                evenLen = 1;
            }else if(b > a){
                evenLen = oddLen + 1;
                oddLen = 1;
            }else {
                oddLen = 1;
                evenLen = 1;
            }
            max = Math.max(max, Math.max(oddLen, evenLen));
        }
        return max;
    }
}
