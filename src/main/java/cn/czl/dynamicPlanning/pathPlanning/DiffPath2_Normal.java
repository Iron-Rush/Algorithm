package cn.czl.dynamicPlanning.pathPlanning;


/**
 * @author wolf
 * @email chenzilin97@gmail.com
 * */

import org.junit.jupiter.api.Test;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 说明：m 和 n 的值均不超过 100。
 *
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * */
public class DiffPath2_Normal {
//    private static int[][] PATH = {{0,0,0},{0,1,0},{0,0,0}};
    private static int[][] PATH = {{0,0},{1,1},{0,0}};

    @Test
    public void getPath(){
        int path = uniquePathsWithObstacles(PATH);
        System.out.println(path);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        // 为空判断
        if (obstacleGrid == null || col == 0 || row ==0){
            return 0;
        }
        int[][] dp = new int[row][col];
        // 将上边界和左边界置1，若存在障碍物则跳过
        for (int i = 0; i < row && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < col && obstacleGrid[0][i] == 0; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 0){
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }
        return dp[row-1][col-1];
    }

//    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
//        int row = obstacleGrid.length;
//        int col = obstacleGrid[0].length;
//        // 为空判断
//        if (obstacleGrid == null || obstacleGrid[0][0] == 1){
//            return 0;
//        }
////        obstacleGrid[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                if (i == 0 || j == 0) {
//                    if (obstacleGrid[i][j] == 0) {
//                        obstacleGrid[i][j] = 1;
//                    } else {
//                        obstacleGrid[i][j] = -1;
//                    }
//                }else if (obstacleGrid[i][j] == 1){
//                    obstacleGrid[i][j] = 0;
//                }else{
//                    obstacleGrid[i][j] = obstacleGrid[i][j-1] + obstacleGrid[i-1][j];
//                }
//            }
//        }
//        return obstacleGrid[row-1][col-1];
//    }

}
