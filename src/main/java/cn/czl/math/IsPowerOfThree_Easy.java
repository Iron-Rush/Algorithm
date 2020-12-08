package cn.czl.math;

/**
 * @author RedRush
 * @date 2020/12/8 16:13
 * @description:
 *      326. 3的幂
 *      - 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 *      - 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
 *      - 提示：-2^31 <= n <= 2^31 - 1
 *      - 进阶：你能不使用循环或者递归来完成本题吗？
 */
public class IsPowerOfThree_Easy {

    /**
     * 从 `1` 开始累乘，与传入`n`作比较
     * */
    public boolean isPowerOfThree(int n) {
        long basic = 1, target = n;
        while(basic < target){
            basic *= 3;
        }
        return basic == target;
    }

    /**
     * 对n进行取余/除3操作
     * */
    public boolean isPowerOfThree2(int n){
        if(n < 1){
            return false;
        }
        while (n % 3 == 0){
            n /= 3;
        }
        return n == 1;
    }

    /**
     * 由于范围已知，可通过范围内3的最高次幂做取余运算
     * */
    public boolean isPowerOfThree3(int n){
        return n > 0 && 1162261467 % n == 0;
    }
}
