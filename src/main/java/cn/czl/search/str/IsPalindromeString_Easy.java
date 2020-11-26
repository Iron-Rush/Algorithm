package cn.czl.search.str;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/11/26 17:32
 * @description:
 *      125. 验证回文串
 *      - 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *      - 说明：本题中，我们将空字符串定义为有效的回文串。
 *      示例 1:
 *          输入: "A man, a plan, a canal: Panama"
 *          输出: true
 *      示例 2:
 *          输入: "race a car"
 *          输出: false
 */
public class IsPalindromeString_Easy {

    private String S = "A man, a plan, a canal: Panama";
    private String S2 = "0P";

    @Test
    public void TestSolution(){
        System.out.println(isPalindrome(S2));
        System.out.println('a' - 'A');
    }

    /**
     * 双指针，左右同时获取第一个有效元素的对应数字
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.90%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了67.45%的用户
     * */
    public boolean isPalindrome(String s) {
        int lpos = 0, rpos = s.length()-1;
        while (lpos < rpos){
            int l = include(s.charAt(lpos));
            int r = include(s.charAt(rpos));
            while (l == -1 && lpos < rpos){
                l = include(s.charAt(++lpos));
            }
            while (r == -1 && lpos < rpos){
                r = include(s.charAt(--rpos));
            }
            if (l != r){
                return false;
            }
            lpos++;
            rpos--;
        }
        return true;
    }

     int include(char ch){  // 可直接将小写字母-32，变为大写字母
        if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')){
            return ch <= 'Z' ? ch - 'A' : ch - 'a';
        }
        if(ch >= '0' && ch <= '9'){
            return ch+100;
        }
        return -1;
    }

}
