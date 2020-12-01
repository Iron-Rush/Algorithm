package cn.czl.list.sum;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author RedRush
 * @date 2020/12/1 16:41
 * @description:
 *      167. 两数之和 II - 输入有序数组
 *      - 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *      - 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *      说明:
 *          返回的下标值（index1 和 index2）不是从零开始的。
 *          你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *      示例:
 *          输入: numbers = [2, 7, 11, 15], target = 9
 *          输出: [1,2]
 *          解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */
public class TwoSum2_Easy {
    private int[] NUMBERS = {2, 7, 11, 15};
    private int TARGET = 9;

    @Test
    public void TestSolution(){
        int[] res = twoSum3(NUMBERS, TARGET);
        System.out.println(Arrays.toString(res));
    }

    /**
     * map记录补集
     * 执行用时：2 ms, 在所有 Java 提交中击败了36.31%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了94.94%的用户
     * */
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < numbers.length; i++){
            if(map.containsKey(numbers[i])){
                return new int[]{map.get(numbers[i]), i+1};
            }else{
                map.put(target - numbers[i], i+ 1);
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 双指针
     * 执行用时：1 ms, 在所有 Java 提交中击败了95.20%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了81.98%的用户
     * */
    public int[] twoSum2(int[] numbers, int target) {
        int lPos = 0, rPos = numbers.length - 1;
        while (lPos < rPos){
            int sum = numbers[lPos] + numbers[rPos];
            if(sum < target){
                lPos++;
            }else if (sum > target){
                rPos--;
            }else {
                return new int[]{lPos+1, rPos+1};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 二分法查找
     * */
    public int[] twoSum3(int[] numbers, int target) {
        int len = numbers.length;
        for (int i = 0; i < len; i++) {
            int left = i + 1;       // 不可包括当前数
            int right = len - 1;
            int curTarget = target - numbers[i];
            while (left <= right){
                int mid = left + (right - left)/2;
                if (numbers[mid] < curTarget){
                    left = mid + 1;
                }else if (numbers[mid] > curTarget){
                    right = mid - 1;
                }else {
                    return new int[]{i + 1, mid + 1};
                }
            }
        }
        return new int[]{-1, -1};
    }

}
