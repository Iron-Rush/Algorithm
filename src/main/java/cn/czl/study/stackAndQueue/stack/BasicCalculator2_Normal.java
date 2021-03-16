package cn.czl.study.stackAndQueue.stack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2021/3/11 10:48
 * @description:
 *      227. 基本计算器 II
 *      - 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *      - 整数除法仅保留整数部分。
 *      示例 1：
 *          输入：s = "3+2*2"
 *          输出：7
 *      示例 2：
 *          输入：s = " 3/2 "
 *          输出：1
 *      示例 3：
 *          输入：s = " 3+5 / 2 "
 *          输出：5
 *      提示：
 *          1 <= s.length <= 3 * 105
 *          s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 *          s 表示一个 有效表达式
 *          表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
 *          题目数据保证答案是一个 32-bit 整数
 */
public class BasicCalculator2_Normal {

    private String S1 = "3+2*2";        // 7
    private String S2 = "3/2 ";         // 1
    private String S3 = " 3+5 / 2 ";    // 5

    @Test
    public void TestSolution(){
        System.out.println(calculate(S1));
//        System.out.println(calculate(S2));
//        System.out.println(calculate(S3));
    }

    /**
     * 借助栈存储之前的数字
     * 入栈时，仅计算 乘法/除法，剩余 + / - 仅以正负的形式存储于栈中
     * 遇见乘法/除法时，用当前数与栈顶元素进行计算，更新栈顶元素
     * 最后累加栈中全部元素
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 93.02% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 83.06% 的用户
     * */
    public int calculate(String s) {
        char[] chs = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        int num = 0;
        int size = s.length();
        char sign = '+';
        for (int i = 0; i < size; i++) {
            char ch = chs[i];
            if(ch >= '0') {      // 读取数字
                num = num * 10 + ch - '0';
            }
            // 当前位为符号/当前位为最后一位时，根据之前的符号sign，对当前num进行计算
            if((ch < '0' && ch != ' ') || i == size - 1){
                switch (sign){
                    case '+':   // 加减法，以正负的形式存入栈
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':   // 乘除法根据栈顶元素进行计算，并更新栈顶元素
                        int temp1 = stack.pop() * num;
                        stack.push(temp1);
                        break;
                    case '/':
                        int temp2 = stack.pop() / num;
                        stack.push(temp2);
                }
                sign = ch;
                num = 0;
            }
        }
        while (!stack.isEmpty()){   // 对栈中全部数字进行累加(加减法运算)
            res += stack.pop();
        }
        return res;
    }
}
