package cn.czl.math.binary;

import java.util.*;

/**
 * @author RedRush
 * @date 2021/5/19 19:48
 * @description:
 *      1738. 找出第 K 大的异或坐标值
 *      - 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 *      - 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m
 *          且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 *      - 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 *      示例 1：
 *          输入：matrix = [[5,2],[1,6]], k = 1
 *          输出：7
 *          解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
 *      示例 2：
 *          输入：matrix = [[5,2],[1,6]], k = 2
 *          输出：5
 *          解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 *      示例 3：
 *          输入：matrix = [[5,2],[1,6]], k = 3
 *          输出：4
 *          解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
 *      示例 4：
 *          输入：matrix = [[5,2],[1,6]], k = 4
 *          输出：0
 *          解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
 *      提示：
 *          m == matrix.length
 *          n == matrix[i].length
 *          1 <= m, n <= 1000
 *          0 <= matrix[i][j] <= 106
 *          1 <= k <= m * n
 */
public class FindKthLargestXorCoordinateValue_Normal {

    /**
     * 生成前缀和 矩阵，将矩阵加入至数组，并排序。
     * 取对应位置数字返回
     * 执行用时： 339 ms , 在所有 Java 提交中击败了 21.82% 的用户
     * 内存消耗： 186.3 MB , 在所有 Java 提交中击败了 10.30% 的用户
     * */
    public int kthLargestValue(int[][] matrix, int k) {
        if(matrix == null){
            return 0;
        }
        int ySize = matrix.length;
        int xSize = matrix[0].length;
        Integer[] sortArr = new Integer[xSize * ySize];
        int[][] pre = new int[ySize + 1][xSize + 1];
        int index = 0;
        for (int y = 1; y <= ySize; y++) {
            for (int x = 1; x <= xSize; x++) {
                pre[y][x] = pre[y-1][x-1] ^ pre[y][x-1] ^ pre[y-1][x] ^ matrix[y-1][x-1];
                sortArr[index++] = pre[y][x];
            }
        }
        Arrays.sort(sortArr,(x, y) -> y - x);
        return sortArr[k - 1];
    }

    /**
     * 前綴和 矩阵 + 三路快排
     * 执行用时： 199 ms , 在所有 Java 提交中击败了 70.91% 的用户
     * 内存消耗： 184.4 MB , 在所有 Java 提交中击败了 15.15% 的用户
     * */
    public int kthLargestValue2(int[][] matrix, int k) {
        if(matrix == null){
            return 0;
        }
        int ySize = matrix.length;
        int xSize = matrix[0].length;
        int[][] pre = new int[ySize + 1][xSize + 1];
        List<Integer> result = new ArrayList<>();
        for (int y = 1; y <= ySize; y++) {
            for (int x = 1; x <= xSize; x++) {
                pre[y][x] = pre[y-1][x-1] ^ pre[y][x-1] ^ pre[y-1][x] ^ matrix[y-1][x-1];
                result.add(pre[y][x]);
            }
        }
        nthElement(result, 0, k-1, result.size() - 1);
        return result.get(k - 1);
    }
    // 三路快排
    public void nthElement(List<Integer> results, int left, int kth, int right) {
        if (left == right) {
            return;
        }
        int pivot = (int) (left + Math.random() * (right - left + 1));
        swap(results, pivot, right);
        // 三路划分（three-way partition）
        int sepl = left - 1, sepr = left - 1;
        for (int i = left; i <= right; i++) {
            if (results.get(i) > results.get(right)) {
                swap(results, ++sepr, i);
                swap(results, ++sepl, sepr);
            } else if (results.get(i) == results.get(right)) {
                swap(results, ++sepr, i);
            }
        }
        if (sepl < left + kth && left + kth <= sepr) {
            return;
        } else if (left + kth <= sepl) {
            nthElement(results, left, kth, sepl);
        } else {
            nthElement(results, sepr + 1, kth - (sepr - left + 1), right);
        }
    }
    // 交换数组中两个数字
    public void swap(List<Integer> list, int a, int b){
        int temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }

    /**
     * 优先队列
     * 执行用时： 192 ms , 在所有 Java 提交中击败了 71.51% 的用户
     * 内存消耗： 194 MB , 在所有 Java 提交中击败了 6.66% 的用户
     * */
    public int kthLargestValue3(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] sum = new int[m + 1][n + 1];
        // 小顶堆
        PriorityQueue<Integer> q = new PriorityQueue<>(k, (a, b)->a - b);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] ^ sum[i][j - 1] ^ sum[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                if (q.size() < k) {
                    q.add(sum[i][j]);
                } else {    // 如果新元素比堆中最小元素要大，则替换
                    if (sum[i][j] > q.peek()) {
                        q.poll();
                        q.add(sum[i][j]);
                    }
                }
            }
        }
        return q.peek();
    }

}
