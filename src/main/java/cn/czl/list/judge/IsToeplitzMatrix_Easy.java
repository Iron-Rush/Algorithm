package cn.czl.list.judge;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/2/22 10:56
 * @description:
 *      766. 托普利茨矩阵
 *      - 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；
 *          否则，返回 false 。
 *      - 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 *      示例 1：
 *          输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 *          输出：true
 *          解释：
 *              在上述矩阵中, 其对角线为:
 *              "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
 *              各条对角线上的所有元素均相同, 因此答案是 True 。
 *      示例 2：
 *          输入：matrix = [[1,2],[2,2]]
 *          输出：false
 *          解释：对角线 "[1, 2]" 上的元素不同。
 *      提示：
 *          1. m == matrix.length
 *          2. n == matrix[i].length
 *          3. 1 <= m, n <= 20
 *          4. 0 <= matrix[i][j] <= 99
 *      进阶：
 *          如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
 *          如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
 */
public class IsToeplitzMatrix_Easy {

    private int[][] MATRIX1 = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};
    private int[][] MATRIX2 = {{1,2},{2,2}};
    private int[][] MATRIX3 = {{11,74,0,93},{40,11,74,7}};

    @Test
    public void TestSolution(){
//        System.out.println(MATRIX3.length + MATRIX3[0].length - 1);
        System.out.println(isToeplitzMatrix2(MATRIX1));
        System.out.println(isToeplitzMatrix2(MATRIX2));
        System.out.println(isToeplitzMatrix2(MATRIX3));
    }

    /**
     * 遍历第一行、第一列，同时搜索比对遍历每位数的对角线是否符合规则
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 23.59% 的用户
     * */
    public boolean isToeplitzMatrix(int[][] matrix) {
        if(matrix == null || matrix.length <= 1 || matrix[0].length <= 1){
            return true;
        }
        int xEdge = matrix[0].length;
        int yEdge = matrix.length;
        for (int i = 0; i < xEdge; i++) {
            int x = i, y = 0;
            int cur = matrix[y][x];
            while (y < yEdge && x < xEdge){
                if(matrix[y++][x++] != cur){
                    return false;
                }
            }
        }
        for (int i = 1; i < yEdge; i++) {
            int x = 0, y = i;
            int cur = matrix[y][x];
            while (y < yEdge && x < xEdge){
                if(matrix[y++][x++] != cur){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 遍历搜索结构代码优化
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 71.54% 的用户
     * */
    public boolean isToeplitzMatrix2(int[][] matrix) {
        if(matrix == null || matrix.length <= 1 || matrix[0].length <= 1){
            return true;
        }
        int xEdge = matrix[0].length;
        int yEdge = matrix.length;
        for (int i = 0; i < xEdge + yEdge - 1; i++) {
            int x = i < xEdge ? i : 0;
            int y = i < xEdge ? 0 : i - xEdge;
            int cur = matrix[y++][x++];
            while (y < yEdge && x < xEdge){
                if(matrix[y++][x++] != cur){
                    return false;
                }
            }
        }
        return true;
    }
}
