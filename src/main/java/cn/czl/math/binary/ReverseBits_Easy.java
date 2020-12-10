package cn.czl.math.binary;

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

    public int reverseBits(int n) {
        return Integer.reverse(n);
    }
}
