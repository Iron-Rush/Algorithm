package cn.czl.study.stackAndQueue.stack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2021/1/28 11:02
 * @description:
 *      394. 字符串解码
 *      - 给定一个经过编码的字符串，返回它解码后的字符串。
 *      - 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *      - 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *      - 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *      示例 1：
 *          输入：s = "3[a]2[bc]"
 *          输出："aaabcbc"
 *      示例 2：
 *          输入：s = "3[a2[c]]"
 *          输出："accaccacc"
 *      示例 3：
 *          输入：s = "2[abc]3[cd]ef"
 *          输出："abcabccdcdcdef"
 *      示例 4：
 *          输入：s = "abc3[cd]xyz"
 *          输出："abccdcdcdxyz"
 */
public class DecodeString_Normal {

    private String S1 = "3[a]2[bc]";
    private String S2 = "3[a2[c]]";
    private String S3 = "2[abc]3[cd]ef";
    private String S4 = "abc3[cd]xyz";

    @Test
    public void TestSolution(){
        System.out.println(decodeString(S1));
        System.out.println(decodeString(S2));
        System.out.println(decodeString(S3));
        System.out.println(decodeString(S4));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 87.59% 的用户
     * 内存消耗： 36.7 MB , 在所有 Java 提交中击败了 38.57% 的用户
     * */
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> wordStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for(char ch : s.toCharArray()){
            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }else if(ch == '['){
                numStack.add(num);
                num = 0;
                wordStack.add(sb);
                sb = new StringBuilder();
            }else if(Character.isAlphabetic(ch)){
                sb.append(ch);
            }else {
                StringBuilder temp = wordStack.pop();
                int count = numStack.pop();
                for (int i = 0; i < count; i++) {
                    temp.append(sb);
                }
                sb = temp;
            }
        }
        return sb.toString();
    }
}
