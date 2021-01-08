package cn.czl.list.search.count;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/12/24 9:18
 * @description:
 *          135. 分发糖果
 *          - 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，
 *          老师会根据每个孩子的表现，预先给他们评分。
 *          - 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 *              - 每个孩子至少分配到 1 个糖果。
 *              - 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 *          - 那么这样下来，老师至少需要准备多少颗糖果呢？
 *          示例 1:
 *              输入: [1,0,2]
 *              输出: 5
 *              解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 *          示例 2:
 *              输入: [1,2,2]
 *              输出: 4
 *              解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *                    第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 */
public class Candy_Hard {
    private int[] RATINGS1 = {1,0,2};       // 5
    private int[] RATINGS2 = {1,2,2};       // 4
    private int[] RATINGS3 = {1,2,3,3,2};   // 9
    private int[] RATINGS4 = {1,2,3,3,5};   // 9
    private int[] RATINGS5 = {1,2,3,4,5};   // 15
    private int[] RATINGS6 = {1,2,3,4,2,1};   // 15


    @Test
    public void TestSolution(){
//        System.out.println(candy2(RATINGS1));
//        System.out.println(candy2(RATINGS2));
//        System.out.println(candy2(RATINGS3));
//        System.out.println(candy2(RATINGS4));
//        System.out.println(candy2(RATINGS5));
        System.out.println(candy2(RATINGS6));
    }

    /**
     * 贪心算法1
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 99.87% 的用户
     * 内存消耗： 39.6 MB , 在所有 Java 提交中击败了 41.68% 的用户
     * */
    public int candy(int[] ratings) {
        int count = 1, upcount = 1, downcount = 0;
        for (int i = 1; i < ratings.length;) {
            while (i < ratings.length && ratings[i-1] <= ratings[i]){
                if(downcount != 0){
                    upcount = 1;
                }
                downcount = 0;
                upcount = ratings[i] == ratings[i-1] ? 1 : upcount + 1;
                count += upcount;
                i++;
            }
            while (i < ratings.length && ratings[i-1] > ratings[i]){
                downcount++;
                if(downcount == upcount){
                    downcount++;
                }
                count += downcount;
                i++;
            }
        }
        return count;
    }

    /**
     * 贪心算法2
     * 执行用时：2 ms , 在所有 Java 提交中击败了 99.87% 的用户
     * 内存消耗： 39.6 MB , 在所有 Java 提交中击败了 43.15% 的用户
     * */
    public int candy2(int[] ratings) {
        // count 为当前所需糖果数
        // upcount/downcount 分别为上升序列长度，下降序列长度
        int count = 1, upcount = 1, downcount = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i-1] < ratings[i]){ // 如果当前为上升序列
                if(downcount != 0){     // 根据下降序列长度判断他所处位置
                    upcount = 1;
                }
                downcount = 0;
                upcount++;
                count += upcount;
            }else if(ratings[i-1] == ratings[i]){   // 如果分数相同，则可以欺负这小孩儿
                downcount = 0;
                upcount = 1;
                count ++;
            }else {     // 下降序列
                downcount++;
                if(downcount == upcount){   // 这里要特别注意。如果下降序列要比上升序列长
                    downcount++;    // 那么往后的计数，要把峰值也算进下降序列中了，
                }                   // 否则下降序列首位同学要比他隔壁上升序列队尾那个孩子拿的多了……
                count += downcount;
            }
        }
        return count;
    }

    /**
     * 双数组，分别找到满足 从左侧 / 右侧 发糖的最优解
     * 然后同步遍历 - 比对双数组，取同位上的较大值
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 66.08% 的用户
     * 内存消耗： 39.9 MB , 在所有 Java 提交中击败了 24.42% 的用户
     * */
    public int candy3(int[] ratings) {
        int len = ratings.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int count = 0;
        for (int i = 0; i < len; i++) {
            if(i != 0 && ratings[i] > ratings[i-1]){
                left[i] = left[i - 1] + 1;
            }else {
                left[i] = 1;
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            if(i != len - 1 && ratings[i] > ratings[i+1]){
                right[i] = right[i + 1] + 1;
            }else {
                right[i] = 1;
            }
        }
        for (int i = 0; i < len; i++) {
            count += Math.max(left[i], right[i]);
        }
        return count;
    }

    /**
     * 双数组优化
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 66.08% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 91.00% 的用户
     * */
    public int candy4(int[] ratings) {
        int len = ratings.length;
        int[] left = new int[len];
        int count = 0, right = 0;
        for (int i = 0; i < len; i++) {
            if(i != 0 && ratings[i] > ratings[i-1]){
                left[i] = left[i-1] + 1;
            }else {
                left[i] = 1;
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            if(i != len-1 && ratings[i] > ratings[i+1]){
                right ++;
            }else {
                right = 1;
            }
            count += Math.max(right, left[i]);
        }
        return count;
    }
}
