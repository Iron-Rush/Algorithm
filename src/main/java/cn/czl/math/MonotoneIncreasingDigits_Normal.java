package cn.czl.math;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/12/15 9:41
 * @description:
 *      738. 单调递增的数字
 *      - 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *      -（当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *      示例 1:
 *          输入: N = 10
 *          输出: 9
 *      示例 2:
 *          输入: N = 1234
 *          输出: 1234
 *      示例 3:
 *          输入: N = 332
 *          输出: 299
 *      - 说明: N 是在 [0, 10^9] 范围内的一个整数。
 */
public class MonotoneIncreasingDigits_Normal {

    @Test
    public void TestSolution(){
        System.out.println(monotoneIncreasingDigits3(3344321));   // 3339999
        System.out.println(monotoneIncreasingDigits3(1234));
    }

    /**
     * 从左向右找到第一个不符合增长规律的数，向左跳过相同的数字
     * 该位-1，该位右侧全部置9
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.96% 的用户
     * 内存消耗： 35.5 MB , 在所有 Java 提交中击败了 53.21% 的用户
     * */
    public int monotoneIncreasingDigits(int N) {
        char[] numArr = Integer.toString(N).toCharArray();
        if(numArr.length <= 1){
            return N;
        }
        int index = 1;
        while(index < numArr.length && numArr[index] >= numArr[index-1]){
            index++;
        }
        if(index == numArr.length){
            return N;
        }
        index--;
        while (index >= 1 && numArr[index-1] == numArr[index]){
            index--;
        }
        numArr[index++]--;
        while (index < numArr.length){
            numArr[index++] = '9';
        }
        return Integer.parseInt(new String(numArr));
    }

    /**
     * 从右向左寻找第一个不符合递减规律的数，
     * 该位-1.其右侧全部置9
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.96% 的用户
     * 内存消耗： 35.3 MB , 在所有 Java 提交中击败了 75.58% 的用户
     * */
    public int monotoneIncreasingDigits2(int N) {
        char[] numArr = Integer.toString(N).toCharArray();
        int len = numArr.length, flag = len;
        for (int i = len - 1; i >= 1; i++) {
            if(numArr[i] < numArr[i-1]){
                numArr[i - 1]--;
                flag = i;
            }
        }
        for (int i = flag; i < len; i++) {
            numArr[i] = '9';
        }
        return Integer.parseInt(new String(numArr));
    }

    /**
     * 从大到小，模拟比对-构建数字
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.3 MB , 在所有 Java 提交中击败了 75.06% 的用户
     * */
    public int monotoneIncreasingDigits3(int N) {
        int result = 0;
        int ones = 111111111;
        for (int i = 0; i < 9; i++) {
            while (result + ones > N){
                ones /= 10;
            }
            result += ones;
        }
        return result;
    }

}
