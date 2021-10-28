package cn.czl.string.judge;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/10/28 8:52
 * @description:
 *      869. 重新排序得到 2 的幂
 *      给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 *      如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 *      示例 1：
 *          输入：1
 *          输出：true
 *      示例 2：
 *          输入：10
 *          输出：false
 *      示例 3：
 *          输入：16
 *          输出：true
 *      示例 4：
 *          输入：24
 *          输出：false
 *      示例 5：
 *          输入：46
 *          输出：true
 *      提示： 1 <= N <= 10^9
 */
public class ReorderedPowerOf2_Normal {

    @Test
    public void TestSoltion(){
        System.out.println(reorderedPowerOf2(822));
    }

    /**
     * 构建相等长度中符合条件的答案字典
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.93% 的用户
     * 内存消耗： 35.3 MB , 在所有 Java 提交中击败了 75.52% 的用户
     * */
    public boolean reorderedPowerOf2(int n) {
        if(n == 1)  return true;    // n^0 = 1 特判
        int len = String.valueOf(n).length();   // 转为String得出当前数字长度
        int curLen = 1;     // 初始化答案长度
        int basic = 2;      // 用于计算2的n次幂
        // 初始化字典(2的幂在相同长度上停留不会超过四次，因此仅将符合长度要求的答案加进字典中)
        int[][] dict = new int[4][10];  // 每一行字典记录一条答案，统计该答案每个数字出现的次数
        int idx = 0;                    // 字典游标
        while (curLen < len + 1){
            // 2的n次幂长度与传入数字长度相同时，则开始写入字典
            if (curLen == len){
                // 统计该答案每个数字出现频率
                int temp = basic;
                while(temp != 0){
                    dict[idx][temp%10]++;
                    temp /= 10;
                }
                idx++;
            }
            // 更新答案及答案长度
            basic *= 2;
            curLen = String.valueOf(basic).length();
        }
        // 计算传入数字每个数字出现频率
        int[] find = new int[10];
        for (int i = 0; i < len; i++) {
            find[n%10]++;
            n /= 10;
        }
        // 遍历字典，查看是否有相同数据
        for(int[] d : dict){
            if (Arrays.equals(d, find)){
                return true;
            }
        }
        return false;
    }

    /**
     * 词频统计改进 - 一边计算一边比较(长度相同即比较)
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.93% 的用户
     * 内存消耗： 35.3 MB , 在所有 Java 提交中击败了 69.71% 的用户
     * */
    public boolean reorderedPowerOf22(int n) {
        int len = String.valueOf(n).length();
        int[] find = new int[10];
        while (n != 0){
            find[n%10]++;
            n /= 10;
        }
        int basic = 0, curLen = 1;
        while (curLen < len + 1){
            int temp = (int)Math.pow(2, basic++);
            curLen = String.valueOf(temp).length();
            // 2的n次幂长度与传入数字长度相同时，则开始写入字典
            if (curLen == len){
                int[] answer = new int[10];
                // 统计该答案每个数字出现频率
                while(temp != 0){
                    answer[temp%10]++;
                    temp /= 10;
                }
                if (Arrays.equals(answer, find))    return true;
            }
        }
        return false;
    }

    @Test
    public void TestMethod(){
        long basic = 2;
        int maxLen = 0, preLen = 0;
        int counter = 1, maxCount = 0;
        while (basic < Integer.MAX_VALUE){
            System.out.println(basic);
            int len = String.valueOf(basic).length();
            maxLen = Math.max(maxLen, len);
            if (len == preLen){
                counter++;
            }else{
                counter = 1;
            }
            maxCount = Math.max(maxCount, counter);
            preLen = len;
            basic *= 2;
        }
        System.out.println("maxLen = " + maxLen);
        System.out.println("maxCount = " + maxCount);
    }
}
