package cn.czl.study.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/9/15 9:29
 * @description:
 *      插入排序算法：
 *          - 时间复杂度：O(nlogn)
 *          - 空间复杂度：O(n)
 *          - 是稳定的排序算法[即，大小相等的元素，前后位置不会改变]
 *          - 因为要额外消耗空间，当数组无序度很高时，效率要优于快速排序
 **/
public class MergeSort {

    // 归并排序 - 排序入口
    public void mergeSort(int[] arr){
        merge(arr, 0, arr.length - 1);
    }
    // 归并排序 - 递归函数
    private void merge(int[] arr, int start, int end){
        if (start >= end)   return;
        // 取中间位置mid分割处理数组
        int mid = (start + end) / 2;
        // 递归 - 分治处理
        merge(arr, start, mid);
        merge(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }
    // 两有序区间 合并函数( [start,mid],[mid+1,end] )
    private void merge(int[] arr, int start, int mid, int end){
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1;
        int k = 0;
        // 比较-插入元素
        while (i <= mid && j <= end){
            if (arr[i] < arr[j]){
                temp[k++] = arr[i++];
            }else {
                temp[k++] = arr[j++];
            }
        }
        // 将左侧剩余元素放入temp
        while (i <= mid){
            temp[k++] = arr[i++];
        }
        // 将右侧剩余元素放入temp
        while (j <= end){
            temp[k++] = arr[j++];
        }
        // 将排序后的temp数组赋值回arr的指定区间
        for (int idx = 0; idx < temp.length; idx++) {
            arr[start + idx] = temp[idx];
        }
    }

    @Test
    public void TestSolution(){
        int[] arr1 = {9,100,9,5,-1,51,20};
        System.out.println(Arrays.toString(arr1));
        mergeSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
