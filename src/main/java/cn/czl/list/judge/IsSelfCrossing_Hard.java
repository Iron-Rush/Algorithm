package cn.czl.list.judge;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author RedRush
 * @date 2021/10/29 14:42
 * @description:
 *      335. 路径交叉
 *      - 给你一个整数数组 distance 。
 *      - 从 X-Y 平面上的点 (0,0) 开始，先向北移动 distance[0] 米，
 *          然后向西移动 distance[1] 米，向南移动 distance[2] 米，
 *          向东移动 distance[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
 *      - 判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。
 *      示例 1：
 *          输入：distance = [2,1,1,2]
 *          输出：true
 *      示例 2：
 *          输入：distance = [1,2,3,4]
 *          输出：false
 *      示例 3：
 *          输入：distance = [1,1,1,1]
 *          输出：true
 *      提示：
 *          1 <= distance.length <= 10^5
 *          1 <= distance[i] <= 10^5
 */
public class    IsSelfCrossing_Hard {

    @Test
    public void TestSolution(){
        int[] dist = {1,1,2,2,4,1,2};   // 360度相交
//        System.out.println(isSelfCrossing(dist));
        System.out.println(isSelfCrossing2(dist));
    }

    /**
     * 利用HashSet记录经过点坐标
     * 超出时间限制
     * */
    public boolean isSelfCrossing(int[] distance) {
        int size = distance.length;
        Set<Long> set = new HashSet<>(size);
        Long x = 0l, y = 0l;
        set.add(0l);
        for(int i = 0; i < distance.length; i++){
            int cur = distance[i];
            switch (i % 4){
                case 0:
                    while(cur > 0){
                        cur--;
                        y++;
                        if(!set.add(x + y*100000))   return true;
                    }
                    break;
                case 1:
                    while(cur > 0){
                        cur--;
                        x--;
                        if(!set.add(x + y*100000))   return true;
                    }
                    break;
                case 2:
                    while(cur > 0){
                        cur--;
                        y--;
                        if(!set.add(x + y*100000))   return true;
                    }
                    break;
                case 3:
                    while(cur > 0){
                        cur--;
                        x++;
                        if(!set.add(x + y*100000))   return true;
                    }
                    break;
            }
        }
        return false;
    }

    /**
     * 找规律 + 分情况讨论
     *  - 至少需要4条边才可能存在相交路径,如果distance长度小于4，可直接返回false
     *      1. d[i] 与 d[i−3] 发生相交：      (i为任何数时，若等式成立，必相交。以i为原点做推算)
     *         此时满足 d[i] >= d[i-2]，同时 d[i-1] <= d[i-3]；
     *      2. d[i] 与 d[i−4] 发生相交：
     *         此时满足 d[i−1]=d[i−3]，同时 d[i]+d[i−4] >= d[i−2]；
     *      3. d[i] 与 d[i−5] 发生相交：
     *         此时满足 d[i−1]<=d[i−3]，同时 d[i−2]>d[i−4]，
     *         同时 d[i]+d[i−4]>=d[i−2]，同时 d[i−1]+d[i−5]>=d[i−3]。
     *                 i-2
     *     case 1 : i-1┌─┐
     *                 └─┼─>i
     *                  i-3
     *
     *                     i-2
     *     case 2 : i-1 ┌────┐
     *                  └─══>┘i-3
     *                  i  i-4      (i overlapped i-4)
     *
     *     case 3 :    i-4
     *                ┌──┐
     *                │i<┼─┐
     *             i-3│ i-5│i-1
     *                └────┘
     *                 i-2
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 80.03% 的用户
     * 内存消耗： 43.5 MB , 在所有 Java 提交中击败了 5.14% 的用户
     * */
    public boolean isSelfCrossing2(int[] dist) {
        int len = dist.length;
        if(len < 4)     return false;
        for(int i = 3; i < len; i++){
            if(dist[i] >= dist[i-2] && dist[i-1] <= dist[i-3]){
                return true;
            }
            if(i >= 4 && dist[i-1] == dist[i-3] && dist[i] + dist[i-4] >= dist[i-2]){
                return true;
            }
            if(i >= 5
                    && dist[i-1] <= dist[i-3] && dist[i-2] > dist[i-4]
                    && dist[i] + dist[i-4] >= dist[i-2]
                    && dist[i-1] + dist[i-5] >= dist[i-3]){
                return true;
            }
        }
        return false;
    }
}
