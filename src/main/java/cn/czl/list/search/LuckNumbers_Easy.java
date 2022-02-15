package cn.czl.list.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author RedRush
 * @date 2022/2/15 10:52
 * @description:
 *      1380. 矩阵中的幸运数
 *      - 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 *      - 幸运数是指矩阵中满足同时下列两个条件的元素：
 *          - 在同一行的所有元素中最小
 *          - 在同一列的所有元素中最大
 *      示例 1：
 *          输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
 *          输出：[15]
 *          解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 *      示例 2：
 *          输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 *          输出：[12]
 *          解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 *      示例 3：
 *          输入：matrix = [[7,8],[1,2]]
 *          输出：[7]
 *      提示：
 *          m == mat.length
 *          n == mat[i].length
 *          1 <= n, m <= 50
 *          1 <= matrix[i][j] <= 10^5
 *          矩阵中的所有元素都是不同的
 */
public class LuckNumbers_Easy {

    /**
     * 模拟 + 记忆化搜索
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.93% 的用户
     * 内存消耗： 42 MB , 在所有 Java 提交中击败了 5.17% 的用户
     * */
    Map<Integer, Integer> memo = new HashMap<>();
    public List<Integer> luckyNumbers (int[][] matrix) {
        int ySize = matrix.length, xSize = matrix[0].length;
        List<Integer> ans = new ArrayList<>();
        for(int y = 0; y < ySize; y++){
            int minIdx = getCurRowMinIdx(matrix[y]);
            int maxIdx = getCurColMaxIdx(matrix, minIdx);
            if(maxIdx == y){
                ans.add(matrix[y][minIdx]);
            }
        }
        return ans;
    }
    // 获取当前行最小数字下标
    private int getCurRowMinIdx(int[] row){
        int minIdx = 0;
        for(int i = 0; i < row.length; i++){
            if(row[i] < row[minIdx]){
                minIdx = i;
            }
        }
        return minIdx;
    }
    // 获取当前列最大数字下标
    private int getCurColMaxIdx(int[][] matrix, int x){
        if(memo.containsKey(x)){
            return memo.get(x);
        }
        int maxIdx = 0;
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i][x] > matrix[maxIdx][x]){
                maxIdx = i;
            }
        }
        memo.put(x, maxIdx);
        return maxIdx;
    }

    /**
     * 预处理矩阵 计算出每列中的最大值和每行中的最小值
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 84.14% 的用户
     * 内存消耗： 41.6 MB , 在所有 Java 提交中击败了 13.10% 的用户
     * */
    public List<Integer> luckyNumbers2 (int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int ySize = matrix.length, xSize = matrix[0].length;
        int[] col = new int[xSize], row = new int[ySize];
        for(int y = 0; y < ySize; y++){
            row[y] = Integer.MAX_VALUE;
            for (int x = 0; x < xSize; x++) {
                int num = matrix[y][x];
                row[y] = Math.min(num, row[y]);
                col[x] = Math.max(num, col[x]);
            }
        }
        for(int y = 0; y < ySize; y++){
            for (int x = 0; x < xSize; x++) {
                int num = matrix[y][x];
                if(num == col[x] && num == row[y]){
                    ans.add(num);
                }
            }
        }
        return ans;
    }
}
