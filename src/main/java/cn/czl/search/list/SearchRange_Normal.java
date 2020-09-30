package cn.czl.search.list;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/9/29 9:40
 * @description:
 *      34. 在排序数组中查找元素的第一个和最后一个位置
 *          - 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 *          找出给定目标值在数组中的开始位置和结束位置。
 *          - 你的算法时间复杂度必须是 O(log n) 级别。
 *          - 如果数组中不存在目标值，返回 [-1, -1]。
 *      示例 1:
 *          输入: nums = [5,7,7,8,8,10], target = 8
 *          输出: [3,4]
 *      示例 2:
 *          输入: nums = [5,7,7,8,8,10], target = 6
 *          输出: [-1,-1]
 */
public class SearchRange_Normal {

//    private int TARGET = 6;
    private int TARGET = 4;
//    private int[] NUMS = new int[]{5,7,7,8,8,10};
    private int[] NUMS = new int[]{1,4};
    @Test
    public void TestSolution(){
        int[] res = searchRange(NUMS, TARGET);
        for (int temp : res) {
            System.out.print(temp + ",");
        }
    }

    /**
     * 垃圾代码= =,各种边界处理不妥
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了66.55%的用户
     * */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        int Lpos = 0, Rpos = nums.length-1, len = nums.length;
        if (Lpos == Rpos && nums[0] == target){
            return new int[]{0,0};
        }
        while (Lpos <= Rpos){
            int index = (Lpos + Rpos)/2;
            if (nums[index] > target){
                Rpos = index;
            }else if (nums[index] < target){
                Lpos = index;
            }else {
                Lpos = index;
                Rpos = index;
                while ((Lpos - 1 >= 0) && (nums[Lpos - 1] == target)){
                    Lpos--;
                }
                while ((Rpos + 1 < len) && (nums[Rpos + 1] == target)){
                    Rpos++;
                }
                if (Lpos != Rpos){
                    result[0] = Lpos;
                    result[1] = Rpos;
                    break;
                }else {
                    break;
                }
            }
            if (Lpos + 1 == Rpos){
                if (nums[Rpos] == target){
                    result[0] = Rpos;
                    result[1] = Rpos;
                }
                if (nums[Lpos] == target){
                    result[0] = Lpos;
                    result[1] = Lpos;
                }
                break;
            }
        }
        return result;
    }

}
