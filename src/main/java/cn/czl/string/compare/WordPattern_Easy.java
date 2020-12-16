package cn.czl.string.compare;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author RedRush
 * @date 2020/12/16 9:09
 * @description:
 *      290. 单词规律
 *      - 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *      - 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *      示例1:
 *          输入: pattern = "abba", str = "dog cat cat dog"
 *          输出: true
 *      示例 2:
 *          输入:pattern = "abba", str = "dog cat cat fish"
 *          输出: false
 *      示例 3:
 *          输入: pattern = "aaaa", str = "dog cat cat dog"
 *          输出: false
 *      示例 4:
 *          输入: pattern = "abba", str = "dog dog dog dog"
 *          输出: false
 *      说明:
 *          你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
 */
public class WordPattern_Easy {

    private String PATTERN1 = "abba";
    private String STR1 = "dog cat cat dog";
    private String PATTERN2 = "abba";
    private String STR2 = "dog cat cat fish";
    private String PATTERN3 = "aaaa";
    private String STR3 = "dog cat cat dog";
    private String PATTERN4 = "abba";
    private String STR4 = "dog dog dog dog";
    private String PATTERN5 = "aba";
    private String STR5 = "dog cat cat";

    @Test
    public void TestSolution(){
        System.out.println(wordPattern3(PATTERN1, STR1) == true);
        System.out.println(wordPattern3(PATTERN2, STR2) == false);
        System.out.println(wordPattern3(PATTERN3, STR3) == false);
        System.out.println(wordPattern3(PATTERN4, STR4) == false);
        System.out.println(wordPattern3(PATTERN5, STR5) == false);
    }

    /**
     * 记忆数组用于去重、比对pattern。数组下标即为当前pattern
     * 数组对应值为之前已有pattern的index，若为-1，则说明之前无相同pattern。
     * HashSet用于记忆pattern对应的str是否唯一。规避多个pattern对应相同str的情况
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.94% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 65.66% 的用户
     * */
    public boolean wordPattern(String pattern, String s) {
        String[] strs = s.split(" ");
        if(strs.length != pattern.length()){
            return false;
        }
        int[] memory = new int[26];
        Arrays.fill(memory, -1);
        HashSet<String> dict = new HashSet<>();
        for (int i = 0; i < strs.length; i++) {
            int flag = pattern.charAt(i) - 'a';
            if(memory[flag] == -1){
                if(dict.contains(strs[i])){
                    return false;
                }
                memory[flag] = i;
                dict.add(strs[i]);
            }else {
                if(!strs[memory[flag]].equals(strs[i])){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 双HashMap，一个记录pattern - str，另一个记录str - pattern
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.94% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 73.14% 的用户
     * */
    public boolean wordPattern2(String pattern, String s) {
        Map<String, Character> str2ch = new HashMap<>();
        Map<Character, String> ch2str = new HashMap<>();
        int strLen = s.length();
        int strPos = 0, patPos = 0;
        for (; patPos < pattern.length(); patPos++) {
            char ch = pattern.charAt(patPos);
            if(strPos >= strLen){
                return false;
            }
            int curStrPos = strPos;
            while (curStrPos < strLen && s.charAt(curStrPos) != ' '){
                curStrPos++;
            }
            String curStr = s.substring(strPos, curStrPos);
            if(str2ch.containsKey(curStr) && str2ch.get(curStr) != ch){
                return false;
            }
            if(ch2str.containsKey(ch) && !curStr.equals(ch2str.get(ch))){
                return false;
            }
            str2ch.put(curStr, ch);
            ch2str.put(ch, curStr);
            strPos = curStrPos + 1;
        }
        return strPos >= strLen;
    }

    /**
     * 双HashMap，同步比对字符串及pattern
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.94% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 70.41% 的用户
     * */
    public boolean wordPattern3(String pattern, String s) {
        String[] strs = s.split(" ");
        if(strs.length != pattern.length()){
            return false;
        }
        Map<String, Character> str2ch = new HashMap<>();
        Map<Character, String> ch2str = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char pat = pattern.charAt(i);
            String curStr = strs[i];
            if(str2ch.containsKey(curStr) && str2ch.get(curStr) != pat){
                return false;
            }
            if(ch2str.containsKey(pat) && !curStr.equals(ch2str.get(pat))){
                return false;
            }
            str2ch.put(curStr, pat);
            ch2str.put(pat, curStr);
        }
        return true;
    }

}
