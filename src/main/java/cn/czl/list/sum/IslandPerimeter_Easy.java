package cn.czl.list.sum;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/10/30 9:16
 * @description:
 *      463. 岛屿的周长
 *      - 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 *      - 网格中的格子水平和垂直方向相连（对角线方向不相连）。
 *      整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 *      - 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
 *      格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *      示例：
 *          输入:
 *          [[0,1,0,0],
 *           [1,1,1,0],
 *           [0,1,0,0],
 *           [1,1,0,0]]
 *          输出: 16
 */
public class IslandPerimeter_Easy {

    private static int[][] GRID1 = new int[][]{{1,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
    private static int[][] GRID2 = new int[][]{{1,1}};

    @Test
    public void TestSolution(){
        System.out.println(islandPerimeter2(GRID1));
    }

    /**
     * 先处理上下边界，再处理中间岛屿
     * 执行用时：7 ms, 在所有 Java 提交中击败了97.52%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了13.34%的用户
     * */
    public int islandPerimeter(int[][] grid) {
        int width = grid[0].length, height = grid.length;
        if (width == 1 && height == 1){
            if (grid[0][0] == 1){
                return 4;
            }else {
                return 0;
            }
        }
        int count = 0;
        boolean pre = false;
        if (width != 1){
            for (int i = 0; i < grid[0].length; i++){
                if (grid[0][i] == 1){
                    if (pre){
                        count+=2;
                    }else {
                        count+=4;
                        pre = true;
                    }
                }else {
                    pre = false;
                }
            }
        }
        pre = false;
        if (height != 1){
            for (int i = 0; i < grid.length; i++){
                if (grid[i][0] == 1){
                    if (pre){
                        count+=2;
                    }else {
                        count+=4;
                        pre = true;
                    }
                }else {
                    pre = false;
                }
            }
        }
        if (grid[0][0] == 1 && height != 1 && width != 1){
            count-=4;
        }
        pre = false;
        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (j == 0){
                    if (grid[i][j] == 1){
                        pre = true;
                    }else {
                        pre = false;
                    }
                }else {
                    if (grid[i][j] == 1){
                        count += 4;
                        if (pre){
                            count-=2;
                        }
                        if (grid[i-1][j] == 1){
                            count-=2;
                        }
                        pre = true;
                    }else {
                        pre = false;
                    }
                }
            }
            pre = false;
        }
        return count;
    }

    /**
     * 遍历全部，每个格仅需判断左相邻/上相邻
     * 执行用时：7 ms, 在所有 Java 提交中击败了97.52%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了19.99%的用户
     * */
    public int islandPerimeter2(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1){
                    count += 4;
                    if (j > 0 && grid[i][j-1] == 1){
                        count-=2;
                    }
                    if (i > 0 && grid[i-1][j] == 1){
                        count-=2;
                    }
                }
            }
        }
        return count;
    }

}
