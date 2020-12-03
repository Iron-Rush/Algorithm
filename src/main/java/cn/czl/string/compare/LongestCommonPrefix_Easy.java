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
        System.out.println(longestCommonPrefix(STRS2));
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
}
