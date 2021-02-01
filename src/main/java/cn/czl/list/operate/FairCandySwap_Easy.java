package cn.czl.list.operate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author RedRush
 * @date 2021/2/1 9:43
 * @description:
 *      888. 公平的糖果棒交换
 *      - 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
 *      - 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
 *      - 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
 *      - 如果有多个答案，你可以返回其中任何一个。保证答案存在。
 *      示例 1：
 *          输入：A = [1,1], B = [2,2]
 *          输出：[1,2]
 *      示例 2：
 *          输入：A = [1,2], B = [2,3]
 *          输出：[1,2]
 *      示例 3：
 *          输入：A = [2], B = [1,3]
 *          输出：[2,3]
 *      示例 4：
 *          输入：A = [1,2,5], B = [2,4]
 *          输出：[5,4]
 *      提示：
 *       1 <= A.length <= 10000
 *       1 <= B.length <= 10000
 *       1 <= A[i] <= 100000
 *       1 <= B[i] <= 100000
 *       保证爱丽丝与鲍勃的糖果总量不同。
 *       答案肯定存在。
 */
public class FairCandySwap_Easy {

    /**
     * 排序 + 双指针
     * 执行用时： 14 ms , 在所有 Java 提交中击败了 42.59% 的用户
     * 内存消耗： 40 MB , 在所有 Java 提交中击败了 73.23% 的用户
     * */
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        if(sumA == sumB){
            return null;
        }
        Arrays.sort(A);
        Arrays.sort(B);
        int[] res = new int[2];
        int target = (sumA - sumB)/2;    // target为A与平均数的查
        for(int i = 0, j = 0; i < A.length && j < B.length; ){
            if(A[i] - B[j] < target){          // 根据差值移动双指针
                i++;
            }else if(A[i] - B[j] > target){
                j++;
            }else {     // A[i] - B[j] == target
                res[0] = A[i];
                res[1] = B[j];
                break;
            }
        }
        return res;
    }

    /**
     * 借助set比对 A 和 B 的差值
     * 执行用时： 20 ms , 在所有 Java 提交中击败了 30.69% 的用户
     * 内存消耗： 39.9 MB , 在所有 Java 提交中击败了 75.13% 的用户
     * */
    public int[] fairCandySwap2(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int target = (sumA - sumB)/2;
        int[] res = new int[2];
        Set<Integer> set = new HashSet<>();
        for (int a : A) {
            set.add(a);
        }
        for (int b : B) {
            int t = target - b;
            if(set.contains(t)){
                res[0] = t;
                res[1] = b;
                break;
            }
        }
        return res;
    }
}
