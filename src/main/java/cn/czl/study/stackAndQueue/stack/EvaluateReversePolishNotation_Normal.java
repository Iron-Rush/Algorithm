package cn.czl.study.stackAndQueue.stack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/12/19 23:51
 * @description:
 *      150. 逆波兰表达式求值
 *      - 根据 逆波兰表示法，求表达式的值。
 *      - 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *      说明：
 *          - 整数除法只保留整数部分。
 *          - 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *      示例 1：
 *          输入: ["2", "1", "+", "3", "*"]
 *          输出: 9
 *          解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 *      示例 2：
 *          输入: ["4", "13", "5", "/", "+"]
 *          输出: 6
 *          解释: 该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 *      示例 3：
 *          输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 *          输出: 22
 *          解释:
 *          该算式转化为常见的中缀算术表达式为：
 *            ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 *          = ((10 * (6 / (12 * -11))) + 17) + 5
 *          = ((10 * (6 / -132)) + 17) + 5
 *          = ((10 * 0) + 17) + 5
 *          = (0 + 17) + 5
 *          = 17 + 5
 *          = 22
 *      逆波兰表达式：
 *      逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
 *          - 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
 *          - 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
 *      逆波兰表达式主要有以下两个优点：
 *          - 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
 *          - 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
 */
public class EvaluateReversePolishNotation_Normal {

    private String[] TOKENS1 = {"2", "1", "+", "3", "*"};
    private String[] TOKENS2 = {"4", "13", "5", "/", "+"};
    private String[] TOKENS3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

    @Test
    public void TestSolution(){
        System.out.println(9 == evalRPN(TOKENS1));
        System.out.println(6 == evalRPN(TOKENS2));
        System.out.println(22 == evalRPN(TOKENS3));
    }

    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 88.45% 的用户
     * 内存消耗： 37.8 MB , 在所有 Java 提交中击败了 97.29% 的用户
     * */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            int flag = isOperator(s);
            if(flag == 0){
                stack.push(Integer.parseInt(s));
            }else {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(calculator(b, a, flag));
            }
        }
        return stack.pop();
    }
    int isOperator(String target){
        switch (target){
            case "+":
                return 1;
            case "-":
                return -1;
            case "*":
                return 2;
            case "/":
                return -2;
            default:
                return 0;
        }
    }
    int calculator(int a, int b, int flag){
        switch (flag){
            case 1:
                return a + b;
            case -1:
                return a - b;
            case 2:
                return a * b;
            case -2:
                return a / b;
            default:
                return 0;
        }
    }
}
