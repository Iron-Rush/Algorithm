package cn.czl.search.str;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author RedRush
 * @date 2020/10/2 22:02
 * @description:
 *      771. 宝石与石头
 *          - 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 
 *          S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 *          - J 中的字母不重复，J 和 S中的所有字符都是字母。
 *          字母区分大小写，因此"a"和"A"是不同类型的石头。
 *      例：
 *          输入: J = "aA", S = "aAAbbbb"
 *          输出: 3
 *      注意：
 *          S 和 J 最多含有50个字母。
 *          J 中的字符不重复。
 */
public class FindJewelsInStones_Easy {
    private static String Jewels = "aA";
    private static String Stone = "aAAbbbb";

    @Test
    public void TestSolution(){
        System.out.println("count:" + numJewelsInStones(Jewels, Stone));
    }

    /**
     * 通过Set判断元素是否存在
     * 执行用时：2 ms, 在所有 Java 提交中击败了47.74%的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了43.56%的用户
     * */
    public int numJewelsInStones(String J, String S) {
        int count = 0;
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < J.length(); i++) {
            set.add(J.charAt(i));
        }
        for (int i = 0; i < S.length(); i++) {
            if (set.contains(S.charAt(i))){
                count++;
            }
        }
        return count;
    }

}
