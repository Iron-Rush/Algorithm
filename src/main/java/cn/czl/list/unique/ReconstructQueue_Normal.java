package cn.czl.list.unique;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/11/16 9:56
 * @description:
 *      406. 根据身高重建队列
 *      - 假设有打乱顺序的一群人站成一个队列。
 *      每个人由一个整数对(h, k)表示，其中h是这个人的身高，
 *      k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *      k代表的是，现在这个队里，自己前面的人里面，h比自己大的人-的个数。
 *      - 注意：总人数少于1100人。
 *      示例：
 *          输入: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *          输出: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class ReconstructQueue_Normal {

    private int[][] PEOPLE = new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};

    @Test
    public void TestSolution(){
        int[][] res = reconstructQueue2(PEOPLE);
        System.out.println(Arrays.deepToString(res));
    }

    /**
     * 高 -> 低 思考
     * 按照身高降序排序，身高相同按k降序
     * 按照身高从高->低插入，每次插入时已插入的人身高均>=自己身高。
     * 因此只需按照自己的k作为index插入即可
     * 执行用时：7 ms, 在所有 Java 提交中击败了99.62%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了81.55%的用户
     * */
    public int[][] reconstructQueue(int[][] people) {
//        // lambda写法1
//        Arrays.sort(people, (person1, person2) -> person1[0] == person2[0] ? person1[1]-person2[1] : person2[0] - person1[0]);
//        // lambda写法2
//        Arrays.sort(people, (person1, person2) -> {
//            if (person1[0] != person2[0]) {
//                return person2[0] - person1[0];
//            } else {
//                return person1[1] - person2[1];
//            }
//        });
        Arrays.sort(people, new Comparator<int[]>() {   // 身高降序，升高相同按k降序
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> res = new ArrayList<int[]>();
        for (int[] person : people) {
            res.add(person[1], person);
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 低 -> 高 思考
     * 执行用时：24 ms, 在所有 Java 提交中击败了8.49%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了93.56%的用户
     * */
    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, (p1, p2) ->{    // 按照h升序，h相同按k降序排列
            return p1[0] == p2[0] ? p2[1] - p1[1] : p1[0] - p2[0];
        });
        int[][] res= new int[people.length][];
        for (int[] p : people) {    // 从左向右，计数插入。因为已入队人均矮于自己，计数空位等于自己的k时插入
            int count = p[1], pos = 0;// count为需要跳过的空位数，pos为从左向右的指针
            while (res[pos] != null || count != 0){
                if(res[pos] == null){ // 如果该位为空，则记录空位数
                    count--;          // 否则仅移动指针
                }
                pos++;
            }
            res[pos] = p;
        }
        return res;
    }

}
