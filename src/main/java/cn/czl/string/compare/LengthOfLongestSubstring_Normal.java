package cn.czl.string.compare;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RedRush
 * @date 2021/3/26 16:23
 * @description:
 *      3. 无重复字符的最长子串
 *      - 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *      示例 1:
 *          输入: s = "abcabcbb"
 *          输出: 3
 *          解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *      示例 2:
 *          输入: s = "bbbbb"
 *          输出: 1
 *          解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *      示例 3:
 *          输入: s = "pwwkew"
 *          输出: 3
 *          解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *               请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *      示例 4:
 *          输入: s = ""
 *          输出: 0
 *      提示：
 *          0 <= s.length <= 5 * 10^4
 *          s 由英文字母、数字、符号和空格组成
 */
public class LengthOfLongestSubstring_Normal {

    /**
     * 数组记录该字符前一个所在位置
     * 通过比较前一个起始点、与当前字符 最近位置得出当前的最长子串
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 94.61% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 90.71% 的用户
     * */
    public int lengthOfLongestSubstring(String s) {
        int max = 0, len = s.length();
        char[] chs = s.toCharArray();
        int[] lastIdx = new int[128];
        Arrays.fill(lastIdx, -1);
        int start = 0;
        for (int i = 0; i < len; i++) {
            int ch = chs[i];
            start = Math.max(start, lastIdx[ch] + 1);
            max = Math.max(max, i - start + 1);
            lastIdx[ch] = i;
        }
        return max;
    }

    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 85.84% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 52.49% 的用户
     * */
    public int lengthOfLongestSubstring2(String s) {
        int max = 0, len = s.length();
        char[] chs = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        for (int i = 0; i < len; i++) {
            char ch = chs[i];
            start = Math.max(start, map.getOrDefault(ch, -1) + 1);
//            if(map.containsKey(ch)){
//                start = Math.max(start, map.get(ch) + 1);
//            }
            max = Math.max(max, i - start + 1);
            map.put(ch, i);
        }
        return max;
    }

}
