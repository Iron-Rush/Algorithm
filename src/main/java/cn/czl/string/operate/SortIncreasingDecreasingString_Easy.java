package cn.czl.string.operate;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/11/25 16:17
 * @description:
 *      1370. 上升下降字符串
 *      示例 1：
 *          输入：s = "aaaabbbbcccc"
 *          输出："abccbaabccba"
 *          解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
 *              第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
 *              第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
 *              第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
 *              第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
 *      示例 3：
 *          输入：s = "leetcode"
 *          输出："cdelotee"
 */
public class SortIncreasingDecreasingString_Easy {

    private String S1 = "leetcode";
    private String S2 = "aaaabbbbcccc";

    @Test
    public void Testsolution(){
        System.out.println(sortString2(S2));
    }

    /**
     * int[]数组存储每个小写字符的出现次数
     * 然后正反遍历数组，通过stringbuilder生成字符串
     * 直到新字符串长度与原字符串长度相等
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.12%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了75.22%的用户
     * */
    public String sortString(String s) {
        int[] counter = new int[26];
        int len = s.length(), pos = 0;
        if(len == 1 || len == 0){
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for(char ch : s.toCharArray()){ // 存储原字符串
            counter[ch - 'a']++;
        }
        while(sb.length() < len){
            for(int i = pos; i < 26; i++){  // 升序遍历
                if(counter[i] != 0){
                    counter[i]--;
                    pos = i;
                    sb.append((char)('a' + i));
                }
            }
            for(int i = pos; i >= 0; i--){  // 降序遍历
                if(counter[i] != 0){
                    counter[i]--;
                    pos = i;
                    sb.append((char)('a' + i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 改为字符数组生成字符串,效率略高一些
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了89.19%的用户
     * */
    public String sortString2(String s) {
        int len = s.length(), pos = 0;
        if(len == 1 || len == 0){
            return s;
        }
        int[] counter = new int[26];
        char[] result = new char[len];
        for(char ch : s.toCharArray()){ // 存储原字符串
            counter[ch - 'a']++;
        }
        while(pos < len){
            for(int i = 0; i < 26; i++){  // 升序遍历
                if(counter[i] != 0){
                    counter[i]--;
                    result[pos++] = (char)('a' + i);
                }
            }
            for(int i = 25; i >= 0; i--){  // 降序遍历
                if(counter[i] != 0){
                    counter[i]--;
                    result[pos++] = (char)('a' + i);
                }
            }
        }
        return new String(result);
    }

}
