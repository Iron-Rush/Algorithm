package cn.czl.search.list;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2021/1/7 10:34
 * @description:
 *      547. 省份数量
 *      - 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *      - 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *      - 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 *      - 返回矩阵中 省份 的数量。
 *      示例 1：
 *          输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 *          输出：2
 *      示例 2：
 *          输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 *          输出：3
 *      提示：
 *          1 <= n <= 200
 *          n == isConnected.length
 *          n == isConnected[i].length
 *          isConnected[i][j] 为 1 或 0
 *          isConnected[i][i] == 1
 *          isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProviences_Normal {


    /**
     * dfs-深度优先搜索
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.49% 的用户
     * 内存消耗： 39.4 MB , 在所有 Java 提交中击败了 60.41% 的用户
     * */
    public int findCircleNum(int[][] isConnected) {
        if(isConnected.length == 0 || isConnected[0].length == 0){
            return 0;
        }
        int city = isConnected.length, count = 0;
        boolean[] visited = new boolean[city];    // 记录目前全部可抵达城市
        for(int i = 0; i < city; i++){
            if(!visited[i]){
                count++;
                dfs(isConnected, visited, i, city);
            }
        }
        return count;
    }
    // 深度优先搜索
    void dfs(int[][] isConnected, boolean[] visited, int i, int city){
        for(int j = 0; j < city; j++){
            if(isConnected[i][j] == 1 && !visited[j]){
                visited[j] = true;
                dfs(isConnected, visited, j, city);
            }
        }
    }

    /**
     * bfs-广度优先搜索
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 13.36% 的用户
     * 内存消耗： 39.4 MB , 在所有 Java 提交中击败了 56.46% 的用户
     * */
    public int findCircleNum2(int[][] isConnected) {
        int city = isConnected.length, count = 0;
        boolean[] visited = new boolean[city];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < city; i++) {
            if(!visited[i]){
                queue.offer(i);
                while (!queue.isEmpty()){
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < city; k++) {
                        if(isConnected[j][k] == 1 && !visited[k]){
                            queue.offer(k);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }

    /**
     * 并查集
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 49.60% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 87.65% 的用户
     * */
    public int findCircleNum3(int[][] isConnected) {
        int count = 0;
        int city = isConnected.length;
        int[] parent = new int[city];
        for (int i = 0; i < city; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < city; i++) {
            for (int j = 0; j < city; j++) {
                if(isConnected[i][j] == 1){
                    union(parent, i, j);
                }
            }
        }
        for (int i = 0; i < city; i++) {
            if(parent[i] == i){
                count++;
            }
        }
        return count;
    }
    void union(int[] parent, int index1, int index2){
        parent[find(parent, index1)] = find(parent, index2);
    }
    int find(int[] parent, int index){
        if(parent[index] != index){
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
