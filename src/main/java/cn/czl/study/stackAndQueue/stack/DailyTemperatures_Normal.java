package cn.czl.study.stackAndQueue.stack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/12/17 13:52
 * @description:
 *      739. 每日温度
 *      - 请根据每日 气温 列表，重新生成一个列表。
 *      对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
 *      如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *      - 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 *              你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *      - 提示：气温 列表长度的范围是 [1, 30000]。
 *      每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class DailyTemperatures_Normal {

    private int[] TEMPERATURES = {73, 74, 75, 71, 69, 72, 76, 73};

    @Test
    public void TestSolution(){
        int[] result = dailyTemperatures2(TEMPERATURES);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 双层循环，逐个向后查找符合条件的温度
     * 执行用时： 1329 ms , 在所有 Java 提交中击败了 10.53% 的用户
     * 内存消耗： 46.2 MB , 在所有 Java 提交中击败了 88.76% 的用户
     * */
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        for(int i = 0; i < T.length; i++){
            for(int j = i+1; j < T.length; j++){
                if(T[j] > T[i]){
                    ans[i] = j - i;
                    break;
                }
            }
        }
        return ans;
    }

    /**
     * 单调栈  [此处为单调狄递减栈]     (栈实现)
     * 若下一个数要比当前栈顶元素大，则不满足单调栈原则，开始出栈操作
     * 直至栈中为空，或栈顶元素大于此时要入栈元素。然后将当前元素入栈。
     * 当前出栈元素，则为找到下一个比他要大的数，根据下标即可得出数组对应位置值
     * 执行用时： 16 ms , 在所有 Java 提交中击败了 84.68% 的用户
     * 内存消耗： 46.8 MB , 在所有 Java 提交中击败了 37.24% 的用户
     * */
    public int[] dailyTemperatures2(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < T.length; i++){
            int tem = T[i];
            while(!stack.isEmpty() && tem > T[stack.peek()]){
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.add(i);
        }
        return ans;
    }

    /**
     * 单调栈  [此处为单调狄递减栈]     (双向队列实现)
     * */
    public int[] dailyTemperatures3(int[] T) {
        int[] ans = new int[T.length];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < T.length; i++) {
            int temperature = T[i];
            // 如果栈顶元素要小于当前温度，则今天则为栈顶日期的升温日。
            while (!deque.isEmpty() && temperature > T[deque.peekFirst()]){
                int prevIndex = deque.pollFirst();
                ans[prevIndex] = i - prevIndex;
            }
            deque.push(i);;
        }
        return ans;
    }

}
