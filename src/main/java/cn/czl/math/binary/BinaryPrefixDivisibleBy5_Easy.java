package cn.czl.math.binary;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author RedRush
 * @date 2021/1/14 9:18
 * @description:
 *          1018. 可被 5 整除的二进制前缀
 *          - 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
 *          - 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
 *          示例 1：
 *              输入：[0,1,1]
 *              输出：[true,false,false]
 *              解释：
 *                  输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。
 *                  只有第一个数可以被 5 整除，因此 answer[0] 为真。
 *          示例 2：
 *              输入：[1,1,1]
 *              输出：[false,false,false]
 *          示例 3：
 *              输入：[0,1,1,1,1,1]
 *              输出：[true,false,false,false,true,false]
 *          示例 4：
 *              输入：[1,1,1,0,1]
 *              输出：[false,false,false,false,false]
 *          提示：
 *              1 <= A.length <= 30000
 *              A[i] 为 0 或 1
 */
public class BinaryPrefixDivisibleBy5_Easy {

    private int[] A1 = {0,1,1};         // 3
    private int[] A2 = {1,1,1};         // 7
    private int[] A3 = {0,1,1,1,1,1};   // 31
    private int[] A4 = {1,1,1,0,1};     // 29

    @Test
    public void TestSolution(){
        for (int i = 0; i < 40; i+=5) {
            System.out.println(i + ":" + Integer.toBinaryString(i));
        }
//        System.out.println(prefixesDivBy5(A1));
//        System.out.println(prefixesDivBy5(A2));
//        System.out.println(prefixesDivBy5(A3));
//        System.out.println(prefixesDivBy5(A4));
    }

    /**
     * 传入A过长时，转化为10进制后，int会溢出
     * */
    public List<Boolean> prefixesDivBy5(int[] A) {
        int len = A.length, x = 0, five = 0;
        List<Boolean> result = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            x = x << 1 | A[i];
            while(five < x){
                five += 5;
            }
            result.add(five == x);
        }
        return result;
    }

    /**
     * 每次将当前拼接的数字对10取余，控制数字长度
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 92.76% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 21.41% 的用户
     * */
    public List<Boolean> prefixesDivBy52(int[] A) {
        int len = A.length, x = 0;
        List<Boolean> result = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            x = x << 1 | A[i];
            x %= 10;
            result.add(x % 5 == 0);
        }
        return result;
    }

    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 92.76% 的用户
     * 内存消耗： 39.5 MB , 在所有 Java 提交中击败了 8.25% 的用户
     * */
    public List<Boolean> prefixesDivBy53(int[] A) {
        int len = A.length, x = 0;
        List<Boolean> result = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            x = ((x << 1) + A[i]) % 5;
            result.add(x == 0);
        }
        return result;
    }
}
