package cn.czl.list.generate;

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
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>(rowIndex);
        for (int i = 0; i < rowIndex; i++) {

        }
        list.set(0, 1);
        return list;
    }

    public List<Integer> getRow2(int rowIndex){
        List<List<Integer>> res = new ArrayList<>();
        if (rowIndex < 1){
            return new ArrayList<>();
        }
        for (int i = 0; i < rowIndex; i++) {
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
        return res.get(res.size());
    }
}
