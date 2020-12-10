package cn.czl.list.search.count;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/12/10 9:26
 * @description:
 *      860. 柠檬水找零
 *      - 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 *      - 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 *      - 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 *      - 注意，一开始你手头没有任何零钱。
 *      - 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 *      示例1：
 *          输入：[5,5,5,10,20]    输出：true
 *      示例2：
 *          输入：[5,5,10]         输出：true
 *      示例3：
 *          输入：[10,10]          输出：false
 *      示例 4：
 *          输入：[5,5,10,10,20]   输出：false
 *      提示：
 *      - 0 <= bills.length <= 10000
 *      - bills[i] 不是 5 就是 10 或是 20
 */
public class LemonadeChange_Easy {

    private int[] BILLS1 = {5,5,5,10,20};   // true
    private int[] BILLS2 = {5,5,10};        // true
    private int[] BILLS3 = {10,10};         // false
    private int[] BILLS4 = {5,5,10,10,20};  // false
    private int[] BILLS5 = {10};

    @Test
    public void TestSolution(){
        System.out.println(lemonadeChange(BILLS5));
//        System.out.println(lemonadeChange(BILLS2));
//        System.out.println(lemonadeChange(BILLS3));
//        System.out.println(lemonadeChange(BILLS4));
    }

    /**
     * 模拟 + 贪心，收到20时，优先找零10+5，否则再看是否有3个5
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.72%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了84.92%的用户
     * */
    public boolean lemonadeChange(int[] bills) {
        int[] wallet = new int[21];
        for (int give : bills) {
            if(!giveChange(wallet, give)){
                return false;
            }
        }
        return true;
    }
    // 找零函数
    boolean giveChange(int[] wallet, int give){
        wallet[give]++;     // 钱款装入钱包
        switch (give){      // 根据收到零钱找零
            case 5:         // 无需找零
                break;
            case 10:        // 找零5元
                if(wallet[5]-- == 0){
                    return false;
                }
                break;
            case 20:        // 找零10元+5元/5元*3
                if(wallet[10] > 0 && wallet[5] > 0){
                    wallet[10]--;
                    wallet[5]--;
                }else if(wallet[5] >= 3){
                    wallet[5] -= 3;
                }else {
                    return false;
                }
                break;
        }
        return true;
    }

    /**
     * 优化，减少不必要的计算，和存储空间
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.72%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了27.73%的用户
     * */
    public boolean lemonadeChange2(int[] bills){
        int five = 0, ten = 0;
        for (int give : bills) {
            switch (give){
                case 5:
                    five++;
                    break;
                case 10:
                    ten++;
                    if(five-- == 0){
                        return false;
                    }
                    break;
                default:
                    if(ten > 0 && five > 0){
                        ten--;
                        five--;
                    }else if(five >= 3){
                        five -= 3;
                    }else {
                        return false;
                    }
            }
        }
        return true;
    }

}
