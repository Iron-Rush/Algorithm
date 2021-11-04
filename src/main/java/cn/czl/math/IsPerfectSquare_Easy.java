package cn.czl.math;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/11/4 9:24
 * @description:
 *      367. 有效的完全平方数
 *      - 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 *      - 进阶：不要 使用任何内置的库函数，如  sqrt 。
 *      示例 1：
 *          输入：num = 16
 *          输出：true
 *      示例 2：
 *          输入：num = 14
 *          输出：false
 *      提示： 1 <= num <= 2^31 - 1
 */
public class IsPerfectSquare_Easy {

    @Test
    public void TestSolution(){
//        System.out.println(isPerfectSquare3(4));
        System.out.println(isPerfectSquare3(5));
        System.out.println(isPerfectSquare3(14));
        System.out.println(isPerfectSquare3(16));
    }

    /**
     * 调用库函数 - 开方,取余
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35 MB , 在所有 Java 提交中击败了 84.70% 的用户
     * */
    public boolean isPerfectSquare(int num) {
        return Math.sqrt(num) % 1 == 0;
    }

    /**
     * 从小到大 - 模拟比对
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 8.56% 的用户
     * 内存消耗： 35 MB , 在所有 Java 提交中击败了 80.72% 的用户
     * */
    public boolean isPerfectSquare2(int num) {
        double basic = 1, target = num / basic;
        // basic * basic <= num -> basic <= target
        while(basic < target){
            basic++;
            target = num / basic;
        }
        return basic == target;
    }

    /**
     * 二分搜索
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.3 MB , 在所有 Java 提交中击败了 8.40% 的用户
     * */
    public boolean isPerfectSquare3(int num){
        long low = 1, high = num/2 + 1;
        while(low <= high){
            long mid = (high - low)/2 + low;
            if(mid * mid > num){
                high = mid - 1;
            }else if(mid * mid < num){
                low = mid + 1;
            }else {
                return true;
            }
        }
        return false;
    }
}
