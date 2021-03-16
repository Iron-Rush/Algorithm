package cn.czl.list.generate;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/3/16 9:30
 * @description:
 *      59. 螺旋矩阵 II
 *      - 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，
 *      且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *      示例 1：
 *          输入：n = 3
 *          输出：[[1,2,3],[8,9,4],[7,6,5]]
 *      示例 2：
 *          输入：n = 1
 *          输出：[[1]]
 *      提示：1 <= n <= 20
 */
public class GenerateSpiralMatrix_Normal {

    @Test
    public void TestSolution(){
        System.out.println(Arrays.deepToString(generateMatrix(3)));
    }

    /**
     * 模拟
     * 设置上、下、左、右四个边界，根据边界，从外向内，逐边生成
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.4 MB , 在所有 Java 提交中击败了 85.22% 的用户
     * */
    public int[][] generateMatrix(int n) {
        if(n < 1){
            return null;
        }
        int[][] ans = new int[n][n];
        int count = 1, total = n * n;
        int left = 0, right = n - 1;
        int up = 0, down = n - 1;
        while(count <= total){
            for (int i = left; i <= right; i++) {
                ans[up][i] = count++;
            }
            up++;
            for (int i = up; i <= down; i++) {
                ans[i][right] = count++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                ans[down][i] = count++;
            }
            down--;
            for (int i = down; i >= up; i--) {
                ans[i][left] = count++;
            }
            left++;
        }
        return ans;
    }

    /**
     * 根据方向矩阵，进行模拟
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 55.85% 的用户
     * */
    public int[][] generateMatrix2(int n) {
        if (n < 1) {
            return null;
        }
        int[][] ans = new int[n][n];
        int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dirIndex = 0;   // 方向指针
        int count = 1, total = n * n;
        int x = 0, y = 0;
        while (count <= total){
            ans [y][x] = count++;
            int nextX = x + direct[dirIndex][1];
            int nextY = y + direct[dirIndex][0];
            if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || ans[nextY][nextX] != 0){
                dirIndex = (dirIndex + 1) % 4;
            }
            x += direct[dirIndex][1];
            y += direct[dirIndex][0];
        }
        return ans;
    }

    /**
     * 递归生成矩阵
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.8 MB , 在所有 Java 提交中击败了 10.71% 的用户
     * */
    public int[][] generateMatrix3(int n) {
        if (n < 1) {
            return null;
        }
        int[][] ans = new int[n][n];
        generate(0, n - 1, 0, n - 1, 1, ans);
        return ans;
    }
    // 递归生成矩阵函数
    void generate(int x1, int x2, int y1, int y2, int start, int[][] ans){
        if(x1 > x2 || y1 > y2) {
            return;
        }
        if(x1 == x2){
            ans[x1][y1] = start;
            return;
        }
        for (int i = x1; i < x2; i++) {
            ans[y1][i] = start++;
        }
        for (int i = y1; i < y2; i++) {
            ans[i][x2] = start++;
        }
        for (int i = x2; i > x1; i--) {
            ans[y2][i] = start++;
        }
        for (int i = y2; i > y1; i--) {
            ans[i][x1] = start++;
        }
        generate(x1 + 1, x2 - 1, y1 + 1, y2 - 1, start, ans);
    }
}
