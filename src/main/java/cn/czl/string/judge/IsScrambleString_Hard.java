package cn.czl.string.judge;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author RedRush
 * @date 2021/4/16 16:19
 * @description:
 *      87. 扰乱字符串
 *      - 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 *          - 如果字符串的长度为 1 ，算法停止
 *          - 如果字符串的长度 > 1 ，执行下述步骤：
 *              - 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，
 *                  则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 *              - 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。
 *                  即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 *              - 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 *      - 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 *          如果是，返回 true ；否则，返回 false 。
 *      示例 1：
 *          输入：s1 = "great", s2 = "rgeat"
 *          输出：true
 *      示例 2：
 *          输入：s1 = "abcde", s2 = "caebd"
 *          输出：false
 *      示例 3：
 *          输入：s1 = "a", s2 = "a"
 *          输出：true
 *      提示：
 *          s1.length == s2.length
 *          1 <= s1.length <= 30
 *          s1 和 s2 由小写英文字母组成
 */
public class IsScrambleString_Hard {

    private String S1A = "great";
    private String S2A = "rgeat";
    private String S1B = "abcde";
    private String S2B = "caebd";
    private String S1C = "a";
    private String S2C = "a";

    @Test
    public void TestSolution(){
        System.out.println(isScramble(S1A, S2A));
        System.out.println(isScramble(S1B, S2B));
        System.out.println(isScramble(S1C, S2C));
    }

    @Test
    public void TestMethod(){
        char[] chs = S1A.toCharArray();
        System.out.println(Arrays.toString(chs));
//        reverse(1,2, chs);
//        reverse(1, 1, chs);
//        reverse(1+1, 3, chs);
        int start = 1, end = 4;
        for (int i = start; i < end; i++) {
            char[] temp = chs.clone();
            reverse(start, i, temp);
            reverse(i+1, end, temp);
            System.out.println(Arrays.toString(temp));
        }
        System.out.println(Arrays.toString(chs));
    }

    /**
     * 朴素解法
     * 超出时间限制 - 286/288
     * */
    public boolean isScramble(String s1, String s2) {
        if(s1.equals(s2)){
            return true;
        }
        if(!checkChs(s1, s2)){
            return false;
        }
        int len = s1.length();
        for (int i = 1; i < len; i++) {
            String lStr1 = s1.substring(0, i);  // s1的[0,i)
            String rStr1 = s1.substring(i);     // s1的[i, len)
            String lStr2 = s2.substring(0, i);  // s2的[0,i)
            String rStr2 = s2.substring(i);     // s2的[i, len)
            if(isScramble(lStr1, lStr2) && isScramble(rStr1, rStr2)){
                return true;
            }
            // 交换s2位置对比
            String rStr3 = s2.substring(0, len - i);    // s2的[0, n-i)
            String lStr3 = s2.substring(len - i);       // s2的[n-i, n)
            if (isScramble(lStr1, lStr3) && isScramble(rStr1, rStr3)){
                return true;
            }
        }
        return false;
    }

    /**
     * 深度优先搜索 - 带字典
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 90.63% 的用户
     * 内存消耗： 39 MB , 在所有 Java 提交中击败了 36.97% 的用户
     * */
    private int[][][] dict;
    int N = -1, Y = 1, EMPTY = 0;
    private String s1, s2;
    public boolean isScramble2(String s1, String s2) {
        if (s1.equals(s2)){
            return true;
        }
        if (s1.length() != s2.length()){
            return false;
        }
        this.s1 = s1;
        this.s2 = s2;
        int len = s1.length();
        dict = new int[len][len][len + 1];
        return dfs2(0,0,len);
    }
    // 深度优先搜索 - 带字典
    private boolean dfs2(int i, int j, int len){
        if(dict[i][j][len] != EMPTY){
            return dict[i][j][len] == Y;
        }
        String a = s1.substring(i, i + len);
        String b = s2.substring(j, j + len);
        if (a.equals(b)){
            dict[i][j][len] = Y;
            return true;
        }
        if (!checkChs(a, b)){
            dict[i][j][len] = N;
            return false;
        }
        for (int k = 1; k < len; k++) {
            // 对应了「s1 的 [0,i) & [i,n)」匹配「s2 的 [0,i) & [i,n)」
            if(dfs2(i, j, k) && dfs2(i + k, j + k, len - k)){
                dict[i][j][len] = Y;
                return true;
            }
            // 对应了「s1 的 [0,i) & [i,n)」匹配「s2 的 [n-i,n) & [0,n-i)」
            if(dfs2(i, j + len - k, k) && dfs2(i + k, j, len - k)){
                dict[i][j][len] = Y;
                return true;
            }
        }
        dict[i][j][len] = N;
        return false;
    }

    // 检查两个字符串词频是否相同
    private boolean checkChs(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        int[] counter = new int[26];
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        for (int i = 0; i < s1.length(); i++) {
            counter[chars1[i] - 'a']++;
            counter[chars2[i] - 'a']--;
        }
        for (int n : counter) {
            if (n != 0){
                return false;
            }
        }
        return true;
    }


    public boolean isScramble3(String s1, String s2) {
        int len = s1.length();
        if(s1.length() != s2.length()){
            return false;
        }
        if(len == 1){
            return true;
        }
        char[] str = s1.toCharArray();
        Set<String> memory = new HashSet<>();
        dfs(0, len - 1, str, memory);
        return memory.contains(s2);
    }
    // 深度优先搜索
    private void dfs(int start, int end, char[] str, Set<String> memory){
        if(start == end){
            memory.add(new String(str));
            return;
        }
        for (int i = start; i < end; i++) {
            char[] temp = str.clone();
            dfs(start, i, temp, memory);
            dfs(i+1, end, temp, memory);
            dfs(start, i, str.clone(), memory);
            dfs(i + 1, end, str.clone(), memory);
        }
    }
    // 在指定位置翻转字符串
    private void reverse(int start, int end, char[] str){
        while (start < end){
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
    }
}
