package cn.czl.list.search;

/**
 * @author RedRush
 * @date 2022/2/24 13:45
 * @description:
 *      1706. 球会落何处
 *      - 用一个大小为 m x n 的二维网格 grid 表示一个箱子。
 *          你有 n 颗球。箱子的顶部和底部都是开着的。
 *      - 箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。
 *          - 将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。
 *          - 将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
 *          - 在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。
 *              如果球恰好卡在两块挡板之间的 "V" 形图案，
 *              或者被一块挡导向到箱子的任意一侧边上，就会卡住。
 *          - 返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在
 *              顶部的第 i 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 -1 。
 *      示例 1：
 *          输入：grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],
 *              [-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
 *          输出：[1,-1,-1,-1,-1]
 *          解释：示例如图：
 *              b0 球开始放在第 0 列上，最终从箱子底部第 1 列掉出。
 *              b1 球开始放在第 1 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
 *              b2 球开始放在第 2 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
 *              b3 球开始放在第 3 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
 *              b4 球开始放在第 4 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
 *      示例 2：
 *          输入：grid = [[-1]]
 *          输出：[-1]
 *          解释：球被卡在箱子左侧边上。
 *      示例 3：
 *          输入：grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
 *          输出：[0,1,2,3,4,-1]
 *          提示：
 *              m == grid.length
 *              n == grid[i].length
 *              1 <= m, n <= 100
 *              grid[i][j] 为 1 或 -1
 */
public class WhereWillTheBallFall_Normal {

    /**
     * 模拟 + 递归搜索
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42.6 MB , 在所有 Java 提交中击败了 22.89% 的用户
     * */
    public int[] findBall(int[][] grid) {
        int ySize = grid.length, xSize = grid[0].length;
        int[] ans = new int[xSize];
        for(int i = 0; i < xSize; i++){
            ans[i] = search(grid, i, 0, xSize, ySize);
        }
        return ans;
    }
    // 递归搜索
    public int search(int[][] grid, int x, int y, int xSize, int ySize){
        // 抵达底部
        if(y == ySize){
            return x;
        }
        // 判断滑落轨迹是否越界
        int dire = grid[y][x];
        if(x + dire < 0 || x + dire >= xSize){
            return -1;
        }
        // 通道挡板不可通行
        if(grid[y][x] + grid[y][x+dire] == 0){
            return -1;
        }
        // 递归搜索
        return search(grid, x+dire, y+1, xSize, ySize);
    }

    /**
     * 模拟 + 迭代
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 41.57% 的用户
     * 内存消耗： 42.9 MB , 在所有 Java 提交中击败了 5.42% 的用户
     * */
    public int[] findBall2(int[][] grid) {
        int ySize = grid.length, xSize = grid[0].length;
        int[] ans = new int[xSize];
        for (int i = 0; i < xSize; i++) {
            int x = i;      // 初始化球的位置
            for(int[] row : grid){
                int dire = row[x];
                x += dire;  // 移动球
                if(x < 0 || x == xSize || row[x] != dire){
                    x = -1;
                    break;
                }
            }
            ans[i] = x;
        }
        return ans;
    }
}
