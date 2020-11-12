package cn.czl.list.unique;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/11/12 9:40
 * @description:
 *      922. 按奇偶排序数组 II
 *      - 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 *      - 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 *      - 你可以返回任何满足上述条件的数组作为答案。
 *      示例：
 *          输入：[4,2,5,7]     输出：[4,5,2,7]
 *          解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *      提示：
 *          1. 2 <= A.length <= 20000
 *          2. A.length % 2 == 0
 *          3. 0 <= A[i] <= 1000
 */
public class SortArrayByParity2_Easy {

    private int[] A1 = new int[]{4,2,5,7};

    @Test
    public void TestSolution(){
        System.out.println(Arrays.toString(sortArrayByParityII(A1)));
    }

    /**
     * 建立新数组，通过奇/偶指针赋值
     * 执行用时：3 ms, 在所有 Java 提交中击败了78.68%的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了39.07%的用户
     * */
    public int[] sortArrayByParityII(int[] A) {
        int[] B = new int[A.length];
        int oddCount = 1, evenCount = 0;
        for(int i = 0; i < A.length; i ++){
            if(A[i] % 2 == 0){
                B[evenCount] = A[i];
                evenCount += 2;
            }else{
                B[oddCount] = A[i];
                oddCount += 2;
            }
        }
        return B;
    }

    /**
     * 修改原数组，通过额外空间，存储不符合排序规则的指针
     * 执行用时：6 ms, 在所有 Java 提交中击败了14.04%的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了5.05%的用户
     * */
    public int[] sortArrayByParityII2(int[] A) {
        Stack<Integer> oddStack = new Stack<>();   // 奇数存储栈
        Stack<Integer> evenStack = new Stack<>();  // 偶数存储栈
        for (int i = 0; i < A.length; i++) {
            if (i % 2 == 0){
                if (A[i] % 2 != 0){ // 偶数位为奇数时
                    if (evenStack.isEmpty()){
                        oddStack.push(i);
                    }else {
                        swap(i, evenStack.pop(), A);
                    }
                }
            }else {
                if (A[i] % 2 != 1){ // 奇数位为偶数时
                    if (oddStack.isEmpty()){
                        evenStack.push(i);
                    }else {
                        swap(i, oddStack.pop(), A);
                    }
                }
            }
        }
        return A;
    }

    /**
     * 修改原数组，仅对偶数位排序
     * 若偶数位不符合规则，则从奇数位寻找偶数进行交换
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了91.94%的用户
     * */
    public int[] sortArrayByParityII3(int[] A) {
        int odd = 1;
        for (int even = 0; even < A.length; even += 2) {
            if (A[even] % 2 != 0){
                while (A[odd] % 2 != 0){
                    odd += 2;
                }
                swap(odd, even, A);
            }
        }
        return A;
    }

    /**
     * 交换数组arr，x和y位置的元素
     * */
    void swap(int x, int y, int[] arr){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
