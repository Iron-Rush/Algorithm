package cn.czl.list.sum;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/10/10 17:14
 * @description:
 *      15. 三数之和
 *          - 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 *          使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组
 *          - 注意：答案中不可以包含重复的三元组。
 *      例：
 *          给定数组 nums = [-1, 0, 1, 2, -1, -4]
 *          满足要求的三元组集合为：
 *              [   [-1, 0, 1],
 *                  [-1, -1, 2] ]
 */
public class ThreeSum_Normal {

    private static int[] NUMS = new int[]{-1, 0, 1, 2, -1, -4};

    @Test
    public void TestSolution(){
        List<List<Integer>> res = threeSum2(NUMS);
        System.out.println(res);
    }

    /**
     * dfs，超时
     * */
    List<List<Integer>> resList = new ArrayList<>();
    List<Integer> tempList = new ArrayList<>();
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        dfs(nums, 0, 0);
        return resList;
    }
    public void dfs(int[] nums, int depth, int pos){
        if(depth == 3){
            if (tempList.get(0) + tempList.get(1) + tempList.get(2) == 0){
                resList.add(new ArrayList<>(tempList));
            }
        }else{
            for (int i = pos; i < nums.length; i++) {
                tempList.add(nums[i]);
                dfs(nums, depth + 1, i + 1);
                tempList.remove(tempList.size()-1);
                while (i+1 < nums.length && nums[i] == nums[i+1]){
                    i++;
                }
            }
        }
    }

    /**
     * 双指针
     * 执行用时：22 ms, 在所有 Java 提交中击败了94.79%的用户
     * 内存消耗：42.4 MB, 在所有 Java 提交中击败了89.04%的用户
     * */
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3){
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0){
                return result;
            }
            if (i != 0 && nums[i] == nums[i-1]){
                continue;
            }
            int Lpos = i + 1;
            int Rpos = nums.length - 1;
            while (Lpos < Rpos){
                int sum = nums[i] + nums[Lpos] + nums[Rpos];
                if (sum == 0){
                    result.add(Arrays.asList(nums[i], nums[Lpos], nums[Rpos]));
                    while (Lpos < Rpos && nums[Lpos] == nums[Lpos + 1]){
                        Lpos++;
                    }
                    while (Lpos < Rpos && nums[Rpos] == nums[Rpos - 1]){
                        Rpos--;
                    }
                    Lpos++;
                    Rpos--;
                }else if (sum > 0){
                    Rpos--;
                }else {
                    Lpos++;
                }
            }
        }
        return result;
    }

}
