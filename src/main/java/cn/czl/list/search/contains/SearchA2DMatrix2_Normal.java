package cn.czl.list.search.contains;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/10/26 11:27
 * @description:
 *      240. 搜索二维矩阵 II
 *      - 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *          - 每行的元素从左到右升序排列。
 *          - 每列的元素从上到下升序排列。
 *      示例 1：
 *          输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]],
 *              target = 5
 *          输出：true
 *      示例 2：
 *          输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]],
 *              target = 20
 *          输出：false
 *      提示：
 *          - m == matrix.length
 *          - n == matrix[i].length
 *          - 1 <= n, m <= 300
 *          - -10^9 <= matrix[i][j] <= 10^9
 *          - 每行的所有元素从左到右升序排列
 *          - 每列的所有元素从上到下升序排列
 *          - -10^9 <= target <= 10^9
 */
public class SearchA2DMatrix2_Normal {

    private int[][] MATRIX1 = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
    private int TARGET1 = 5;
    private int[][] MATRIX2 = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
    private int TARGET2 = 20;

    @Test
    public void TestSolution() {
        System.out.println(searchMatrix3(MATRIX1, TARGET1));
        System.out.println(searchMatrix3(MATRIX2, TARGET2));
    }

    /**
     * 二维矩阵 从右上角看成二叉搜索树 进行数据检索
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 96.38% 的用户
     * 内存消耗： 43.9 MB , 在所有 Java 提交中击败了 48.24% 的用户
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int x = matrix[0].length - 1;
        int y = 0;
        int yRange = matrix.length;
        while (y < yRange && x >= 0) {
            if (matrix[y][x] > target) {
                x--;
            } else if (matrix[y][x] < target) {
                y++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 全部遍历
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 12.64% 的用户
     * 内存消耗： 43.1 MB , 在所有 Java 提交中击败了 98.40% 的用户
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        for (int[] arr : matrix) {
            for (int num : arr) {
                if (num == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 逐行 - 二分搜索
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 33.33% 的用户
     * 内存消耗： 44 MB , 在所有 Java 提交中击败了 37.16% 的用户
     */
    public boolean searchMatrix3(int[][] matrix, int target) {
        for(int[] arr : matrix){
            if (binarySearch(arr, target) != -1){
                return true;
            }
        }
        return false;
    }
    // 二分搜索
    private int binarySearch(int[] arr, int target){
        int low = 0, high = arr.length - 1;
        while (low <= high){
            int mid = (low + high) >>> 1;
            if (arr[mid] > target){
                high = mid - 1;
            }else if (arr[mid] < target){
                low = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

}