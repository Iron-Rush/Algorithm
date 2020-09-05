package cn.czl.string;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/9/4 14:53
 * @description:
 *      9.回文数
 *      判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class IsPalindrome_Easy {

    private static int num = 121;

    @Test
    public void TestSolution(){
        System.out.println(isPalindrome(num));
    }

    /**
     * StringBuilder reverse方法，对比实现
     * */
    public boolean isPalindrome(int x){
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        return sb.toString().equals(sb.reverse().toString());
    }
    /**
     * StringBuilder charAt，逐个对比
     * */
    public boolean isPalindrome1(int x) {
        if (x < 0){
            return false;
        }
        if (x < 10){
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        int len = sb.length();
        int lPos = 0, rPos = len - 1;
        for (int i = 0; i < len/2; i++) {
            if (sb.charAt(lPos) != sb.charAt(rPos)){
                return false;
            }
            lPos ++;
            rPos--;
        }
        return true;
    }

    /**
     * 转变成char[],逐个对比
     * */
    public boolean isPalindrome2(int x) {
        if (x < 0){
            return false;
        }
        if (x < 10){
            return true;
        }
        char[] chars = String.valueOf(x).toCharArray();
        int len = chars.length;
        int lPos = 0, rPos = len - 1;
        while (lPos <= rPos){
            if (chars[lPos] == chars[rPos]){
                lPos ++;
                rPos--;
                continue;
            }
            return false;
        }
        return true;
    }
    /**
     * 不转变字符串实现
     * */
    public boolean isPalindrome3(int x){
        /**
         * 特殊情况：
         * 当 x < 0 时，x 不是回文数。
         * 同样地，如果数字的最后一位是 0，为了使该数字为回文，
         * 则其第一位数字也应该是 0,只有 0 满足这一属性
         * */
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertedNumber = 0;
        while ( x > revertedNumber){    // 新建revertedNumber，用于记录后半截数字反转后的结果
            revertedNumber = revertedNumber * 10 + x % 10;  // 将传入数字末尾取出，将已累加数字X10，腾出个位
            x /= 10;        // 缩短原数,除去尾部
        }
        /**
         * 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
         * 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
         * 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
         * */
        return x == revertedNumber || x == revertedNumber / 10;
    }

}
