package cn.czl.search.map;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

/**
 * @author RedRush
 * @date 2021/1/15 10:25
 * @description:
 *      947. 移除最多的同行或同列石头
 *      - n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 *      - 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 *      - 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 *      示例 1：
 *          输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 *          输出：5
 *      示例 2：
 *          输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 *          输出：3
 *      示例 3：
 *          输入：stones = [[0,0]]
 *          输出：0
 *      提示：
 *          1 <= stones.length <= 1000
 *          0 <= xi, yi <= 104
 *          不会有两块石头放在同一个坐标点上
 */
public class RemoveMostStones_Normal {
    private int[][] STONES1 = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
    private int[][] STONES2 = {{0,0},{0,2},{1,1},{2,0},{2,2}};
    private int[][] STONES3 = {{1,0},{0,1}};

    @Test
    public void TestSolution(){
        System.out.println(removeStones(STONES1));
        System.out.println(removeStones(STONES2));
        System.out.println(removeStones(STONES3));
    }
    public int removeStones(int[][] stones) {
        int size = stones.length;
        UnionSearch us = new UnionSearch(20000);    // 0 <= xi, yi <= 104
        for(int i = 0; i < size; i++){
            us.union(stones[i][0], stones[i][1] + 10000);// 用于区分横纵坐标，避免(1,0)与(0,1)关联
        }
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < size; i++){          // 统计母坐标数量(必留石头数)
            set.add(us.search(stones[i][0]));
        }
        return size - set.size();   // 总石头数 - 必留石头数，即为可删石头数
    }
}
// 并查集模版
//class UnionSearch{
//    private int[] parent;
//    public UnionSearch(int size){
//        parent = new int[size];
//        for(int i = 0; i < size; i++){
//            parent[i] = i;
//        }
//    }
//    public int search(int x){
//        if(parent[x] != x){
//            parent[x] = search(parent[x]);
//        }
//        return parent[x];
//    }
//    public void union(int x, int y){
//        parent[search(x)] = search(y);
//    }
//}
