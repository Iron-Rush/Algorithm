package cn.czl.list.search;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/11/17 11:10
 * @description:
 *      1030. 距离顺序排列矩阵单元格
 *      - 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，
 *      满足 0 <= r < R 且 0 <= c < C。
 *      - 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 *      - 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，
 *      其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。
 *      （你可以按任何满足此条件的顺序返回答案。）
 *      示例：
 *          输入：R = 2, C = 2, r0 = 0, c0 = 1
 *          输出：[[0,1],[0,0],[1,1],[1,0]] / [[0,1],[1,1],[0,0],[1,0]]
 *          解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 */
public class MatrixCellsInDistanceOrder_Easy {

    @Test
    public void TestSolution(){
        int[][] res = allCellsDistOrder2(2,3,1,2);
        System.out.println(Arrays.deepToString(res));
    }

    /**
     * 保存每个坐标的(距离,坐标数组)到map中
     * 根据距离从小至大遍历map和map中的数组
     * 执行用时：15 ms, 在所有 Java 提交中击败了69.71%的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了96.25%的用户
     * */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][2];
        Map<Integer, List<int[]>> orderMap = new HashMap<>();
        int max = 0;
        for (int x = 0; x < R; x++){
            for (int y = 0; y < C; y++) {
                Integer dist = Math.abs(x - r0) + Math.abs(y - c0);
                max = max < dist ? dist : max;
                int[] temp = new int[]{x,y};
                if (orderMap.containsKey(dist)){
                    orderMap.get(dist).add(temp);
                }else {
                    List<int[]> list = new ArrayList<int[]>();
                    list.add(temp);
                    orderMap.put(dist, list);
                }
            }
        }
        int pos = 0;
        for (int i = 0; i <= max; i++) {
            List<int[]> list = orderMap.get(i);
            for (int[] temp : list) {
                res[pos++] = temp;
            }
        }
        return res;
    }

    /**
     * 存储全部点，根据曼哈顿距离，直接排序
     * 执行用时：21 ms, 在所有 Java 提交中击败了21.17%的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了82.25%的用户
     * */
    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][2];
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                res[x * C + y][0] = x;
                res[x * C + y][1] = y;
            }
        }
        Arrays.sort(res, (int[] a, int[] b) -> {
            return (Math.abs(a[0] - r0) + Math.abs(a[1] - c0)) -
                    (Math.abs(b[0] - r0) + Math.abs(b[1] - c0));
        });
        return res;
    }

    /**
     * BFS-广度优先搜索。
     * 范围内，所达步数，即为距离
     * 执行用时：7 ms, 在所有 Java 提交中击败了93.98%的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了82.64%的用户
     * */
    public int[][] allCellsDistOrder3(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][2];
        int[] choices = {-1, 1};
        int pos = 0, step = 0;
        while (pos < R * C){
            for (int xStep = 0; xStep <= step; xStep++) {
                int yStep = step - xStep;
                for (int x : choices) {
                    for (int y : choices) {
                        int xPos = r0 + x * xStep;
                        int yPos = c0 + y * yStep;
                        if (xPos >= 0 && xPos < R && yPos >= 0 && yPos < C){
                            res[pos++] = new int[]{xPos, yPos};
                        }
                        if (yStep == 0) break;
                    }
                    if (xStep == 0) break;
                }
            }
            step++;
        }
        return res;
    }
}
