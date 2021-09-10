package cn.czl.list.search;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/9/10 9:28
 * @description:
 *      - 给你一个数组，其中数组中的每个值与相邻元素之间的差值的绝对值是m，
 *          现在给你一个目标值k，找到数组中所有等于k的元素的索引，使用集合返回。
 *      - 遍历的元素越少越好，无序
 *      - 例：[1,2,3,2,1,0,-1,0,1] m=1 k=3 返回[2]
 */
public class FindK {

    public int findK(int[] arr, int m, int k){
        if (arr == null)    return -1;
        int idx = 0;
        int len = arr.length;
        while (idx < len){
            if (arr[idx] == k){
                return idx;
            }
            int step = Math.abs(arr[idx] - k)/m;
            idx += step;
        }
        return -1;
    }

    @Test
    public void TestSolution(){
        int[] arr1 = {1,2,3,2,1,0,-1,0,1};
        System.out.println(findK(arr1, 1, 2));
    }
}
