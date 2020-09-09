package cn.czl.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/9/9 11:29
 * @description:
 */
public class CombinationSum_Normal {
    /**
     * 全递归(dfs)
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.92%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了50.19%的用户
     * */
    private List<List<Integer>> resList = new ArrayList<List<Integer>>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        Arrays.sort(candidates);
        List<Integer> searchList = new ArrayList<Integer>();
        dfs(candidates, target, searchList, 0);
        return resList;
    }
    public void dfs(int[] allNum, int target, List<Integer> searchList, int pos){
        if (pos == allNum.length){
            return;
        }
        if (target == 0){
            resList.add(new ArrayList<>(searchList));
            return;
        }
        dfs(allNum, target, searchList, pos+1);
        if (target - allNum[pos] >= 0){
//            dfs(allNum, target, searchList, pos+1);
            searchList.add(allNum[pos]);
            dfs(allNum, target - allNum[pos], searchList, pos);
            searchList.remove(searchList.size() - 1);
        }
    }

    /**
     * for循环内嵌递归(dfs)
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.92%的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了29.79%的用户
     * */
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private List<Integer> searchList = new ArrayList<Integer>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        deepSearch(candidates, target,0);
        return result;
    }
    public void deepSearch(int[] allNum, int target, int pos){
        if (target == 0){
            result.add(new ArrayList<>(searchList));
        }
        for (int i = pos; i < allNum.length; i++) {
            if (allNum[i] <= target){
                searchList.add(allNum[i]);
                deepSearch(allNum, target - allNum[i], i);
                searchList.remove(searchList.size() - 1);
            }else {
                break;
            }
        }
    }
}
