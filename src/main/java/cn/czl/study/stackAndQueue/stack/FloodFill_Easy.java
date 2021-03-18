package cn.czl.study.stackAndQueue.stack;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2021/3/18 15:37
 * @description:
 *      733. 图像渲染
 *      - 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
 *      - 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
 *      - 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
 *      - 最后返回经过上色渲染后的图像。
 *      示例 1:
 *          输入:
 *              image = [[1,1,1],[1,1,0],[1,0,1]]
 *              sr = 1, sc = 1, newColor = 2
 *          输出: [[2,2,2],[2,2,0],[2,0,1]]
 *          解析:
 *              在图像的正中间，(坐标(sr,sc)=(1,1)),
 *              在路径上所有符合条件的像素点的颜色都被更改成2。
 *              注意，右下角的像素没有更改为2，
 *              因为它不是在上下左右四个方向上与初始点相连的像素点。
 *      注意:
 *          image 和 image[0] 的长度在范围 [1, 50] 内。
 *          给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
 *          image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
 */
public class FloodFill_Easy {

    private int[][] IMAGE1 = {{1,1,1}, {1,1,0}, {1,0,1}};

    @Test
    public void TestSolution(){
        System.out.println(Arrays.deepToString(floodFill3(IMAGE1, 1, 1, 2)));
    }

    /**
     * DFS - 借住栈
     * 如果当前格与初始格颜色一致，则将该坐标入栈
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 27.19% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 69.83% 的用户
     * */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image == null || image.length == 0 || image[0].length == 0){
            return image;
        }
        int curColor = image[sr][sc];
        if(curColor == newColor){
            return image;
        }
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{sr, sc});
        int yRange = image.length;
        int xRange = image[0].length;
        while (!stack.isEmpty()){
            int[] pos = stack.pop();
            int y = pos[0], x = pos[1];
            image[y][x] = newColor;
            if(y - 1 >= 0 && image[y - 1][x] == curColor){
                stack.push(new int[]{y-1, x});
            }
            if(y + 1 < yRange && image[y + 1][x] == curColor){
                stack.push(new int[]{y+1, x});
            }
            if(x - 1 >= 0 && image[y][x - 1] == curColor){
                stack.push(new int[]{y, x-1});
            }
            if(x + 1 < xRange && image[y][x + 1] == curColor){
                stack.push(new int[]{y, x+1});
            }
        }
        return image;
    }

    /**
     * DFS - 递归实现
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 91.96% 的用户
     * 内存消耗： 39.1 MB , 在所有 Java 提交中击败了 95.12% 的用户
     * */
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        if (image != null && image.length != 0 && image[sr][sc] != newColor){
            int target = image[sr][sc];
            int yEdge = image.length, xEdge = image[0].length;
            dfs(image, target, newColor, sc, sr, xEdge, yEdge);
        }
        return image;
    }

    // 深度优先搜索 - 为image上色
    void dfs(int[][] image, int target, int color, int x, int y, int xEdge, int yEdge){
        if(x >= 0 && y >= 0 && x < xEdge && y < yEdge && image[y][x] == target){
            image[y][x] = color;
            dfs(image, target, color, x-1, y, xEdge, yEdge);
            dfs(image, target, color, x+1, y, xEdge, yEdge);
            dfs(image, target, color, x, y-1, xEdge, yEdge);
            dfs(image, target, color, x, y+1, xEdge, yEdge);
        }
    }

    /**
     * BFS
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 27.19% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 86.18% 的用户
     * */
    public int[][] floodFill3(int[][] image, int sr, int sc, int newColor) {
        if (image != null && image.length != 0 && image[sr][sc] != newColor){
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{sr, sc});
            int[][] direct = {{0,1},{0,-1},{1,0},{-1,0}};
            int xEdge = image[0].length, yEdge = image.length;
            int target = image[sr][sc];
            image[sr][sc] = newColor;
            while (!queue.isEmpty()){
                int[] pos = queue.poll();
                int y = pos[0], x = pos[1];
                for (int[] dir : direct) {
                    int y1 = y + dir[0];
                    int x1 = x + dir[1];
                    if(x1 >= 0 && y1 >= 0 && x1 < xEdge && y1 < yEdge && image[y1][x1] == target){
                        queue.offer(new int[]{y1, x1});
                        image[y1][x1] = newColor;
                    }
                }
            }
        }
        return image;
    }
}
