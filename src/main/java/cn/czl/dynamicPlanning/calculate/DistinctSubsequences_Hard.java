package cn.czl.dynamicPlanning.calculate;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author RedRush
 * @date 2021/3/17 11:28
 * @description:
 *      115. 不同的子序列
 *      - 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 *      - 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *      - 题目数据保证答案符合 32 位带符号整数范围。
 *      示例 1：
 *          输入：s = "rabbbit", t = "rabbit"
 *          输出：3
 *          解释：
 *              如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 *              (上箭头符号 ^ 表示选取的字母)
 *              rabbbit,    rabbbit,    rabbbit
 *              ^^^^ ^^     ^^ ^^^^     ^^^ ^^^
 *      示例 2：
 *          输入：s = "babgbag", t = "bag"
 *          输出：5
 *          解释：
 *              如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 *              babgbag,    babgbag,    babgbag,    babgbag,    babgbag
 *              ^^ ^        ^^    ^     ^    ^^       ^  ^^         ^^^
 *      提示：
 *          0 <= s.length, t.length <= 1000
 *          s 和 t 由英文字母组成
 */
public class DistinctSubsequences_Hard {

    @Test
    public void TestSolution(){
//        System.out.println(numDistinct("rabbbit", "rabbit"));
        System.out.println(numDistinct("babgbag", "bag"));
    }

    /**
     * dfs-深度优先搜索
     * 直接递归：    超出时间限制
     * 记忆化递归：
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 5.06% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 5.06% 的用户
     * */
    HashMap<String, Integer> map = new HashMap<>();
    public int numDistinct(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
//        return dfs(sChars, tChars, 0, 0, 0);
        return dfs2(sChars, tChars, 0, 0);
    }
    // 深度优先搜索-递归函数
    int dfs(char[] sChars, char[] tChars, int sIdx, int tIdx, int count){
        if(sIdx < sChars.length && tIdx < tChars.length){
            if(sChars[sIdx] == tChars[tIdx]){   // 当前对应位置字符匹配
                if(tIdx == tChars.length - 1){  // 如果匹配完整个字符串则计数+1
                    count++;
                }
                // 如果当前字符匹配，则s指针与t指针同步后移，继续进行递归匹配
                count = dfs(sChars, tChars, sIdx + 1, tIdx + 1, count);
            }
            // 无论当前字符是否匹配，都进行一次只移动s指针的递归
            count = dfs(sChars, tChars, sIdx+1, tIdx, count);
        }
        return count;
    }

    // 记忆化递归(12ms)
    int dfs2(char[] sChars, char[] tChars, int sIdx, int tIdx){
        if(tIdx == tChars.length){  // 完全匹配目标字符串，return 1
            return 1;
        }
        if(sIdx == sChars.length){  // s已扫描完，仍未匹配到，retun 0
            return 0;
        }
        String key = sIdx + "|" + tIdx;
        if(map.containsKey(key)){
            return map.get(key);
        }
        int ans = 0;
        if(sChars[sIdx] == tChars[tIdx]){   // 当前对应位置字符匹配
            // 如果当前字符匹配，则s指针与t指针同步后移，继续进行递归匹配
            ans += dfs2(sChars, tChars, sIdx + 1, tIdx + 1);
        }
        // 无论当前字符是否匹配，都进行一次只移动s指针的递归
        ans += dfs2(sChars, tChars, sIdx+1, tIdx);
        map.put(key, ans);
        return ans;
    }

    /**
     * 动态规划
     * 如果选择匹配，那么 dp[i][j] 其中一部分数量就是 dp[i+1][j+1]
     * 如果选择不匹配,dp[i][j] 另外一部分就是 dp[i+1][j]
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 92.49% 的用户
     * 内存消耗： 36.8 MB , 在所有 Java 提交中击败了 52.57% 的用户
     * */
    public int numDistinct2(String s, String t) {
        int len = s.length(), tLen = t.length();
        if(len < tLen){
            return 0;
        }
        int[][] dp = new int[len + 1][tLen + 1];
        for (int i = 0; i <= len; i++) {
            dp[i][tLen] = 1;
        }
        for (int i = len - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            for (int j = tLen - 1; j >= 0; j--) {
                char tCh = t.charAt(j);
                if(ch == tCh){  // 本行匹配数 + 上一行匹配数
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                }else {     // 获取上一行匹配数
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }
}
