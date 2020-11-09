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
    private List<String> WORDLIST2 = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log"));

    @Test
    public void TestSolution(){
        System.out.println(WORDLIST1);
    }

    Map<String, Integer> wordId = new HashMap<>();
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int count = 0;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        return 0;
    }

}
