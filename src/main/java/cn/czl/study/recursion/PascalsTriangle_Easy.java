package cn.czl.study.recursion;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2021/3/19 14:33
 * @description:
 *      118. 杨辉三角
 *      - 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *      - 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *      示例:
 *          输入: 5
 *          输出:
 *              [[1],
 *              [1,1],
 *             [1,2,1],
 *            [1,3,3,1],
 *           [1,4,6,4,1]]
 */
public class PascalsTriangle_Easy {

    @Test
    public void TestSolution(){
        System.out.println(generate(1));
    }

    /**
     * 递归法
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 48.75% 的用户
     * 内存消耗： 36.3 MB , 在所有 Java 提交中击败了 64.11% 的用户
     * */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows < 1){
            return res;
        }
        res.add(new ArrayList<Integer>(){{add(1);}});
        helper(res, numRows - 1);
        return res;
    }
    // 递归辅助函数
    void helper(List<List<Integer>> res, int rest){
        if(rest == 0){
            return;
        }
        List<Integer> pre = res.get(res.size() - 1);
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 0; i < pre.size() - 1; i++) {
            cur.add(pre.get(i) + pre.get(i + 1));
        }
        cur.add(1);
        res.add(cur);
        helper(res, rest -1);
    }

    /**
     * 迭代法
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.3 MB , 在所有 Java 提交中击败了 66.95% 的用户
     * */
    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows < 1){
            return res;
        }
        for (int i = 0; i <= numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if(j == 0 || j == i){
                    temp.add(1);
                }else {
                    temp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(temp);
        }
        return res;
    }
}
