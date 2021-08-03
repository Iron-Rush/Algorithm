package cn.czl.math;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/6/30 9:28
 * @description:
 *      168. Excel表列名称
 *      - 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 *      - 例如：
 *          A -> 1
 *          B -> 2
 *          C -> 3
 *          Z -> 26
 *          AA -> 27
 *          AB -> 28
 *      示例 1：
 *          输入：columnNumber = 1
 *          输出："A"
 *      示例 2：
 *          输入：columnNumber = 28
 *          输出："AB"
 *      示例 3：
 *          输入：columnNumber = 701
 *          输出："ZY"
 *      示例 4：
 *          输入：columnNumber = 2147483647
 *          输出："FXSHRXW"
 *      提示：
 *          1 <= columnNumber <= 2^31 - 1
 */
public class ConvertToTitle_Easy {

    @Test
    public void TestSolution(){
        System.out.println(convertToTitle(27));
        System.out.println(convertToTitle(28));
        System.out.println(convertToTitle(701));
        System.out.println(convertToTitle(2147483647));

        System.out.println(convertToTitle2(27));
        System.out.println(convertToTitle2(28));
        System.out.println(convertToTitle2(701));
        System.out.println(convertToTitle2(2147483647));
    }

    /**
     * 递归解决
     * 1 - 26的进制转换，传统进制问题从零开始
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 9.28% 的用户
     * 内存消耗： 36.3 MB , 在所有 Java 提交中击败了 9.82% 的用户
     * */
    public String convertToTitle(int columnNumber) {
        String res = "";
        columnNumber--;
        if(columnNumber < 0){
            return "";
        }
        if(columnNumber >= 26){
            res = convertToTitle(columnNumber/26) + res;
        }
        res += (char)(65 + columnNumber % 26 );
        return res;
    }


    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.5 MB , 在所有 Java 提交中击败了 66.34% 的用户
     * */
    public String convertToTitle2(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber != 0){
            columnNumber--;
            sb.append((char)(columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }
}
