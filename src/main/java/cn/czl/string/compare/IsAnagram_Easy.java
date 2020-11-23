package cn.czl.string.compare;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/11/23 9:38
 * @description:
 *      242. 有效的字母异位词
 *      - 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *      示例 1:
 *          输入: s = "anagram", t = "nagaram"     输出: true
 *      示例 2:
 *          输入: s = "rat", t = "car"             输出: false
 */
public class IsAnagram_Easy {

    private String S1 = "anagram";
    private String T1 = "nagaram";
    private String S2 = "ab";
    private String T2 = "a";

    @Test
    public void TestSolution(){
        System.out.println(isAnagram2(S2, T2));
    }

    /**
     * 转换为字符数组，排序后，比较数组。
     * 若数组相等，则两单词元素相同
     * 执行用时：3 ms, 在所有 Java 提交中击败了85.83%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了95.74%的用户
     * */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }

    /**
     * 通过数组统计每个字母出现次数
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.91%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了97.29%的用户
     * */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
        int[] count = new int[26];
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for (char ch : sChars) {
            count[ch-'a'] += 1;
        }
        for (char ch : tChars) {
            count[ch-'a'] -= 1;
            if (count[ch-'a'] < 0){
                return false;
            }
        }
        return true;
    }

}
