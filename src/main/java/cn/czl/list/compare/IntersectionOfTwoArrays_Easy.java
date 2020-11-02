package cn.czl.list.compare;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/11/2 11:31
 * @description:
 *      349. 两个数组的交集
 *      - 给定两个数组，编写一个函数来计算它们的交集。
 *      示例 1：
 *          输入：nums1 = [1,2,2,1], nums2 = [2,2]
 *          输出：[2]
 *      示例 2：
 *          输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *          输出：[9,4]
 *      - 输出结果中的每个元素一定是唯一的。
 *      - 我们可以不考虑输出结果的顺序。
 */
public class IntersectionOfTwoArrays_Easy {

    private static int[] NUMS1 = new int[]{4,9,5};
    private static int[] NUMS2 = new int[]{9,4,9,8,4};

    @Test
    public void TestSolution(){
        int[] res = intersection(NUMS1, NUMS2);
        for (int temp : res) {
            System.out.print(temp + ",");
        }
    }

    /**
     * HashSet去重存储
     * 执行用时：3 ms, 在所有 Java 提交中击败了95.82%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了91.55%的用户
     * */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> memory = new HashSet();
        HashSet<Integer> contains = new HashSet();
        for (int temp : nums1) {
            memory.add(temp);
        }
        for (int temp : nums2) {
            if (memory.contains(temp)){
                contains.add(temp);
            }
        }
        int[] res = new int[contains.size()];
        Iterator<Integer> iterator = contains.iterator();
        int pos = 0;
        while (iterator.hasNext()){
            res[pos++] = iterator.next();
        }
        return res;
    }

}
