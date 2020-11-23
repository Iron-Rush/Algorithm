package cn.czl.list.search.contains;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RedRush
 * @date 2020/11/19 10:56
 * @description:
 *      136. 只出现一次的数字
 *      - 给定一个非空整数数组，除了某个元素只出现一次以外，
 *      其余每个元素均出现两次。找出那个只出现了一次的元素。
 *      示例：
 *          输入: [4,1,2,1,2]
 *          输出: 4
 */
public class FindSingleNumber_Easy {

    /**
     * 排序后，找到落单数字。
     * 执行用时：6 ms, 在所有 Java 提交中击败了27.73%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了77.79%的用户
     * */
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-1; i+=2){
            if(nums[i] != nums[i+1]){
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    /**
     * 通过异或遍历。最终剩下的即为所求
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.88%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了67.48%的用户
     * */
    public int singleNumber2(int[] nums) {
        int target = 0;
        for (int temp : nums) {
            target ^= temp;
        }
        return target;
    }

    /**
     * HashMap存储数字及频率
     * 执行用时：16 ms, 在所有 Java 提交中击败了8.95%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了67.70%的用户
     * */
    public int singleNumber3(int[] nums) {
        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int temp : nums) {
            counter.put(temp, counter.getOrDefault(temp, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : counter.entrySet()){
            if(entry.getValue() == 1){
                return entry.getKey();
            }
        }
        return -1;
    }

}
