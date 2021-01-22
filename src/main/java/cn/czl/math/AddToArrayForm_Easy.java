package cn.czl.math;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2021/1/22 9:24
 * @description:
 *      989. 数组形式的整数加法
 *      - 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 *      - 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 *      示例 1：
 *          输入：A = [1,2,0,0], K = 34
 *          输出：[1,2,3,4]
 *          解释：1200 + 34 = 1234
 *      示例 2：
 *          输入：A = [2,7,4], K = 181
 *          输出：[4,5,5]
 *          解释：274 + 181 = 455
 *      示例 3：
 *          输入：A = [2,1,5], K = 806
 *          输出：[1,0,2,1]
 *          解释：215 + 806 = 1021
 *      示例 4：
 *          输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 *          输出：[1,0,0,0,0,0,0,0,0,0,0]
 *          解释：9999999999 + 1 = 10000000000
 *      提示：
 *          1 <= A.length <= 10000
 *          0 <= A[i] <= 9
 *          0 <= K <= 10000
 *          如果 A.length > 1，那么 A[0] != 0
 */
public class AddToArrayForm_Easy {

    private int[] A1 = {1,2,0,0};
    private int[] A2 = {2,7,4};
    private int[] A3 = {2,1,5};
    private int[] A4 = {9,9,9,9,9,9,9,9,9,9};
    private int[] A5 = {1};

    @Test
    public void TestSolution(){
        System.out.println(addToArrayForm2(A1, 34));     // 1234
        System.out.println(addToArrayForm2(A2, 181));    // 455
        System.out.println(addToArrayForm2(A3, 806));    // 1021
        System.out.println(addToArrayForm2(A4, 1));      // 10000000000
        System.out.println(addToArrayForm2(A5, 33));
    }

    /**
     * 借助队列，将数字存入队列，再进行操作
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 56.68% 的用户
     * 内存消耗： 40.6 MB , 在所有 Java 提交中击败了 15.97% 的用户
     * */
    public List<Integer> addToArrayForm(int[] A, int K) {
        Queue<Integer> num = new LinkedList<>();
        while (K !=0){
            num.add(K % 10);
            K /= 10;
        }
        List<Integer> res = new LinkedList<>();
        boolean flag = false;
        int maxSize = Math.max(A.length, num.size()), alen = A.length;
        for (int i = 0; i < maxSize; i++) {
            int a = i < alen ? A[alen - i - 1] : 0;
            int b = !num.isEmpty() ? num.poll() : 0;
            int cur = a + b;
            if (flag){
                cur++;
            }
            flag = cur > 9;
            res.add(0,cur % 10);
        }
        if(flag){
            res.add(0, 1);
        }
        return res;
    }

    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 86.63% 的用户
     * 内存消耗： 40.6 MB , 在所有 Java 提交中击败了 18.61% 的用户
     * */
    public List<Integer> addToArrayForm2(int[] A, int K) {
//        List<Integer> res = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        int len = A.length;
        boolean flag = false;
        for (int i = len-1; i >= 0 || K != 0; i--) {
            int a = i >= 0 ? A[i] : 0;
            int b = K % 10;
            K /= 10;
            int cur = a + b;
            if(flag){
                cur++;
            }
            flag = cur > 9;
            res.addFirst(cur % 10);
        }
        if(flag){
            res.addFirst(1);
        }
        return res;
    }

    /**
     * 基于K直接计算当前位数字
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 98.45% 的用户
     * 内存消耗： 40.5 MB , 在所有 Java 提交中击败了 22.58% 的用户
     * */
    public List<Integer> addToArrayForm3(int[] A, int K) {
        LinkedList<Integer> ret = new LinkedList<>();
        int i = A.length - 1;
        int lastNum = K;
        while(i >= 0 || lastNum > 0){
            if (i >= 0) {
                lastNum += A[i];
            }
            ret.addFirst(lastNum % 10);
            lastNum /= 10;
            i--;
        }
        return ret;
    }

}
