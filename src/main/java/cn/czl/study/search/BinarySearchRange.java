package cn.czl.study.search;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/9/27 9:41
 * @description:    二分搜索 - 重复元素边界处理
 */
public class BinarySearchRange {

    /**
     * 二分搜索 - 带重复元素，搜索数组中最左侧的key
     * */
    public int binarySearchLeftRangeA(int[] arr, int key){
        int low = 0, high = arr.length - 1;
        while(low <= high){
            int mid = (low + high) >>> 1;
            if (arr[mid] > key){
                high = mid - 1;
            }else if (arr[mid] < key){
                low = mid + 1;
            }else {
                if (mid == 0 || arr[mid - 1] != key){
                    return mid;
                }else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 二分搜索 - 带重复元素，搜索数组中最左侧的key
     * high先卡到第一个小于key的位置
     * 用左侧区间向右侧逼近,当low > high时，且arr[low] 满足条件时，该low即为所求
     * */
    public int binarySearchLeftRangeB(int[] arr, int key){
        int low = 0, high = arr.length - 1;
        while (low <= high){
            int mid = (low + high) >>> 1;
            if (arr[mid] >= key){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        if (low < arr.length && arr[low] == key){
            return low;
        }else {
            return -1;
        }
    }


    /**
     * 二分搜索 - 带重复元素，搜索数组中最右侧的key
     * */
    public int binarySearchRightRangeA(int[] arr, int key){
        int low = 0, high = arr.length - 1;
        while(low <= high){
            int mid = (low + high) >>> 1;
            if(arr[mid] > key){
                high = mid - 1;
            }else if (arr[mid] < key){
                low = mid + 1;
            }else {
                if (mid == arr.length - 1 || arr[mid + 1] != key){
                    return mid;
                }else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找第一个 >= key 的元素
     * */
    public int binartSearchFistBiggerNum(int[] arr, int key){
        int len = arr.length;
        int low = 0, high = len - 1;
        while(low <= high){
            int mid = (low + high) >>> 1;
            if (arr[mid] >= key){   // 向前搜索，搜索该区间内第一个元素
                if (high == 0 || arr[high - 1] < key){
                    return mid;
                }else {
                    high = mid - 1;
                }
//            }else if (arr[mid] < key){
            }else {
                low = mid + 1;
            }
        }
        return -1;
    }
    /**
     * 查找最后一个 <= key 的元素
     * */
    public int binarySearchLastLowerNum(int[] arr, int key){
        int low = 0, high = arr.length - 1;
        while (low <= high){
            int mid = (low + high) >>> 1;
            if(arr[mid] > key) {
                high = mid -1;
//            }else if(arr[mid] <= target){
            }else{      // 向后搜索，寻找该区间内最后一个元素
                if (mid == arr.length - 1 || arr[mid + 1] > key){
                    return mid;
                }else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void TestSolution(){
        int[] arr = {1,1,1,2,2,3,10,10};
        System.out.println(binarySearchLeftRangeA(arr, 1));
        System.out.println(binarySearchLeftRangeB(arr, 1));
        System.out.println(binarySearchLeftRangeA(arr, 2));
        System.out.println(binarySearchLeftRangeB(arr, 2));
        System.out.println(binarySearchLeftRangeA(arr, 11));
        System.out.println(binarySearchLeftRangeB(arr, 11));

        System.out.println(binarySearchLastLowerNum(arr, 1));
    }
}
