package cn.czl.dynamicPlanning.minimax;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/9/1 9:40
 * @description: 486.两端取数，预测赢家
 */
public class PredictTheWinner_Normal {

//    private static int[] scores = {1, 5, 233, 7};
    private static int[] scores = {1, 5, 2};

    @Test
    public void TestSolution(){
        System.out.println(PredictTheWinner(scores));
    }


    /**
     * 递归dfs：
     *      不关注谁得几分，作差求和。
     *      先手为正，后手为负，若存在和为正的情况，则可获胜。
     * 执行用时：81 ms,在所有 Java 提交中击败了6.77%的用户
     * 内存消耗：37.1 MB,在所有 Java 提交中击败了48.68%的用户
     * */
    public boolean PredictTheWinner(int[] nums) {
        int result = getNumber(nums, 0, nums.length-1, 1);
        return result >= 0;
    }

    public int getNumber(int[] nums, int lPos, int rPos, int player){
        if (lPos == rPos){
            return nums[lPos] * player;
        }
        int lScoreTemp = nums[lPos] * player + getNumber(nums, lPos + 1, rPos, -player);
        int rScoreTemp = nums[rPos] * player + getNumber(nums, lPos, rPos - 1, -player);
        if (player > 0){
            return lScoreTemp > rScoreTemp ? lScoreTemp : rScoreTemp;
        }
        return lScoreTemp < rScoreTemp ? lScoreTemp : rScoreTemp;
    }


}
