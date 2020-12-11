package cn.czl.study.stackAndQueue.queue;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/12/11 14:48
 * @description:
 *      200. 岛屿数量
 *      - 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *      - 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *      - 此外，你可以假设该网格的四条边均被水包围。
 *      示例 1：
 *          输入：grid = [
 *            ["1","1","1","1","0"],
 *            ["1","1","0","1","0"],
 *            ["1","1","0","0","0"],
 *            ["0","0","0","0","0"] ]
 *          输出：1
 *      示例 2：
 *          输入：grid = [
 *            ["1","1","0","0","0"],
 *            ["1","1","0","0","0"],
 *            ["0","0","1","0","0"],
 *            ["0","0","0","1","1"] ]
 *          输出：3
 *      提示：
 *          m == grid.length
 *          n == grid[i].length
 *          1 <= m, n <= 300
 *          grid[i][j] 的值为 '0' 或 '1'
 */
public class NumIslands_Normal {

    private char[][] GRID1 = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','1','1','0'},};
    private char[][] GRID2 = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'},};
    private char[][] GRID3 = {};
    @Test
    public void TestSolution(){
        System.out.println(numIslands2(GRID1));
        System.out.println(numIslands2(GRID2));
    }
    /**
     * dfs-深度优先搜索，填充本岛屿土地。
     * 执行用时：2 ms, 在所有 Java 提交中击败了93.42%的用户
     * 内存消耗：41.3 MB, 在所有 Java 提交中击败了27.55%的用户
     * */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)   return 0;
        int count = 0;
        int ySize = grid.length;
        int xSize = grid[0].length;
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if(grid[y][x] == '1'){
                    count++;
                    fillIslands(grid, x, y, xSize, ySize);
                }
            }
        }
        return count;
    }
    // 深度优先搜索-填充小岛
    void fillIslands(char[][] grid, int x, int y, int xSize, int ySize){
        if(x < 0 || y < 0 || x >= xSize || y >= ySize){
            return;
        }
        if(grid[y][x] == '1'){
            grid[y][x] = '.';
            fillIslands(grid, x-1, y, xSize, ySize);
            fillIslands(grid, x+1, y, xSize, ySize);
            fillIslands(grid, x, y-1, xSize, ySize);
            fillIslands(grid, x, y+1, xSize, ySize);
        }
    }

    /**
     * bfs-广度优先搜索
     * 执行用时：11 ms, 在所有 Java 提交中击败了5.75%的用户
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了72.26%的用户
     * */
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int count = 0;
        int ySize = grid.length;
        int xSize = grid[0].length;
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if(grid[y][x] == '1'){
                    count++;
                    bfs(grid, ySize, xSize, y, x);
                }
            }
        }
        return count;
    }
    // 广度优先搜索-填充小岛
    private void bfs(char[][] grid, int ySize, int xSize, int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y,x});
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int y1 = cur[0], x1 = cur[1];
            if(!(x1 < 0 || y1 < 0 || x1 >= xSize || y1 >= ySize)
                    && grid[y1][x1] == '1'){
                grid[y1][x1] = '0';
                queue.add(new int[]{y1+1, x1});
                queue.add(new int[]{y1-1, x1});
                queue.add(new int[]{y1, x1+1});
                queue.add(new int[]{y1, x1-1});
            }
        }
    }
}
