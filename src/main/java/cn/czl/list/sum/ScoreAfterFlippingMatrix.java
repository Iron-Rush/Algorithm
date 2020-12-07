package cn.czl.list.sum;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/12/7 10:39
 * @description:
 *      861. 翻转矩阵后的得分
 *      - 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 *      - 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 *      - 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 *      - 返回尽可能高的分数。
 *      示例：
 *          输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 *          输出：39
 *          解释：
 *              转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 *              0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 *      提示：
 *           1 <= A.length <= 20
 *           1 <= A[0].length <= 20
 *           A[i][j] 是 0 或 1
 */
public class ScoreAfterFlippingMatrix {

    private int[][] NUMS = {{0,0,1,1},{1,0,1,0},{1,1,0,0}};

    @Test
    public void TestSolution(){
        System.out.println(matrixScore(NUMS));
        System.out.println(Arrays.deepToString(NUMS));
    }
    @Test
    public void TestMethods(){
        for (int i = 0; i < 5; i++) {
            System.out.println(getBasic(i) == getBasic2(i));
        }
//        System.out.println(getBasic(0));
//        System.out.println(getBasic2(0));
//        reverseX(NUMS, 0);
//        System.out.println(Arrays.deepToString(NUMS));
    }

    /**
     * - 一位一位的算
     * - 首先固定每行首位为1 后，就可以不管横向翻转了。
     * - 纵向翻转用来控制第二位开始的最大值。
     * - (假如这列有五个数，4个0，1个1，那就要保最多的，纵向翻转变为4个1，1个0)
     * - 不用管1和0是哪一行的，因为在同样的二进制位置上，最后累计后都是一样的。
     * - 好比，例子最后答案是`1111,1001,1111`。
     * - 可以直接拆成`1000 * 3,100*2,10*2,1*3 ->8*3+4*2+2*2+1*3=39`
     * - 综上，横向翻转保证首位为1，它就足够了。剩下的交给纵向翻转来计算最大值。
     * */

    /**
     * 先根据每行首位，判断是否要执行横向翻转，保证首位全部为 1
     * 跳过第一列，对每列进行0/1的计数，取最大值，置1，
     * 根据当前二进制位置，计算出二进制->十进制基数，再乘以该列置为1的数量，累加到sum
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了72.03%的用户
     * */
    public int matrixScore(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0){
            return 0;
        }
        int x = A[0].length - 1, y = A.length;
        int sum = A.length * getBasic(x);
        // 横向翻转，保证每行首位为 1
        for (int i = 0; i < y; i++) {
            if (A[i][0] == 0){
                reverseX(A, i);
            }
        }
        // 取每列0/1的最大值，计算该位上最优解
        for (int i = x; i > 0; i--) {
            int zeroCount = 0;
            for (int j = 0; j < y; j++) {
                if (A[j][i] == 0){  // // 如果首位为0，则执行翻转
                    zeroCount++;
                }
            }
            sum += Math.max(zeroCount, y-zeroCount) * getBasic(x - i);
        }
        return sum;
    }
    // 获取二进制->十进制基数
    int getBasic(int x){
        return 1 << x;
    }
    int getBasic2(int x){
        return x == 0 ? 1 : 2 << (x - 1);
    }
    // 横向翻转
    void reverseX(int[][] A, int x){
        for (int i = 0; i < A[0].length; i++) {
            A[x][i] = A[x][i] == 0 ? 1 : 0;
        }
    }
}
