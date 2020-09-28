package cn.czl.search.str;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/9/28 9:27
 * @description:
 *          20. 有效的括号
 *              给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *              有效字符串需满足：
 *                  1. 左括号必须用相同类型的右括号闭合。
 *                  2. 左括号必须以正确的顺序闭合。
 *                  3. 注意空字符串可被认为是有效字符串。
 */
public class ParenthesesIsValid_Easy {

    private static String str = "()[]{}";
    private static String str1 = "([)]";
    private static String str2 = "()[]{}";

    @Test
    public void TestSoltion(){
        System.out.println(isValid3(str2));
    }

    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了10.35%的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了27.20%的用户
     * */
    public boolean isValid(String s) {
        int flag = 0;
        HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
        dict.put('(', 1);
        dict.put(')', -1);
        dict.put('[', 2);
        dict.put(']', -2);
        dict.put('{', 3);
        dict.put('}', -3);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            int cur = dict.get(ch);
            flag += cur;
            if (flag >= 0){
                if (cur > 0){
                    stack.push(cur);
                }else {
                    int pre = stack.pop();
                    if (pre + cur != 0){
                        return false;
                    }
                }
            }else {
                return false;
            }
        }
        return flag == 0;
    }

    /**
     * 栈中仅存入左括号，否则检查栈是否为空：若为空说明该符号前-无左括号，返回fasle;
     *    若不为空则出栈，根据map获取出栈元素的闭合括号与当前符号是否一致，一致则可闭合，继续判断。
     * 执行用时：2 ms, 在所有 Java 提交中击败了78.05%的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了18.53%的用户
     * */
    public boolean isValid2(String s) {
        if (s.length() % 2 == 1){
            return false;
        }
        HashMap<Character, Character> dict = new HashMap<Character, Character>();
        dict.put('(', ')');
        dict.put('[', ']');
        dict.put('{', '}');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (dict.containsKey(ch)){
                stack.push(ch);
            }else if (!stack.isEmpty()){
                Character chPre = stack.pop();
                if (dict.get(chPre) != ch){
                    return false;
                }
            }else {
                return false;
            }
        }
        if (stack.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 将判断直接写死在逻辑中遇见对应左括号，入栈对应右括号。
     * 减少非必要判断，最后若栈为空则括号刚好对应上。
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.76%的用户
     * 内存消耗：36.8 MB, 在所有 Java 提交中击败了67.45%的用户
     * */
    public boolean isValid3(String s){
        if (s.length() % 2 == 1){
            return false;
        }
        Stack<Character> stack = new Stack<>();
//        LinkedList<Character> stack = new LinkedList<>();
        for (char ch : s.toCharArray()) {
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
                    if (stack.isEmpty() || ch != stack.pop()){
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }

}
