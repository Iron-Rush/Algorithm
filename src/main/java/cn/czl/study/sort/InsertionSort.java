package cn.czl.study.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/9/9 9:59
 * @description:
 *      插入排序算法：
 *          - 时间复杂度：O(n²)
 *          - 空间复杂度：O(1)
 *          - 是稳定的排序算法[即，大小相等的元素，前后位置不会改变]
 *          - O(n²)时间复杂度下，相对最优的排序算法
 */
public class InsertionSort {


    /**
     * 插入排序
     * */
    public void insertionSort(int[] arr){
        if(arr == null || arr.length <= 1)    return;
        int len = arr.length;
        for (int i = 1; i < len; i++) {     // [0,i)为已排序区间，[i,len)为未排序区间
            int val = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {   // 查找数据插入位置
                if(arr[j] > val){
                    arr[j + 1] = arr[j];  // 数据移动
                }else{
                    break;
                }
            }
            arr[j + 1] = val;      // 插入数据
        }
    }

    @Test
    public void TestSolution(){
        int[] arr1 = {3,3,5,2,6,8,10,5,1};
        insertionSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
