package cn.czl.list.compare;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/11/4 9:23
 * @description:
 *      57. 插入区间
 *      - 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *      - 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（
 *      如果有必要的话，可以合并区间）。
 *      示例 1：
 *          输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 *          输出：[[1,5],[6,9]]
 *      示例 2：
 *          输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 *          输出：[[1,2],[3,10],[12,16]]
 *          解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class InsertInterval_Hard {

    private int[][] INTERVALS1 = new int[][]{{1,3},{6,9}};
    private int[][] INTERVALS2 = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
    private int[][] INTERVALS3 = new int[][]{{6,8}};
    private int[] NEWINTERVAL1 = new int[]{2,5};
    private int[] NEWINTERVAL2 = new int[]{1,16};
    private int[] NEWINTERVAL3 = new int[]{1,5};

    @Test
    public void TestSolution(){
        int[][] res = insert(INTERVALS3, NEWINTERVAL3);
        System.out.println(Arrays.deepToString(res));
    }

    /**
     * 寻找左边界 -> 判断左边界是否被初始化
     * -> 寻找右边界 -> 判断右边界是否被初始化 -> 拷贝剩余数组 -> 截取数组
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.65%的用户
     * 内存消耗：40.7 MB, 在所有 Java 提交中击败了85.59%的用户
     * */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0){
            return new int[][]{newInterval};
        }
        int[][] res = new int[intervals.length+1][2];
        int lPos = newInterval[0], rPos = newInterval[1], endPos = 0;
        boolean lFlag = false, rFlag = false;
        // 寻找左边界
        while (endPos < intervals.length) {
            int l = intervals[endPos][0], r = intervals[endPos][1];
            if (r < lPos){
                res[endPos] = intervals[endPos];
            }else{
                res[endPos][0] = Math.min(l, lPos);
                lFlag = true;
                break;
            }
            endPos++;
        }
        int inPos = endPos;
        if (!lFlag){    // 检查左边界是否已加载
            res[endPos][0] = lPos;
        }
        // 寻找右边界
        while (inPos < intervals.length){
            int l = intervals[inPos][0], r = intervals[inPos][1];
            if (rPos < l){
                res[endPos][1] = rPos;
                rFlag = true;
                break;
            }else if (rPos <= r){
                res[endPos][1] = r;
                inPos++;
                rFlag = true;
                break;
            }
            inPos++;
        }
        if (!rFlag){    // 检查右边界是否已加载
            res[endPos][1] = rPos;
        }
        endPos++;
        // 复制剩余数组
        while (inPos < intervals.length){
            res[endPos] = intervals[inPos];
            inPos++;
            endPos++;
        }
        return Arrays.copyOfRange(res,0, endPos);
    }

    public int[][] insert2(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

}
