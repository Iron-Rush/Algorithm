package cn.czl.string.compare;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author RedRush
 * @date 2022/1/18 19:48
 * @description:
 *      539. 最小时间差
 *      - 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，
 *          找出列表中任意两个时间的最小时间差并以分钟数表示。
 *      示例 1：
 *          输入：timePoints = ["23:59","00:00"]
 *          输出：1
 *      示例 2：
 *          输入：timePoints = ["00:00","23:59","00:00"]
 *          输出：0
 *      提示：
 *          - 2 <= timePoints.length <= 2 * 10^4
 *          - timePoints[i] 格式为 "HH:MM"
 */
public class MinimumTimeDifference_Normal {

    @Test
    public void TestSolution(){
        List<String> time = new ArrayList<>();
        time.add("00:00");
        time.add("23:59");
        time.add("00:00");
        System.out.println(findMinDifference(time));
    }

    /**
     * [鸽巢原理]转换，排序 - 求最小区间
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.7 MB , 在所有 Java 提交中击败了 97.23% 的用户
     * */
    public int findMinDifference(List<String> timePoints) {
        int size = timePoints.size();
        // [鸽巢原理]如果长度超过1440，则说明必有一个时间是重复的
        if(size > 1440)    return 0;
        // 转换时间
        int[] minTrans = new int[size];
        for(int i = 0; i < size; i++){
            String time = timePoints.get(i);
            String hour = time.substring(0,2);
            String minute = time.substring(3,5);
            int curTime = Integer.valueOf(hour) * 60 + Integer.valueOf(minute);
            minTrans[i] = curTime;
        }
        // 排序
        Arrays.sort(minTrans);
        // 获取相邻时间中最小区间
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < size; i++){
            min = Math.min(min, minTrans[i] - minTrans[i-1]);
        }
        min = Math.min(min, 1440 - minTrans[size-1] + minTrans[0]);
        return min;
    }

    /**
     * [鸽巢原理]转换，排序 - 求最小区间
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 89.12% 的用户
     * 内存消耗： 38 MB , 在所有 Java 提交中击败了 69.65% 的用户
     * */
    public int findMinDifference2(List<String> timePoints) {
        int size = timePoints.size();
        // [鸽巢原理]如果长度超过1440，则说明必有一个时间是重复的
        if(size > 1440)    return 0;
        // 排序
        Collections.sort(timePoints);
        // 获取相邻时间中最小区间
        int min = Integer.MAX_VALUE;
        int first = getMinutes(timePoints.get(0));
        int preMinute = first;
        for(int i = 1; i < size; i++){
            int curMinute = getMinutes(timePoints.get(i));
            min = Math.min(min, curMinute - preMinute);
            preMinute = curMinute;
        }
        min = Math.min(min, 1440 - preMinute + first);
        return min;
    }
    // 转换时间
    private int getMinutes(String time){
        int minutes = ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60
                + (time.charAt(3) - '0') * 10 + time.charAt(4) - '0';
        return minutes;
    }
}
