package cn.czl.string.operate;

/**
 * @author RedRush
 * @date 2020/8/30 13:50
 * @description: 反转字符串3
 */

import org.junit.jupiter.api.Test;

/**
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 *       s'teL ekat edoCteeL tsetno
 * */
public class ReverseWords3_Easy {

    private static String STR = "Let's take LeetCode contest";

    @Test
    public void TestReverseSoulution(){
        System.out.println(reverseWords2(STR));
    }

    /**
     * split分割字符串，局部字符串采用StringBuilder的reverse方法反转
     * 手动补齐缺少的" "，并手动删除最后多余的" "
     * 执行用时：6 ms, 在所有 Java 提交中击败了75.46%的用户
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了11.76%的用户
     * */
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] strList = s.split(" ");
        for (String strTemp : strList) {
            StringBuilder stringBuilderTemp = new StringBuilder(strTemp);
            stringBuilderTemp.reverse();
            sb.append(stringBuilderTemp);
            sb.append(" ");
        }
        sb.deleteCharAt(s.length());
        return sb.toString();
    }

    public String reverseWords2(String s){
        StringBuilder sb = new StringBuilder();
        StringBuilder sbTemp = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' '){
                sb.append(sbTemp.reverse());
                sb.append(' ');
                sbTemp.delete(0,sbTemp.length());
            }else {
                sbTemp.append(s.charAt(i));
            }
        }
        sb.append(sbTemp.reverse());
        return sb.toString();
    }

}
