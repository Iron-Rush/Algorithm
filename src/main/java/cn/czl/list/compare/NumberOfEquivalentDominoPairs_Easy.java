package cn.czl.list.compare;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author RedRush
 * @date 2021/1/26 14:32
 * @description:
 *      1128. 等价多米诺骨牌对的数量
 *      - 给你一个由一些多米诺骨牌组成的列表 dominoes。
 *      - 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 *      - 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 *      - 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 *      示例：
 *          输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 *          输出：1
 *      提示：
 *          1 <= dominoes.length <= 40000
 *          1 <= dominoes[i][j] <= 9
 */
public class NumberOfEquivalentDominoPairs_Easy {

    private int[][] DOMINOES = {{1,2},{2,1},{3,4},{5,6}};

    @Test
    public void TestSolution(){
        System.out.println(numEquivDominoPairs(DOMINOES));
    }

    /**
     * 通过数组，记录每个规格的多米诺骨牌数量
     * 根据数量计算出可能的组合数
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 58.16% 的用户
     * 内存消耗： 47.8 MB , 在所有 Java 提交中击败了 16.87% 的用户
     * */
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] map = new int[100];
        int count = 0;
        for (int[] d : dominoes) {
            Arrays.sort(d);
            map[d[0]*10 + d[1]]++;
        }
        for (int num : map) {
            count += num > 1 ? num * (num-1)/2 : 0;
        }
        return count;
    }

    /**
     * HashMap记录每张牌对应的数量
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 85.51% 的用户
     * 内存消耗： 47.4 MB , 在所有 Java 提交中击败了 82.92% 的用户
     * */
    public int numEquivDominoPairs2(int[][] dominoes) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int[] d : dominoes){
            Arrays.sort(d);
            int key = d[0] * 10 + d[1];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int count = 0;
        for (int num : map.values()) {
            count += num > 1 ? num * (num-1)/2 : 0;
        }
        return count;
    }

    /**
     * 通过数组，记录每个规格的多米诺骨牌数量
     * 累计之前的牌数即可
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 58.16% 的用户
     * 内存消耗： 47.8 MB , 在所有 Java 提交中击败了 16.87% 的用户
     * */
    public int numEquivDominoPairs3(int[][] dominoes) {
        int[] map = new int[100];
        int count = 0;
        for (int[] d : dominoes) {
            Arrays.sort(d);
            int val = d[0] * 10 + d[1];
//            int val = d[0] < d[1] ? d[0] * 10 + d[1] : d[1] * 10 + d[0];
            count += map[val];
            map[val]++;
        }
        return count;
    }
}
