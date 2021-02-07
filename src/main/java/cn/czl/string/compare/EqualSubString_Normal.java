package cn.czl.string.compare;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/2/5 10:27
 * @description:
 *      1208. 尽可能使字符串相等
 *      - 给你两个长度相同的字符串，s 和 t。
 *      - 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
 *      - 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 *      - 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 *      - 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 *      示例 1：
 *          输入：s = "abcd", t = "bcdf", cost = 3
 *          输出：3
 *          解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
 *      示例 2：
 *          输入：s = "abcd", t = "cdef", cost = 3
 *          输出：1
 *          解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
 *      示例 3：
 *          输入：s = "abcd", t = "acde", cost = 0
 *          输出：1
 *          解释：你无法作出任何改动，所以最大长度为 1。
 *      提示：
 *          1 <= s.length, t.length <= 10^5
 *          0 <= maxCost <= 10^6
 *          s 和 t 都只含小写英文字母。
 */
public class EqualSubString_Normal {

    private String S1 = "abcd";     // 3
    private String T1 = "bcdf";
    private int COST1 = 3;
    private String S2 = "abcd";     // 1
    private String T2 = "cdef";
    private int COST2 = 3;
    private String S3 = "abcd";     // 1
    private String T3 = "acde";
    private int COST3 = 0;
    private String S4 = "anryddgaqpjdw";     // 1
    private String T4 = "zjhotgdlmadcf";
    private int COST4 = 5;

    @Test
    public void TestSolution(){
        System.out.println(equalSubstring2(S1, T1, COST1));
        System.out.println(equalSubstring2(S2, T2, COST2));
        System.out.println(equalSubstring2(S3, T3, COST3));
        System.out.println(equalSubstring2(S4, T4, COST4));
    }

    /**
     * 双指针1
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 98.84% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 26.99% 的用户
     * */
    public int equalSubstring(String s, String t, int maxCost) {
        char[] sChs = s.toCharArray();
        char[] tChs = t.toCharArray();
        int len = s.length();
        int[] counter = new int[len];
        for (int i = 0; i < len; i++) {
            counter[i] = Math.abs(sChs[i] - tChs[i]);
        }
        int res = 0, curSum = 0;
        int l = 0, r = 0;
        while (r < len){
            curSum += counter[r];
            while (curSum > maxCost){
                curSum -= counter[l++];
            }
            res = Math.max(res, r - l + 1);
            r++;
        }
        return res;
    }

    /**
     * 双指针2
     * */
    public int equalSubstring2(String s, String t, int maxCost) {
        int res = 0, l = 0;
        char[] sChs = s.toCharArray();
        char[] tChs = t.toCharArray();
        for (int r = 0; r < s.length(); r++) {
            res += Math.abs(sChs[r] - tChs[r]);
            if(res > maxCost){
                res -= Math.abs(sChs[l] - tChs[l]);
                l++;
            }
        }
        return s.length() - l;
    }
}
