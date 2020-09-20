package cn.czl.list.unique;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/9/20 11:25
 * @description:
 *      78.子集
 *          - 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *          - 说明：解集不能包含重复的子集。
 *      例：
 *          输入: nums = [1,2,3]
 *          输出:
 *          [
 *            [3],[1],[2],[1,2,3],
 *            [1,3],[2,3],[1,2],[]
 *          ]
 */
public class Subsets_Normal {

    private static int[] NUMS = new int[]{1,2,3};

    @Test
    public void TestSolution(){
        List<List<Integer>> resLists = subsets(NUMS);
        System.out.println(resLists);
        for (List<Integer> numList : resLists) {
            for (int num : numList) {
                System.out.print(num + ",");
            }
            System.out.println();
        }
    }

    /**
     * 递归实现
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.39%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了58.56%的用户
     * */
    List<List<Integer>> resLists = new ArrayList<>();
    List<Integer> tempList = new ArrayList<Integer>();
    boolean[] locks = new boolean[]{};
    public List<List<Integer>> subsets(int[] nums) {
        int size = nums.length;
        locks = new boolean[size];
        for (int i = size; i >= 0 ; i--) {
            getSubset(0, i, nums);
        }
        return resLists;
    }
    private void getSubset(int pos, int max, int[] nums){
        if (pos == max){
            resLists.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = pos; i < nums.length; i++){
            if (!locks[i]){
                tempList.add(nums[i]);
                locks[i] = true;
                getSubset(i + 1, max, nums);
                tempList.remove(tempList.size() - 1);
                locks[i] = false;
            }
        }
    }
}
