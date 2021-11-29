package cn.czl.study.sort;

import cn.czl.utils.auto.test.ArraySortTest;
import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/9/17 17:50
 * @description:    计数排序
 */
public class CountingSort {

    public void countingSort(int[] arr){
        if (arr == null)    return;
        int maxVal = 0;
        for(int num : arr){
            maxVal = Math.max(maxVal, num);
            if (num < 0)    throw new RuntimeException("数组内存在负数，无法直接使用计数排序");
        }
        int[] counter = new int[maxVal + 1];
        for(int num : arr){
            counter[num]++;
        }
        for (int i = 1; i < counter.length; i++) {
            counter[i] += counter[i-1];
        }
        int[] ans = new int[arr.length];
        for(int num : arr){
            int idx = --counter[num];
            ans[idx] = num;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ans[i];
        }
    }

    @Test
    public void TestSolution(){
        ArraySortTest.setRange(0, 100);
//        ArraySortTest.setMethodName("countingSort");
        ArraySortTest.test("CountingSort");
    }

    public void sort(int[] arr){

    }
}
