package cn.czl.string.operate;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/10/22 10:16
 * @description:
 *      763. 划分字母区间
 *          字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，
 *          同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *      示例：
 *          输入：S = "ababcbacadefegdehijhklij"
 *          输出：[9,7,8]
 *          解释：
 *          - 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 *          - 每个字母最多出现在一个片段中。
 *          像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *      S只包含小写字母 'a' 到 'z' 。
 */
public class PartitionLabels_Normal {

    private static String S1 = "ababcbacadefegdehijhklij";


    @Test
    public void TestSolution(){
        System.out.println(partitionLabels(S1));
    }

    /**
     * 记忆数组 + 双指针，两次遍历
     * 执行用时：4 ms, 在所有 Java 提交中击败了86.91%的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了97.74%的用户
     * */
    public List<Integer> partitionLabels(String S) {
        List<Integer> resList = new LinkedList<>();
        int[] posDict = new int[26];
        for (int i = 0; i < S.length(); i++) {
            posDict[S.charAt(i) - 'a'] = i;
        }
        int lPos = 0, rPos = 0;
        for (int i = 0; i < S.length(); i++) {
            rPos = Math.max(rPos, posDict[S.charAt(i) - 'a']);
            if (i == rPos){
                resList.add(rPos - lPos + 1);
                lPos = rPos + 1;
            }
        }
        return resList;
    }
}
