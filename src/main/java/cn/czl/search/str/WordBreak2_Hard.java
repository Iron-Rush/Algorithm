package cn.czl.search.str;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/11/1 14:24
 * @description:
 *      140. 单词拆分 II
 *      - 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 *      在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。
 *      返回所有这些可能的句子。
 *      - 分隔时可以重复使用字典中的单词。
 *      - 你可以假设字典中没有重复的单词。
 *      例：
 *          输入    s = "catsanddog"
 *                  wordDict = ["cat", "cats", "and", "sand", "dog"]
 *          输出  ["cats and dog",
 *                  "cat sand dog"]
 */
public class WordBreak2_Hard {

    private static String S1 = "catsanddog";
    private static String S2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    private static List<String> WORDDICT1 = new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
    private static List<String> WORDDICT2 = new ArrayList<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"));

    @Test
    public void TestSolution(){
        System.out.println(wordBreak(S2, WORDDICT2));
    }

    /**
     * 执行用时：7 ms, 在所有 Java 提交中击败了84.70%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了92.86%的用户
     * */
    List<String> resList = new ArrayList<>();
    List<Integer> tempList = new ArrayList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        char[] words = s.toCharArray();
        HashSet set = new HashSet();
        for (String str : wordDict) {
            for (int i = 0; i < str.length(); i++) {
                set.add(str.charAt(i));
            }
        }
        for (char ch : words) {
            if (set.add(ch)){
                return resList;
            }
        }
        Breaker(words, wordDict, 0);
        return resList;
    }

    private void Breaker(char[] words, List<String> wordDict, int pos){
        if (pos == words.length){
            Builder(wordDict);
            return;
        }
        for (int i = 0; i < wordDict.size(); i++){
            String word = wordDict.get(i);
            if (word.length() > words.length-pos){
                continue;
            }
            boolean flag = true;
            char[] chars = word.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] != words[j + pos]){
                    flag = false;
                    break;
                }
            }
            if (flag){
                tempList.add(i);
                Breaker(words, wordDict, pos + word.length());
                tempList.remove(tempList.size()-1);
            }
        }
    }

    private void Builder(List<String> wordDict){
        StringBuilder sb = new StringBuilder();
        for (int pos : tempList) {
            sb.append(wordDict.get(pos) + " ");
        }
        sb.setLength(sb.length()-1);
        resList.add(sb.toString());
    }
}
