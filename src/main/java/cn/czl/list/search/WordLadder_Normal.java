package cn.czl.list.search;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/11/6 17:47
 * @description:
 *      127. 单词接龙
 */
public class WordLadder_Normal {

    private String BEGINWORD1 = "hit";
    private String ENDWORD1 = "cog";
    private List<String> WORDLIST1 = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
    private String BEGINWORD2 = "hit";
    private String ENDWORD2 = "cog";
    private List<String> WORDLIST2 = new ArrayList<>(Arrays.asList("hot","dot","tog","cog"));

    @Test
    public void TestSolution(){
        System.out.println(ladderLength3(BEGINWORD2, ENDWORD2, WORDLIST2));
    }

    /**
     * 步进-单向BFS，记录已有轨迹，存储当前可能至队列
     * 每个单词中，逐字节对比，未剪枝
     * *** 超出时间限制 ***
     * */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> wordQueue = new LinkedList<>();
        Set<String> memory = new HashSet<>();
        wordQueue.add(beginWord);
        memory.add(beginWord);
        int step = 0;
        while (!wordQueue.isEmpty()){
            int curSize = wordQueue.size();
            for (int i = 0; i < curSize; i++) {
                String curWord = wordQueue.poll();
                if (curWord.equals(endWord)){
                    return step+1;
                }
                memory.add(curWord);
                for (int j = 0; j < curWord.length(); j++) {
                    List<String> add = getPossible(j, curWord, wordList, memory);
                    wordQueue.addAll(add);
                }
            }
            step++;
        }
        return 0;
    }



    /**
     * 双向BFS, 选择规模小的一端进行BFS搜索。直至相遇
     * 执行用时：1108 ms, 在所有 Java 提交中击败了14.33%的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了58.80%的用户
     * */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> memory = new HashSet<>();
        Set<String> headSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        headSet.add(beginWord);
        endSet.add(endWord);
        if (!wordList.contains(endWord)){
            return 0;
        }
        int step = 1;
        while (!headSet.isEmpty() && !endSet.isEmpty()){
            Set<String> tempSet = new HashSet<>();
            for (String curWord : headSet) {
                if (endSet.contains(curWord)){
                    return step;
                }
                memory.add(curWord);
                for (int i = 0; i < curWord.length(); i++) {
                    List<String> add = getPossible(i, curWord, wordList, memory);
                    tempSet.addAll(add);
                }
            }
            if(tempSet.size() < endSet.size()){
                headSet = tempSet;
            }else{
                headSet = endSet;
                endSet = tempSet;
            }
            step++;
        }
        return 0;
    }

    /**
     * 双向BFS, 字符匹配优化-模拟每位字符的26种可能性
     * 模拟结果匹配字典，若存在字典则加入下一轮搜索
     * [函数负责模拟-匹配，当前单词某一位置的全部可能性]
     * 执行用时：18 ms, 在所有 Java 提交中击败了92.06%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了64.61%的用户
     * */
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        Set<String> headSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        headSet.add(beginWord);
        endSet.add(endWord);
        if (!dict.contains(endWord)){
            return 0;
        }
        int step = 1;
        while (!headSet.isEmpty() && !endSet.isEmpty()){
            Set<String> tempSet = new HashSet<>();
            for (String curWord : headSet) {
                if (endSet.contains(curWord)){
                    return step;
                }
                visited.add(curWord);
                for (int i = 0; i < curWord.length(); i++) {
                    Set<String> add = getPossible2(i, curWord, visited, dict);
                    tempSet.addAll(add);
                }
            }
            if(tempSet.size() < endSet.size()){
                headSet = tempSet;
            }else{
                headSet = endSet;
                endSet = tempSet;
            }
            step++;
        }
        return 0;
    }

    /**
     * 双向BFS,字符匹配优化-模拟每位字符的26种可能性
     * 模拟结果匹配字典，若存在字典则加入下一轮搜索
     * [函数负责模拟-匹配，当前单词的全部可能性]
     * 执行用时：17 ms, 在所有 Java 提交中击败了92.64%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了64.27%的用户
     * */
    public int ladderLength4(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        Set<String> headSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        headSet.add(beginWord);
        endSet.add(endWord);
        if (!dict.contains(endWord)){
            return 0;
        }
        int step = 1;
        while (!headSet.isEmpty() && !endSet.isEmpty()){
            Set<String> tempSet = new HashSet<>();
            for (String curWord : headSet) {
                if (endSet.contains(curWord)){
                    return step;
                }
                visited.add(curWord);
                Set<String> add = getPossible3(curWord, visited, dict);
                tempSet.addAll(add);
            }
            if(tempSet.size() < endSet.size()){
                headSet = tempSet;
            }else{
                headSet = endSet;
                endSet = tempSet;
            }
            step++;
        }
        return 0;
    }

    private List<String> getPossible(int pos, String curWord, List<String> wordList, Set<String> memory){
        List<String> build = new ArrayList<>();
        char[] chars = curWord.toCharArray();
        for (String word : wordList) {
            if (memory.contains(word)){
                continue;
            }
            char[] tempWord = word.toCharArray();
            boolean flag = true;
            for (int i = 0; i < tempWord.length; i++) {
                if (tempWord[i] != chars[i] && i != pos){
                    flag = false;
                    break;
                }
            }
            if (flag){
                build.add(word);
            }
        }
        return build;
    }

    private Set<String> getPossible2(int pos, String curWord, Set<String> visited, Set<String> dict){
        Set<String> build = new HashSet<>();
        char[] curChars = curWord.toCharArray();
        char temp = curChars[pos];
        for (char c = 'a'; c <= 'z'; c++){
            if (temp == c){
                continue;
            }
            curChars[pos] = c;
            String strTemp = new String(curChars);
            if (dict.contains(strTemp) && !visited.contains(strTemp)){
                build.add(strTemp);
            }
        }
        return build;
    }

    private Set<String> getPossible3(String curWord, Set<String> visited, Set<String> dict){
        Set<String> build = new HashSet<>();
        char[] chars = curWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            for (char c= 'a'; c <= 'z'; c++){
                if (temp == c){
                    continue;
                }
                chars[i] = c;
                String tempStr = new String(chars);
                if (dict.contains(tempStr) && !visited.contains(tempStr)){
                    build.add(tempStr);
                }
            }
            chars[i] = temp;
        }
        return build;
    }



}
