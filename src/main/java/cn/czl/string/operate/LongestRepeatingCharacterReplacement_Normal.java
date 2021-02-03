package cn.czl.string.operate;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/2/2 9:45
 * @description:
 *      424. 替换后的最长重复字符
 *      - 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 *      - 注意：字符串长度 和 k 不会超过 10^4。
 *      示例 1：
 *          输入：s = "ABAB", k = 2
 *          输出：4
 *          解释：用两个'A'替换为两个'B',反之亦然。
 *      示例 2：
 *          输入：s = "AABABBA", k = 1
 *          输出：4
 *          解释：将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 *               子串 "BBBB" 有最长重复字母, 答案为 4。
 */
public class LongestRepeatingCharacterReplacement_Normal {

    private String S1 = "ABAB";
    private String S2 = "AABABBA";

    @Test
    public void TestSolution(){
        System.out.println(characterReplacement(S1, 2));
        System.out.println(characterReplacement(S2, 1));
    }

    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 89.72% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 38.45% 的用户
     * */
    public int characterReplacement(String s, int k) {
        int l = 0, r= 0, maxLen = 0;
        int[] counter = new int[26];
        char[] chs = s.toCharArray();
        while (r < chs.length){
            counter[chs[r] - 'A']++;
            maxLen = Math.max(maxLen, counter[r] - 'A');
            if((r - l + 1 - maxLen) > k){
                counter[chs[l] - 'A']--;
                l++;
            }
            r++;
        }
        return r - l;   // r-l即为当前滑动窗口最大值，即为最长重复字符串
    }
}
