package cn.czl.search.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/9/5 19:16
 * @description:
 *      60.第k个排列
 *      给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *      给定 n 和 k，返回第 k 个排列。
 */
public class PermutationSequence_Normal {

    private static int N = 4;
    private static int K = 9;

    @Test
    public void TestSolution(){
        System.out.println(getPermutation2(N, K));
    }

    /**
	* 这种方法，可以通过88/200 个用例
	* 但是会超出时间限制
	* 本质上就是想拿到全排列
    */
    // 全排列数组存储于res
    public List<List<Integer>> res = new ArrayList<>();
    public int count = 0;
    public String getPermutation(int n, int k) {
        int[] sequence = new int[n];
        // 初始化数列全部元素(1 - n)
        for (int i = 0; i < n; i++){
            sequence[i] = i + 1;
        }
        count = k;
        List<Integer> array = new ArrayList<>();
        getArray(sequence, array);
        return toStr(res.get(k));
    }
    // 递归-获取全部排列组合(方式一)
    private void getArray(int[] sequence, List<Integer> array){
        // 当组合数组array长度，等于全部元素长度，则可组合数组,终止递归
        if (array.size() == sequence.length){
            res.add(new ArrayList<>(array));
            return;
        }
        // 递归-获取数组
        for (int i = 0; i < sequence.length; i++){
            if (!array.contains(sequence[i])){
                array.add(sequence[i]);
                getArray(sequence, array);
                array.remove(array.size() - 1);
            }
        }
    }

    // 递归-获取全部排列组合(方式2)
    private void getArray2(int[] sequence, List<Integer> array){
        // 当组合数组array长度,等于全部元素长度,则可组合数组,终止递归
        if (array.size() == sequence.length){
            // 当count等于零时,第一个添加至res,res第一个即为所求数组
            if (count == 0){
                res.add(new ArrayList<>(array));
                return;
            // 当count不等于零时，表示仍未达到第k位，count--
            }else {
                count--;
                return;
            }
        }
        // 递归-获取数组
        for (int i = 0; i < sequence.length; i++){
            if (!array.contains(sequence[i])){
                array.add(sequence[i]);
                getArray(sequence, array);
                array.remove(array.size() - 1);
            }
        }
    }
    // 将得到数组array构建成string返回
    private static String toStr(List<Integer> array){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            sb.append(array.get(i));
        }
        return sb.toString();
    }


    /**
     * 数学方法，计算每位数字
     * StringBuilder实现：
     * 执行用时：3 ms, 在所有 Java 提交中击败了44.35%的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了94.80%的用户
     * 数组实现：
     * 执行用时：2 ms, 在所有 Java 提交中击败了86.48%的用户
     * 内存消耗：37.5 MB, 在所有 Java 提交中击败了42.06%的用户
     * */
//    private StringBuilder sb = new StringBuilder();
    private List<Integer> array = new ArrayList<>();
    private int size = 0;

    public String getPermutation2(int n, int k) {
        List<Integer> sequence = new ArrayList<>();
        k--;
        size = n;
        // 初始化数列全部元素(1 - n)
        for (int i = 0; i < n; i++){
            sequence.add(i+1);
        }
        while (array.size() != n){
            k = getIndex(k, sequence);
        }
        return toStr(array);
//        while (sb.length() != n){
//            k = getIndex(k, sequence);
//        }
//        return sb.toString();
    }

    private int getIndex(int k, List<Integer> sequence){
        int unit = 1;
        for (int i = 1; i < size; i++) {
            unit *= i;
        }
        int pos;
        if (k < unit){
            pos = 0;
        }else {
            pos = k / unit;
        }
        array.add(sequence.get(pos));
//        sb.append(sequence.get(pos));
        sequence.remove(pos);
        k = k % unit;
        size--;
        return k;
    }

}
