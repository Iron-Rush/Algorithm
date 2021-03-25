package cn.czl.list.operate;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/3/25 13:26
 * @description:
 *      73. 矩阵置零
 *      - 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 *      - 进阶：
 *          1. 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 *          2. 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 *          3. 你能想出一个仅使用常量空间的解决方案吗？
 *      示例 1：
 *          输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 *          输出：[[1,0,1],[0,0,0],[1,0,1]]
 *      示例 2：
 *          输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 *          输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *      提示：
 *          m == matrix.length
 *          n == matrix[0].length
 *          1 <= m, n <= 200
 *          -2^31 <= matrix[i][j] <= 2^31 - 1
 */
public class SetMatrixZeroes_Normal {

    private int[][] MATRIX1 = {{1,1,1},{1,0,1},{1,1,1}};
    private int[][] MATRIX2 = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};

    @Test
    public void TestSolution(){
        setZeroes4(MATRIX1);
        setZeroes4(MATRIX2);
        System.out.println(Arrays.deepToString(MATRIX1));
        System.out.println(Arrays.deepToString(MATRIX2));
    }

    /**
     * 设置 横轴/纵轴 置零记忆数组，根据记忆数组将对应 行/列 置零
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.93% 的用户
     * 内存消耗： 39.9 MB , 在所有 Java 提交中击败了 76.24% 的用户
     * */
    public void setZeroes(int[][] matrix) {
        int ySize = matrix.length;
        int xSize = matrix[0].length;
        int[] xMemory = new int[xSize];
        int[] yMemory = new int[ySize];
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if(matrix[y][x] == 0){
                    yMemory[y] = 1;
                    xMemory[x] = 1;

                }
            }
        }
        for (int y = 0; y < ySize; y++) {
            if(yMemory[y] == 1){
                for (int x = 0; x < xSize; x++) {
                    matrix[y][x] = 0;
                }
            }
        }
        for (int x = 0; x < xSize; x++) {
            if(xMemory[x] == 1){
                for (int y = 0; y < ySize; y++) {
                    matrix[y][x] = 0;
                }
            }
        }
    }

    /**
     * 改变数组填充逻辑
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.93% 的用户
     * 内存消耗： 39.9 MB , 在所有 Java 提交中击败了 80.42% 的用户
     * */
    public void setZeroes2(int[][] matrix) {
        int ySize = matrix.length;
        int xSize = matrix[0].length;
        boolean[] yMemory = new boolean[ySize];
        boolean[] xMemory = new boolean[xSize];
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if(matrix[y][x] == 0){
                    yMemory[y] = true;
                    xMemory[x] = true;

                }
            }
        }
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if(xMemory[x] || yMemory[y]){
                    matrix[y][x] = 0;
                }
            }
        }
    }

    /**
     * 占用原数组第一行、第一列对矩阵其余位置进行标记
     * 而第一行、第一列仅使用两个变量进行标记
     * 执行用时： 10 ms , 在所有 Java 提交中击败了 12.78% 的用户
     * 内存消耗： 40 MB , 在所有 Java 提交中击败了 55.69% 的用户
     * */
    public void setZeroes3(int[][] matrix) {
        int ySize = matrix.length;
        int xSize = matrix[0].length;
        boolean yFlag = false, xFlag = false;
        // 预处理第一行、第一列
        for (int y = 0; y < ySize; y++) {
            if(matrix[y][0] == 0){
                yFlag = true;
            }
        }
        for (int x = 0; x < xSize; x++) {
            if(matrix[0][x] == 0){
                xFlag = true;
            }
        }
        // 处理其余方格，将结果标记于第一行、第一列上
        for (int y = 1; y < ySize; y++) {
            for (int x = 1; x < xSize; x++) {
                if(matrix[y][x] == 0){
                    matrix[y][0] = 0;
                    matrix[0][x] = 0;
                }
            }
        }
        // 根据第一行、第一列渲染矩阵
        for (int y = 1; y < ySize; y++) {
            for (int x = 1; x < xSize; x++) {
                if(matrix[y][0] == 0 || matrix[0][x] == 0){
                    matrix[y][x] = 0;
                }
            }
        }
        // 根据标记，最后处理第一行、第一列
        if(xFlag){
            for (int x = 0; x < xSize; x++) {
                matrix[0][x] = 0;
            }
        }
        if (yFlag){
            for (int y = 0; y < ySize; y++) {
                matrix[y][0] = 0;
            }
        }
    }

    /**
     * 借助数组第一行，第一列标识其余位置是否存在0
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.93% 的用户
     * 内存消耗： 40 MB , 在所有 Java 提交中击败了 61.53% 的用户
     * */
    public void setZeroes4(int[][] matrix) {
        int ySize = matrix.length;
        int xSize = matrix[0].length;
        boolean yFlag = false;
        for (int y = 0; y < ySize; y++) {
            if(matrix[y][0] == 0){
                yFlag = true;
            }
            for (int x = 1; x < xSize; x++) {
                if(matrix[y][x] == 0){
                    matrix[y][0] = 0;
                    matrix[0][x] = 0;
                }
            }
        }
        for (int y = ySize - 1; y >= 0; y--) {
            for (int x = 1; x < xSize; x++) {   // 由于第一行为标记行，且未预处理该行
                if(matrix[y][0] == 0 || matrix[0][x] == 0){ // 因此从后向前处理，避免标记被覆盖
                    matrix[y][x] = 0;
                }
            }
            if(yFlag){
                matrix[y][0] = 0;
            }
        }
    }
}
