package cn.czl.search.list;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/10/19 10:47
 * @description:
 *      52. N皇后 II
 *      - n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *      - 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *      输入：4    输出：2
 *      解释：4 皇后问题存在如下两个不同的解法。
 *      [[".Q..",  // 解法 1
 *        "...Q",
 *        "Q...",
 *        "..Q."],
 *       ["..Q.",  // 解法 2
 *        "Q...",
 *        "...Q",
 *        ".Q.."]]
 */
public class NQueens2_Hard {
    private static int N = 4;

    @Test
    public void TestSolution(){
        System.out.println(totalNQueens(N));
    }

    /**
     * 递归回溯
     * 执行用时：2 ms, 在所有 Java 提交中击败了56.50%的用户
     * 内存消耗：34.7 MB, 在所有 Java 提交中击败了100.00%的用户
     * */
    int count = 0;
    public int totalNQueens(int n) {
        int[][] map = new int[n][n];    // 默认为0, 皇后为1
        BuildMap(map, 0);
        return count;
    }
    // 回溯函数
    private void BuildMap(int[][] map, int row){
        if(row == map.length){
//            for (int[] temp : map) {
//                System.out.print(Arrays.toString(temp));
//            }
//            System.out.println();
            count++;
        }else {
            for (int col = 0; col < map.length; col++) {
                if (isValid(map, row, col)){
                    map[row][col] = 1;
                    BuildMap(map, row + 1);
                    map[row][col] = 0;
                }
            }
        }
    }
    // 冲突检查函数
    private boolean isValid(int[][] map, int row, int col){
        int n = map.length;
        // 列检查
        for (int r = 0; r < n; r++) {
            if (map[r][col] == 1){
                return false;
            }
        }
        // 右上检查
        for (int r = row - 1, c = col + 1;
             r >= 0 && c < n; r--, c++) {
            if(map[r][c] == 1){
                return false;
            }
        }
        // 左上检查
        for (int r = row - 1, c = col - 1;
             r >= 0 && c >= 0; r--, c--) {
            if(map[r][c] == 1){
                return false;
            }
        }
        return true;
    }
}
