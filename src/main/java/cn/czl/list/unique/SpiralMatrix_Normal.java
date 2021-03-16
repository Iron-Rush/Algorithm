package cn.czl.list.unique;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2021/3/15 9:43
 * @description:
 *      54. 螺旋矩阵
 *      - 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *      示例 1：
 *          输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *          输出：[1,2,3,6,9,8,7,4,5]
 *      示例 2：
 *          输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 *          输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *      提示：
 *          m == matrix.length
 *          n == matrix[i].length
 *          1 <= m, n <= 10
 *          -100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix_Normal {

    private int[][] MATRIX1 = {{1,2,3},{4,5,6},{7,8,9}};
    private int[][] MATRIX2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};

    @Test
    public void TestSolution(){
        System.out.println(spiralOrder2(MATRIX1));
    }

    /**
     * 模拟 - 设置边界，根据边界逐边生成
     * 一次遍历一圈
     * 执行用时：0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：36.6 MB , 在所有 Java 提交中击败了 54.18% 的用户
     * */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0){
            return res;
        }
        int rangeL = 0, rangeR = matrix[0].length - 1;
        int rangeTop = 0, rangeBottom = matrix.length - 1;
        int count = 0, max = (rangeR  + 1) * (rangeBottom + 1);
        while(count < max){
            for (int i = rangeL; i <= rangeR && count < max; i++) {
                res.add(matrix[rangeTop][i]);
                count++;
            }
            rangeTop++;
            for (int i = rangeTop; i <= rangeBottom && count < max; i++) {
                res.add(matrix[i][rangeR]);
                count++;
            }
            rangeR--;
            for (int i = rangeR; i >= rangeL && count < max; i--) {
                res.add(matrix[rangeBottom][i]);
                count++;
            }
            rangeBottom--;
            for (int i = rangeBottom; i >= rangeTop && count < max; i--) {
                res.add(matrix[i][rangeL]);
                count++;
            }
            rangeL++;
        }
        return res;
    }

    /**
     * 方向数组 + 数组游标 + 记录数组
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.7 MB , 在所有 Java 提交中击败了 25.54% 的用户
     * */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int x = 0, y = 0, total = rows * cols;
        int dirIndex = 0;
        int[][] direct = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < total; i++) {
            res.add(matrix[y][x]);
            visited[y][x] = true;
            int nextx = x + direct[dirIndex][0];
            int nexty = y + direct[dirIndex][1];
            if(nextx < 0 || nexty < 0 || nextx >= cols
                || nexty >= rows || visited[nexty][nextx]){
                dirIndex = (dirIndex + 1) % 4;
            }
            x += direct[dirIndex][0];
            y += direct[dirIndex][1];
        }
        return res;
    }
}
