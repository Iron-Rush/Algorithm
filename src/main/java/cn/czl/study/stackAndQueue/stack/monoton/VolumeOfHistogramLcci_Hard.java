package cn.czl.study.stackAndQueue.stack.monoton;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2021/4/2 13:46
 * @description:
 *      面试题 17.21. 直方图的水量
 *      - 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，
 *        最后直方图能存多少水量?直方图的宽度为 1。
 *      - 由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，
 *        在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。
 *      示例:
 *          输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 *          输出: 6
 */
public class VolumeOfHistogramLcci_Hard {


    /**
     * 动态规划 - 计算每个位置最高的水柱，累加
     * 求出当前位置，左侧最高点，右侧最高点，取最小值，
     * 减去当前高度，即为当前水柱高度
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.90% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 13.40% 的用户
     * */
    public int trap(int[] height) {
        int len = height.length;
        if(len <= 2){
            return 0;
        }
        int[] lMax = new int[len];
        int[] rMax = new int[len];
        lMax[0] = height[0];
        rMax[len-1] = height[len - 1];
        for (int i = 1; i < len; i++) {
            lMax[i] = Math.max(lMax[i-1], height[i]);
        }
        for (int i = len - 1; i >= 0; i--) {
            rMax[i] = Math.max(rMax[i+1], height[i]);
        }
        int area = 0;
        for (int i = 0; i < len; i++) {
            area += Math.min(lMax[i], rMax[i]) - height[i];
        }
        return area;
    }

    /**
     * 单调栈 - 单调递减栈
     * 每遇见比当前栈顶元素大的，则可继续灌水。
     * [栈顶元素出栈，下一个栈顶元素为左侧墙，i为右侧墙，
     * 灌水：高度为左侧墙与右侧墙中较矮的那侧，宽度为右侧墙- 左侧墙 - 1]
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 20.64% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 26.90% 的用户
     * */
    public int trap2(int[] height) {
        int area = 0, len = height.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]){
                int top = stack.pop();
                if (stack.isEmpty()){   // 如果当前位置左侧无更高点，则无法灌水
                    break;
                }
                int left = stack.peek();
                int curWidth = i - left - 1;
                // 当前高度 = 左右两侧，较低值 - 当前高度
                int curHeight = Math.min(height[left], height[i]) - height[top];
                area += curWidth * curHeight;
            }
            stack.push(i);
        }
        return area;
    }

    /**
     * 双指针
     * 根据左右边界，得出当前高度较小一侧的水柱高度
     * 将当前高度累加至面积。
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 40.59% 的用户
     * 内存消耗： 37.9 MB , 在所有 Java 提交中击败了 88.44% 的用户
     * */
    public int trap3(int[] height) {
        int area = 0, len = height.length;
        int l = 0, r = len - 1;
        int lMax = 0, rMax = 0;
        while (l < r){
            lMax = Math.max(lMax, height[l]);
            rMax = Math.max(rMax, height[r]);
            if(height[l] < height[r]){
                area += lMax - height[l];
                l++;
            }else {
                area += rMax - height[r];
                r--;
            }
        }
        return area;
    }
}
