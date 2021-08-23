package cn.czl.string.operate;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author RedRush
 * @date 2021/8/19 9:46
 * @description:
 *      345. 反转字符串中的元音字母
 *      - 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *      示例 1：
 *          输入："hello"
 *          输出："holle"
 *      示例 2：
 *          输入："leetcode"
 *          输出："leotcede"
 *      提示： 元音字母不包含字母 "y" 。
 */
public class ReverseVowelsOfString_Easy {

    private String S1 = "hello";
    private String S2 = "leetcode";

    @Test
    public void TestSolution(){
        System.out.println(reverseVowels(S1));
        System.out.println(reverseVowels(S2));

    }



    /**
     * 双指针 - 从两侧搜索元音字母，并调转顺序(HashSet判断元音)
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 83.83% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 33.80% 的用户
     * */
    public String reverseVowels(String s) {
        char[] chs = s.toCharArray();
        Set<Character> set = new HashSet<Character>();
        set.add('a');
        set.add('A');
        set.add('e');
        set.add('E');
        set.add('i');
        set.add('I');
        set.add('o');
        set.add('O');
        set.add('u');
        set.add('U');
        int len = s.length();
        int l = 0, r = len - 1;
        while (l < r){
            while (l < len && !set.contains(chs[l])){
                l++;
            }
            while (r >= 0 && !set.contains(chs[r])){
                r--;
            }
            if (l < r){
                swapChar(chs, l, r);
                l++;
                r--;
            }
        }
        return new String(chs);
    }

    /**
     * 双指针2 - 修改元音字符判断方式，改为从元音中搜索当前字符
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 83.83% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 61.46% 的用户
     * */
    public String reverseVowels2(String s) {
        char[] chs = s.toCharArray();
        int len = s.length();
        int l = 0, r = len - 1;
        while (l < r){
            while (l < len && !isVowel(chs[l])){
                l++;
            }
            while (r >= 0 && !isVowel(chs[r])){
                r--;
            }
            if (l < r){
                swapChar(chs, l, r);
                l++;
                r--;
            }
        }
        return new String(chs);
    }


    /**
     * 双指针3 - 通过boolean[]作为元音字符判断依据
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 41.54% 的用户
     * */
    static boolean[] hash = new boolean[128];
    static char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
    static {
        for(char c : vowels){
            hash[c - 'a'] = hash['A' - 'a' + c - ' '] = true;
        }
    }
    public String reverseVowels3(String s) {
        char[] chs = s.toCharArray();
        int len = s.length();
        int l = 0, r = len - 1;
        while (l < r){
            while (l < len && !hash[chs[l] - 'a']){
                l++;
            }
            while (r >= 0 && !hash[chs[r] - 'a']){
                r--;
            }
            if(l < r){
                swapChar(chs, l , r);
                l++;
                r--;
            }
        }
        return new String(chs);
    }

    // 判断字符 ch 是否为元音
    private boolean isVowel(char ch){
        return "aeiouAEIOU".indexOf(ch) >= 0;   // 如果字符串中不包含当前字符则返回 -1
    }

    // 根据下标，交换字符数组中的两个字符
    private void swapChar(char[] chs, int i, int j){
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
    }
}
