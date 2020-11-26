package cn.czl.search.str;

import java.util.HashMap;

/**
 * @author RedRush
 * @date 2020/11/26 16:37
 * @description:
 *      387. 字符串中的第一个唯一字符
 *      - 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *      示例1：
 *          输入：s = "leetcode"
 *          返回：0
 *      示例2：
 *          输入：s = "loveleetcode"
 *          返回：2
 */
public class FirstUniqChar_Easy {

    /**
     * 两次遍历，第一次存储至记忆数组，统计每个字母的出现次数
     * 第二次按照字符串顺序遍历记忆数组，返回第一个出现次数为 1 的字母的下标
     * 执行用时：4 ms, 在所有 Java 提交中击败了97.19%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了62.91%的用户
     * */
    public int firstUniqChar(String s) {
        char[] chArray = s.toCharArray();
        int[] counter = new int[26];
        for(char ch : chArray){
            counter[ch - 'a']++;
        }
        for(int i = 0; i < chArray.length; i++){
            if(counter[chArray[i] - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }

    /**
     * 通过HashMap实现对字母的出现次数统计
     * 执行用时：40 ms, 在所有 Java 提交中击败了21.10%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了81.24%的用户
     * */
    public int firstUniqChar2(String s) {
        HashMap<Character, Integer> counter = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            counter.put(ch, counter.getOrDefault(ch, 0) + 1);
        }
        for(int i = 0; i < s.length(); i++){
            if(counter.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }

}
