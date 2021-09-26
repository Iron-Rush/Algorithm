package cn.czl.study.search;

import cn.czl.utils.autoTest.GenerateArray;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author RedRush
 * @date 2021/9/26 16:03
 * @description:    二分搜索法
 *          时间复杂度：O(logn)
 *          要求数据必须为有序数据
 *          适用数据结构：顺序表结构(数组)
 *          适用于数据量较大的静态数据(数据量小直接遍历即可，数据量过大需要占用极大的连续内存空间)
 *              - 静态数据：不经常进行增删操作，但会经常访问查询。这样一次排序后即可实现快速搜索
 */
public class BinarySearch {

    public int bSearch(int[] arr, int key){
        int low = 0, high = arr.length-1;
        while(low <= high){
            int mid = (low + high) >>> 1;
            if (arr[mid] < key){
                low = mid + 1;
            }else if (arr[mid] > key){
                high = mid - 1;
            }else {
                return mid;
            }
        }
        // 无匹配数据
        return -1;
    }


    @Test
    public void TestSolution(){
        boolean flag = true;
        for (int i = 0; i < 100; i++) {
            int[] arr = GenerateArray.generate(1000);
            int target = arr[40];
            Arrays.sort(arr);
            int a = bSearch(arr, target);
            int b = Arrays.binarySearch(arr, target);
            flag &= a == b;
        }
        System.out.println(flag ? "算法正确" : "算法错误！");
    }
}
