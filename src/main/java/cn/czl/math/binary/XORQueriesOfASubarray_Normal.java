package cn.czl.math.binary;

/**
 * @author RedRush
 * @date 2021/5/12 15:51
 * @description:
 *      1310. 子数组异或查询
 *      - 有一个正整数数组 arr，现给你一个对应的查询数组 queries，
 *          其中 queries[i] = [Li, Ri]。
 *      - 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值
 *          （即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
 *      - 并返回一个包含给定查询 queries 所有结果的数组。
 *      示例 1：
 *          输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 *          输出：[2,7,14,8]
 *          解释：
 *              数组中元素的二进制表示形式是：
 *              1 = 0001
 *              3 = 0011
 *              4 = 0100
 *              8 = 1000
 *          查询的 XOR 值为：
 *              [0,1] = 1 xor 3 = 2
 *              [1,2] = 3 xor 4 = 7
 *              [0,3] = 1 xor 3 xor 4 xor 8 = 14
 *              [3,3] = 8
 *      示例 2：
 *          输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 *          输出：[8,0,4,4]
 *      提示：
 *          1 <= arr.length <= 3 * 10^4
 *          1 <= arr[i] <= 10^9
 *          1 <= queries.length <= 3 * 10^4
 *          queries[i].length == 2
 *          0 <= queries[i][0] <= queries[i][1] < arr.length
 */
public class XORQueriesOfASubarray_Normal {

    /**
     * [硬解]构建获取l-r的异或值的函数。逐位调用函数得出解。
     * 执行用时： 1041 ms , 在所有 Java 提交中击败了 11.21% 的用户
     * 内存消耗： 49.4 MB , 在所有 Java 提交中击败了 24.14% 的用户
     * */
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] res = new int[queries.length];
        int i = 0;
        for(int[] query : queries){
            int l = query[0];
            int r = query[1];
            res[i++] = getAreaXOR(arr, l, r);
        }
        return res;
    }
    // 获取[l, r]区间内的全部异或值
    int getAreaXOR(int[] arr, int l, int r){
        if(l == r){
            return arr[l];
        }
        int res = arr[r];
        while(l < r){
            res ^= arr[l++];
        }
        return res;
    }

    /**
     * [前缀数组]预处理得出前缀数组，将下标代入前缀数组求解。
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 49.3 MB , 在所有 Java 提交中击败了 34.48% 的用户
     * */
    public int[] xorQueries2(int[] arr, int[][] queries) {
        int total = arr.length;
        int[] memo = new int[total + 1];
        for (int i = 0; i < total; i++){
            memo[i + 1] = memo[i] ^ arr[i];
        }
        int len = queries.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++){
            int l = queries[i][0];
            int r = queries[i][1];
            res[i] = memo[l] ^ memo[r + 1];
        }
        return res;
    }

}
