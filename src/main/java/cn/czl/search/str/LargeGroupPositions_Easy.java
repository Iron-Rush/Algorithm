package cn.czl.search.str;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author RedRush
 * @date 2021/1/5 9:46
 * @description:
 *      830. 较大分组的位置
 *      - 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
 *      - 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
 *      - 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。
 *      - 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
 *      - 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
 *      示例 1：
 *          输入：s = "abbxxxxzzy"
 *          输出：[[3,6]]
 *          解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。
 *      示例 2：
 *          输入：s = "abc"
 *          输出：[]
 *          解释："a","b" 和 "c" 均不是符合要求的较大分组。
 *      示例 3：
 *          输入：s = "abcdddeeeeaabbbcd"
 *          输出：[[3,5],[6,9],[12,14]]
 *          解释：较大分组为 "ddd", "eeee" 和 "bbb"
 *      示例 4：
 *          输入：s = "aba"
 *          输出：[]
 *      提示：
 *          1 <= s.length <= 1000
 *          s 仅含小写英文字母
 */
public class LargeGroupPositions_Easy {

    private String S1 = "abbxxxxzzy";
    private String S2 = "aaa";
    private String S3 = "abcdddeeeeaabbbcd";

    @Test
    public void TestSolution(){
        System.out.println(largeGroupPositions(S1));
        System.out.println(largeGroupPositions2(S1));
        System.out.println(largeGroupPositions(S2));
        System.out.println(largeGroupPositions2(S2));
        System.out.println(largeGroupPositions(S3));
        System.out.println(largeGroupPositions2(S3));
    }

    /**
     * 每次向后搜索与当前字符一致的字符
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 67.79% 的用户
     * */
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new LinkedList<>();
        char[] strs = s.toCharArray();
        int len = s.length(), pos = 0;
        while (pos < len){
            int end = pos;
            while (end + 1 < len && strs[pos] == strs[end + 1]){
                end++;
            }
            if(pos + 2 <= end){
                result.add(Arrays.asList(pos, end));
            }
            pos = end + 1;
        }
        return result;
    }

    /**
     * 逐个遍历，记录前一个相同的字符及对应下标
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 51.07% 的用户
     * */
    public List<List<Integer>> largeGroupPositions2(String s) {
        List<List<Integer>> result = new LinkedList<>();
        char[] strs = s.toCharArray();
        char preCh = strs[0];
        int preStart = 0, preEnd = -1;
        for (char ch : strs) {
            if(ch == preCh){
                preEnd++;
            }else {
                if(preStart + 2 <= preEnd){
                    result.add(Arrays.asList(preStart, preEnd));
                }
                preCh = ch;
                preEnd++;
                preStart = preEnd;
            }
        }
        if(preStart + 2 <= preEnd){
            result.add(Arrays.asList(preStart, preEnd));
        }
        return result;
    }
}
