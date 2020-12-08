package cn.czl.string.operate;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/12/8 9:15
 * @description:
 *      842. 将数组拆分成斐波那契序列
 *      - 给定一个数字字符串 S，比如 S = "123456579"，
 *      我们可以将它分成斐波那契式的序列 [123, 456, 579]。
 *      - 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
 *          1. 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
 *          2. F.length >= 3；
 *          3. 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
 *      - 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
 *      - 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
 */
public class SplitIntoFibonacciSequence_Normal {
    private String S1 = "123456579";    // [123,456,579]
    private String S2 = "11235813";     // [1,1,2,3,5,8,13]
    private String S3 = "112358130";    // []
    private String S4 = "0123";         // []
    private String S5 = "1101111";      // [110, 1, 111]/[11, 0, 11, 11]
    private String S6 = "214748364721474836422147483641";

    @Test
    public void TestSolution(){
        System.out.println(splitIntoFibonacci2(S6));
    }
    @Test
    public void TestMethod(){
        System.out.println(checkCur());
    }

    /**
     * 回溯，如果当前数字符合累加条件，则深入递归。
     * 执行用时：6 ms, 在所有 Java 提交中击败了41.78%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了45.41%的用户
     * */
    List<Integer> res = new ArrayList<>();
    public List<Integer> splitIntoFibonacci(String S) {
        generateHelper(S, 0);
        return res;
    }
    // 生成数组-递归帮助类
    boolean generateHelper(String S, int start){
        if(S.length() == start){
            return res.size() >= 3;
        }
        int curSize = Math.min(10, S.length()-start);
        for (int i = 1; i <= curSize; i++) {
            if (generateNumber(S, start, i)){
                if (checkCur() && generateHelper(S, start + i)){
                    return true;
                }
                res.remove(res.size()-1);
            }else {
                break;
            }
        }
        return false;
    }
    // 根据字符串及指针，生成数字，添加至数组
    boolean generateNumber(String S, int start, int size){
        long curVal = Long.valueOf(S.substring(start, start + size));
        if(curVal > Integer.MAX_VALUE
                ||(S.charAt(start) == '0' && size != 1)){
            return false;
        }
        res.add((int)curVal);
        return true;
    }
    // 检查当前数组末尾是否合法
    boolean checkCur(){
        int curSize = res.size();
        if (curSize < 3 || (res.get(curSize-1) - res.get(curSize-2) == res.get(curSize-3))){
            return true;
        }
        return false;
    }


    /**
     * 回溯2[初步剪枝]
     * 执行用时：3 ms, 在所有 Java 提交中击败了89.50%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了73.85%的用户
     * */
    public List<Integer> splitIntoFibonacci2(String S) {
        LinkedList<Integer> res=new LinkedList<>();
        dfs(S,0,res);
        return res;
    }
    public boolean dfs(String S,int index,List<Integer> list){
        if (index == S.length()) {
            return list.size() >= 3;
        }
        int end = S.charAt(index) == '0' ? index + 1 : Math.min(index + 10, S.length());
        for (int i = index; i < end; i++) {
            long longVal = Long.valueOf(S.substring(index, i + 1));
            //长度大于10,或者Long解析出来大于INT_MAX了就直接break
            if (longVal > Integer.MAX_VALUE) {
                break;
            }
            int curNum = (int)longVal;
            if(list.size() >= 2){
                int preSum = list.get(list.size()-1) + list.get(list.size()-2);
                if(preSum > curNum){
                    continue;
                }else if(preSum < curNum){
                    break;
                }
            }
            list.add(curNum);
            if (dfs(S, i + 1, list)) {
                return true;
            }
            list.remove(list.size()-1);
        }
        return false;
    }

    /**
     * 回溯 + 剪枝 [根据当前和与目标和的差距，适时进行递归。效果明显]
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了99.77%的用户
     * */
    List<Integer> splitIntoFibonacci3(String S){
        List<Integer> list = new ArrayList<>();
        backtrack(list, S, 0, 0, 0);
        return list;
    }
    // 回溯函数
    boolean backtrack(List<Integer> list, String S, int index, int sum, int prev){
        if(index == S.length()){
            return list.size() >= 3;
        }
        long curVal = 0;
        int end = S.charAt(index) == '0' ? index + 1 : Math.min(index + 10, S.length());
        for (int i = index; i < end; i++) {
            curVal = curVal * 10 + S.charAt(i)-'0';
            if (curVal > Integer.MAX_VALUE){
                break;
            }
            int curInt = (int)curVal;
            if(list.size() >= 2){
                if(curInt < sum){
                    continue;
                }else if (curInt > sum){
                    break;
                }
            }
            list.add(curInt);
            if(backtrack(list, S, i+1, prev + curInt, curInt)){
                return true;
            }else {
                list.remove(list.size() - 1);
            }
        }
        return false;
    }

}
