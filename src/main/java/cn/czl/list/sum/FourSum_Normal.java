package cn.czl.list.sum;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/10/12 14:06
 * @description:
 *          18. 四数之和
 *              给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 *              判断 nums 中是否存在四个元素 a，b，c 和 d ，
 *              使得 a + b + c + d 的值与 target 相等？
 *              找出所有满足条件且不重复的四元组。
 *          注意：答案中不可以包含重复的四元组。
 *          例：给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *          满足要求的四元组集合为：
 *          [ [-1,  0, 0, 1],
 *            [-2, -1, 1, 2],
 *            [-2,  0, 0, 2] ]
 */
public class FourSum_Normal {

    private int[] NUMS = new int[]{1, 0, -1, 0, -2, 2};
    private int TARGET = 0;

    @Test
    public void TestSolution(){
        List<List<Integer>> result = fourSum(NUMS, TARGET);
        System.out.println(result);
    }

    /**
     * 双指针，嵌套控制区间，内设双指针
     * 执行用时：21 ms, 在所有 Java 提交中击败了39.18%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了91.86%的用户
     * */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums.length < 4){
            return resList;
        }
        Arrays.sort(nums);
        for (int L = 0; L < nums.length - 2; L++) {
            if (L != 0 && nums[L] == nums[L - 1]){
                continue;
            }
            for (int R = nums.length-1; R > 2; R--) {
                if (R != nums.length-1 && nums[R] == nums[R+1]){
                    continue;
                }
                int Lpos = L + 1, Rpos = R-1;
                while (Lpos < Rpos){
                    int sum = nums[L] + nums[Lpos] + nums[Rpos] + nums[R];
                    if (sum == target){
                        resList.add(Arrays.asList(nums[L], nums[Lpos], nums[Rpos], nums[R]));
                        while (Lpos < Rpos && nums[Lpos] == nums[Lpos+1]){
                            Lpos++;
                        }
                        while (Lpos < Rpos && nums[Rpos] == nums[Rpos-1]){
                            Rpos--;
                        }
                        Lpos++;
                        Rpos--;
                    }else if (sum > target){
                        Rpos--;
                    }else {
                        Lpos++;
                    }
                }
            }
        }
        return resList;
    }
    /**
     * 双指针剪枝优化
     * 执行用时：6 ms, 在所有 Java 提交中击败了67.75%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了64.94%的用户
     * */
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> resList = new ArrayList<>();
        int len = nums.length;
        if (len < 4){
            return resList;
        }
        Arrays.sort(nums);
        for (int L = 0; L < len - 3; L++) {
            if (L != 0 && nums[L] == nums[L - 1]){
                continue;
            }
            // 剪枝
            if (nums[L] + nums[L+1] + nums[L+2] + nums[L+3] > target){
                break;
            }
            if (nums[L] + nums[len-1] + nums[len-2] + nums[len-3] < target){
                continue;
            }
            for (int R = len-1; R > L; R--) {
                if (R != len-1 && nums[R] == nums[R+1]){
                    continue;
                }
                int Lpos = L + 1, Rpos = R-1;
                while (Lpos < Rpos){
                    int sum = nums[L] + nums[Lpos] + nums[Rpos] + nums[R];
                    if (sum == target){
                        resList.add(Arrays.asList(nums[L], nums[Lpos], nums[Rpos], nums[R]));
                        while (Lpos < Rpos && nums[Lpos] == nums[Lpos+1]){
                            Lpos++;
                        }
                        while (Lpos < Rpos && nums[Rpos] == nums[Rpos-1]){
                            Rpos--;
                        }
                        Lpos++;
                        Rpos--;
                    }else if (sum > target){
                        Rpos--;
                    }else {
                        Lpos++;
                    }
                }
            }
        }
        return resList;
    }

    /**
     * 四指针
     * 执行用时：4 ms, 在所有 Java 提交中击败了89.69%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了79.01%的用户
     * */
    public List<List<Integer>> fourSum3(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);  // 便于后面的 “去重” 和 “剪枝”
        int length = nums.length;
        for (int index1 = 0; index1 < length - 3; index1++) {  // index1 为结果中 第一个值
            if (index1 > 0 && nums[index1] == nums[index1 - 1]) {
                continue;
            }

            /*
                剪枝：
                    1、当前最小和 > target，结束“当前层循环”
                    2、当前最大和 < target，跳过“当前循环”
             */
            int curMin = nums[index1] + nums[index1 + 1] + nums[index1 + 2] + nums[index1 + 3];
            if (curMin > target) {
                break;
            }
            int curMax = nums[index1] + nums[length - 1] + nums[length - 2] + nums[length - 3];
            if (curMax < target) {
                continue;
            }
            for (int index2 = index1 + 1; index2 < length - 2; index2++) {  // index2 为结果中 第2个值
                if (index2 > index1 + 1 && nums[index2] == nums[index2 - 1]) {
                    continue;
                }
                int index3 = index2 + 1;
                int maxIndex = length - 1;

                /*
                    剪枝：
                        1、当前最小和 > target，结束“当前层循环”
                        2、当前最大和 < target，跳过“当前循环”
                 */
                curMin = nums[index1] + nums[index2] + nums[index3] + nums[index3 + 1];
                if (curMin > target) {
                    break;
                }
                curMax = nums[index1] + nums[index2] + nums[maxIndex] + nums[maxIndex - 1];
                if (curMax < target) {
                    continue;
                }
                while (index3 < maxIndex) {  // index3 为结果中 第3个值
                    int curValue = nums[index1] + nums[index2] + nums[index3] + nums[maxIndex];
                    if (curValue == target) {
                        result.add(Arrays.asList(nums[index1], nums[index2], nums[index3], nums[maxIndex]));

                        /*
                            index3、maxIndex去重
                         */
                        index3++;
                        while (index3 < maxIndex && nums[index3] == nums[index3 - 1]) {
                            index3++;
                        }
                        maxIndex--;
                        while (index3 < maxIndex && nums[maxIndex] == nums[maxIndex + 1]) {
                            maxIndex--;
                        }
                    } else if (curValue > target) {
                        maxIndex--;
                    } else {
                        index3++;
                    }
                }
            }
        }
        return result;
    }
}
