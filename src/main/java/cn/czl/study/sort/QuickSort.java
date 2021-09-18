package cn.czl.study.sort;

import cn.czl.utils.autoTest.ArraySortTest;
import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/9/18 14:20
 * @description:    快速排序
 *      时间复杂度:O(nlogn),有可能会退化到 O(n^2)
 *      空间复杂度:O(1)
 *      不是稳定的排序算法
 */
public class QuickSort {

    @Test
    public void TestSolution(){
        ArraySortTest.test("QuickSort", "quickSort");
    }
    // 快速排序入口函数
    public void quickSort(int[] arr){
        if (arr == null)    return;
        quickSort(arr, 0, arr.length-1);
    }
    // 快速排序递归函数
    private void quickSort(int[] arr, int start, int end){
        if (start >= end){
            return;
        }
        int mid = partition(arr, start, end);
        quickSort(arr, start, mid-1);
        quickSort(arr, mid+1, end);
    }
    // 快速排序分区函数
    private int partition(int[] arr, int start, int end){
        int target = arr[end];
        int l = start, r = start;
        for(; r <= end-1; r++){
            if(arr[r] < target){
                swap(arr, l++, r);
            }
        }
        swap(arr, l, end);
        return l;
    }
    // 交换数组中两个元素
    private void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
