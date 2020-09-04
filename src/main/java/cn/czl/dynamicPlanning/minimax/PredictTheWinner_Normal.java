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
        // return Math.max(scoreStart * player, scoreEnd * player) * player;
        if (player > 0){
            return lScoreTemp > rScoreTemp ? lScoreTemp : rScoreTemp;
        }
        return lScoreTemp < rScoreTemp ? lScoreTemp : rScoreTemp;
    }

    /**
     * 递归dfs优化：
     *      由于为两人轮流取数，因此仅通过对上一次得分取负，则可得到对应的当前得分
     *      当前人得分，等于当前取得数 - 上个人的净分值(由于玩家均为聪明人，每次取数均寻求对自己有利的数字)
     *      开始递归者的得分，即为第一次开始递归的得数。
     * 执行用时：69 ms,在所有 Java 提交中击败了9.29%的用户
     * 内存消耗：36.8 MB,在所有 Java 提交中击败了92.44%的用户
     * 此方法节省内存，但用时较长
     * */
    public boolean PredictTheWinner2(int[] nums) {
        int result = getNumber2(nums, 0, nums.length-1);
        return result >= 0;
    }
    public int getNumber2(int[] nums, int lPos, int rPos){
        if (lPos == rPos){
            return nums[lPos];
        }
        int lScoreTemp = nums[lPos] - getNumber2(nums, lPos+1, rPos);
        int rScoreTemp = nums[rPos] - getNumber2(nums, lPos, rPos - 1);
//        return Math.max(lScoreTemp, rScoreTemp);
        return lScoreTemp > rScoreTemp ? lScoreTemp : rScoreTemp;
    }

    /**
     * 动态规划：（使用dp数组，降低时间复杂度）
     * */
    public boolean PredictTheWinner3(int[] nums){
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) { // 将数组，初始化至对角线上
            dp[i][i] = nums[i];         // 由于左指针，必然大于等于右指针，所以i > j无意义(即:数组左下部分无需计算，无意义)
        }
        // 由于dp[n-1][n-1]已被初始化，因此从dp[n-2][n-1]开始计算
        // 状态转移方程：dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++){
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] >= 0;
    }
    /**
     * dp数组再优化，压缩为一维存储空间内完成计算
     * */
    public boolean PredictTheWinner4(int[] nums){
        int length = nums.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] >= 0;
    }

}
