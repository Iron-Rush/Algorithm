package cn.czl.string.operate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author RedRush
 * @date 2021/1/11 9:29
 * @description:
 *      1202. 交换字符串中的元素
 *      - 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 *      - 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 *      - 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 *      示例 1:
 *          输入：s = "dcab", pairs = [[0,3],[1,2]]
 *          输出："bacd"
 *          解释： 交换 s[0] 和 s[3], s = "bcad", 交换 s[1] 和 s[2], s = "bacd"
 *      示例 2：
 *          输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 *          输出："abcd"
 *      示例 3：
 *          输入：s = "cba", pairs = [[0,1],[1,2]]
 *          输出："abc"
 *      提示：
 *          1 <= s.length <= 10^5
 *          0 <= pairs.length <= 10^5
 *          0 <= pairs[i][0], pairs[i][1] < s.length
 *          s 中只含有小写英文字母
 */
public class SmallestStringWithSwaps_Normal {

    private String S1 = "dcab";
    private String S2 = "dcab";
    private String S3 = "cba";

    @Test
    public void TestSolution(){
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(new ArrayList<Integer>(){{this.add(0);this.add(3);}});
        pairs.add(new ArrayList<Integer>(){{this.add(1);this.add(2);}});
//        pairs.add(new ArrayList<Integer>(){{this.add(0);this.add(2);}});
        System.out.println(smallestStringWithSwaps(S1, pairs));
    }

    /**
     * 并查集
     * 执行用时： 34 ms , 在所有 Java 提交中击败了 88.54% 的用户
     * 内存消耗： 86.2 MB , 在所有 Java 提交中击败了 76.09% 的用户
     * */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] str = s.toCharArray();
        int len = s.length();
        DSU dsu = new DSU(len);
        // 构造下标集合
        for (List<Integer> temp : pairs) {
            dsu.union(temp.get(0), temp.get(1));
        }
        // 每个下标集合用leader作为key，下标集合List作为value
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int key = dsu.find(i);
            List<Integer> temp = map.getOrDefault(key, new ArrayList<>());
            temp.add(i);
            map.put(key, temp);
        }
        for (List<Integer> list : map.values()) {
            if(list.size() > 1){
                sort(str, list);
            }
        }
        return new String(str);
    }
    // 根据list中下标，进行局部排序
    private void sort(char[] str, List<Integer> list){
        int len = list.size();
        char[] chs = new char[len];
        // 根据下标集合构建字符集合
        for (int i = 0; i < len; i++) {
            chs[i] = str[list.get(i)];
        }
        // 将字符集合排序
        Arrays.sort(chs);
        // 将字符按序插入回数组
        for (int i = 0; i < len; i++) {
            str[list.get(i)] = chs[i];
        }
    }
}
class DSU{
    int[] parent;

    public DSU(int len){
        parent = new int[len];
        for (int i = 0; i < len; i++) {
            parent[i] = i;
        }
    }
    public int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
//        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }
    public void union(int x, int y){
        parent[find(x)] = find(y);
    }
}
