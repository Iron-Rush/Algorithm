package cn.czl.dynamicPlanning.pathPlanning;

/**
 * @author wolf
 * @email chenzilin97@gmail.com
 * */

import org.junit.jupiter.api.Test;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * */

public class MininumPath_Normal {
//    private static int[][] PATH = {{1,3,1},{1,5,1},{4,2,1}};
    private static int[][] PATH = {{1,2,5},{3,2,1}};
//    private static int[][] PATH = {{0}};

    @Test
    public void getMinPath(){
        System.out.println(minPathSum3(PATH));
    }
    // 原数组直接修改的动态规划
    public int minPathSum(int[][] grid) {
        int len = grid.length;
        int height = grid[0].length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < height; j++){
                if (i == 0 && j == 0){      // 起始点，无需操作
                    continue;
                }else if(i == 0){           // 填充上边界
                    grid[i][j] = grid[i][j-1] + grid[i][j];
                } else if(j == 0){          // 填充右边界
                    grid[i][j] = grid[i-1][j] + grid[i][j];
                }else{                      // 填充剩余格,每个格子必然从上或者左侧进入，通过取最小值，得到到达此格的最优方法
//                    grid[i][j] = grid[i][j] + (grid[i-1][j] > grid[i][j-1] ? grid[i][j-1]:grid[i-1][j]);
                    grid[i][j] = grid[i][j] + Math.min(grid[i][j-1],grid[i-1][j]);
                }
            }
        }

        return grid[len-1][height-1];
    }

    // 一维数组记录的动态规划
    public int minPathSum2(int[][] grid) {
        int len = grid[0].length;   // 一位数组长度
        int height = grid.length;   // 二维数组高度
        int[] dp = new int[len];    // 初始化动态规划数组
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < len; j++) { // 逐行计算，通过dp数组，记录最优解
                if (i == 0 && j == 0){      // 起始点，赋值给dp数组
                    dp[j] = grid[i][j];
                }else if(i == 0){           // i=0时，为上边界，该格解为前一个与当前之和
                    dp[j] = dp[j-1] + grid[i][j];
                } else if(j == 0){          // j=0时，为行首，该格解为上一行行首与当前格之和
                    dp[j] = dp[j] + grid[i][j];
                }else{                      // 中间格，取当前格左侧(已计算的dp[j-1])和上一行此格的最小值，并与此格相加
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j-1]);
                }
            }
        }
        return dp[len-1];
    }
    // 二维数组记录的动态规划
    public int minPathSum3(int[][] grid){
        int len = grid[0].length;
        int height = grid.length;
        int[][] dp = new int[height][len];
        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < len; j++){
                if (i == 0 && j == 0){      // 起始点，赋值给dp数组
                    dp[i][j] = grid[i][j];
                }else if(i == 0){           // i=0时，为上边界，该格解为前一个与当前之和
                    dp[i][j] = grid[i][j] + dp[i][j-1];
                } else if(j == 0){          // j=0时，为行首，该格解为上一行行首与当前格之和
                    dp[i][j] = grid[i][j] + dp[i-1][j];
                }else{                      // 中间格，取当前格左侧(已计算的dp[j-1])和上一行此格的最小值，并与此格相加
                    dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[height-1][len-1];
    }
    // 下面为最快解
    public int minPathSum4(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[] mark = new int[col];
        mark[col-1] = grid[row-1][col-1];
        for(int i=col-2; i>=0; i--){
            mark[i] = grid[row-1][i] + mark[i+1];
        }
        update(mark, grid, col, row-2);
        return mark[0];
    }

    private void update(int[] mark, int[][] grid, int col, int row) {
        if(row<0)
            return;
        mark[col-1] += grid[row][col-1];
        for(int i=col-2; i>=0; i--){
            mark[i] = grid[row][i] + Math.min(mark[i], mark[i+1]);
        }
        update(mark, grid, col, row-1);
    }
}
