package cn.czl.list.sum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RedRush
 * @date 2020/11/27 9:20
 * @description:
 *      454. 四数相加 II
 *      - 给定四个包含整数的数组列表 A , B , C , D ,
 *      计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 *      - 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
 *      所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 2^31 - 1 。
 *      例如：
 *          输入: A = [ 1, 2], B = [-2,-1], C = [-1, 2], D = [ 0, 2]
 *          输出: 2
 *          解释:
 *          两个元组如下:
 *              1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 *              2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class FourSumCount2_Normal {


    /**
     * 根据备忘录，暴力解(该题Map使用integer累加即可)
     * 执行用时：71 ms, 在所有 Java 提交中击败了84.06%的用户
     * 内存消耗：62.1 MB, 在所有 Java 提交中击败了12.00%的用户
     * */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count= 0;
        Map<Long, Integer> memory = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                memory.put((long)a + b, memory.getOrDefault((long)a + b, 0) + 1);
            }
        }
        for (int c : C) {
            for (int d : D) {
                if(memory.containsKey((long)-c-d)){
                    count += memory.get((long)-c-d);
                }
//                long target = 0 - (c + d);
//                count += memory.getOrDefault(target, 0);
            }
        }
        return count;
    }

}
