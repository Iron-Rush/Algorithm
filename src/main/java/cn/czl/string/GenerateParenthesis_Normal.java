package cn.czl.string;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/9/30 10:25
 * @description:
 *      22. 括号生成
 *          数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *      示例：
 *          输入：n = 3
 *          输出：[
 *                 "((()))",
 *                 "(()())",
 *                 "(())()",
 *                 "()(())",
 *                 "()()()"
 *               ]
 */
public class GenerateParenthesis_Normal {

    private static int N = 3;

    @Test
    public void TestSolution(){
        List<String> strList = generateParenthesis(N);
        for (String tempStr : strList) {
            System.out.println(tempStr);
        }
    }
    /**
     * dfs,深度优先搜索
     * 执行用时：1 ms, 在所有 Java 提交中击败了96.84%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了92.39%的用户
     * */
    List<String> resList = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        generate(n, 0, 0, new char[n*2]);
        return resList;
    }
    private void generate(int n, int l, int r, char[] temp){
        if (l == n && r == n){
            StringBuilder sb = new StringBuilder();
            for (char tempChar : temp) {
                sb.append(tempChar);
            }
            resList.add(sb.toString());
        }else{
            if(l < n){
                temp[l+r] = '(';
                generate(n, l+1, r, temp);
            }
            if (l > r){
                temp[l+r] = ')';
                generate(n, l, r+1, temp);
            }
        }
    }
    /**
     * 动态规划：
     *      dp[i]表示i组括号的所有有效组合
     *      dp[i] = "(dp[p]的所有有效组合)+【dp[q]的组合】"，
     *          其中 1 + p + q = i , p从0遍历到i-1, q则相应从i-1到0
     * 执行用时：11 ms, 在所有 Java 提交中击败了7.89%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了57.19%的用户
     * */
    public List<String> generateParenthesis2(int n) {
        List<List<String>> res = new LinkedList<>();
        res.add(new LinkedList<>(Arrays.asList("")));   // 0组括号时记为None
        res.add(new LinkedList<>(Arrays.asList("()"))); // 1组括号只有一种情况
        for (int i = 2; i <= n; i++) {                  // 开始计算i组括号时的括号组合
            List<String> tmp = new LinkedList<>();
            for (int j = 0; j < i; j++) {               // 开始遍历 p q ，其中p+q=i-1 , j 作为索引
                List<String> str1 = res.get(j);         // p = j 时的括号组合情况
                List<String> str2 = res.get(i - 1 - j); // q = (i-1) - j 时的括号组合情况
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        String str = "(" + s1 + ")" + s2;
                        tmp.add(str);                   // 把所有可能的情况添加到 l 中
                    }
                }
            }
            res.add(tmp);   // l这个list就是i组括号的所有情况，添加到total_l中，继续求解i=i+1的情况
        }
        return res.get(n);
    }
}
