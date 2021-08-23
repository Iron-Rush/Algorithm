package cn.czl.list.generate;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/8/23 10:59
 * @description:
 *      1646. 获取生成数组中的最大值
 *      - 给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：
 *          - nums[0] = 0
 *          - nums[1] = 1
 *          - 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
 *          - 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
 *      - 返回生成数组 nums 中的 最大 值。
 *      示例 1：
 *          输入：n = 7
 *          输出：3
 *          解释：根据规则：
 *            nums[0] = 0
 *            nums[1] = 1
 *            nums[(1 * 2) = 2] = nums[1] = 1
 *            nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
 *            nums[(2 * 2) = 4] = nums[2] = 1
 *            nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
 *            nums[(3 * 2) = 6] = nums[3] = 2
 *            nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
 *          因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
 *      示例 2：
 *          输入：n = 2
 *          输出：1
 *          解释：根据规则，nums[0]、nums[1] 和 nums[2] 之中的最大值是 1
 *      示例 3：
 *          输入：n = 3
 *          输出：2
 *          解释：根据规则，nums[0]、nums[1]、nums[2] 和 nums[3] 之中的最大值是 2
 *      提示：0 <= n <= 100
 */
public class GetMaximumInGeneratedArray_Easy {

    @Test
    public void TestSolution(){
        System.out.println(getMaximumGenerated(7)); //[0, 1, 1, 2, 1, 3, 2, 3]
    }

    /**
     * 模拟
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.3 MB , 在所有 Java 提交中击败了 19.40% 的用户
     * */
    public int getMaximumGenerated(int n) {
        if(n == 0) return 0;
        int[] arr = new int[n + 1];
        int max = 1;
        arr[0] = 0;
        arr[1] = 1;
        for(int i = 1; i <= n; i++){
            if(2 <= 2 * i && 2 * i <= n){
                arr[2 * i] = arr[i];
                max = Math.max(max, arr[i]);
            }
            if(2 <= 2 * i + 1&& 2 * i + 1 <= n){
                int ans = arr[i] + arr[i + 1];
                arr[2 * i + 1] = ans;
                max = Math.max(max, ans);
            }
        }
        return max;
    }

    /**
     * 简化逻辑 - 反向模拟
     * */
    public int getMaximumGenerated2(int n) {
        if (n == 0) return 0;
        int[] arr = new int[n + 1];
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i / 2] + i % 2 * arr[i / 2 + 1];   // 奇数位/偶数位的推算不同
        }
        return Arrays.stream(arr).max().getAsInt();
    }
}
