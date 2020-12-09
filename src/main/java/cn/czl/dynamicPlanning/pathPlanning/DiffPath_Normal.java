package cn.czl.dynamicPlanning.pathPlanning;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class DiffPath_Normal {
    public static int M = 4;
    public static int N = 3;

    @Test
    public void getPath(){
        System.out.println(uniquePaths(M,N));
        System.out.println(uniquePaths2(M,N));
    }

    /**
     * 动态规划
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了61.37%的用户
     * */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                if(x == 0 || y == 0){
                    dp[x][y] = 1;
                }else {
                    dp[x][y] = dp[x-1][y] + dp[x][y-1];
                }
            }
        }
        return dp[n-1][m-1];
    }

    /**
     * 递归解决
     * 执行用时：21 ms, 在所有 Java 提交中击败了9.42%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了5.21%的用户
     * */
    public int uniquePaths2(int m, int n){
        HashMap<String, Integer> memory = new HashMap<>();
        return uniquePathsHelper(m, n, 1, 1, memory);
    }
    // 递归帮助类
    int uniquePathsHelper(int x, int y, int xPos, int yPos, HashMap<String, Integer> memory){
        if(x < xPos || y < yPos){
            return 0;
        }
        if (x == xPos & y == yPos){
            return 1;
        }
        String key = xPos + "|" + yPos;
        if (memory.containsKey(key)){
            return memory.get(key);
        }
        int xPlan = uniquePathsHelper(x, y, xPos+1, yPos, memory);
        int yPlan = uniquePathsHelper(x, y, xPos, yPos+1, memory);
        int total = xPlan + yPlan;
        memory.put(key, total);
        return total;
    }

    /**
     * 排列组合[从左上角至右下角需移动m+n-2步]
     * -> 向下移动m-1次，向右移动n-1次
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35 MB, 在所有 Java 提交中击败了92.74%的用户
     * */
    public int uniquePaths3(int m, int n){
        long result = 1;
        for(int x = n, y = 1; y < m; x++, y++){
            result = result * x /y;
        }
        return (int)result;
    }

}
