package cn.czl.list.slidingWindow;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author RedRush
 * @date 2021/2/9 9:17
 * @description:
 *      992. K 个不同整数的子数组
 *      - 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，
 *          则称 A 的这个连续、不一定独立的子数组为好子数组。
 *      - （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 *      - 返回 A 中好子数组的数目。
 *      示例 1：
 *          输入：A = [1,2,1,2,3], K = 2
 *          输出：7
 *          解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 *      示例 2：
 *          输入：A = [1,2,1,3,4], K = 3
 *          输出：3
 *          解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 *      提示：
 *          1 <= A.length <= 20000
 *          1 <= A[i] <= A.length
 *          1 <= K <= A.length
 */
public class SubarraysWithDistinct_Hard {
    private int[] A1 = {1,2,1,2,3};
    private int[] A2 = {1,2,1,3,4};
    private int K1 = 2;
    private int K2 = 3;

    @Test
    public void TestSolution(){
        System.out.println(subarraysWithKDistinct(A1, K1));
        System.out.println(subarraysWithKDistinct(A2, K2));
    }

    /**
     * 暴力解 - 超时
     * */
    public int subarraysWithKDistinct(int[] A, int K) {
        int len = A.length, count = 0;
        for (int i = 0; i < len; i++) {
            if(len - i < K){
                break;
            }
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < len; j++) {
                set.add(A[j]);
                if(set.size() > K){
                    break;
                }else if(set.size() == K){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * atMostDistinct()函数用于统计数组中，全部非重复元素<=k的子数组
     * 通过计算(1 -> k)的全部子数组个数，和(1 -> k-1)的全部子数组个数，作差
     * 得出长度为 k 的全部子数组个数
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 86.55% 的用户
     * 内存消耗： 41.8 MB , 在所有 Java 提交中击败了 73.73% 的用户
     * */
    public int subarraysWithKDistinct2(int[] A, int K) {
        return atMostDistinct(A, K) - atMostDistinct(A, K - 1);
    }
    // 最多包含 K 个不同整数的子区间个数
    // 根据条件：1 <= A[i] <= A.length, 使用数组记录数字
    private int atMostDistinct(int[] A, int K){
        int len = A.length, count = 0, res = 0;
        int l = 0, r = 0;
        int[] counter = new int[len + 1];
        for (; r < len; r++) {
            if(counter[A[r]] == 0){
                count++;
            }
            counter[A[r]]++;
            while (count > K){
                counter[A[l]]--;
                if(counter[A[l]] == 0){
                    count--;
                }
                l++;
            }
            res += r - l;
        }
        return res;
    }
}
