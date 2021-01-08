package cn.czl.list.search.max;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/12/26 8:59
 * @description:
 *      85. 最大矩形
 *      - 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *      示例 1：
 *          输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 *          输出：6
 *          解释：最大矩形如上图所示。
 *      示例 2：
 *          输入：matrix = []
 *          输出：0
 *      示例 3：
 *          输入：matrix = [["0"]]
 *          输出：0
 *      示例 4：
 *          输入：matrix = [["1"]]
 *          输出：1
 *      示例 5：
 *          输入：matrix = [["0","0"]]
 *          输出：0
 *      提示：
 *          rows == matrix.length
 *          cols == matrix[0].length
 *          0 <= row, cols <= 200
 *          matrix[i][j] 为 '0' 或 '1'
 */
public class MaxRectangle_Hard {

    private char[][] MATRIX1 = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};

    @Test
    public void TestSolution(){
        System.out.println(maximalRectangle(MATRIX1));
    }

    /**
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 66.83% 的用户
     * 内存消耗： 41.8 MB , 在所有 Java 提交中击败了 41.87% 的用户
     * */
    public int maximalRectangle(char[][] matrix) {
        int maxArea = 0;
        int height = matrix.length;
        if(height == 0){
            return maxArea;
        }
        int width = matrix[0].length;
        int left[][] = new int[height][width];  // 每个格子，最大的宽度。
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(matrix[i][j] == '1'){
                    left[i][j] = (j == 0 ? 0 : left[i][j-1]) + 1;
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(matrix[i][j] == '0'){
                    continue;
                }
                int curWid = left[i][j];
                int curArea = curWid;
                for (int k = i - 1; k >= 0; k--) {  // 往上模拟，找到当前格子上最大的矩形
                    curWid = Math.min(curWid, left[k][j]);
                    curArea = Math.max(curArea, (i - k + 1) * curWid);
                    if(curWid == 0){    // 剪枝
                        break;
                    }
                }
                maxArea = Math.max(maxArea, curArea);
            }
        }
        return maxArea;
    }

    public int maximalRectangle2(char[][] matrix) {
        int maxArea = 0;
        int height = matrix.length;
        if (height == 0) {
            return maxArea;
        }
        int width = matrix[0].length;
        int left[][] = new int[height][width];  // 每个格子，最大的宽度。
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }
        for(int j = 0; j < width; j++){
            int[] up = new int[height];
            int[] down = new int[height];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < height; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]){
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            
        }
        return maxArea;
    }
}
