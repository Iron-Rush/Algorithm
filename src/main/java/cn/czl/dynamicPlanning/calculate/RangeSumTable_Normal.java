package cn.czl.dynamicPlanning.calculate;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/3/2 10:24
 * @description:
 *      304. 二维区域和检索 - 矩阵不可变
 *      - 给定一个二维矩阵，计算其子矩形范围内元素的总和，
 *          该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。
 *      - 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，
 *          该子矩形内元素的总和为 8。
 *      示例:
 *          给定 matrix = [[3, 0, 1, 4, 2],
 *                         [5, 6, 3, 2, 1],
 *                         [1, 2, 0, 1, 5],
 *                         [4, 1, 0, 1, 7],
 *                         [1, 0, 3, 0, 5]]
 *          sumRegion(2, 1, 4, 3) -> 8
 *          sumRegion(1, 1, 2, 2) -> 11
 *          sumRegion(1, 2, 2, 4) -> 12
 *      说明:
 *          你可以假设矩阵不可变。
 *          会多次调用 sumRegion 方法。
 *          你可以假设 row1 ≤ row2 且 col1 ≤ col2。
 */
public class RangeSumTable_Normal {

    @Test
    public void TestSolution(){
        int[][] matrix = {{3, 0, 1, 4, 2},
                          {5, 6, 3, 2, 1},
                          {1, 2, 0, 1, 5},
                          {4, 1, 0, 1, 7},
                          {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2,1,4,3));
        System.out.println(numMatrix.sumRegion(1,1,2,2));
        System.out.println(numMatrix.sumRegion(1,2,2,4));
    }

}

/**
 * 执行用时： 14 ms , 在所有 Java 提交中击败了 98.81% 的用户
 * 内存消耗： 43.9 MB , 在所有 Java 提交中击败了 85.72% 的用户
 * */
class NumMatrix {

    private int xSize;
    private int ySize;
    private int[][]dp;

    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length <= 0){
            return;
        }
        this.ySize = matrix.length;
        this.xSize = matrix[0].length;
        this.dp = new int[ySize + 1][xSize + 1];
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                dp[y + 1][x + 1] = matrix[y][x] + dp[y + 1][x] + dp[y][x + 1] - dp[y][x];
            }
        }
    }
    // 根据给出的两顶点坐标，返回矩形面积
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(row1 < 0 || col1 < 0 || row2 > ySize || col2 > xSize){
            return 0;
        }
        // 大矩形 - 上矩形 - 左矩形 + 右矩形
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}
