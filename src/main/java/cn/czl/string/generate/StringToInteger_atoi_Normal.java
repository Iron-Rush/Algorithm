package cn.czl.string.generate;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RedRush
 * @date 2020/11/27 9:52
 * @description:
 *      8. 字符串转换整数 (atoi)
 *      - 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 *      - 首先，该函数会根据需要丢弃无用的开头空格字符，
 *      直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 *          - 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的
 *          连续数字字符组合起来，形成一个有符号整数
 *          - 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 *          - 该字符串在有效的整数部分之后也可能会存在多余的字符，
 *          那么这些字符可以被忽略，它们对函数不应该造成影响。
 *      - 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 *      - 如果数值超过这个范围，请返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。
 */
public class StringToInteger_atoi_Normal {

    private String S1 = "-42";
    private String S2 = "  -4193 with words";
    private String S3 = "words and 987";
    private String S4 = "-91283472332";

    @Test
    public void TestSoltion(){
        System.out.println(myAtoi(S2));
    }

    /**
     * 从头挨个字符匹配，过滤
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.72%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了77.45%的用户
     * */
    public int myAtoi(String s) {
        if(s.length() == 0){
            return 0;
        }
        int result = 0;
        char[] chars = s.toCharArray();
        boolean flag = true;    // true 为正数，false 为负数
        int pos = 0;
        while (chars[pos] == ' ' && pos < chars.length-1){
            pos++;
        }
        if (chars[pos] == '-' || chars[pos] == '+'){
            flag = chars[pos] == '+';
            pos++;
        }
        while (pos < s.length() && chars[pos] >= '0' && chars[pos] <= '9'){
            int compare = result;
            int n = chars[pos] - '0';
            result = result * 10 + n;
            pos++;
            if (compare != result/10){
                return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
        }
        if (!flag){
            result *= -1;
        }
        return result;
    }


    /**
     * Atoi，自动机
     * */
    public int myAtoi2(String s) {
        Automaton automaton = new Automaton();
        for (int i = 0; i < s.length(); i++) {
            automaton.get(s.charAt(i));
        }
        return (int)(automaton.sign * automaton.ans);
    }
}

class Automaton{
    public int sign = 1;
    public long ans = 0;
    private String state = "start";
    private Map<String, String[]> table = new HashMap<String, String[]>() {{
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
    }};

    public void get(char c){
        state = table.get(state)[get_col(c)];
        if ("in_number".equals(state)){
            ans = ans * 10 + c - '0';
            ans = sign == 1 ? Math.min(ans, (long)Integer.MAX_VALUE) : Math.min(ans, -(long)Integer.MIN_VALUE);
        }else if ("signed".equals(state)){
            sign = c == '+' ? 1 : -1;
        }
    }

    private int get_col(char c){
        if (Character.isDigit(c)){
            return 2;
        }
        switch (c){
            case ' ':
                return 0;
            case '+':
            case '-':
                return 1;
            default:
                return 3;
        }
    }

}
