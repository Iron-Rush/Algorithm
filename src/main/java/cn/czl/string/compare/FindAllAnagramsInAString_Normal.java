package cn.czl.string.compare;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2021/3/26 10:34
 * @description:
 *      438. 找到字符串中所有字母异位词
 *      - 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *      - 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *      说明：
 *          字母异位词指字母相同，但排列不同的字符串。
 *          不考虑答案输出的顺序。
 *      示例 1:
 *          输入: s: "cbaebabacd" p: "abc"
 *          输出: [0, 6]
 *          解释: 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 *                起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *      示例 2:
 *          输入: s: "abab" p: "ab"
 *          输出: [0, 1, 2]
 *          解释: 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 *                起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 *                起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 */
public class FindAllAnagramsInAString_Normal {

    private String S1 = "cbaebabacd";
    private String P1 = "abc";
    private String S2 = "abab";
    private String P2 = "ab";

    @Test
    public void TestSolution(){
        System.out.println(findAnagrams(S1, P1));
        System.out.println(findAnagrams(S2, P2));
    }

    /**
     * 两个int[]分别记录两个表中的字符数量，
     * 滑动窗口，判断当前位置是否存在异位词
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 82.29% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 83.64% 的用户
     * */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        if(sLen < pLen){
            return res;
        }
        char[] chs = s.toCharArray();
        char[] pchs = p.toCharArray();
        int[] target = new int[26];
        int[] counter = new int[26];
        for (char ch : pchs) {
            target[ch - 'a']++;
        }
        for (int end = 0; end < sLen; end++) {
            char curCh = chs[end];
            if(end >= pLen){
                int start = end - pLen;
                if(Arrays.equals(target, counter)){
                    res.add(start);
                }
                counter[chs[start] - 'a']--;
            }
            counter[curCh - 'a']++;
        }
        if(Arrays.equals(target, counter)){
            res.add(sLen - pLen);
        }
        return res;
    }
}
