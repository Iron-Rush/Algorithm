package cn.czl.list.search.max;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RedRush
 * @date 2021/11/5 9:34
 * @description:
 *      1218. 最长定差子序列
 *      - 给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
 *      - 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 *      示例 1：
 *          输入：arr = [1,2,3,4], difference = 1
 *          输出：4
 *          解释：最长的等差子序列是 [1,2,3,4]。
 *      示例 2：
 *          输入：arr = [1,3,5,7], difference = 1
 *          输出：1
 *          解释：最长的等差子序列是任意单个元素。
 *      示例 3：
 *          输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
 *          输出：4
 *          解释：最长的等差子序列是 [7,5,3,1]。
 *      提示：
 *          1 <= arr.length <= 10^5
 *          -10^4 <= arr[i], difference <= 10^4
 */
public class LongestSubsequence_Normal {

    private int[] ARR1 = {1,2,3,4};
    private int[] ARR2 = {1,3,5,7};
    private int[] ARR3 = {1,5,7,8,5,3,4,2,1};
    private int[] ARR4 = {3,4,-3,-2,-4};
    @Test
    public void TestSolution(){
        System.out.println(longestSubsequence(ARR1, 1));
        System.out.println(longestSubsequence(ARR2, 1));
        System.out.println(longestSubsequence(ARR2, 2));
        System.out.println(longestSubsequence(ARR3, -2));   // 4
        System.out.println(longestSubsequence(ARR4, -5));   // 2
    }

    /**
     * 深度优先搜索
     * 超出时间限制
     * */
    public int longestSubsequence(int[] arr, int difference) {
        int len = arr.length;
        boolean[] vis = new boolean[len];
        int maxLen = 0;
        for(int i = 0; i < len; i++){
            if(!vis[i]){
                int cur = dfs(arr, i, difference, vis, len);
                maxLen = Math.max(maxLen, cur);
            }
        }
        return maxLen;
    }
    // 深度优先搜索
    private int dfs(int[] arr, int i, int difference, boolean[] vis, int len){
        int step = 0;
        int target = arr[i];
        while(i < len){
            if(target == arr[i]){
                step++;
                target += difference;
                vis[i] = true;
            }
            i++;
        }
        return step;
    }

    /**
     * 动态规划 - 通过map记录之前的计数
     * 执行用时： 28 ms , 在所有 Java 提交中击败了 85.78% 用户
     * 内存消耗： 54.9 MB , 在所有 Java 提交中击败了 26.70% 的用户
     * */
    public int longestSubsequence2(int[] arr, int difference) {
        int ans = 0;
        Map<Integer, Integer> dp = new HashMap<>(arr.length);
        for(int v : arr){
            // 记录当前数字序列长度
            int curLen = dp.getOrDefault(v - difference, 0) + 1;
            dp.put(v, curLen);
            // 更新最长长度
            ans = Math.max(ans, curLen);
        }
        return ans;
    }

    /**
     * 动态规划 - 通过数组记录之前的计数
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 98.78% 的用户
     * 内存消耗： 47.4 MB , 在所有 Java 提交中击败了 88.98% 的用户
     * */
    public int longestSubsequence3(int[] arr, int difference) {
        int ans = 0;
        int limit = 40009, start = limit / 2;
        int[] memo = new int[limit];
        for(int v : arr){
            memo[start + v] = memo[start + v - difference] + 1;
            ans = Math.max(ans, memo[start + v]);
        }
        return ans;
    }
}
