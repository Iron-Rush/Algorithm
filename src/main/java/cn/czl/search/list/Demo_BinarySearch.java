package cn.czl.search.list;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/12/1 15:12
 * @description: 二分法搜索
 */
public class Demo_BinarySearch {

    @Test
    public void TestBinarySearchClose(){
        int[] nums = {1,3,3,4,6,6,7};
        int target = 3; // target在左闭右闭区间，[left, right]
        /***********/
        int left = 0, right = nums.length-1;
        int res = -2;
        while (left <= right){  // 当left == right,区间[left, right]依然有效
            int middle = left + ((left + right)/2); // 防溢出，等同(left + right)/2
            if (nums[middle] > target){
                right = middle - 1; // target在区间[left, middle-1]
            }else if (nums[middle] < target){
                left = middle + 1;  // target在区间[middle+1, right]
            }else {     // nums[middle] == target;
                res = middle;
                break;
            }
        }
//        res = res == -2 ? left : res;
        res = res == -2 ? right + 1 : res;
        System.out.println(res);
    }

    @Test
    public void TestBinarySearchOpen(){
        int[] nums = {1,3,3,4,6,6,7};
        int target = 3; // target在左闭右开区间，[left, right)
        /***********/
        int left = 0, right = nums.length;
        int res = -2;
        while (left < right){   // left == right时，[left, right)是无效区间
            int middle = left + ((left + right)/2); // 防溢出，等同(left + right)/2
            if (nums[middle] > target){
                right = middle; // target在左区间,[left, middle)中
            }else if (nums[middle] < target){
                left = middle + 1;// target在右区间,[middle+1, right)中
            }else {     // nums[middle] == target;
                res = middle;
                break;
            }
        }
//        res = res == -2 ? left : res;
        res = res == -2 ? right : res;
        System.out.println(res);
    }
}
