package cn.czl.list.unique;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/11/9 9:33
 * @description:
 *      973. 最接近原点的 K 个点
 *      - 平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 *      - 平面上两点之间的距离是欧几里德距离。
 *      - 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 *      示例:
 *          输入：points = [[1,3],[-2,2]], K = 1
 *          输出：[[-2,2]]
 *          解释：
 *              (1, 3) 和原点之间的距离为 sqrt(10)，
 *              (-2, 2) 和原点之间的距离为 sqrt(8)，
 *              由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 *              我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 */
public class KClosest_PointToOrigin_Normal {

    private int[][] POINTS1 = new int[][]{{1,3},{-2,2}};
    private int K1 = 1;
    private int[][] POINTS2 = new int[][]{{3,3},{-2,4},{5,-1}};
    private int K2 = 2;

    @Test
    public void TestSolution(){
        int[][] res = kClosest3(POINTS2, K1);
        System.out.println(Arrays.deepToString(res));
    }

    /**
     * 存储每个点的下标，和距离平方至二维数组，
     * 利用排序器根据距离平方进行排序，
     * 提取排序后的二维数组的前K个脚标，从points中提取对应数组并返回
     * 执行用时：34 ms, 在所有 Java 提交中击败了47.17%的用户
     * 内存消耗：46.4 MB, 在所有 Java 提交中击败了95.14%的用户
     * */
    public int[][] kClosest(int[][] points, int K) {
        int[][] counter = new int[points.length][2];
        for (int i = 0; i < points.length; i++) {
            int s = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            counter[i][0] = s;
            counter[i][1] = i;
        }
        int[][] res = new int[K][2];
        Arrays.sort(counter, (a, b) -> a[0]-b[0]);
        for (int i = 0; i < K; i++) {
            res[i] = points[counter[i][1]];
        }
        return res;
    }

    /**
     * 直接使用Arrays.sort排序
     * 执行用时：29 ms, 在所有 Java 提交中击败了61.93%的用户
     * 内存消耗：46.8 MB, 在所有 Java 提交中击败了84.85%的用户
     * */
    public int[][] kClosest2(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] s1, int[] s2) {
                return (s1[0] * s1[0] + s1[1] * s1[1]) - (s2[0] * s2[0] + s2[1] * s2[1]);
            }
        });
        return Arrays.copyOfRange(points, 0, K);
    }

    /**
     * 优先队列，实时维护K个最小的距离平方
     * 执行用时：32 ms, 在所有 Java 提交中击败了53.92%的用户
     * 内存消耗：47.3 MB, 在所有 Java 提交中击败了41.41%的用户
     * */
    public int[][] kClosest3(int[][] points, int K) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] array1, int[] array2) {
                return array2[0] - array1[0];
            }
        });
        for (int i = 0; i < K; i++) {
            priorityQueue.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1],i});
        }
        for (int i = K; i < points.length; i++) {
            int s = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (s < priorityQueue.peek()[0]){
                priorityQueue.poll();
                priorityQueue.offer(new int[]{s, i});
            }
        }
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = points[priorityQueue.poll()[1]];
        }
        return res;
    }

}
