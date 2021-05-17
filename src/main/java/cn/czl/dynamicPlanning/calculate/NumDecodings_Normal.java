package cn.czl.dynamicPlanning.calculate;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/4/21 9:29
 * @description:
 *      91. 解码方法
 *      - 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *          'A' -> 1
 *          'B' -> 2
 *          ...
 *          'Z' -> 26
 *      - 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。
 *          例如，"11106" 可以映射为：
 *           "AAJF" ，将消息分组为 (1 1 10 6)
 *           "KJF" ，将消息分组为 (11 10 6)
 *      - 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，
 *          这是由于 "6" 和 "06" 在映射中并不等价。
 *      - 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 *      - 题目数据保证答案肯定是一个 32 位 的整数。
 *      示例 1：
 *          输入：s = "12"
 *          输出：2
 *          解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 *      示例 2：
 *          输入：s = "226"
 *          输出：3
 *          解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *      示例 3：
 *          输入：s = "0"
 *          输出：0
 *          解释：没有字符映射到以 0 开头的数字。
 *               含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 *               由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 *      示例 4：
 *          输入：s = "06"
 *          输出：0
 *          解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
 *      提示：
 *          1 <= s.length <= 100
 *          s 只包含数字，并且可能包含前导零。
 */
public class NumDecodings_Normal {

    private String S1 = "12";
    private String S2 = "226";
    private String S3 = "2101";
    private String S4 = "06";

    @Test
    public void TestSolution(){
        System.out.println(numDecodings2(S1));
        System.out.println(numDecodings2(S2));
        System.out.println(numDecodings2(S3));
        System.out.println(numDecodings2(S4));
    }

    /**
     * 动态规划 - 基于前面的递推结果，得出当前结果
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.6 MB , 在所有 Java 提交中击败了 66.79% 的用户
     * */
    public int numDecodings(String s) {
        char[] chs = s.toCharArray();
        int len = s.length();
        if (len == 0 || chs[0] == '0'){
            return 0;
        }
        int[] dp = new int[len + 1];
        dp[0] = 1;
        char cur, pre = ' ';
        for (int i = 0; i < len; i++) {
            cur = chs[i];
            if(cur != '0'){     // 使用一个字符进行解码[0不可单独解码]
                dp[i + 1] += dp[i];
            }
            if(i != 0 && pre != '0'     // 使用两个字符进行解码
                    && ((pre - '0') * 10 + (cur - '0') <= 26)){
                dp[i + 1] += dp[i - 1];
            }
            pre = cur;
        }
        return dp[len];
    }

    /**
     * 动态规划
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.9 MB , 在所有 Java 提交中击败了 24.72% 的用户
     * */
    public int numDecodings2(String s) {
        int len = s.length();
        char[] chs = s.toCharArray();
        if (len == 0 || chs[0] == '0') {
            return 0;
        }
        int[] dp = new int[len + 1];
        dp[0] = dp[1] = 1;
        char cur, pre = chs[0];
        for (int i = 1; i < len; i++) {
            cur = chs[i];
            if(cur == '0'){ // 如果当前位为0，则前面为1或2才可组成数字
                if(pre == '1' || pre == '2'){
                    dp[i + 1] = dp[i - 1];  // 此时，pre位必须与0组合才有解，因此dp[i + 1] = dp[i - 1]
                }else{
                    return 0;
                }   // 检查当前数是否可与前一位组成两位数
            }else if (pre == '1' || (pre == '2' && cur >= '1' && cur <= '6')){
                dp[i + 1] = dp[i] + dp[i - 1];
            }else{
                dp[i + 1] = dp[i];
            }
            pre = cur;
        }
        return dp[len];
    }
}
