package cn.czl.study.stackAndQueue.stack;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/12/16 11:16
 * @description:
 *      20. 有效的括号
 *      - 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *      - 有效字符串需满足：
 *          1. 左括号必须用相同类型的右括号闭合。
 *          2. 左括号必须以正确的顺序闭合。
 *      - 注意空字符串可被认为是有效字符串。
 */
public class ValidParentheses_Easy {

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.15% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 86.39% 的用户
     * */
    public boolean isValid(String s) {
        if(s.length() % 2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for(char ch : s.toCharArray()){
            switch (ch){
                case '(':
                    stack.push(')');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '{':
                    stack.push('}');
                    break;
                default:
                    if(stack.isEmpty() || ch != stack.pop()){
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }
}
