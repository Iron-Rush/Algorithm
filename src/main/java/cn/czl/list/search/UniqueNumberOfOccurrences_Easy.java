package cn.czl.list.search;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author RedRush
 * @date 2020/10/28 9:26
 * @description:
 *      1207. 独一无二的出现次数
 *      - 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 *      - 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 *      示例 1：
 *          输入：arr = [1,2,2,1,1,3]  输出：true
 *          1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 *      示例 2：
 *          输入：arr = [1,2]      输出：false
 */
public class UniqueNumberOfOccurrences_Easy {

    private int[] ARR1 = new int[]{1,2,2,1,1,3};    // true
    private int[] ARR2 = new int[]{1,2};            // false
    private int[] ARR3 = new int[]{-3,0,1,-3,1,1,1,-3,10,0};    // true

    @Test
    public void TestSolution(){
        System.out.println(uniqueOccurrences(ARR3));
    }

    /**
     * map统计出现次数，set判断出现频率是否重复
     * 执行用时：2 ms, 在所有 Java 提交中击败了91.43%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了90.03%的用户
     * */
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> countMap = new HashMap();
        HashSet<Integer> countSet = new HashSet<>();
        for (int temp : arr) {
            countMap.put(temp,countMap.getOrDefault(temp,0) + 1);
        }
        for (int time : countMap.values()) {
            if (!countSet.add(time)){
                return false;
            }
        }
        return true;
    }
    /**
     * 代码简化版，直接为set赋值去重
     * 执行用时：2 ms, 在所有 Java 提交中击败了91.43%的用户
     * 内存消耗：36.5 MB, 在所有 Java 提交中击败了89.60%的用户
     * */
    public boolean uniqueOccurrences2(int[] arr) {
        Map<Integer, Integer> counter = new HashMap<>();
        for(int temp : arr){
            counter.put(temp, counter.getOrDefault(temp, 0) + 1);
        }
        return counter.size() == new HashSet<Integer>(counter.values()).size();
    }
}
