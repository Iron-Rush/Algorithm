package cn.czl.study.book.recursive;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/5/24 20:11
 * @description:
 *      正数阶乘
 */
public class FactDemo {

    @Test
    public void TestMethod(){
        System.out.println(Fact(5));
    }
    // 计算一个正数的阶乘
    int Fact(int n){
        // 基本情形：当参数为 0 或 1 时， 返回 1
        if(n == 1 || n == 0){
            return 1;
        // 递归情形：返回 n * (n-1)!
        }else {
            return n * Fact(n - 1);
        }
    }
}
