package cn.czl.study.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/9/9 10:16
 * @description:插入排序算法：
 *      - 时间复杂度：O(n²)
 *      - 空间复杂度：O(1)
 *      - 不稳定的排序算法[即，大小相等的元素，前后位置可能改变]
 */
public class SelectionSort {

    /**
     * 选择排序1
     * 每次搜索到最小值后，再进行交换
     *      - 选择排序每次会从未排序区间中找到最小的元素，
     *          将其放到已排序区间的末尾
     * */
    public void selectionSort(int[] arr){
        if (arr == null || arr.length <= 1) return;
        int len = arr.length;
        for (int i = 0; i < len; i++) {         //[0,j)为已排序区间，[j,len)为未排序区间
            int minIdx = i;
            for (int j = i + 1; j < len; j++) { // 搜索未排序区间，最小值的下标
                if (arr[j] < arr[minIdx]){
                    minIdx = j;                 // 记录未排序区间，最小值下标
                }
            }
            if (minIdx != i){       // 如果最小值下标未变，则无需交换
                int temp = arr[i];  // 将未排序区间最小值追加到排序区间末尾
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }

    /**
     * 选择排序2
     * */
    public void selectionSort2(int[] arr){
        if (arr == null || arr.length <= 1) return;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int minVal = arr[i];
            for (int j = i; j < len; j++) {
                if (minVal > arr[j]){
                    int temp = arr[j];
                    arr[j] = minVal;
                    minVal = temp;
                }
            }
            arr[i] = minVal;
        }
    }

    @Test
    public void TestSolution(){
        int[] arr1 = {3,3,5,2,6,8,10,5,1};
        selectionSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
