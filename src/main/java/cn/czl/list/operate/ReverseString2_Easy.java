package cn.czl.list.operate;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/8/20 13:47
 * @description:
 *      541. 反转字符串 II
 *      - 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
 *          - 如果剩余字符少于 k 个，则将剩余字符全部反转。
 *          - 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *      示例 1：
 *          输入：s = "abcdefg", k = 2
 *          输出："bacdfeg"
 *      示例 2：
 *          输入：s = "abcd", k = 2
 *          输出："bacd"
 *      提示：
 *          1 <= s.length <= 10^4
 *          s 仅由小写英文组成
 *          1 <= k <= 10^4
 */
public class ReverseString2_Easy {

    private String S1 = "abcdefg";
    private String S2 = "abcd";

    @Test
    public void TestSolution(){
        System.out.println(reverseStr(S1, 2));
        System.out.println(reverseStr(S1, 3));
        System.out.println(reverseStr(S2, 2));
    }

    @Test
    public void TestMethod(){
        char[] arr = S1.toCharArray();
        System.out.println(Arrays.toString(arr));
        reverseArr(arr, 0, 4);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 双指针 + 模拟
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 60.35% 的用户
     * */
    public String reverseStr(String s, int k) {
        if (s == null || s.length() < 2 || k <= 1){
            return s;
        }
        char[] chs = s.toCharArray();
        k--;
        int start = 0, len = s.length();
        int end = Math.min(len-1, start + k);
        boolean flag = true;
        while (start < len){
            if (flag){
                reverseArr(chs, start, end);
            }
            flag = !flag;
            start = end + 1;
            end = Math.min(len-1, start + k);
        }
        return new String(chs);
    }

    /**
     * 模拟
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38 MB , 在所有 Java 提交中击败了 98.79% 的用户
     * */
    public String reverseStr2(String s, int k) {
        if (s == null || s.length() < 2 || k <= 1) {
            return s;
        }
        char[] chs = s.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i += 2 * k) {
            reverseArr(chs, i, Math.min(i + k, len) - 1);
        }
        return new String(chs);
    }

    // 翻转数组区间内的元素
    private void reverseArr(char[] arr, int a, int b){
        while (a < b){
            char temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
            a++;
            b--;
        }
    }
}
