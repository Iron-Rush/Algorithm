package cn.czl.list.operate;

/**
 * @author RedRush
 * @date 2020/11/26 15:45
 * @description:
 *      7. 整数反转
 *      - 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *      示例 1:
 *          输入: 123
 *          输出: 321
 *      示例 2:
 *          输入: -123
 *          输出: -321
 *      示例 3:
 *          输入: 120
 *          输出: 21
 *      注意:
 *          假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。
 *          请根据这个假设，如果反转后整数溢出
 */
public class ReverseNumber_Easy {


    /**
     * 从末尾取余，拼接至结果数字末尾。需要注意每轮数字越界的判断。
     * 通过判断当前result是否处于越界范围判断，较为麻烦
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了86.42%的用户
     * */
    public int reverse(int x) {
        int result = 0;
        while (x != 0){
            int n = x % 10;
            x /= 10;
            if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && n > 7)){
                return 0;
            }
            if (result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE/10 && n < -8)){
                return 0;
            }
            result = result * 10 + n;
        }
        return result;
    }

    /**
     * 从末尾取余，拼接至结果数字末尾。优化越界判断
     * 通过记录每次越界前的result，与累加后的result/10对比，判断是否越界
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了86.42%的用户
     * */
    public int reverse2(int x) {
        int result = 0;
        while(x != 0){
            int memory = result;
            int n = x % 10;
            x /= 10;
            result = result * 10 + n;
            if(result/10 != memory){
                return 0;
            }
        }
        return result;
    }

    /**
     * 转为字符串操作
     * 执行用时：3 ms, 在所有 Java 提交中击败了22.03%的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了65.14%的用户
     * */
    public int reverse3(int x){
        String num = Integer.toString(x);
        try {
            if(x < 0){
                return -Integer.parseInt(new StringBuilder(num.substring(1)).reverse().toString());
            }else {
                return Integer.parseInt(new StringBuilder(num).reverse().toString());
            }
        }catch (Exception e){
            return 0;
        }
    }

}
