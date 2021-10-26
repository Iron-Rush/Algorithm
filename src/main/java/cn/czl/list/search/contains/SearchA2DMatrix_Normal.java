package cn.czl.list.search.contains;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/3/30 9:54
 * @description:
 *      74. 搜索二维矩阵
 *      - 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *      - 每行中的整数从左到右按升序排列。
 *      - 每行的第一个整数大于前一行的最后一个整数。
 *      示例 1：
 *          输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 *          输出：true
 *      示例 2：
 *          输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 *          输出：false
 *      提示：
 *          m == matrix.length
 *          n == matrix[i].length
 *          1 <= m, n <= 100
 *          -10^4 <= matrix[i][j], target <= 10^4
 */
public class SearchA2DMatrix_Normal {

    private int[][] MATRIX1 = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};


    @Test
    public void TestSolution(){
        System.out.println(searchMatrix(MATRIX1, 24));   // true
        System.out.println(searchMatrix2(MATRIX1, 24));   // true
    }

    /**
     * 先搜索第一列，找到target可能在的行，然后搜索该行
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.7 MB , 在所有 Java 提交中击败了 92.82% 的用户
     * */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix[0][0] > target){
            return false;
        }
        int ySize = matrix.length, xSize = matrix[0].length;
        int y = 0, x = 0;
        while (y < ySize && matrix[y][x] <= target){
            y++;
        }
        y--;
        while (x < xSize && matrix[y][x] <= target){
            if(matrix[y][x] == target){
                return true;
            }
            x++;
        }
        return false;
    }


    /**
     * 两次二分查找
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.8 MB , 在所有 Java 提交中击败了 86.56% 的用户
     * */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix[0][0] > target){
            return false;
        }
        int yLow = 0, yHigh = matrix.length - 1;
        int yRange = matrix.length;
        while(yLow <=  yHigh){
            int mid = (yLow + yHigh) >>> 1;
            if (matrix[mid][0] > target){
                yHigh = mid-1;
            }else {
                if (mid == yRange-1 || matrix[mid + 1][0] > target){
                    yLow = mid;
                    break;
                }else {
                    yLow = mid+1;
                }
            }
        }
//        int yLow = -1, yHigh = matrix.length - 1;
//        while (yLow < yHigh){   // 二分搜索，获取数据所在行
//            int mid = (yHigh - yLow + 1) / 2 + yLow;
//            if(matrix[mid][0] <= target){
//                yLow = mid;
//            }else {
//                yHigh = mid - 1;
//            }
//        }
        int rowIndex = yLow;
        if(rowIndex < 0){
            return false;
        }
        int[] row = matrix[rowIndex];
        int xLow = 0, xHigh = row.length - 1;
        while (xLow <= xHigh){  // 二分搜索，获取数据所在列
            int mid = (xHigh - xLow) / 2 + xLow;
            if(row[mid] == target){
                return true;
            }else if(row[mid] < target){
                xLow = mid + 1;
            }else {
                xHigh = mid - 1;
            }
        }
        return false;
    }

    /**
     * 一次二分查找
     * 二维数组想象，展开为一维数组，直接进行二分搜索
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.8 MB , 在所有 Java 提交中击败了 74.18% 的用户
     * */
    public boolean searchMatrix3(int[][] matrix, int target) {
        if (matrix[0][0] > target) {
            return false;
        }
        int ySize = matrix.length, xSize = matrix[0].length;
        int low = 0, high = xSize * ySize - 1;
        while (low <= high){
            int mid = (high - low)/2 + low;
            int num = matrix[mid / xSize][mid % xSize];
            if(num > target){
                high = mid - 1;     // 因为mid不符合条件，可以直接跳过
            }else if(num < target){
                low = mid + 1;
            }else {
                return true;
            }
        }
        return false;
    }


}
