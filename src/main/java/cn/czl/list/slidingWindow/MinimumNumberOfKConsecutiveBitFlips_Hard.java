package cn.czl.list.slidingWindow;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/2/18 9:58
 * @description:
 *      995. K 连续位的最小翻转次数
 *      - 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
 *      - 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
 *      示例 1：
 *          输入：A = [0,1,0], K = 1
 *          输出：2
 *          解释：先翻转 A[0]，然后翻转 A[2]。
 *      示例 2：
 *          输入：A = [1,1,0], K = 2
 *          输出：-1
 *          解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
 *      示例 3：
 *          输入：A = [0,0,0,1,0,1,1,0], K = 3
 *          输出：3
 *          解释：
 *              翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
 *              翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
 *              翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
 *      提示：
 *          1 <= A.length <= 30000
 *          1 <= K <= A.length
 */
public class MinimumNumberOfKConsecutiveBitFlips_Hard {

    private int[] A1 = {0,1,0};
    private int[] A2 = {1,1,0};
    private int[] A3 = {0,0,0,1,0,1,1,0};

    @Test
    public void TestSolution(){
//        System.out.println(minKBitFlips(A1, 1));
//        System.out.println(minKBitFlips(A2, 2));
//        System.out.println(minKBitFlips(A3, 3));
        System.out.println(minKBitFlips2(A1, 1));
        System.out.println(minKBitFlips2(A2, 2));
        System.out.println(minKBitFlips2(A3, 3));
    }

    /**
     * 模拟翻转     超出时间限制
     * 从左向右，逐K个翻转数字。
     * 若当前数字为0，则翻转当前位至当前位->当前位+K
     * 检查最后K位是否满足要求
     * */
    public int minKBitFlips(int[] A, int K) {
        int count = 0, len = A.length;
        if(len < K){
            for (int temp : A) {
                if(temp == 0){
                    return -1;
                }
            }
            return 0;
        }
        for (int i = 0; i <= len- K; i++) {
            if(A[i] == 0){
                count++;
                reverse(A, i, K);
            }
        }
        for (int i = len - K; i < len; i++) {
            if(A[i] == 0){
                return -1;
            }
        }
        return count;
    }
    // 翻转目标数组，start -> start + size位上的数字
    void reverse(int[] A, int start, int size){
        for (int i = start; i < start + size; i++) {
            A[i] = A[i] == 1 ? 0 : 1;
        }
    }

    /**
     * 差分数组
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 83.23% 的用户
     * 内存消耗： 46.6 MB , 在所有 Java 提交中击败了 33.75% 的用户
     * */
    public int minKBitFlips2(int[] A, int K) {
        int count = 0, revCount = 0;
        int len = A.length;
        int[] diff = new int[len + 1];      // diff数组记录翻转次数
        for (int i = 0; i < len; i++) {
            revCount += diff[i];
            if((A[i] + revCount) % 2 == 0){     // 当前值+翻转次数为偶说明当前位置为0
                if(i + K > len){
                    return -1;
                }
                count++;
                revCount++;
                diff[i + K]++;
            }
        }
        return count;
    }

}
