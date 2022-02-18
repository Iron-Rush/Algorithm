package cn.czl.math;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2022/2/10 9:31
 * @description:
 *      1447. 最简分数
 *      - 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）
 *          满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 *      示例 1：
 *          输入：n = 2
 *          输出：["1/2"]
 *          解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
 *      示例 2：
 *          输入：n = 3
 *          输出：["1/2","1/3","2/3"]
 *      示例 3：
 *          输入：n = 4
 *          输出：["1/2","1/3","1/4","2/3","3/4"]
 *          解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
 *      示例 4：
 *          输入：n = 1
 *          输出：[]
 *      - 提示：1 <= n <= 100
 */
public class SimplifiedFractions_Normal {

    @Test
    public void TestFunction(){
//        System.out.println(judge(4,10));
        System.out.println(gcd(888,22));
        System.out.println(gcd(22, 888));
    }

    /**
     * 欧几里得算法 - 若最大公约数为1，则为最简分数
     * 执行用时： 20 ms , 在所有 Java 提交中击败了 55.32% 的用户
     * 内存消耗： 42.1 MB , 在所有 Java 提交中击败了 13.83% 的用户
     * */
    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (gcd(i, j) == 1) ans.add(i + "/" + j);
            }
        }
        return ans;
    }
    // 欧几里得算法[辗转相除] 计算两个非负整数的最大公约数
    private int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }
}
