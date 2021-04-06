package cn.czl.list.slidingWindow;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author RedRush
 * @date 2021/2/3 9:43
 * @description:
 *      480. 滑动窗口中位数
 *      - 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；
 *      此时中位数是最中间的两个数的平均数。
 *       例如：
 *          [2,3,4]，中位数是 3
 *          [2,3]，中位数是 (2 + 3) / 2 = 2.5
 *      - 给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。
 *      窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后
 *      得到的新窗口中元素的中位数，并输出由它们组成的数组。
 *       示例：
 *          给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 *          窗口位置                      中位数
 *          ---------------               -----
 *          [1  3  -1] -3  5  3  6  7       1
 *           1 [3  -1  -3] 5  3  6  7      -1
 *           1  3 [-1  -3  5] 3  6  7      -1
 *           1  3  -1 [-3  5  3] 6  7       3
 *           1  3  -1  -3 [5  3  6] 7       5
 *           1  3  -1  -3  5 [3  6  7]      6
 *          因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 *      - 提示：
 *          你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
 *          与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 */
public class SlidingWindowMedian_Hard {

    private int[] NUMS1 = {1,3,-1,-3,5,3,6,7};
    private int K1 = 3;

    @Test
    public void TestSolution(){
        System.out.println(Arrays.toString(medianSlidingWindow(NUMS1, K1)));
    }

    /**
     * 暴力解，每次截取数组-排序-找到对应中位数
     * 超出时间限制
     * */
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            res[i] = getMedian(nums, i, i + k);
        }
        return res;
    }
    // 根据数组，起始位置-终止位置，返回对应的中位数
    double getMedian(int[] nums, int start, int end){
        int[] curNum = Arrays.copyOfRange(nums, start, end);
        Arrays.sort(curNum);
        double res = 0;
        int len = end - start;
        if(len % 2 == 0){
            res = curNum[len / 2] / 2.0 + curNum[len/2 - 1] / 2.0;
        }else {
            res = curNum[len / 2];
        }
        return res;
    }

}
