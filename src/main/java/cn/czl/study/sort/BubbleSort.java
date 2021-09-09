package cn.czl.study.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/9/9 9:46
 * @description:
 *      冒泡排序算法：
 *              - 时间复杂度：O(n²)
 *              - 空间复杂度：O(1)
 *              - 是稳定的排序算法[即，大小相等的元素，前后位置不会改变]
 *              - 同为时间复杂度为O(n²)的算法，插入排序和选择排序都要快于它，
 *                因为冒泡排序时，交换元素位置更为繁琐
 */
public class BubbleSort {


    public void bubbleSort(int[] arr){
        if (arr == null)    return;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            boolean exchangeFlag = false;       // 如果本次遍历无交换操作，则说明剩余区间已有序，无需继续排序
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    exchangeFlag = true;
                }
            }
            if (!exchangeFlag){
                break;
            }
        }
    }

    @Test
    public void TestSolution(){
        int[] arr1 = {3,3,1,2,6,8,10,5};
        bubbleSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
