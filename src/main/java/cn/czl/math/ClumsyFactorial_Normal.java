package cn.czl.math;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2021/4/1 10:30
 * @description:
 *      1006. 笨阶乘
 *      - 通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。
 *        例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
 *      - 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，
 *        我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：
 *        乘法(*)，除法(/)，加法(+)和减法(-)。
 *      - 例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。
 *        然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前
 *        执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
 *      - 另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。
 *        这保证结果是一个整数。
 *      - 实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
 *      示例 1：
 *          输入：4
 *          输出：7
 *          解释：7 = 4 * 3 / 2 + 1
 *      示例 2：
 *          输入：10
 *          输出：12
 *          解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
 *      提示：
 *          1 <= N <= 10000
 *          -2^31 <= answer <= 2^31 - 1  （答案保证符合 32 位整数。）
 */
public class ClumsyFactorial_Normal {

    @Test
    public void TestSolution(){
        System.out.println(clumsy(10));
        System.out.println(clumsy(4));
    }

    @Test
    public void TestMethod(){
        System.out.println(getNextFour(4, true));
    }

    /**
     * 每四位数做一次计算，用首次计算的结果，减去后面每次计算的结果。
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 47.10% 的用户
     * 内存消耗： 35.3 MB , 在所有 Java 提交中击败了 57.42% 的用户
     * */
    public int clumsy2(int N) {
        int res = getNextFour(N, true);
        N -= 4;
        while (N > 0){
            res -= getNextFour(N, false);
            N -= 4;
        }
        return res;
    }
    // 计算后面四个数的结果。flag 为 true 时，为首次调用，最后加法不做逆转处理; 为 false 时，加法做逆转处理
    int getNextFour(int N, boolean flag){
        int compare = N < 4 ? 0 : N - 4;    // 计算出本次循环结束位置
        int res = N--;  // 以第一个数为基础做计算。
        int idx = 1;    // 运算符标记
        while (N > compare){
            switch (idx++){
                case 1:
                    res *= N--;
                    break;
                case 2:
                    res /= N--;
                    break;
                case 3:
                    if(flag){   // 当前式子前面运算符是否为 正
                        res += N--;
                    }else{      // 如果为负，对本次运算提前取反
                        res -= N--;
                    }
            }
        }
        return res;
    }

    /**
     * 四个一组计算， 最后问题的尾巴递归即可
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.1 MB , 在所有 Java 提交中击败了 87.74% 的用户
     * */
    public int clumsy(int N) {
        if(N<=2)return N;
        if(N==3)return 6;
        int sum=N*(N-1)/(N-2)+N-3;
        N-=4;
        while(N>=4){
            sum+=(-N*(N-1)/(N-2)+N-3);
            N-=4;
        }
        return sum-clumsy(N);
    }

    /**
     * 使用栈模拟
     * 执行用时： 16 ms , 在所有 Java 提交中击败了 7.74% 的用户
     * 内存消耗： 37.2 MB , 在所有 Java 提交中击败了 21.29% 的用户
     * */
    public int clumsy3(int N) {
        Stack<Integer> stack = new Stack<>();
        stack.push(N--);
        int index = 0;  // 运算标识符
        while(N > 0){   // 将 乘/除 法优先计算，剩余数字入栈
            switch (index % 4){
                case 0:
                    stack.push(stack.pop() * N);
                    break;
                case 1:
                    stack.push(stack.pop() / N);
                    break;
                case 2:
                    stack.push(N);
                    break;
                case 3:
                    stack.push(-N);
            }
            index++;
            N--;
        }
        // 将栈中元素弹出，求和
        int sum = 0;
        while (!stack.isEmpty()){
            sum += stack.pop();
        }
        return sum;
    }

    /**
     * 数学
     * 当 N ≤ 4 时，可以分别单独计算「笨阶乘」；
     * 当 N > 4 时，可以根据 N 对 4 取模的余数进行分类讨论。
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.3 MB , 在所有 Java 提交中击败了 58.06% 的用户
     * */
    public int clumsy4(int N) {
        if(N == 1){
            return 1;
        }else if(N == 2){
            return 2;
        }else if(N == 3){
            return 6;
        }else if(N == 4){
            return 7;
        }

        if(N % 4 == 0){
            return  N + 1;
        }else if(N % 4 <= 2){
            return N + 2;
        }else {
            return N - 1;
        }
    }

}
