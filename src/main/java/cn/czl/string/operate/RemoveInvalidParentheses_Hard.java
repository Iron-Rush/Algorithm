package cn.czl.string.operate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author RedRush
 * @date 2021/10/27 9:27
 * @description:
 *      301. 删除无效的括号
 *      - 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 *      - 返回所有可能的结果。答案可以按 任意顺序 返回。
 *      示例 1：
 *          输入：s = "()())()"
 *          输出：["(())()","()()()"]
 *      示例 2：
 *          输入：s = "(a)())()"
 *          输出：["(a())()","(a)()()"]
 *      示例 3：
 *          输入：s = ")("
 *          输出：[""]
 *      提示：
 *          1 <= s.length <= 25
 *          s 由小写英文字母以及括号 '(' 和 ')' 组成
 *          s 中至多含 20 个括号
 */
public class RemoveInvalidParentheses_Hard {

    /**
     * dfs - 深度优先搜索[暴力搜索 - 未剪枝]
     * 执行用时： 76 ms , 在所有 Java 提交中击败了 62.69% 的用户
     * 内存消耗： 39.1 MB , 在所有 Java 提交中击败了 25.22% 的用户
     */
    private int max, ansMaxLen, strLen;
    private char[] chs;
    private Set<String> set = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        chs = s.toCharArray();
        strLen = s.length();
        List<String> ans = new ArrayList<>();
        // 统计左右括号的数量
        int lCount = 0, rCount = 0;
        for (char ch : chs) {
            if (ch == '(') {
                lCount++;
            } else if (ch == ')') {
                rCount++;
            }
        }
        max = Math.min(lCount, rCount);
        dfs(0, "", 0);
        ans.addAll(set);
        return ans;
    }

    // 深度优先搜索
    private void dfs(int idx, String curStr, int point) {
        if (point < 0 || point > max) return;
        if (idx == strLen) {
            if (point == 0 && curStr.length() >= ansMaxLen) {
                if (curStr.length() > ansMaxLen) {
                    set.clear();
                    ansMaxLen = curStr.length();
                }
                set.add(curStr);
            }
            return;
        }
        char curCh = chs[idx];
        if (curCh == '(') {
            dfs(idx + 1, curStr + String.valueOf(curCh), point + 1);
            dfs(idx + 1, curStr, point);
        } else if (curCh == ')') {
            dfs(idx + 1, curStr + String.valueOf(curCh), point - 1);
            dfs(idx + 1, curStr, point);
        } else {
            dfs(idx + 1, curStr + String.valueOf(curCh), point);
        }
    }

    /**
     * dfs + 剪枝
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 87.75% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 43.81% 的用户
     */
    public List<String> removeInvalidParentheses2(String s) {
        chs = s.toCharArray();
        strLen = s.length();
        // 根据无法匹配的括号数，计算出答案字符串长度
        int lRest = 0, rRest = 0;
        for(char ch : chs){
            if (ch == '('){     // 统计左括号数量
                lRest++;
            }else if (ch == ')'){
                if (lRest > 0){     // 如果存在多余的左括号，则用左括号抵消当前右括号
                    lRest--;
                }else {
                    rRest++;        // 否则当前右括号多余
                }
            }
        }
        // 答案字符串最大长度 = 原始字符串长度 - [多余(] - [多余)]
        ansMaxLen = strLen - lRest - rRest;
        // 分别统计 ( 和 ) 的个数
        int lCount = 0, rCount = 0;
        for(char ch : chs){
            if (ch == '('){
                lCount++;
            }else if (ch == ')'){
                rCount++;
            }
        }
        max = Math.min(lCount, rCount);
        dfs(0, "", lRest, rRest, 0);
        return new ArrayList<>(set);
    }
    // dfs - 深度优先搜索
    void dfs(int idx, String cur, int lRest, int rRest, int point){
        if(lRest < 0 || rRest < 0 || point < 0 || point > max)  return;
        // 如果需要删除的 ( 和 ) 均删除完毕，且当前字符串长度符合预期。则添加至set去重
        if (lRest == 0 && rRest == 0){
            if (cur.length() == ansMaxLen){
                set.add(cur);
            }
        }
        // 游标遍历完成 return
        if (idx == strLen)  return;
        char curCh = chs[idx];
        if (curCh == '('){
            dfs(idx+1, cur + curCh, lRest, rRest, point+1);
            dfs(idx+1, cur, lRest-1, rRest, point);
        }else if (curCh == ')'){
            dfs(idx+1, cur + curCh, lRest, rRest, point-1);
            dfs(idx+1, cur, lRest, rRest-1, point);
        }else {
            dfs(idx+1, cur + curCh, lRest, rRest, point);
        }
    }
}