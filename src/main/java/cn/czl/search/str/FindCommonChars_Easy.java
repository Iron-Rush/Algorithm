package cn.czl.search.str;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/10/14 9:55
 * @description:
 *      1002. 查找常用字符
 *          1. 给定仅有小写字母组成的字符串数组 A，
 *          返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
 *          2. 如果一个字符在每个字符串中出现 3 次，但不是 4 次，
 *          则需要在最终答案中包含该字符 3 次。
 *      示例 1：
 *          输入：["bella","label","roller"]
 *          输出：["e","l","l"]
 */
public class FindCommonChars_Easy {

    private static String[] ALL = new String[]{"bella","label","roller"};

    @Test
    public void TestSolution(){
        System.out.println(commonChars2(ALL));
    }

    /**
     * 双HashMap，存储字符，比较 + 替换
     * 执行用时：19 ms, 在所有 Java 提交中击败了19.73%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了96.41%的用户
     * */
    public List<String> commonChars(String[] A) {
        if (A.length == 0){
            return null;
        }
        List<String> resList = new ArrayList<>();
        Map<Character, Integer> resMap = new HashMap<>();
        for (Character tempChar : A[0].toCharArray()){
            resMap.put(tempChar, resMap.getOrDefault(tempChar, 0) + 1);
        }
        for (int i = 1; i < A.length; i++) {
            String word = A[i];
            Map<Character, Integer> tempMap = new HashMap<>();
            for (Character tempChar : word.toCharArray()) {
                if (resMap.containsKey(tempChar)){
                    tempMap.put(tempChar, Math.min(tempMap.getOrDefault(tempChar, 0) + 1, resMap.get(tempChar)));
                }
            }
            resMap = tempMap;
        }
        for (Map.Entry<Character, Integer> entry : resMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                resList.add(String.valueOf(entry.getKey()));
            }
        }
        return resList;
    }

    /**
     * 字符转int -> int数组存储字符出现的最小频率
     * 执行用时：4 ms, 在所有 Java 提交中击败了71.47%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了99.73%的用户
     * */
    public List<String> commonChars2(String[] A) {
        if (A.length == 0) {
            return null;
        }
        List<String> resList = new ArrayList<>();
        int[] wordCounter = new int[26];   // 总统计结果记录数组，默认为0
        // 填充第一个单词
        for (int i = 0; i < A[0].length(); i++) {
            wordCounter[A[0].charAt(i) - 'a']++;
        }
        for (int i = 1; i < A.length; i++) {
            // 迭代字符统计数组，记录 总统计结果与当前字符的比较结果
            int[] tempCounter = new int[26];
            for(int j = 0; j < A[i].length(); j++){
                tempCounter[A[i].charAt(j) - 'a']++;
            }
            for (int k = 0; k < 26; k++) {
                // 取当前统计结果与总统计结果的最小值
                wordCounter[k] = Math.min(tempCounter[k], wordCounter[k]);
            }
        }
        // 生成统计结果为string
        for (int i = 0; i < 26; i++) {
            while (wordCounter[i] != 0){
                resList.add(String.valueOf((char)(i + 97)));
                wordCounter[i]--;
            }
        }
        return resList;
    }

}
