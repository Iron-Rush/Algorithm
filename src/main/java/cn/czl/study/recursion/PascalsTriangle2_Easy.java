package cn.czl.study.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2021/3/19 14:33
 * @description:
 *      119. 杨辉三角 II
 *      - 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *      - 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *      示例:
 *          输入: 3
 *          输出: [1,3,3,1]
 *      进阶：你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class PascalsTriangle2_Easy {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if(j == 0 || j == i){
                    temp.add(1);
                }else {
                    temp.add(row.get(j-1) + row.get(j));
                }
            }
            row = temp;
        }
        return row;
    }

    /**
     * 在一个数组上堆砌，推算
     * */
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 0; i <= rowIndex; i++) {
            row.add(0);
            for (int j = i; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }

    public List<Integer> getRow3(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            row.add((int)((long)row.get(i-1) * (rowIndex - i + 1)/i));
        }
        return row;
    }
}
