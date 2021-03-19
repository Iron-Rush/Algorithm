package cn.czl.study.stackAndQueue.stack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2021/3/18 16:24
 * @description:
 *      542. 01 矩阵
 *      - 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 *      - 两个相邻元素间的距离为 1 。
 *      示例 1：
 *          输入： [[0,0,0],
 *                 [0,1,0],
 *                 [0,0,0]]
 *          输出： [[0,0,0],
 *                 [0,1,0],
 *                 [0,0,0]]
 *      示例 2：
 *          输入： [[0,0,0],
 *                 [0,1,0],
 *                 [1,1,1]]
 *
 *          输出： [[0,0,0],
 *                 [0,1,0],
 *                 [1,2,1]]
 *      提示：
 *          给定矩阵的元素个数不超过 10000。
 *          给定矩阵中至少有一个元素是 0。
 *          矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 */
public class UpdateMatrix_Normal {

    private int[][] MATRIX1 = {{0,0,0},{0,1,0},{0,0,0}};
    private int[][] MATRIX2 = {{0,0,0},{0,1,0},{1,1,1}};

    @Test
    public void TestSolution(){
        System.out.println(Arrays.deepToString(updateMatrix3(MATRIX1)));
        System.out.println(Arrays.deepToString(updateMatrix3(MATRIX2)));

    }

