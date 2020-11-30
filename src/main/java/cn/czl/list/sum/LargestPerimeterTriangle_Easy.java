package cn.czl.list.sum;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/11/29 15:41
 * @description:
 *      976. 三角形的最大周长
 *      - 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 *      - 如果不能形成任何面积不为零的三角形，返回 0。
 *      示例 1：
 *          输入：[2,1,2]
 *          输出：5
 *      示例 2：
 *          输入：[1,2,1]
 *          输出：0
 *      示例 3：
 *          输入：[3,2,3,4]
 *          输出：10
 *      示例 4：
 *          输入：[3,6,2,3]
 *          输出：8
 *      提示：
 *          1. 3 <= A.length <= 10000
 *          2. 1 <= A[i] <= 10^6
 */
public class LargestPerimeterTriangle_Easy {

    private int[] A1 = {2,1,2};     // 5
    private int[] A2 = {1,2,1};     // 0
    private int[] A3 = {3,2,3,4};   // 10
    private int[] A4 = {3,6,2,3};   // 8

    @Test
    public void TestSolution(){
        System.out.println(largestPerimeter(A2));
    }

    /**
     * 根据三角形性质，仅需判断排序后数组相邻的三条边即可
     * 而且进需验证两条较短边要大于长边即可。
     * 若A[i-2] + A[i-2] <= A[i],则左侧的边只会更小，无法使等式成立
     * 执行用时：9 ms, 在所有 Java 提交中击败了82.34%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了87.03%的用户
     * */
    public int largestPerimeter(int[] A) {
        if (A.length < 3){
            return 0;
        }
        Arrays.sort(A);
        int rpos1 = A.length-1;
        for (int i = rpos1; i >= 2; i--) {
            if(A[i-2] + A[i-1] > A[i]){
                return A[i-2] + A[i-1] + A[i];
            }
        }
        return 0;
    }

}
