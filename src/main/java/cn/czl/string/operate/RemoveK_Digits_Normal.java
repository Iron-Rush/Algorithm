package cn.czl.string.operate;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author RedRush
 * @date 2020/11/15 10:43
 * @description:
 *      402. 移掉K位数字
 *      - 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *      - num 的长度小于 10002 且 ≥ k。
 *      - num 不会包含任何前导零。
 *      示例1：
 *          输入: num = "1432219", k = 3       输出: "1219"
 *          解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 *      示例2：
 *          输入: num = "10200", k = 1         输出: "200"
 *          解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 *      示例3：
 *          输入: num = "10", k = 2            输出: "0"
 *          解释: 从原数字移除所有的数字，剩余为空就是0。
 */
public class RemoveK_Digits_Normal {

    private String NUM1 = "1432219";    // 1219
    private int K1 = 3;
    private String NUM2 = "10200";      // 200
    private int K2 = 1;
    private String NUM3 = "1002";         // 0
    private int K3 = 1;

    @Test
    public void TestSoltion(){
        System.out.println(removeKdigits2(NUM1, K1));
    }

    /**
     * 从左到右 k次扫描，找到第一个比后面大的字符，删除，清除左侧0。
     * 执行用时：5 ms, 在所有 Java 提交中击败了94.01%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了66.34%的用户
     * */
    public String removeKdigits(String num, int k) {
        if(num.length() <= k){
            return "0";
        }
        StringBuilder sb = new StringBuilder(num);
        // k次扫描
        for (int i = 0; i < k; i++) {
            int index = 0;
            // 找到第一个比后面大的字符
            for (int j = 1; j < sb.length() && sb.charAt(j) >= sb.charAt(j - 1); j++) {
                index = j;
            }
            sb.delete(index, index + 1);    // 删除它
            // 去除左端的 0
            while (sb.length() > 1 && sb.charAt(0) == '0'){
                sb.delete(0, 1);
            }
        }
        return sb.toString();
    }


    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了74.97%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了71.82%的用户
     * */
    public String removeKdigits2(String num, int k) {
        if(num.length() <= k){
            return "0";
        }
        // 双端队列
        Deque<Character> deque = new LinkedList<>();
        for (char ch : num.toCharArray()) {
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > ch){
                deque.pollLast();
                k--;
            }
            deque.offerLast(ch);
        }
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }
        StringBuilder sb = new StringBuilder();
        boolean zeroFlag = true;
        while (!deque.isEmpty()){
            char ch = deque.pollFirst();
            while (zeroFlag && ch == '0' && !deque.isEmpty()){
                ch = deque.pollFirst();
            }
            zeroFlag = false;
            sb.append(ch);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

}
