package cn.czl.list.search.contains;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author RedRush
 * @date 2020/11/19 16:27
 * @description:
 *      36. 有效的数独
 *      1.数字 1-9 在每一行只能出现一次。
 *      2.数字 1-9 在每一列只能出现一次。
 *      3.数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *      数独部分空格内已填入了数字，空白格用 '.' 表示。
 */
public class IsValidSudoku_Normal {

    @Test
    public void TestSolution(){
        char[][] board = new char[][]{{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        System.out.println(checkSquare(board));
        System.out.println(checkX(board));
        System.out.println(checkY(board));
    }

    /**
     * 三个方法，分配判断横轴、纵轴、3*3方格
     * 执行用时：3 ms, 在所有 Java 提交中击败了53.33%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了62.81%的用户
     * */
    public boolean isValidSudoku(char[][] board) {
        return checkX(board) && checkY(board) && checkSquare(board);
    }
    boolean checkX(char[][] board){
        for (char[] x : board) {
            HashSet<Character> set = new HashSet<>();
            for (char ch : x) {
                if (ch != '.' && !set.add(ch)){
                    return false;
                }
            }
        }
        return true;
    }
    boolean checkY(char[][] board){
        for (int y = 0; y < 9; y++) {
            HashSet<Character> set = new HashSet<>();
            for (int x = 0; x < 9; x++) {
                char ch = board[x][y];
                if (ch != '.' && !set.add(ch)){
                    return false;
                }
            }
        }
        return true;
    }
    boolean checkSquare(char[][] board){
        for (int x = 0; x < 9; x++) {
            HashSet<Character> set = new HashSet<>();
            for (int y = 0; y < 9; y++) {
                int xPos = (x%3)*3 + y%3;
                int yPos = (x/3) * 3 + y/3;
                char ch = board[xPos][yPos];
                if (ch != '.' && !set.add(ch)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 整合三个方法
     * 执行用时：3 ms, 在所有 Java 提交中击败了53.33%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了87.00%的用户
     * */
    public boolean isValidSudoku2(char[][] board) {
        for (int x = 0; x < 9; x++) {
            HashSet<Character> setSquare = new HashSet<>();
            HashSet<Character> setX = new HashSet<>();
            HashSet<Character> setY = new HashSet<>();
            for (int y = 0; y < 9; y++) {
                int xPos = (x%3)*3 + y%3;
                int yPos = (x/3) * 3 + y/3;
                char ch = board[xPos][yPos];
                char chX = board[y][x];
                char chY = board[x][y];
                if (ch != '.' && !setSquare.add(ch)){
                    return false;
                }
                if (chX != '.' && !setX.add(chX)){
                    return false;
                }
                if (chY != '.' && !setY.add(chY)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * HashMap数组，记录每个验算单位中数字出现次数
     * 执行用时：3 ms, 在所有 Java 提交中击败了53.49%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了68.10%的用户
     * */
    public boolean isValidSudoku3(char[][] board) {
        HashMap<Integer, Integer>[] row = new HashMap[9];
        HashMap<Integer, Integer>[] col = new HashMap[9];
        HashMap<Integer, Integer>[] box = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            row[i] = new HashMap<Integer, Integer>();
            col[i] = new HashMap<Integer, Integer>();
            box[i] = new HashMap<Integer, Integer>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.'){
                    int n = (int)num;
                    int boxIndex = (i/3)*3 + j/3;
                    row[i].put(n, row[i].getOrDefault(n, 0) + 1);
                    col[j].put(n, col[j].getOrDefault(n, 0) + 1);
                    box[boxIndex].put(n, box[boxIndex].getOrDefault(n, 0) + 1);
                    if (row[i].get(n) > 1 || col[j].get(n) > 1 || box[boxIndex].get(n) > 1){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 通过int数组记录每个验算单位中数字的出现次数
     * 执行用时：2 , 在所有 Java 提交中击败了95.42%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了88.17%的用户
     * */
    public boolean isValidSudoku4(char[][] board) {
        int[][] row = new int[9][9];
        int[][] col = new int[9][9];
        int[][] box = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.'){
                    int num = board[i][j] - '1';
                    int boxIndex = (i/3) * 3 + j/3;
                    if (row[i][num] != 0 || col[j][num] != 0 || box[boxIndex][num] != 0){
                        return false;
                    }
                    row[i][num] = 1;
                    col[j][num] = 1;
                    box[boxIndex][num] = 1;
                }
            }
        }
        return true;
    }

}
