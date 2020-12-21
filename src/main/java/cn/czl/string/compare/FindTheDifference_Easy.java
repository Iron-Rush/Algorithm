package cn.czl.string.compare;

/**
 * @author RedRush
 * @date 2020/12/18 9:21
 * @description:
 *      389. 找不同
 *      - 给定两个字符串 s 和 t，它们只包含小写字母。
 *      - 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *      - 请找出在 t 中被添加的字母。
 *      示例 1：
 *          输入：s = "abcd", t = "abcde"
 *          输出："e"
 *          解释：'e' 是那个被添加的字母。
 *      示例 2：
 *          输入：s = "", t = "y"
 *          输出："y"
 *      示例 3：
 *          输入：s = "a", t = "aa"
 *          输出："a"
 *      示例 4：
 *          输入：s = "ae", t = "aea"
 *          输出："a"
 *      - 提示：
 *      1. 0 <= s.length <= 1000
 *      2. t.length == s.length + 1
 *      3. s 和 t 只包含小写字母
 */
public class FindTheDifference_Easy {

    /**
     * `^`异或位运算的使用
     * 根据 a^b^b == a, 可以抵消两个字符串中重复的字符，
     * 最终留下差异字符
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.6 MB , 在所有 Java 提交中击败了 94.82% 的用户
     * */
    public char findTheDifference(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        char ch = tChars[tChars.length - 1];
        for (int i = 0; i < s.length(); i++) {
            ch ^= sChars[i];
            ch ^= tChars[i];
        }
        return ch;
    }

    /**
     * 通过将两个字符串中各字符的ascii码的累加值作差
     * 得出两个字符串中的差异字符的ascii码
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.8 MB , 在所有 Java 提交中击败了 79.05% 的用户
     * */
    public char findTheDifference2(String s, String t) {
        int res = 0;
        for (char ch : t.toCharArray()) {
            res += ch;
        }
        for (char ch : s.toCharArray()){
            res -= ch;
        }
        return (char)res;
    }

    /**
     * 通过int数组，统计比对两个字符串中每个字符的个个数，找新增字符
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.9 MB , 在所有 Java 提交中击败了 61.44% 的用户
     * */
    public char findTheDifference3(String s, String t) {
        int[] counter = new int[26];
        for (char ch : t.toCharArray()) {
            counter[ch - 'a']++;
        }
        for (char ch : s.toCharArray()) {
            counter[ch - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if(counter[i] == 1){
                return (char)(i + 'a');
            }
        }
        return ' ';
    }
}
