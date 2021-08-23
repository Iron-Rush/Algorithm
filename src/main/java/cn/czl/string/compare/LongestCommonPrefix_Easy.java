package cn.czl.string.compare;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author RedRush
 * @date 2020/11/27 17:11
 * @description:
 *      14. 最长公共前缀
 *      - 编写一个函数来查找字符串数组中的最长公共前缀。
 *      - 如果不存在公共前缀，返回空字符串 ""。
 *      示例 1:
 *          输入: ["flower","flow","flight"]
 *          输出: "fl"
 *      示例 2:
 *          输入: ["dog","racecar","car"]
 *          输出: ""
 *          解释: 输入不存在公共前缀。
 *      说明: 所有输入只包含小写字母 a-z 。
 */
public class LongestCommonPrefix_Easy {

    private String[] STRS1 = {"flower", "flow", "flight"};
    private String[] STRS2 = {"ab", "a"};

    @Test
    public void TestSolution(){
        System.out.println(longestCommonPrefix4(STRS2));
//        System.out.println(longestCommonPrefix4(STRS1));
    }

    /**
     * 以第零个字符串为基准，控制指针比较字符串。
     * 根据指针截取字符串并返回[使用字符数组配合charAt比较]
     * 执行用时：1 ms, 在所有 Java 提交中击败了88.05%的用户
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了92.59%的用户
     * */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0){
            return "";
        }
        char[] chars = strs[0].toCharArray();
        int pos = strs[0].length()-1;
        for (int i = 1; i < strs.length; i++) {
            String curStr = strs[i];
            pos = Math.min(pos, curStr.length()-1);
            for (int j = 0; j <= pos; j++) {
                if (curStr.charAt(j) != chars[j]){
                    pos = j - 1;
                    break;
                }
            }
        }
        return pos < 0 ? "" : new String(Arrays.copyOfRange(chars, 0, pos+1));
    }
    /**
     * 使用charAt比较字符串
     * */
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0){
            return "";
        }
        String target = strs[0];
        int pos = target.length()-1;
        for (int i = 1; i < strs.length; i++) {
            String curStr = strs[i];
            pos = Math.min(pos, curStr.length()-1);
            for (int j = 0; j <= pos; j++) {
                if (curStr.charAt(j) != target.charAt(j)){
                    pos = j - 1;
                    break;
                }
            }
        }
        return pos < 0 ? "" : target.substring(0, pos + 1);
    }

    /**
     * 横向比较
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 52.04% 的用户
     * */
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0){
                break;
            }
        }
        return prefix;
    }
    // 获取当前两字符串的公共前缀
    private String longestCommonPrefix(String s1, String s2){
        int len = Math.min(s1.length(), s2.length());
        int pos = 0;
        while (pos < len && s1.charAt(pos) == s2.charAt(pos)){
            pos++;
        }
        return s1.substring(0, pos);
    }

    /**
     * 纵向比较 （指针超过当前字符串长度/字符不匹配是则停止）
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 52.04% 的用户
     * */
    public String longestCommonPrefix4(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        int len = strs[0].length();
        int count = strs.length;
        String target = strs[0];
        for (int i = 0; i < len; i++) {
            char ch = target.charAt(i);
            for (int j = 1; j < count; j++) {
                if(i == strs[j].length() || strs[j].charAt(i) != ch){
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
