package cn.czl.math;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/10/29 9:09
 * @description:
 *      69. Sqrt(x)
 *      - 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 *      - 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 *      - 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *      示例 1：
 *          输入：x = 4
 *          输出：2
 *      示例 2：
 *          输入：x = 8
 *          输出：2
 *          解释：8 的算术平方根是 2.82842...,
 *              由于返回类型是整数，小数部分将被舍去。
 *      提示：0 <= x <= 2^31 - 1
 */
public class MySqrt_Easy {

    private int X1 = 2147395600;
    private int X2 = 2147395600;
    @Test
    public void TestSoutlution(){
        System.out.println(mySqrt2(X1));
    }


    /**
     * 从小到大递增 模拟 - 比对
     * 执行用时： 29 ms , 在所有 Java 提交中击败了 6.12% 的用户
     * 内存消耗： 35.5 MB , 在所有 Java 提交中击败了 46.69% 的用户
     * */
    public int mySqrt(int x) {
        long basic = 1;
        long target = 1;
        long pre = 0;
        while(target <= x){
            pre = basic;
            basic++;
            target = basic * basic;
        }
        return (int)pre;
    }

    /**
     * 从小到大递增 模拟 - 比对
     * 防溢出，不使用long，改换比较形式
     * 执行用时： 55 ms , 在所有 Java 提交中击败了 5.08% 的用户
     * 内存消耗： 35.6 MB , 在所有 Java 提交中击败了 36.44% 的用户
     * */
    public int mySqrt2(int x) {
        int basic = 1;
        int pre = 0;
        /**
         * target = basic * basic <= x
         * basic <= x/basic
         * */
        while(basic <= x/basic){
            pre = basic;
            basic++;
        }
        return pre;
    }

    /**
     * 二分查找
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.97% 的用户
     * 内存消耗： 35.5 MB , 在所有 Java 提交中击败了 47.49% 的用户
     * */
    public int mySqrt3(int x){
        long low = 0, high = x/2 + 1;
        long mid = 0;
        while(high >= low){
            mid = (high + low) >>> 1;
            long temp = mid * mid;
            if(temp > x){
                high = mid - 1;
            }else {
                if((mid + 1) * (mid + 1) > x){
                    return (int)mid;
                }else{
                    low = mid + 1;
                }
            }
        }
        return (int)mid;
    }

    /**
     * 二分搜索 - 判断改为除法(需留意被除数为0的情况)
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.97% 的用户
     * 内存消耗： 35.5 MB , 在所有 Java 提交中击败了 54.16% 的用户
     * */
    public int mySqrt4(int x){
        if(x <= 1)  return x;
        int low = 0, high = x/2 + 1;
        int mid = 0;
        while(high >= low){
            mid = (high + low) >>> 1;
            if(mid > x / mid){
                high = mid - 1;
            }else {
                if((mid + 1) > x / (mid + 1) ){
                    return mid;
                }else{
                    low = mid + 1;
                }
            }
        }
        return mid;
    }
}
