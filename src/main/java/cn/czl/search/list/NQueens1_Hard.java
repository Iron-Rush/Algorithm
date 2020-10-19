package cn.czl.search.list;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/10/17 21:55
 * @description:
 *      51. N皇后，n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，
 *      并且使皇后彼此之间不能相互攻击。
 *      - 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，
 *      该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *      示例：
 *          输入：4
 *          输出：[
 *           [".Q..",  // 解法 1
 *            "...Q",
 *            "Q...",
 *            "..Q."],
 *           ["..Q.",  // 解法 2
 *            "Q...",
 *            "...Q",
 *            ".Q.."]]
 */
public class NQueens1_Hard {

    private static int N = 4;

    @Test
    public void TestSolution(){
        System.out.println(solveNQueens(N));
    }

    /**
     * 递归回溯
     * 执行用时：9 ms, 在所有 Java 提交中击败了19.31%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了99.98%的用户
     * */
    List<List<String>> resList = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        //初始化空棋盘'.'
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }
        List<StringBuilder> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new StringBuilder(sb));
        }
        buildMap(map, 0);
        return resList;
    }
    // 回溯函数
    private void buildMap(List<StringBuilder> path, int row){
        //触发结束条件
        if(row == path.size()){
            List<String> res = new ArrayList<>();
            for (StringBuilder sb : path) {
                res.add(sb.toString());
            }
            resList.add(new ArrayList<>(res));
        }else {
            for (int col = 0; col < path.size(); col++) {
                // 排除不合法选择
                if(isVaild(path, row, col)){
                    // 做选择
                    path.get(row).setCharAt(col, 'Q');
                    buildMap(path, row + 1);
                    path.get(row).setCharAt(col, '.');
                }
            }
        }
    }
    // path[row][col]是否冲突
    private boolean isVaild(List<StringBuilder> path, int row, int col){
        int n = path.size();
        // 列冲突检查
        for (int i = 0; i < n; i++) {
            if (path.get(i).charAt(col) == 'Q'){
                return false;
            }
        }
        // 右上冲突检查
        for (int r = row - 1, c = col + 1;
        r >= 0 && c < n; r--, c++) {
            if (path.get(r).charAt(c) == 'Q'){
                return false;
            }
        }
        // 左上冲突检查
        for (int r = row - 1, c = col - 1;
             r >= 0 && c >= 0; r--, c--) {
            if (path.get(r).charAt(c) == 'Q'){
                return false;
            }
        }
        return true;
    }

}
