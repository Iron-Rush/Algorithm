package cn.czl.study.search;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/9/26 16:43
 * @description:
 *          二分搜索 - 获取目标数的平方根
 */
public class GetNumSqrt {

    public double getSqrt(double num, double offset){
        double mid = 0.0;
        offset = Math.abs(offset);
        if (Math.abs(num) <= offset){
            return mid;
        }else if (num < -offset){   // 负数返回
            throw new IllegalArgumentException();
        }else {
            double low = 0;
            double high = num/2 + 1;
            while(Math.abs(high - low) > offset){
                // 找到中间值
                mid = low + (high - low) / 2;
                double temp = mid * mid;
                // 比较更新上下限
                if (temp > num){
                    high = mid;
                }else if (temp < num){
                    low = mid;
                }else {
                    return mid;
                }
            }
        }
        return mid;
    }

    @Test
    public void TestSolution(){
        double offset = 0.00001;
        for (int i = 0; i < 1000; i++) {
            double a = getSqrt(i, offset);
            double b = Math.sqrt(i);
            if (!(Math.abs(b-a) <= offset)){
                System.out.println("答案错误");
            }
        }
    }
}
