package cn.czl.string.compare;

import org.junit.jupiter.api.Test;

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

    @Test
    public void TestSolution(){
        System.out.println(longestCommonPrefix(STRS1));
    }

    public String longestCommonPrefix(String[] strs) {
        HashSet<String> set = new HashSet<>();

        String res = "";
        for (String temp : set) {
            if (temp.length() > res.length()){
                res = temp;
            }
        }
        return res;
    }
}
