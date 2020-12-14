package cn.czl.string.compare;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author RedRush
 * @date 2020/12/14 10:33
 * @description:
 *      - 49. 字母异位词分组
 *      - 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *      示例:
 *          输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 *          输出:
 *          [ ["ate","eat","tea"],
 *            ["nat","tan"],
 *            ["bat"] ]
 *      说明：
 *          所有输入均为小写字母。
 *          不考虑答案输出的顺序。
 */
public class GroupAnagrams_Normal {

    private String[] STRS = {"eat", "tea", "tan", "ate", "nat", "bat"};

    @Test
    public void TestSolution(){
        System.out.println(groupAnagrams3(STRS));
    }

    /**
     * 二维数组记录，第一版
     * 执行用时： 1327 ms , 在所有 Java 提交中击败了 5.00% 的用户
     * 内存消耗： 44 MB , 在所有 Java 提交中击败了 5.03% 的用户
     * */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        HashMap<Integer, List> map = new HashMap<>();
        int[][] strsDict = new int[strs.length][26];
        for (int i = 0; i < strs.length; i++) {
            char[] str = strs[i].toCharArray();
            for (char ch : str) {
                strsDict[i][ch-'a']++;
            }
        }
        for (int i = 0; i < strs.length; i++) {
            boolean has = false;
            for(int key : map.keySet()){
                if(isSameArray(strsDict[i], strsDict[key])){
                    List<Integer> list = map.get(key);
                    list.add(i);
                    map.put(key, list);
                    has = true;
                    break;
                }
            }
            if (!has){
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(i, list);
            }
        }
        for (List<Integer> list : map.values()) {
            List<String> curList = new ArrayList<>();
            for (int i : list) {
                curList.add(strs[i]);
            }
            result.add(curList);
        }
        return result;
    }


    /**
     * 二维数组记录，第二版。  - 比较时，初始化、完善result数组
     * 执行用时： 896 ms , 在所有 Java 提交中击败了 5.00% 的用户
     * 内存消耗： 42.5 MB , 在所有 Java 提交中击败了 14.20% 的用户
     * */
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        List<Integer> memory = new ArrayList<>();
        int[][] strsDict = new int[strs.length][26];
        for (int i = 0; i < strs.length; i++) {
            boolean has = false;
            char[] str = strs[i].toCharArray();
            for (char ch : str) {
                strsDict[i][ch-'a']++;
            }
            for(int key = 0; key < memory.size(); key++){
                if(isSameArray(strsDict[i], strsDict[memory.get(key)])){
                    List<String> list = result.get(key);
                    list.add(strs[i]);
                    has = true;
                    break;
                }
            }
            if (!has){
                List<String> curList = new ArrayList<>();
                curList.add(strs[i]);
                result.add(curList);
                memory.add(i);
            }
        }
        return result;
    }

    /**
     * 第三版。 当前字符串排序后 与 已生成字符数组 做匹配。
     * 若无匹配，则存储新的字符数组
     * 执行用时： 377 ms , 在所有 Java 提交中击败了 5.99% 的用户
     * 内存消耗： 41.4 MB , 在所有 Java 提交中击败了 76.95% 的用户
     * */
    public List<List<String>> groupAnagrams3(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        List<char[]> dict = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            boolean has = false;
            char[] str = strs[i].toCharArray();
            Arrays.sort(str);
            for(int key = 0; key < dict.size(); key++){
                if(Arrays.equals(str, dict.get(key))){
                    List<String> list = result.get(key);
                    list.add(strs[i]);
                    has = true;
                    break;
                }
            }
            if (!has){
                List<String> curList = new ArrayList<>();
                curList.add(strs[i]);
                result.add(curList);
                dict.add(str);
            }
        }
        return result;
    }

    /**
     * Map中存放 key: 排序后单词， val: 同一key下的全部单词
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 95.84% 的用户
     * 内存消耗： 41.7 MB , 在所有 Java 提交中击败了 52.81% 的用户
     * */
    public List<List<String>> groupAnagrams4(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * Map中存放 key: 统计字母个数-生成key， val: 同一key下的全部单词
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 31.57% 的用户
     * 内存消耗： 41.7 MB , 在所有 Java 提交中击败了 47.39% 的用户
     * */
    public List<List<String>> groupAnagrams5(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] counter = new int[26];
            for (char ch : str.toCharArray()) {
                counter[ch - 'a']++;
            }
            // 按字符顺序，根据字符数量 - 生成key
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if(counter[i] != 0){
                    sb.append((char)(i + 'a'));
                    sb.append(counter[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    // 检查两数组中各字母数量是否一致
    boolean isSameArray(int[] arr1, int[] arr2){
        if(arr1.length == arr2.length){
            for (int i = 0; i < arr1.length; i++) {
                if(arr1[i] != arr2[i]){
                    return false;
                }
            }
            return true;
        }else {
            return false;
        }
    }

}
