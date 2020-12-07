package cn.czl.list.generate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/12/6 13:22
 * @description:
 *      118. 杨辉三角
 *      - 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *      - 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *      示例:
 *          输入: 5
 *          输出:
 *          [
 *               [1],
 *              [1,1],
 *             [1,2,1],
 *            [1,3,3,1],
 *           [1,4,6,4,1]
 *          ]
 */
public class GeneratePascalsTriangle_Easy {

    @Test
    public void TestSolution(){
        System.out.println(generate(1));
    }

    /**
     * 根据前一行，生成当前行。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了52.86%的用户
     * */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows < 1){
            return res;
        }
        for (int i = 0; i < numRows; i++) {
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
        return res;
    }
}
