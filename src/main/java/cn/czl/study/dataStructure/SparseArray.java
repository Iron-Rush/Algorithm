package cn.czl.study.dataStructure;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/1/8 16:03
 * @description:
 *      稀疏数组：二维数组 转 稀疏数组
 *      原始数组 8 * 8, 0表示无棋子, 1表示黑子, 2表示白子
 *      稀疏数组：行 | 列 | 值
 */
public class SparseArray {

    private int[][] chessArr1;

    @Test
    public void TestSolution(){
        chessArr1 = new int[8][7];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[5][6] = 8;
//        for(int[] temp : chessArr1){
//            System.out.println(Arrays.toString(temp));
//        }
        int[][] serialize = ArrayToSparse(chessArr1);
        System.out.println(Arrays.deepToString(serialize));
        int[][] arr = SparseToArray(serialize);
        System.out.println(Arrays.deepEquals(arr, chessArr1));
    }

    // 将 二维数组 转化为 稀疏数组
    public int[][] ArrayToSparse(int[][] arr){
        int sum = 0;
        int height = arr.length, width = arr[0].length;
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++) {
                if(arr[i][j] != 0){
                    sum++;
                }
            }
        }
        int[][] res = new int[sum+1][3];
        res[0][0] = height;
        res[0][1] = width;
        res[0][2] = sum;
        int index = 1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(arr[i][j] != 0){
                    res[index][0] = i;
                    res[index][1] = j;
                    res[index][2] = arr[i][j];
                    index++;
                }
            }
        }
        return res;
    }

    // 将 稀疏数组 恢复为 二维数组
    public int[][] SparseToArray(int[][] arr){
        int[][] res = new int[arr[0][0]][arr[0][1]];
        int sum = arr[0][2];
        for (int i = 1; i <= sum; i++) {
            int x = arr[i][0];
            int y = arr[i][1];
            res[x][y] = arr[i][2];
        }
        return res;
    }

}
