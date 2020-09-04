package cn.czl.search.list;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/9/3 17:16
 * @description:
 */
public class FindNumber {

    private int[] nums = {2, 3, 1, 0, 2, 5, 3};

    @Test
    public void testSolution(){
        System.out.println(findRepeatNumber5(nums));
    }
    /**
     * 执行用时：3 ms,在所有 Java 提交中击败了60.38%的用户
     * 内存消耗：47.3 MB,在所有 Java 提交中击败了92.70%的用户
     * */
    public int findRepeatNumber1(int[] nums){
        Arrays.sort(nums);
        for (int i = nums.length -1; i >= 0;) {
            System.out.println(nums[i]);
            if (nums[i] == nums[--i]){
                return nums[i];
            }
        }
        return 0;
    }
    /**
     * 执行用时：3 ms,在所有 Java 提交中击败了60.38%的用户
     * 内存消耗：47.8 MB,在所有 Java 提交中击败了57.32%的用户
     * */
    public int findRepeatNumber2(int[] nums){
        int i = 0;
        Arrays.sort(nums);
        while (true){
            if (nums[i] == nums[++i]){
                return nums[i];
            }
        }
    }

    /**
     * 放HashSet,利用set中元素不可重复
     * 执行用时：6 ms,在所有 Java 提交中击败了41.02%的用户
     * 内存消耗：49.9 MB,在所有 Java 提交中击败了9.96%的用户
     * */
    public int findRepeatNumber3(int[] nums){
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums){
            if (!set.add(num)){
                return num;
            }
        }
        return 0;
    }

    /**
     * 放ArrayList里试试
     * 结果，超时= =
     * */
    public int findRepeatNumber4(int[] nums) {
        List<Integer> numList = new ArrayList<>();
        for (int temp : nums) {
            if (numList.contains(temp)){
                return temp;
            }
            numList.add(temp);
        }
        return 0;
    }
    /**
     * 1ms范例
     * 鸽巢原理(nums 里的所有数字都在 0～n-1 的范围内,所以不会越界)
     * 按照index=value进行归位，当归位至该位置已存在相等数字时，则返回该值。
     * */
    public int findRepeatNumber5(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == i) continue;
            if (nums[nums[i]] == nums[i]) return nums[i];
            int temp = nums[i];
            nums[nums[i]] = nums[i];
            nums[temp] = temp;
        }
        return -1;
    }
    /**
     * 最快的范例(鸽巢原理优化版本)
     * 执行用时：0 ms,在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：47.9 MB,在所有 Java 提交中击败了49.48%的用户
     * */
    public int findRepeatNumber6(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            //如果该数字没有不和他的索引相等
            while(nums[i] != i) {
                //重复返回
                if(nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                //不重复交换
                int temp = nums[i];
                nums[temp] = nums[i];
                nums[i] = nums[temp];
            }
        }
        return 0;
    }

    /**
     * 不修改原数据，用时间换空间：对0到n-1进行二分查找，时间O(nlogn)，空间O(1)
     * 该方法需要数字一定有重复的才行，因此如果题目修改在长度为n，数字在1到n-1的情况下，此时数组中至少有一个数字是重复的，该方法可以通过。
     * */
    /**
     * java中有三种移位运算符
     *      左移运算符，num << 1,相当于num乘以2
     *      右移运算符，num >> 1,相当于num除以2
     *      符号右移，忽略符号位，空位都以0补齐
     * */
     public int findRepeatNumber7(int[] nums) {
         //统计nums中元素位于0到m的数量，如果数量大于这个值，那么重复的元素肯定是位于0到m的
         int min = 0 ;
         int max = nums.length-1;
         while(min<max){
             int mid = (max+min)>>1;    // 位运算，小数点右移一位，相当于num/2
             int count = countRange(nums,min,mid);
             if(count > mid-min+1) {
                 max = mid;
             }else{
                 min = mid+1;
             }
         }
         min=max;
         return min;
     }
     //统计范围内元素数量,时间O(n)
     private int countRange(int[] nums,int min,int max){
         int count = 0 ;
         for(int num:nums){
             if(num>=min && num<=max){
                 count++;
             }
         }
         return count;
     }
}
