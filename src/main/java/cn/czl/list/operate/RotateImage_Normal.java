package cn.czl.list.operate;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/11/24 17:40
 * @description:
 *      48. 旋转图像
 *      - 给定一个 n × n 的二维矩阵表示一个图像。将图像顺时针旋转 90 度。
 *      - 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。
 *      示例：
 *          给定 matrix =
 *          [
 *            [1,2,3],
 *            [4,5,6],
 *            [7,8,9]
 *          ],
 *      原地旋转输入矩阵，使其变为:
 *          [
 *            [7,4,1],
 *            [8,5,2],
 *            [9,6,3]
 *          ]
 */
public class RotateImage_Normal {

    private int[][] MATRIX1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
    private int[][] MATRIX2 = new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};

    @Test
    public void TestSolution(){
        rotate2(MATRIX1);
        System.out.println(Arrays.deepToString(MATRIX1));
    }

    /**
     * 按照规律，仅旋转左上角1/4矩阵
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了64.13%的用户
     * */
    public void rotate(int[][] matrix) {
        int len = matrix.length - 1;
        int borderX = (matrix.length/2);
        int border = (matrix.length/2) + (matrix.length%2);
        for (int i = 0; i < borderX; i++) {
            for (int j = 0; j < border; j++) {
                int x = i, y = j;
                int temp = matrix[x][y], next = 0;
                x = len - x;
                next = matrix[y][x];
                matrix[y][x] = temp;
                temp = next;

                y = len -y;
                next = matrix[x][y];
                matrix[x][y] = temp;
                temp = next;

                x = len - x;
                next = matrix[y][x];
                matrix[y][x] = temp;
                temp = next;

                y = len -y;
                matrix[x][y] = temp;
            }
        }
    }

    /**
     * 转置 + 翻转
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了87.16%的用户
     * */
    public void rotate2(int[][] matrix){
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        System.out.println(Arrays.deepToString(matrix));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = temp;
            }
        }
    }

}
