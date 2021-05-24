package cn.czl.study.book.recursive;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/5/24 20:14
 * @description:
 *      递归和内存(可视化)
 */
public class PrintDemo {

    @Test
    public void TestMethod(){
        System.out.println(Print(5));
    }

    int Print(int n){
        if(n <= 0){
            return 0;
        }else {
            System.out.println(n);
            return Print(n - 1);
        }
    }

}
