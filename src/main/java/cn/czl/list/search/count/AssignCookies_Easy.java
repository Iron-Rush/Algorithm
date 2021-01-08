package cn.czl.list.search.count;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2020/12/25 10:37
 * @description:
 *      455. 分发饼干
 *      - 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 *      - 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *       示例 1:
 *          输入: g = [1,2,3], s = [1,1]
 *          输出: 1
 *          解释:
 *              你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 *              虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 *              所以你应该输出1。
 *       示例 2:
 *          输入: g = [1,2], s = [1,2,3]
 *          输出: 2
 *          解释:
 *              你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 *              你拥有的饼干数量和尺寸都足以让所有孩子满足。
 *              所以你应该输出2.
 *      提示：
 *          1 <= g.length <= 3 * 10^4
 *          0 <= s.length <= 3 * 10^4
 *          1 <= g[i], s[j] <= 2^31 - 1
 */
public class AssignCookies_Easy {

    private int[] G1 = {1,2,3};
    private int[] G2 = {1,2};
    private int[] S1 = {1,1};
    private int[] S2 = {1,2,3};


    @Test
    public void TestSolution(){
        System.out.println(findContentChildren(G1, S1));
        System.out.println(findContentChildren(G2, S2));
    }

    /**
     * 贪心算法     先喂胃口小的孩子，满足更多孩子
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 89.07% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 68.75% 的用户
     * */
    public int findContentChildren(int[] g, int[] s) {
        int count = 0, size = s.length;
        Arrays.sort(g);
        Arrays.sort(s);
        int cookie = 0;
        for (int child : g) {   // 每个孩子只能有一个饼干，由小至大满足孩子
            while (cookie < size && child > s[cookie]){ // 尽可能给胃口小的孩子，尽可能的小的饼干
                cookie++;
            }
            if(cookie == size){ // 当前最大的饼干也不能满足当前孩子时，则该孩子及之后的孩子均无法被满足
                break;
            }else {
                count++;
                cookie++;
            }
        }
        return count;
    }
}
