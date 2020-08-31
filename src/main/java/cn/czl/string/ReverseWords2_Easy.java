package cn.czl.string;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/8/30 14:35
 * @description:
 *      给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
 * - 输入: s = "abcdefg", k = 2
 * - 输出: "bacdfeg"
 */
public class ReverseWords2_Easy {

    private static String STR = "abcdefg";

    @Test
    public void testSolution(){
        System.out.println(reverseStr(STR, 2));
    }

    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < s.length();) {
            StringBuilder sbTemp = new StringBuilder();
            int limit = i + k;
            for (int j = i; j < limit && j < s.length(); j++){
                sbTemp.append(s.charAt(j));
                flag = !flag;
                i = j;
            }
            if (flag){
                sb.append(sbTemp.reverse());
            }else {
                sb.append(sbTemp);
            }
        }
        return sb.toString();
    }

}