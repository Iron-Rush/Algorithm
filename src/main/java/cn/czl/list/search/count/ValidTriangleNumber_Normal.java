package cn.czl.list.search.count;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/8/4 16:25
 * @description:
 *      611. 有效三角形的个数
 *      - 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 *      示例 1:
 *          输入: [2,2,3,4]
 *          输出: 3
 *          解释:
 *              有效的组合是:
 *              2,3,4 (使用第一个 2)
 *              2,3,4 (使用第二个 2)
 *              2,2,3
 *      注意:
 *          数组长度不超过1000。
 *          数组里整数的范围为 [0, 1000]。
 */
public class ValidTriangleNumber_Normal {

    private int[] NUMS1 = {2,2,3,4};
    private int[] NUMS2 = {1,2,3,4,5,6};    // 7

    @Test
    public void TestSolution(){
        System.out.println(triangleNumber3(NUMS1));
        System.out.println(triangleNumber3(NUMS2));
    }

    /**
     * 暴力解 - 三层循环
     * 执行用时： 1817 ms , 在所有 Java 提交中击败了 5.06% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 6.83% 的用户
     * */
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3){
            return 0;
        }
        int ans = 0;
        Arrays.sort(nums);
        for (int l = 0; l < nums.length - 2; l++) {
            for (int r = nums.length - 1; r >= 2; r--) {
                int rVal = nums[r], lVal = nums[l];
                int mid = l + 1;
                while (mid < r){
                    if (nums[mid] + lVal > rVal){
                        ans++;
                    }
                    mid++;
                }
            }
        }
        return ans;
    }

    /**
     * 排序 + 双指针[1] 固定右端搜索
     * 执行用时： 30 ms , 在所有 Java 提交中击败了 88.61% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 15.40% 的用户
     * */
    public int triangleNumber2(int[] nums) {
        if (nums == null || nums.length < 3){
            return 0;
        }
        int ans = 0;
        Arrays.sort(nums);
        for(int r = nums.length - 1; r >= 2; r--){
            int l = 0, mid = r - 1;
            while(mid > l){
                // 满足条件则说明 l - mid中全部l均满足要求
                if(nums[l] + nums[mid] > nums[r]){
                    ans += mid - l;
                    mid--;
                }else{  // 否则l左移，增加nums[l] + nums[mid]的和
                    l++;
                }
            }
        }
        return ans;
    }

    /**
     * 排序 + 双指针[2] 固定左端搜索
     * 执行用时： 37 ms , 在所有 Java 提交中击败了 77.81% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 30.58% 的用户
     * */
    public int triangleNumber3(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int ans = 0, len = nums.length;
        Arrays.sort(nums);
        for (int l = 0; l < len; l++) {
            int r = l;
            for (int mid = l + 1; mid < len; mid++) {
                // 获得当前 l 和 mid 下，满足等式的最大 r
                while(r + 1 < len && nums[l] + nums[mid] > nums[r + 1]){
                    r++;
                }
                ans += Math.max(r - mid, 0);
            }
        }
        return ans;
    }
}
