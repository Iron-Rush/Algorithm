package cn.czl.string.compare;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RedRush
 * @date 2020/12/27 8:46
 * @description:
 *      205. 同构字符串
 *      -  给定两个字符串 s 和 t，判断它们是否是同构的。
 *      - 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 *      - 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *      示例 1:
 *          输入: s = "egg", t = "add"
 *          输出: true
 *      示例 2:
 *          输入: s = "foo", t = "bar"
 *          输出: false
 *      示例 3:
 *          输入: s = "paper", t = "title"
 *          输出: true
 *      说明: 你可以假设 s 和 t 具有相同的长度。
 */
public class IsIsomorphicStrings_Easy {

    private String S1 = "egg";
    private String S2 = "foo";
    private String S3 = "paper";
    private String T1 = "add";
    private String T2 = "bar";
    private String T3 = "title";

    @Test
    public void TestSolution(){
        System.out.println(isIsomorphic2(S1, T1));
        System.out.println(isIsomorphic2(S2, T2));
        System.out.println(isIsomorphic2(S3, T3));
    }

    /**
     * 通过StringBuilder构造模版串，map记录模版串与字符的对应
     * 最后比对模版串是否一致。
     * 执行用时： 22 ms , 在所有 Java 提交中击败了 14.15% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 8.40% 的用户
     * */
    public boolean isIsomorphic(String s, String t) {
        int len = s.length();
        if(len != t.length()){
            return false;
        }
        int count1 = 0, count2 = 0;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        char[] char1 = s.toCharArray();
        char[] char2 = t.toCharArray();
        for (int i = 0; i < len; i++) {
            if(map1.containsKey(char1[i])){
                sb1.append(map1.get(char1[i]));
            }else {
                char flag = (char)('a' + count1++);
                map1.put(char1[i], flag);
                sb1.append(flag);
            }
            if (map2.containsKey(char2[i])){
                sb2.append(map2.get(char2[i]));
            }else {
                char flag = (char)('a' + count2++);
                map2.put(char2[i], flag);
                sb2.append(flag);
            }
        }
        return sb1.toString().equals(sb2.toString());
    }

    /**
     * map优化 - 单HashMap建立映射
     * 执行用时： 10 ms , 在所有 Java 提交中击败了 70.00% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 83.98% 的用户
     * */
    public boolean isIsomorphic2(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        char[] char1 = s.toCharArray();
        char[] char2 = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char c1 = char1[i];
            char c2 = char2[i];
            if(map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
            }else if (map.containsValue(c2)){
                return false;
            }else {
                map.put(c1, c2);
            }
        }
        return true;
    }

    /**
     * 优化为使用char[] 记录模版串， 效果不明显
     * 执行用时： 18 ms , 在所有 Java 提交中击败了 21.23% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 83.53% 的用户
     * */
    public boolean isIsomorphic3(String s, String t) {
        int len = s.length();
        if(len != t.length()){
            return false;
        }
        int count1 = 0, count2 = 0;
        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        char[] char1 = s.toCharArray();
        char[] char2 = t.toCharArray();
        for (int i = 0; i < len; i++) {
            if(map1.containsKey(char1[i])){
                char1[i] = map1.get(char1[i]);
            }else {
                char flag = (char)('a' + count1++);
                map1.put(char1[i], flag);
                char1[i] = flag;
            }
            if (map2.containsKey(char2[i])){
                char2[i] = map2.get(char2[i]);
            }else {
                char flag = (char)('a' + count2++);
                map2.put(char2[i], flag);
                char2[i] = flag;
            }
        }
        return Arrays.equals(char1, char2);
    }

    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 90.32% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 31.07% 的用户
     * */
    public boolean isIsomorphic4(String s, String t) {
        int len = s.length();
        if(len != t.length()){
            return false;
        }
        int count1 = 0, count2 = 0;
        int[][] counter1 = new int[128][2];
        int[][] counter2 = new int[128][2];
        char[] char1 = s.toCharArray();
        char[] char2 = t.toCharArray();
        for (int i = 0; i < len; i++) {
            int a1 = char1[i];
            int a2 = char2[i];
            if(counter1[a1][0] == 0){
                counter1[a1][0]++;
                counter1[a1][1] = count1;
                char1[i] = (char)(count1++);
            }else {
                char1[i] = (char)(counter1[a1][1]);
            }
            if(counter2[a2][0] == 0){
                counter2[a2][0]++;
                counter2[a2][1] = count2;
                char2[i] = (char)(count2++);
            }else {
                char2[i] = (char)(counter2[a2][1]);
            }
        }
        return Arrays.equals(char1, char2);
    }

    /**
     * counter记录该字符上一次出现的位置+1,
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 98.51% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 90.80% 的用户
     * */
    public boolean isIsomorphic5(String s, String t) {
        int len = s.length();
        if(len != t.length()){
            return false;
        }
        int[] counter1 = new int[128];
        int[] counter2 = new int[128];
        char[] char1 = s.toCharArray();
        char[] char2 = t.toCharArray();
        for (int i = 0; i < len; i++) {
            if(counter1[char1[i]] != counter2[char2[i]]){
                return false;
            }
            counter1[char1[i]] = i + 1;
            counter2[char2[i]] = i + 1;
        }
        return true;
    }

}
