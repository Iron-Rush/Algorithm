package cn.czl.list.unique;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/10/16 9:30
 * @description:
 *      977. 有序数组的平方
 *          给定一个按非递减顺序排序的整数数组 A，
 *          返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *      示例 1：
 *          输入：[-4,-1,0,3,10]   输出：[0,1,9,16,100]
 *      示例 2：
 *          输入：[-7,-3,2,3,11]   输出：[4,9,9,49,121]
 */
public class SortedSquares_Easy {

    private int[] NUMS = new int[]{-4,-1,0,3,10};
    private int[] NUMS2 = new int[]{-7,-3,2,3,11};
    private int[] NUMS3 = new int[]{-1,1};

    @Test
    public void TestSolution(){
        int[] res = sortedSquares(NUMS);
        for (int temp : res) {
            System.out.print(temp + ",");
        }
    }

    /**
     * 根据数值域，分类排序(双指针)
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了99.75%的用户
     * */
    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int[] res = new int[len];
        if (len == 0){
            return res;
        }
        if(A[0] < 0 && A[len-1] > 0){
            int L = A[0] * A[0], Lpos = 0;
            int R = A[len-1] * A[len-1], Rpos = len-1;
            int pos = Rpos;
            while (Lpos < Rpos){
                if (L > R){
                    res[pos] = L;
                    Lpos++;
                    L = A[Lpos] * A[Lpos];
                }else {
                    res[pos] = R;
                    Rpos--;
                    R = A[Rpos] * A[Rpos];
                }
                pos--;
            }
            res[pos] = L > R ? L : R;
        }else if(A[0] < 0){
            for(int i = len - 1; i >= 0; i--){
                res[len - i - 1] = A[i] * A[i];
            }
        }else{
            for (int i = 0; i < len; i++) {
                res[i] = A[i] * A[i];
            }
        }
        return res;
    }

    /**
     * 计算后直接排序
     * 执行用时：4 ms, 在所有 Java 提交中击败了15.68%的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了91.62%的用户
     * */
    public int[] sortedSquares2(int[] A) {
        int len = A.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = A[i] * A[i];
        }
        Arrays.sort(res);
        return res;
    }

}
