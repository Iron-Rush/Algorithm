package cn.czl.list.search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2021/1/29 9:20
 * @description:
 *      1631. 最小体力消耗路径
 *      - 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 *      - 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 *      - 请你返回从左上角走到右下角的最小 体力消耗值 。
 *      示例 1：
 *          输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 *          输出：2
 *          解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 *              这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 *      示例 2：
 *          输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 *          输出：1
 *          解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 *      示例 3：
 *          输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 *          输出：0
 *          解释：上图所示路径不需要消耗任何体力。
 *      提示：
 *          rows == heights.length
 *          columns == heights[i].length
 *          1 <= rows, columns <= 100
 *          1 <= heights[i][j] <= 106
 */
public class PathWithMinimumEffort_Normal {

    /**
     * 二分搜索 + dfs
     * 二分搜索提供当前的分数， dfs负责判断该分是否能够抵达终点
     * 执行用时： 83 ms , 在所有 Java 提交中击败了 72.73% 的用户
     * 内存消耗： 39.5 MB , 在所有 Java 提交中击败了 27.10% 的用户
     * */
    int[][] direct = {{1,0},{-1,0},{0,-1},{0,1}};
    public int minimumEffortPath(int[][] heights) {
        int xEdge = heights.length;
        int yEdge = heights[0].length;
        // 二分查找
//        int l = 0, r = 1000000;
        int l = 0, r = getMaxVal(heights);
        while (true){
            boolean[][] visited = new boolean[xEdge][yEdge];
            if(l >= r){
                break;
            }
            int mid = (l + r) / 2;
            if(dfs(heights, visited, 0, 0, xEdge, yEdge, mid)){
                r = mid;
            }else {
                l = mid + 1;
            }
        }
        return r;
    }
    // 根据二分查找给出的数值，判断当前值是否能抵达终点
    boolean dfs(int[][] map, boolean[][] visited, int x, int y, int xEdge, int yEdge, int target){
        if(x == xEdge - 1 && y == yEdge - 1){
            return true;
        }
        visited[x][y] = true;
        for(int[] d : direct){
            int x1 = x + d[0];
            int y1 = y + d[1];
            // 判断该点是否可用
            if(x1 < 0 || x1 >= xEdge || y1 < 0 || y1 >= yEdge || visited[x1][y1]){
                continue;
            }
            // 判断预估值能否通过这条路
            if(target < Math.abs(map[x][y] - map[x1][y1])){
                continue;
            }
            // 递归，判断该点是否能抵达终点
            if(dfs(map, visited, x1, y1, xEdge, yEdge, target)){
                return true;
            }
        }
        return false;
    }
    // 获取二维数组中的最大值
    int getMaxVal(int[][] nums){
        int ans = Integer.MIN_VALUE;
        for (int[] list : nums) {
            for (int num : list) {
                ans = Math.max(ans, num);
            }
        }
        return ans;
    }

    /**
     * BFS-广度优先搜索
     * 执行用时： 183 ms , 在所有 Java 提交中击败了 16.11% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 53.07% 的用户
     * */
    public int minimumEffortPath2(int[][] heights) {
        int xEdge = heights.length;
        int yEdge = heights[0].length;
        int left = 0, right = getMaxVal(heights), ans = 0;
        while (left <= right){
            int mid = (left + right) / 2;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0});
            boolean[][] visited = new boolean[xEdge][yEdge];
            visited[0][0] = true;
            while (!queue.isEmpty()){
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int[] d : direct) {
                    int x1 = d[0] + x;
                    int y1 = d[1] + y;
                    if(x1 >= 0 && y1 >= 0 && x1 < xEdge && y1 < yEdge
                        && !visited[x1][y1] && Math.abs(heights[x][y] - heights[x1][y1]) <= mid){
                        queue.offer(new int[]{x1, y1});
                        visited[x1][y1] = true;
                    }
                }
            }
            if(visited[xEdge-1][yEdge-1]){
                ans = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
