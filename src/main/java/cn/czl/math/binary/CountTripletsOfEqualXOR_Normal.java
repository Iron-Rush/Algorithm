package cn.czl.math.binary;

/**
 * @author RedRush
 * @date 2021/5/19 9:28
 * @description:
 *      1442. 形成两个异或相等数组的三元组数目
 *      - 给你一个整数数组 arr 。
 *      - 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 *      - a 和 b 定义如下：
 *          - a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 *          - b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 *      - 注意：^ 表示 按位异或 操作。
 *      - 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 *      示例 1：
 *          输入：arr = [2,3,1,6,7]
 *          输出：4
 *          解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
 *      示例 2：
 *          输入：arr = [1,1,1,1,1]
 *          输出：10
 *      示例 3：
 *          输入：arr = [2,3]
 *          输出：0
 *      示例 4：
 *          输入：arr = [1,3,5,7,9]
 *          输出：3
 *      示例 5：
 *          输入：arr = [7,11,12,9,5,2,7,17,22]
 *          输出：8
 *      提示：
 *          1 <= arr.length <= 300
 *          1 <= arr[i] <= 10^8
 */
public class CountTripletsOfEqualXOR_Normal {

    /**
     * 暴力解 [根据前缀和，暴力解]
     * 执行用时： 108 ms , 在所有 Java 提交中击败了 5.10% 的用户
     * 内存消耗： 36 MB , 在所有 Java 提交中击败了 40.19% 的用户
     * */
    public int countTriplets(int[] arr) {
        if(arr == null || arr.length < 2){
            return 0;
        }
        int len = arr.length;
        int ans = 0;
        int[] dp = new int[len + 1];
        for(int i = 0; i < len; i++){
            dp[i + 1] = dp[i] ^ arr[i];
        }
        for (int l = 1; l < len; l++){
            for(int r = len; r > l; r--){
                int mid = r;
                while(mid > l){
                    if(check(dp, l, mid, r)){
                        ans++;
                    }
                    mid--;
                }
            }
        }

        return ans;
    }
    // 根据下标判断当前位置，是否符合条件
    private boolean check(int[] dp, int start, int mid, int end){
        int a = dp[mid - 1] ^ dp[start  - 1];
        int b = dp[mid - 1] ^ dp[end];
        return a == b;
    }

    /**
     * 当区间内，异或和为0时，区间内游标可以任意取，均满足题意
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 19.21% 的用户
     * */
    public int countTriplets2(int[] arr) {
        int len = arr.length;
        int ans = 0;

        for (int l = 0; l < len - 1; l++) {
            int sum = 0;
            for (int r = l; r < len; r++) {
                sum ^= arr[r];
                if(sum == 0 && r > l){
                    ans += (r - l);
                }
            }
        }
        return ans;
    }
}
