package cn.czl.list.operate;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2021/8/17 9:53
 * @description:
 *      551. 学生出勤记录 I
 *      - 给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况
 *          （缺勤、迟到、到场）。记录中只含下面三种字符：
 *      - 'A'：Absent，缺勤
 *      - 'L'：Late，迟到
 *      - 'P'：Present，到场
 *      如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 *      - 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 *      - 学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
 *      如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。
 *      示例 1：
 *          输入：s = "PPALLP"
 *          输出：true
 *          解释：学生缺勤次数少于 2 次，且不存在 3 天或以上的连续迟到记录。
 *      示例 2：
 *          输入：s = "PPALLL"
 *          输出：false
 *          解释：学生最后三天连续迟到，所以不满足出勤奖励的条件。
 *      提示：
 *      - 1 <= s.length <= 1000
 *      - s[i] 为 'A'、'L' 或 'P'
 */
public class StudentAttendanceRecord_Easy {

    private String S1 = "PPALLP";
    private String S2 = "PPALLL";

    @Test
    public void TestSolution(){
        System.out.println(checkRecord(S1));
        System.out.println(checkRecord(S2));
    }

    /**
     * 分别统计迟到/缺勤日期，如果当前日期未迟到，则迟到日期清零
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.4 MB , 在所有 Java 提交中击败了 78.73% 的用户
     * */
    public boolean checkRecord(String s) {
        char[] chs = s.toCharArray();
        int late = 0, absent = 0;
        for (char ch : chs) {
            if (ch == 'L'){
                if (late == 2){
                    return false;
                }else {
                    late++;
                }
            }else{
                late = 0;
                if(ch == 'A'){
                    if (absent == 1){
                        return false;
                    }else {
                        absent++;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 一行实现逻辑判断
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.6 MB , 在所有 Java 提交中击败了 40.14% 的用户
     * */
    public boolean checkRecord2(String s) {
        return s.indexOf('A') == s.lastIndexOf('A') && !s.contains("LLL");
    }
}
