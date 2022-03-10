package cn.czl.math;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2022/3/10 9:56
 * @description:
 *      504. 七进制数
 *      - 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 *      示例 1:
 *          输入: num = 100
 *          输出: "202"
 *      示例 2:
 *          输入: num = -7
 *          输出: "-10"
 *      提示：-10^7 <= num <= 10^7
 */
public class ConvertToBase7_Easy {

    @Test
    public void TestSolution(){
        System.out.println(convertToBase7(888));
    }

    /**
     * 逐位计算，翻转
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 77.41% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 36.98% 的用户
     * */
    public String convertToBase7(int num) {
        if(num == 0)    return "0";
        // 符号flag,true为负数, false为正数
        boolean flag = num < 0;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while (num > 0){
            sb.append(num % 7);
            num /= 7;
        }
        if(flag){
            sb.append("-");
        }
        return sb.reverse().toString();
    }

    /**
     * 借助函数库
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39 MB , 在所有 Java 提交中击败了 27.16% 的用户
     * */
    public String convertToBase7_2(int num){
        return Integer.toString(num, 7);
    }
}
