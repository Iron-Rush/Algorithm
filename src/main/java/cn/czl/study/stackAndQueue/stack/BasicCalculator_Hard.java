package cn.czl.study.stackAndQueue.stack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2021/3/10 14:02
 * @description:
 *      224. 基本计算器
 *      - 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 *      示例 1：
 *          输入：s = "1 + 1"
 *          输出：2
 *      示例 2：
 *          输入：s = " 2-1 + 2 "
 *          输出：3
 *      示例 3：
 *          输入：s = "(1+(4+5+2)-3)+(6+8)"
 *          输出：23
 *      提示：
 *       1 <= s.length <= 3 * 105
 *       s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 *       s 表示一个有效的表达式
 */
public class BasicCalculator_Hard {

    private String S1 = "1 + 1";
    private String S2 = " 2-1 + 2 ";
    private String S3 = "(1+(4+5+2)-3)+(6+8)";

    @Test
    public void TestSolution(){
        System.out.println(calculate(S1));
    }

    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 83.16% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 70.82% 的用户
     * */
    public int calculate(String s) {
        char[] chs = s.toCharArray();
        int res = 0, flag = 1;  // flag为当前运算符
        int i = 0, len = s.length();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        while (i < len){
            char ch = chs[i];
            switch (ch){
                case ' ':
                    i++;
                    break;
                case '+':
                    flag = stack.peek();
                    i++;
                    break;
                case '-':
                    flag = -stack.peek();
                    i++;
                    break;
                case '(':
                    stack.push(flag);
                    i++;
                    break;
                case ')':
                    stack.pop();
                    i++;
                    break;
                default:
                    long num = 0;
                    while (i < len && Character.isDigit(chs[i])){
                        num = num * 10 + chs[i] - '0';
                        i++;
                    }
                    res += flag * num;
            }
        }
        return res;
    }

}
