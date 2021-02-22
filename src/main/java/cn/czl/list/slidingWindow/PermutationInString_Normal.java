package cn.czl.list.slidingWindow;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/2/10 9:34
 * @description:
 *      567. 字符串的排列
 *      - 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *      - 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *      示例1:
 *          输入: s1 = "ab" s2 = "eidbaooo"
 *          输出: True
 *          解释: s2 包含 s1 的排列之一 ("ba").
 *      示例2:
 *          输入: s1= "ab" s2 = "eidboaoo"
 *          输出: False
 *      注意：
 *          输入的字符串只包含小写字母
 *          两个字符串的长度都在 [1, 10,000] 之间
 *      *** 可解读为：s2中是否存在长度为s1，且存在字符与s1相同的子数组
 */
public class PermutationInString_Normal {

    private String S1a = "ab";
    private String S1b = "eidbaooo";
    private String S2a = "ab";
    private String S2b = "eidboaoo";
    @Test
    public void TestSolution(){
        System.out.println(checkInclusion(S1a, S1b));
    }

    /**
     * 滑动窗口
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 97.54% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 78.65% 的用户
     * */
    public boolean checkInclusion(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int[] counter = new int[26];
        for (char ch : chars1) {
            counter[ch - 'a']++;
        }
        int l = 0, r = 0, len = s1.length();
        for(; r < s2.length(); r++){
            counter[chars2[r] - 'a']--;
            if(r - l + 1 == len){
                boolean flag = true;
                for (int ch : counter) {
                    if (ch != 0){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    return true;
                }
                counter[chars2[l++] - 'a']++;
            }
        }
        return false;
    }

    /**
     * 滑动窗口2
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 97.54% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 20.68% 的用户
     * */
    public boolean checkInclusion2(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int[] counter = new int[26];
        int[] target = new int[26];
        int len1 = s1.length(), len2 = s2.length();
        if(len1 > len2){
            return false;
        }
        for (int i = 0; i < len1; i++) {
            target[chars1[i] - 'a']++;
            counter[chars2[i] - 'a']++;
        }
        if(Arrays.equals(counter, target)){
            return true;
        }
        for (int i = len1; i < len2; i++) {
            counter[chars2[i] - 'a']++;
            counter[chars2[i - len1] - 'a']--;
            if(Arrays.equals(counter, target)){
                return true;
            }
        }
        return false;
    }
}
