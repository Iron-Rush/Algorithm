package cn.czl.study.recursion;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/3/19 13:17
 * @description:
 *      344. 反转字符串
 *      - 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *      - 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *      - 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *      示例 1：
 *          输入：["h","e","l","l","o"]
 *          输出：["o","l","l","e","h"]
 *      示例 2：
 *          输入：["H","a","n","n","a","h"]
 *          输出：["h","a","n","n","a","H"]
 */
public class ReverseString_Easy {

    private String S1 = "hello";
    private String S2 = "Hannah";

    @Test
    public void TestSolution(){
        char[] chars1 = S1.toCharArray();
        char[] chars2 = S2.toCharArray();
        reverseString(chars1);
        System.out.println(Arrays.toString(chars1));
    }

    /**
     * 双指针
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 45 MB , 在所有 Java 提交中击败了 75.66% 的用户
     * */
    public void reverseString(char[] s) {
        int l = 0, r= s.length - 1;
        while (l < r){
            swap(s, l++, r--);
        }
    }

    /**
     * 递归
     * */
    public void reverseString2(char[] s) {
        helper(s, 0);
    }
    // 递归帮助类
    void helper(char[] chs, int l){
        if(l < chs.length/2){
            swap(chs, l, chs.length - l - 1);
            helper(chs, l + 1);
        }
    }

    // 交换char[]中的两个字符
    void swap(char[] chs, int l, int r){
        char temp = chs[l];
        chs[l] = chs[r];
        chs[r] = temp;
    }
}
