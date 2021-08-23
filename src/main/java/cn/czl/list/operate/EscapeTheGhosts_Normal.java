package cn.czl.list.operate;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/8/23 15:31
 * @description:
 *      789. 逃脱阻碍者
 *      - 你在进行一个简化版的吃豆人游戏。你从 [0, 0] 点开始出发，
 *          你的目的地是 target = [xtarget, ytarget] 。地图上有一些阻碍者，
 *          以数组 ghosts 给出，第 i 个阻碍者从 ghosts[i] = [xi, yi] 出发。
 *          所有输入均为 整数坐标 。
 *      - 每一回合，你和阻碍者们可以同时向东，西，南，北四个方向移动，每次可以移动到
 *          距离原位置 1 个单位 的新位置。当然，也可以选择 不动 。所有动作 同时 发生。
 *      - 如果你可以在任何阻碍者抓住你 之前 到达目的地（阻碍者可以采取任意行动方式），
 *          则被视为逃脱成功。如果你和阻碍者同时到达了一个位置（包括目的地）都不算是逃脱成功。
 *      - 只有在你有可能成功逃脱时，输出 true ；否则，输出 false 。
 *      示例 1：
 *          输入：ghosts = [[1,0],[0,3]], target = [0,1]
 *          输出：true
 *          解释：你可以直接一步到达目的地 (0,1) ，在 (1, 0) 或者 (0, 3) 位置的阻碍者都不可能抓住你。
 *      示例 2：
 *          输入：ghosts = [[1,0]], target = [2,0]
 *          输出：false
 *          解释：你需要走到位于 (2, 0) 的目的地，但是在 (1, 0) 的阻碍者位于你和目的地之间。
 *      示例 3：
 *          输入：ghosts = [[2,0]], target = [1,0]
 *          输出：false
 *          解释：阻碍者可以和你同时达到目的地。
 *      示例 4：
 *          输入：ghosts = [[5,0],[-10,-2],[0,-5],[-2,-2],[-7,1]], target = [7,7]
 *          输出：false
 *      示例 5：
 *          输入：ghosts = [[-1,0],[0,1],[-1,0],[0,1],[-1,0]], target = [0,0]
 *          输出：true
 *      提示：
 *          1 <= ghosts.length <= 100
 *          ghosts[i].length == 2
 *          -10^4 <= xi, yi <= 10^4
 *          同一位置可能有 多个阻碍者 。
 *          target.length == 2
 *          -10^4 <= xtarget, ytarget <= 10^4
 */
public class EscapeTheGhosts_Normal {

    private int[][] Ghosts1 = {{1, 0}, {0, 3}};
    private int[] Target1 = {0, 1};
    private int[][] Ghosts2 = {{1, 0}};
    private int[] Target2 = {2, 0};
    private int[][] Ghosts3 = {{2, 0}};
    private int[] Target3 = {1, 0};
    private int[][] Ghosts4 = {{5, 0},{-10, -2},{0, -5},{-2, -2},{-7, 1}};
    private int[] Target4 = {7, 7};
    private int[][] Ghosts5 = {{-1, 0},{0, 1},{-1, 0},{0, 1},{-1, 0}};
    private int[] Target5 = {0, 0};
    private int[][] Ghosts6 = {{1,8},{-9,0},{-7,-6},{4,3},{1,3}};
    private int[] Target6 = {6,-9};

    @Test
    public void TestSolution(){
//        System.out.println(escapeGhosts(Ghosts1, Target1));
//        System.out.println(escapeGhosts(Ghosts2, Target2));
//        System.out.println(escapeGhosts(Ghosts3, Target3));
//        System.out.println(escapeGhosts(Ghosts4, Target4));
//        System.out.println(escapeGhosts(Ghosts5, Target5));
        System.out.println(escapeGhosts(Ghosts6, Target6));
    }

    /**
     * 直接比较曼哈顿距离
     * 直接比较玩家 与 怪 谁能够先抵达出口 (即，怪直接去堵门即可)
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 6.50% 的用户
     * */
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int way = Math.abs(target[0]) + Math.abs(target[1]);
        for(int[] ghost : ghosts){
            int x = Math.abs(ghost[0] - target[0]);
            int y = Math.abs(ghost[1] - target[1]);
            int gway = x + y;
            if (gway <= way){
                return false;
            }
        }
        return true;
    }

    /**
     * 曼哈顿距离2
     * 封装计算曼哈顿距离的方法
     * */
    public boolean escapeGhosts2(int[][] ghosts, int[] target) {
        int[] start = new int[]{0, 0};
        int distance = manhattanDistance(start, target);
        for (int[] ghost : ghosts){
            int ghostDist = manhattanDistance(ghost, target);
            if (ghostDist <= distance){
                return false;
            }
        }
        return true;
    }
    // 计算曼哈顿距离
    private int manhattanDistance(int[] pos1, int[] pos2){
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }
}
