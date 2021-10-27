package cn.czl.study.stackAndQueue.stack.monoton;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2021/3/24 10:03
 * @description:
 *      456. 132模式
 *      - 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。
 *          设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
 *      - 注意：n 的值小于15000。
 *      示例1:
 *          输入: [1, 2, 3, 4]
 *          输出: False
 *          解释: 序列中不存在132模式的子序列。
 *      示例 2:
 *          输入: [3, 1, 4, 2]
 *          输出: True
 *          解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
 *      示例 3:
 *          输入: [-1, 3, 2, 0]
 *          输出: True
 *          解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
 */
public class Find132Pattern_Normal {

    private int[] NUMS1 = {1,2,3,4};
    private int[] NUMS2 = {3,1,4,2};
    private int[] NUMS3 = {-1,3,2,0};
    private int[] NUMS4 = {3,5,0,3,4};
    private int[] NUMS5 = {1,0,1,-4,3};

    @Test
    public void TestSolution(){
        System.out.println(find132pattern3(NUMS1));
        System.out.println(find132pattern3(NUMS2));
        System.out.println(find132pattern3(NUMS3));
        System.out.println(find132pattern3(NUMS4));
        System.out.println(find132pattern3(NUMS5));
    }


    /**
     * 单调栈
     * 执行用时： 10 ms , 在所有 Java 提交中击败了 33.58% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 59.38% 的用户
     * */
    public boolean find132pattern(int[] nums) {
        int len = nums.length;
        if(nums != null && len >= 3){
            Stack<Integer> stack = new Stack<>();
            stack.push(nums[len - 1]);
            int popVal = Integer.MIN_VALUE;
            for (int i = len - 2; i >= 0; i--) {
                if(nums[i] < popVal){
                    return true;
                }
                while (!stack.isEmpty() && nums[i] > stack.peek()){
                    popVal = stack.pop();
                }
                if(nums[i] > popVal){
                    stack.push(nums[i]);
                }
            }
        }
        return false;
    }

    /**
     * 单调栈 + 数组
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 48.51% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 69.62% 的用户
     * */
    public boolean find132pattern2(int[] nums) {
        int len = nums.length;
        if(nums != null && len >= 3){
            int[] min = new int[len];   // 当前位置上，前面数字中的最小值
            min[0] = nums[0];
            for (int i = 1; i < len; i++) {
                min[i] = Math.min(min[i-1], nums[i]);
            }
            Stack<Integer> stack = new Stack<>();
            for (int i = len - 1; i >= 0; i--) {
                while (!stack.isEmpty() && stack.peek() <= min[i]){
                    stack.pop();
                }
                if(!stack.isEmpty() && stack.peek() < nums[i]){
                    return true;
                }
                stack.push(nums[i]);
            }
        }
        return false;
    }

    /**
     * 从后向前维护一个单调栈 [逻辑清晰]
     * [3，1，4，2]从后向前遍历
     * 1.枚举到`2`，stack:[2],k: minVal
     * 2.枚举到`4`, 不满足单调递减，stack:[4],k: 2
     * 3.枚举到`1`, 满足nums[i] < k，对于i而言，后面有一个比其大的元素(i<k)，
     *   同时k又是由于维护[单调递减]而弹出，意味着i和k中间，存在比k还要大的元素。
     *   此时找到了132组合。
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 43.28% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 25.38% 的用户
     * */
    public boolean find132pattern3(int[] nums) {
        int len = nums.length;
        int popVal = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = len - 1; i >= 0; i--) {
            if(nums[i] < popVal){
                return true;
            }
            while (!stack.isEmpty() && stack.peek() < nums[i]){
                popVal = stack.pop();
//                popVal = Math.max(popVal, stack.pop());
            }
            stack.add(nums[i]);
        }
        return false;
    }


}
