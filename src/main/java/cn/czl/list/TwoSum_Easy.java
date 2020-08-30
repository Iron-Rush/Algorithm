package cn.czl.list;

import org.junit.jupiter.api.Test;
import java.util.HashMap;

/**
 * @author RedRush
 * @date 2020/8/30 10:49
 * @description: 给出数组，和目标值。以数组形式，返回满足条件和的两个数字
 */
public class TwoSum_Easy {

//    private static int[] NUMS = {1,2,3,5,6,100,200};
private static int[] NUMS = {18,19,25,30,39,41,61,77,88,97};
    private static int RESULT = 59;

    @Test
    public void TestSolution(){
        int[] res = twoSum(NUMS, RESULT);
        for (int temp : res) {
            System.out.println(temp);
        }
    }
    /**
     * 双指针，根据和，移动不同指针
     * 执行用时：2 ms, 在所有 Java 提交中击败了98.49%的用户
     * 内存消耗：56.8 MB, 在所有 Java 提交中击败了75.77%的用户
     */
    public int[] twoSum(int[] nums, int target) {
        int Lpos = 0;
        int Rpos = nums.length - 1;
        while (Lpos <= Rpos){
            if (nums[Lpos] + nums[Rpos] == target){
                return new int[] {nums[Lpos], nums[Rpos]};
            }
            if (nums[Lpos] + nums[Rpos] > target){
                Rpos--;
            }else {
                Lpos++;
            }
        }
        return new int[0];
    }

    /**
     * 将已遍历元素元素的补数，作为键存入Map，值为该元素下标
     * 每次先去Map中查看，当前数字是否为之前数的补数
     * 执行用时：56 ms, 在所有 Java 提交中击败了13.80%的用户
     * 内存消耗：59.6 MB, 在所有 Java 提交中击败了5.03%的用户
     * */
    public int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer,Integer> map = new HashMap();
        map.put(target - nums[0],0);
        for (int i = 1; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                return new int[]{target-nums[i], nums[i]};
            }
            map.put(target - nums[i], i);
        }
        return res;
    }
}
