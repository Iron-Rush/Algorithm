package cn.czl.math;

/**
 * @author RedRush
 * @date 2021/4/19 15:26
 * @description:
 *      剑指 Offer 64. 求1+2+…+n
 *      - 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case
 *          等关键字及条件判断语句（A?B:C）。
 *      示例 1：
 *          输入: n = 3
 *          输出: 6
 *      示例 2：
 *          输入: n = 9
 *          输出: 45
 *      限制： 1 <= n <= 10000
 */
public class SumNums_Normal {

    /**
     * 递归 - 实现循环
     * 短路运算符 - 实现终止
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 59.12% 的用户
     * 内存消耗： 35.8 MB , 在所有 Java 提交中击败了 35.74% 的用户
     * */
    public int sumNums(int n) {
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }
}
