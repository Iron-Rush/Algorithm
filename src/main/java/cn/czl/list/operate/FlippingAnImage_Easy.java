package cn.czl.list.operate;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/2/24 10:00
 * @description:
 *      832. 翻转图像
 *      - 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 *      - 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 *      - 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 *      示例 1：
 *          输入：[[1,1,0],[1,0,1],[0,0,0]]
 *          输出：[[1,0,0],[0,1,0],[1,1,1]]
 *          解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 *               然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 *      示例 2：
 *          输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 *          输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *          解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 *               然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *      提示：
 *          1 <= A.length = A[0].length <= 20
 *          0 <= A[i][j] <= 1
 */
public class FlippingAnImage_Easy {

    private int[][] A = {{1,1,0},{1,0,1},{0,0,0}};

    @Test
    public void TestSolution(){
        System.out.println(Arrays.deepToString(flipAndInvertImage(A)));
    }

    @Test
    public void TestMethod(){
        int a0 = 0, a1 = 1, a2 = 2;
        System.out.println(a0 ^ 1);
        System.out.println(a1 ^ 1);
        System.out.println(a2 ^ 1);
    }

    /**
     * 创建新的数组，翻转后返回
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 46.56% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 80.95% 的用户
     * */
    public int[][] flipAndInvertImage(int[][] A) {
        if(A == null || A.length < 1){
            return A;
        }
        int h = A.length;
        int w = A[0].length;
        int[][] res = new int[h][w];
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                res[y][w - x - 1] = A[y][x] == 0 ? 1 : 0;
            }
        }
        return res;
    }

    /**
     * 原地翻转数组
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 35.98% 的用户
     * */
    public int[][] flipAndInvertImage2(int[][] A) {
        int w = A[0].length;
        for (int[] row : A) {
            for (int x = 0; x < (w + 1) / 2; x++) {
                int temp = row[x] ^ 1;
                row[x] = row[w - x - 1] ^ 1;
                row[w - x - 1] = temp;
            }
        }
        return A;
    }
}
