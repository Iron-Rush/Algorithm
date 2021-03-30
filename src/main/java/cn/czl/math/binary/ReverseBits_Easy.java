package cn.czl.math.binary;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/12/10 17:41
 * @description:
 *      190. 颠倒二进制位
 *      - 颠倒给定的 32 位无符号整数的二进制位。
 *      示例 1：
 *          输入: 00000010100101000001111010011100
 *          输出: 00111001011110000010100101000000
 *      示例 2：
 *          输入：11111111111111111111111111111101
 *          输出：10111111111111111111111111111111
 */
public class ReverseBits_Easy {

    /**
     * Java自带reverse函数，直接调用颠倒二进制位
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了61.83%的用户
     * */
    public int reverseBits(int n) {
        return Integer.reverse(n);
    }

    /**
     * 逐位颠倒 (n & 1 为获取n的最右侧数字)
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 30.92% 的用户
     * 炫耀一下:
     * */
    public int reverseBits2(int n) {
        int result = 0;
        for (int power = 31; power >= 0 ; power--) {
            result += (n & 1) << power;
            n >>= 1;
        }
        return result;
    }

    public int reverseBits3(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result += n & 1;
            n >>= 1;
        }
        return result;
    }


    @Test
    public void TestSolution(){
        int a = 125, b = 1;
        System.out.println(Integer.toBinaryString(a));         // 1111101
        System.out.println(Integer.toBinaryString(a & b));  // 1
        System.out.println(Integer.toBinaryString(a | b));  // 1111101
        System.out.println(Integer.toBinaryString(a ^ b));  // 1111100
        System.out.println(Integer.toBinaryString(~a)); // 11111111111111111111111110000010
        System.out.println(Integer.toBinaryString(0x55555555));
        System.out.println(Integer.toBinaryString(0x33333333));
        System.out.println(Integer.toBinaryString(0x0f0f0f0f));
        System.out.println(Integer.toBinaryString(0xff00));
    }
}
