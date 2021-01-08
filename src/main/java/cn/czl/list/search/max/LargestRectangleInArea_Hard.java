package cn.czl.list.search.max;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/12/26 10:46
 * @description:
 *      84. 柱状图中最大的矩形
 *      - 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *      - 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *      例：
 *          柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *          图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *          - 输入: [2,1,5,6,2,3]
 *          - 输出: 10
 */
public class LargestRectangleInArea_Hard {

    private int[] HEIGHTS = {2,1,5,6,2,3};

    @Test
    public void TestSolution(){
        System.out.println(largestRectangleArea(HEIGHTS));
    }

    /**
     * 暴力解法
     * 执行用时： 970 ms , 在所有 Java 提交中击败了 15.20% 的用户
     * 内存消耗： 40.4 MB , 在所有 Java 提交中击败了 6.39% 的用户
     * */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0, len = heights.length;
        for (int i = 0; i < len; i++) {
            int h = heights[i];
            int w = 1, l = i, r = i;
            while (l >= 1 && heights[i] <= heights[--l]){
                w++;
            }
            while (r <= len-2 && heights[i] <= heights[++r]){
                w++;
            }
            maxArea = Math.max(maxArea, h * w);
        }
        return maxArea;
    }

    /**
     * 单调栈
     * 执行用时： 22 ms , 在所有 Java 提交中击败了 32.01% 的用户
     * 内存消耗： 47.4 MB , 在所有 Java 提交中击败了 5.03% 的用户
     * */
    public int largestRectangleArea2(int[] heights) {
        int maxArea = 0, len = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] > heights[i]){
                int height = heights[stack.pollLast()];
                while (!stack.isEmpty() && heights[stack.peekLast()] == height){
                    stack.pollLast();
                }
                int width = stack.isEmpty() ? i : i - stack.peekLast() - 1;
                maxArea = Math.max(maxArea, width * height);
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()){
            int height = heights[stack.pollLast()];
            int width = stack.isEmpty() ? len : len - stack.peekLast() - 1;
            maxArea = Math.max(maxArea, width * height);
        }
        return maxArea;
    }

    /**
     * 单调栈
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 69.25% 的用户
     * 内存消耗： 39.1 MB , 在所有 Java 提交中击败了 94.36% 的用户
     * */
    public int largestRectangleArea3(int[] heights) {
        int maxArea = 0;
        int len = heights.length;
        int[] left = new int[len], right = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        stack.clear();
        for (int i = len - 1; i >= 0; i--) {
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? len : stack.peek());
            stack.push(i);
        }
        for (int i = 0; i < len; i++) {
            maxArea = Math.max(maxArea, (right[i] - left[i] - 1) * heights[i]);
        }
        return maxArea;
    }

    /**
     * 单调栈-优化
     * */
    public int largestRectangleArea4(int[] heights) {
        int maxArea = 0;
        int len = heights.length;
        int[] left = new int[len], right = new int[len];
        Arrays.fill(right, len);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        for (int i = 0; i < len; i++) {
            maxArea = Math.max(maxArea, (right[i] - left[i] - 1) * heights[i]);
        }
        return maxArea;
    }
}
