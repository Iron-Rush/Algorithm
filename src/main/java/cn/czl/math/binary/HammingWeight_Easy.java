package cn.czl.math.binary;

/**
 * @author RedRush
 * @date 2020/12/10 15:40
 * @description:
 *      191. 位1的个数
 *      - 编写一个函数，输入是一个无符号整数（以二进制串的形式），
 *      返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 *      示例：
 *          输入：00000000000000000000000000001011
 *          输出：3
 *          解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 */
public class HammingWeight_Easy {
    /**
     * `>>>`为无符号右移(高位补0，低位移除)
     * 执行用时：1 ms, 在所有 Java 提交中击败了96.91%的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了69.56%的用户
     * */
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            if(n % 2 != 0){
                count++;
            }
            n >>>= 1;
        }
        return count;
    }

    /**
     * 设置mask=1，通过将mask不断左移，并与n做`&`操作，判断出每位上是否为1
     * 执行用时：1 ms, 在所有 Java 提交中击败了96.91%的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了76.76%的用户
     * */
    public int hammingWeight2(int n) {
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if((n & mask) != 0){
                count++;
            }
            mask <<= 1;
        }
        return count;
    }

    /**
     * 位运算技巧[n和n-1做`&`运算，总是能把n中的最低位1变成0，其他位保持不变]
     * 执行用时：1 ms, 在所有 Java 提交中击败了96.91%的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了65.89%的用户
     * */
    public int hammingWeight3(int n){
        int sum = 0;
        while (n != 0){
            sum++;
            n &= (n-1);
        }
        return sum;
    }
    /**
     * 直接使用java内部提供的工具
     * */
    public int hammingWeight4(int n){
        return Integer.bitCount(n);
    }

}
