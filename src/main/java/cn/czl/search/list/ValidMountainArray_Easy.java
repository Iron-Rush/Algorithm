package cn.czl.search.list;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/11/3 9:12
 * @description:
 *      941. 有效的山脉数组
 *      - 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 *      - 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 *          1.A.length >= 3
 *          2.在 0 < i < A.length - 1 条件下，存在 i 使得：
 *              A[0] < A[1] < ... A[i-1] < A[i]
 *              A[i] > A[i+1] > ... > A[A.length - 1]
 *      示例1：
 *          输入：[3,5,5]      输出：false
 *      示例2：
 *          输入：[0,3,2,1]    输出：true
 */
public class ValidMountainArray_Easy {
    private int[] A1 = new int[]{0,3,3,1};
    private int[] A2 = new int[]{0,1,2,3,4,5,6,7,8};

    @Test
    public void TestSolution(){
        System.out.println(validMountainArray2(A2));
    }

    /**
     * 线性扫描，标记上下坡
     * 执行用时：2 ms, 在所有 Java 提交中击败了41.65%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了98.35%的用户
     * */
    public boolean validMountainArray(int[] A) {
        if (A.length < 3){
            return false;
        }
        int pos = 1;
        boolean up = false, down = false;
        if (A[pos] > A[pos-1]){
            up = true;
        }
        while (pos < A.length && A[pos] > A[pos-1]){
            pos ++;
        }
        if (pos < A.length && A[pos] < A[pos-1]){
            down = true;
        }
        while (pos < A.length && A[pos] < A[pos-1]){
            pos ++;
        }
        return pos == A.length && up && down;
    }

    /**
     * 线性扫描，判断最高点位置
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了40.57%的用户
     * */
    public boolean validMountainArray2(int[] A) {
        if (A.length < 3){
            return false;
        }
        int pos = 0;
        while (pos < A.length-1 && A[pos] < A[pos+1]){
            pos ++;
        }
        if (pos == 0 || pos == A.length-1){
            return false;
        }
        while (pos < A.length-1 && A[pos] > A[pos+1]){
            pos ++;
        }
        return pos == A.length-1;
    }

}