    /**
     * 广度优先搜索
     * 超出时间限制
     * */
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return matrix;
        }
        int yEdge = matrix.length, xEdge = matrix[0].length;
        int[][] ans = new int[yEdge][xEdge];
        for (int y = 0; y < yEdge; y++) {
            for (int x = 0; x < xEdge; x++) {
                if(matrix[y][x] == 0){
                    ans[y][x] = 0;
                }else{
                    ans[y][x] = BFS(matrix, x, y, xEdge, yEdge);
                }
            }
        }
        return ans;
    }
    // BFS - 广度优先搜索
    int BFS(int[][] matrix, int x, int y, int xEdge, int yEdge){
        boolean[][] visited = new boolean[yEdge][xEdge];
        int[][] direct = {{0,1},{0,-1},{1,0},{-1,0}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{y, x});
        int depth = 0;
        while (!queue.isEmpty()){
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                int[] pos = queue.poll();
                int x1 = pos[1], y1 = pos[0];
                visited[y1][x1] = true;
                if(matrix[y1][x1] == 0){
                    return depth;
                }
                for (int[] dir : direct) {
                    int x2 = x1 + dir[1];
                    int y2 = y1 + dir[0];
                    if(x2 >= 0 && y2 >= 0 && x2 < xEdge && y2 < yEdge && !visited[y2][x2]){
                        queue.offer(new int[]{y2, x2});
                    }
                }
            }
            depth++;
        }
        return 0;
    }

    /**
     * 广度优先搜索 - 逻辑优化
     * 从有 0 的位置，向四周扩散。通过boolean[]记录走过的位置
     * 执行用时： 18 ms , 在所有 Java 提交中击败了 28.40% 的用户
     * 内存消耗： 41.5 MB , 在所有 Java 提交中击败了 68.77% 的用户
     * */
    public int[][] updateMatrix2(int[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return matrix;
        }
        int yEdge = matrix.length, xEdge = matrix[0].length;
        int[][] ans = new int[yEdge][xEdge];
        int[][] direct = {{0,1},{0,-1},{1,0},{-1,0}};
        boolean[][] visited = new boolean[yEdge][xEdge];
        Queue<int[]> queue = new LinkedList<>();
        for (int y = 0; y < yEdge; y++) {
            for (int x = 0; x < xEdge; x++) {
                if(matrix[y][x] == 0){
                    queue.offer(new int[]{x,y});
                    visited[y][x] = true;
                }
            }
        }
        while (!queue.isEmpty()){
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            for (int[] dir : direct) {
                int x1 = x + dir[0];
                int y1 = y + dir[1];
                if(x1 >= 0 && y1 >= 0 && x1 < xEdge && y1 < yEdge && !visited[y1][x1]){
                    ans[y1][x1] = ans[y][x] + 1;
                    queue.offer(new int[]{x1, y1});
                    visited[y1][x1] = true;
                }
            }
        }
        return ans;
    }

    /**
     * 动态规划 - 从四个方向递推到达该位置的最近距离
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 78.70% 的用户
     * 内存消耗： 41.8 MB , 在所有 Java 提交中击败了 53.26% 的用户
     * */
    public int[][] updateMatrix3(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }
        int yEdge = matrix.length, xEdge = matrix[0].length;
        int[][] ans = new int[yEdge][xEdge];
        for (int y = 0; y < yEdge; y++) {       // 初始化dp数组
            for (int x = 0; x < xEdge; x++) {
                ans[y][x] = matrix[y][x] == 0 ? 0 : Integer.MAX_VALUE/2;
            }
        }
        // 水平向左移动，竖直向上移动
        for (int y = 0; y < yEdge; y++) {
            for (int x = 0; x < xEdge; x++) {
                if(y - 1 >= 0){
                    ans[y][x] = Math.min(ans[y][x], ans[y-1][x] + 1);
                }
                if(x - 1 >= 0){
                    ans[y][x] = Math.min(ans[y][x], ans[y][x-1] + 1);
                }
            }
        }
        // 水平向左移动，竖直向下移动
        for (int y = yEdge - 1; y >= 0; y--) {
            for (int x = 0; x < xEdge; x++) {
                if(y + 1 < yEdge){
                    ans[y][x] = Math.min(ans[y][x], ans[y+1][x] + 1);
                }
                if(x - 1 >= 0){
                    ans[y][x] = Math.min(ans[y][x], ans[y][x-1] + 1);
                }
            }
        }
        // 水平向右移动，竖直向上移动
        for (int y = 0; y < yEdge; y++) {
            for (int x = xEdge - 1; x >= 0; x--) {
                if(y - 1 >= 0){
                    ans[y][x] = Math.min(ans[y][x], ans[y-1][x] + 1);
                }
                if(x + 1 < xEdge){
                    ans[y][x] = Math.min(ans[y][x], ans[y][x+1] + 1);
                }
            }
        }
        // 水平向右移动，竖直向下移动
        for (int y = yEdge - 1; y >= 0; y--) {
            for (int x = xEdge - 1; x >= 0; x--) {
                if(y + 1 < yEdge){
                    ans[y][x] = Math.min(ans[y][x], ans[y+1][x] + 1);
                }
                if(x + 1 < xEdge){
                    ans[y][x] = Math.min(ans[y][x], ans[y][x+1] + 1);
                }
            }
        }
        return ans;
    }

    /**
     * 动态规划 - 四个方向优化[减免重复计算]
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 84.89% 的用户
     * 内存消耗： 41.9 MB , 在所有 Java 提交中击败了 37.41% 的用户
     * */
    public int[][] updateMatrix4(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }
        int yEdge = matrix.length, xEdge = matrix[0].length;
        int[][] ans = new int[yEdge][xEdge];
        for (int y = 0; y < yEdge; y++) {       // 初始化dp数组
            for (int x = 0; x < xEdge; x++) {
                ans[y][x] = matrix[y][x] == 0 ? 0 : Integer.MAX_VALUE/2;
            }
        }
        // 水平向左移动，竖直向上移动
        for (int y = 0; y < yEdge; y++) {
            for (int x = 0; x < xEdge; x++) {
                if(y - 1 >= 0){
                    ans[y][x] = Math.min(ans[y][x], ans[y-1][x] + 1);
                }
                if(x - 1 >= 0){
                    ans[y][x] = Math.min(ans[y][x], ans[y][x-1] + 1);
                }
            }
        }
        // 水平向右移动，竖直向下移动
        for (int y = yEdge - 1; y >= 0; y--) {
            for (int x = xEdge - 1; x >= 0; x--) {
                if(y + 1 < yEdge){
                    ans[y][x] = Math.min(ans[y][x], ans[y+1][x] + 1);
                }
                if(x + 1 < xEdge){
                    ans[y][x] = Math.min(ans[y][x], ans[y][x+1] + 1);
                }
            }
        }
        return ans;
    }
}
