package cn.czl.search.list;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/10/25 15:42
 * @description:
 *      845. 数组中的最长山脉
 *      我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 *          1. B.length >= 3
 *          2. 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 *      注意：B 可以是 A 的任意子数组，包括整个数组 A。
 *      给出一个整数数组 A，返回最长 “山脉” 的长度
 *      如果不含有 “山脉” 则返回 0。
 *      示例 1：
 *          输入：[2,1,4,7,3,2,5]  输出：5
 *          解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 *      示例 2：
 *          输入：[2,2,2]      输出：0
 *          解释：不含 “山脉”。
 */
public class LongestMountain_Normal {

    private static int[] MOUNTAIN = new int[]{2,1,4,7,3,2,5};
    private static int[] MOUNTAIN2 = new int[]{2,2,3,2,2,3,3,3,3};
    private static int[] MOUNTAIN3 = new int[]{0,1,2,3,4,5,4,3,2,1,0};
    private static int[] MOUNTAIN4 = new int[]{875,884,239,731,723,685};

    @Test
    public void TestSolution(){
        System.out.println(longestMountain(MOUNTAIN2));
    }

    /**
     * 单次大循环，执行完一次上山判断。
     * 若不符合山脉判断则不会进入到山脉长度计算，步进，进行下一次判断
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.84%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了85.94%的用户
     * */
    public int longestMountain(int[] A) {
        int mountain = 0;
        int lPos = 0;
        while (lPos + 2 < A.length){
            int rPos = lPos + 1;
            if (A[lPos] < A[lPos+1]){
                while (rPos + 1 < A.length && A[rPos] < A[rPos + 1]){
                    rPos++;
                }
                if (rPos + 1 < A.length && A[rPos] > A[rPos + 1]){
                    while (rPos + 1 < A.length && A[rPos] > A[rPos + 1]){
                        rPos++;
                    }
                    mountain = Math.max(mountain, rPos - lPos + 1);
                }
            }
            lPos = rPos;
        }
        return mountain;
    }

}
