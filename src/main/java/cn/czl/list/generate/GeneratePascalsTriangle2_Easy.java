package cn.czl.list.generate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2021/2/20 14:24
 * @description:
 *      119. 杨辉三角 II
 *      - 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *      - 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *      示例:
 *          输入: 3
 *          输出: [1,3,3,1]
 *      进阶：你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class GeneratePascalsTriangle2_Easy {

    @Test
    public void TestSolution(){
        System.out.println(getRow(5));
        System.out.println(getRow2(5));
    }

    /**
     * 在一维数组原地累加计算，逐行计算生成当前行的杨辉三角
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.3 MB , 在所有 Java 提交中击败了 40.79% 的用户
     * */
    public List<Integer> getRow(int rowIndex) {
        rowIndex++;
        int[] res = new int[rowIndex];
        List<Integer> list = new ArrayList<Integer>(rowIndex);
        for (int i = 0; i < rowIndex; i++) {
            for(int j = i; j >= 0; j--){
                if(j == 0 || j == i){
                    res[j] = 1;
                }else {
                    res[j] = res[j] + res[j - 1];
                }
            }
        }
        for (int num : res) {
            list.add(num);
        }
        return list;
    }

    public List<Integer> getRow2(int rowIndex){
        List<Integer> res = new ArrayList<>();

        return res;
    }

    /**
     * 先生成杨辉三角[二维数组]
     * 在获取-返回最后一行数组
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 30.36% 的用户
     * 内存消耗： 36.7 MB , 在所有 Java 提交中击败了 5.13% 的用户
     * */
    public List<Integer> getRow3(int rowIndex){
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < rowIndex + 1; i++) {
            List<Integer> tempList = new ArrayList<>();
            tempList.add(1);
            if (i != 0){
                List<Integer> preList = res.get(i - 1);
                for (int j = 1; j < preList.size(); j++) {
                    tempList.add(preList.get( j - 1)  + preList.get(j));
                }
                tempList.add(1);
            }
            res.add(tempList);
        }
        return res.get(rowIndex);
    }

    /**
     * 线性递推-根据公式
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.4 MB , 在所有 Java 提交中击败了 19.45% 的用户
     * */
    public List<Integer> getRow4(int rowIndex){
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            row.add((int)((long)row.get(i-1) * (rowIndex - i + 1)/i));
        }
        return row;
    }

}
