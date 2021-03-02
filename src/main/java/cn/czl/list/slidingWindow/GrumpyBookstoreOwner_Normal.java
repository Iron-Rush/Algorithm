package cn.czl.list.slidingWindow;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/2/23 15:58
 * @description:
 *      1052. 爱生气的书店老板
 *      - 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 *      - 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 *      - 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 *      - 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 *      示例：
 *          输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 *          输出：16
 *          解释：
 *              书店老板在最后 3 分钟保持冷静。
 *              感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *      提示：
 *          1 <= X <= customers.length == grumpy.length <= 20000
 *          0 <= customers[i] <= 1000
 *          0 <= grumpy[i] <= 1
 */
public class GrumpyBookstoreOwner_Normal {

    private int[] CUSTOMERS = {1,0,1,2,1,1,7,5};
    private int[] GRUMPY = {0,1,0,1,0,1,0,1};
    private int X = 3;

    @Test
    public void TestSolution(){
        System.out.println(maxSatisfied(CUSTOMERS, GRUMPY, X));
    }

    /**
     * 滑动窗口
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 79.44% 的用户
     * 内存消耗： 40.9 MB , 在所有 Java 提交中击败了 61.29% 的用户
     * */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int res = 0;
        for (int i = 0; i < customers.length; i++) {
            if(grumpy[i] == 0){
                res += customers[i];
            }
        }
        int curSum = 0;
        for (int i = 0; i < X; i++) {
            curSum += customers[i] * grumpy[i]; // 由于grumpy[i]用于区分本分钟是否已被累加过
        }
        int max = curSum;
        for (int i = X; i < customers.length; i++) {
            curSum = curSum - customers[i - X] * grumpy[i - X]
                    + customers[i] * grumpy[i];
            max = Math.max(max, curSum);
        }
        return res + max;
    }
}
