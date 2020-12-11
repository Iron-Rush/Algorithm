package cn.czl.string.judge;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/12/11 9:33
 * @description:
 *      649. Dota2 参议院
 *      - Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)
 *      - 每一轮中，每一位参议员都可以行使两项权利中的一项：
 *          - 禁止一名参议员的权利：参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
 *          - 宣布胜利
 *      示例 1：
 *          输入："RD"     输出："Radiant"
 *      示例 2：
 *          输入："RDD"    输出："Dire"
 */
public class PredictPartyVictory_Normal {

    @Test
    public void TestSolution(){
        System.out.println(predictPartyVictory("DRDRDRRR"));
    }
    @Test
    public void TestMethod(){
        char[] sen = ".DD".toCharArray();
        System.out.println(usePower(sen, 1));
        System.out.println(Arrays.toString(sen));
    }

    /**
     * [模拟]规规矩矩的模拟。被禁言者，置为'.'不可发言
     * 执行用时：260 ms, 在所有 Java 提交中击败了10.46%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了75.78%的用户
     * */
    public String predictPartyVictory(String senate) {
        char[] sen = senate.toCharArray();
        while (true){
            for (int i = 0; i < sen.length; i++) {
                int result = usePower(sen, i);
                if(result == 1){
                    return sen[i] == 'R' ? "Radiant" : "Dire";
                }
            }
        }
    }
    // 行使权力函数。-1代表该人被禁言, 1代表该方胜利, 0代表正常行使权力
    int usePower(char[] sen, int pos){
        int len = sen.length;
        switch (sen[pos]){
            case 'R':
                for (int i = 1; i < len; i++) {
                    if(sen[(pos+i) % len] == 'D'){
                        sen[(pos+i) % len] = '.';
                        return 0;
                    }
                }
                return 1;
            case 'D':
                for (int i = 1; i < len; i++) {
                    if(sen[(pos+i) % len] == 'R'){
                        sen[(pos+i) % len] = '.';
                        return 0;
                    }
                }
                return 1;
            default:
                return -1;
        }
    }

    /**
     * 双队列，分别存储R/D方的议员对应的索引
     * 执行用时：11 ms, 在所有 Java 提交中击败了53.88%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了24.22%的用户
     * */
    public String predictPartyVictory2(String senate) {
        Queue<Integer> rQueue = new LinkedList<>();
        Queue<Integer> dQueue = new LinkedList<>();
        char[] sen = senate.toCharArray();
        int len = senate.length();
        // 初始化两队列
        for (int i = 0; i < len; i++) {
            if (sen[i] == 'R'){
                rQueue.offer(i);
            }else {
                dQueue.offer(i);
            }
        }
        while (!rQueue.isEmpty() && !dQueue.isEmpty()){
            // 两个队列同时出队一名议员
            int rSenate = rQueue.poll();
            int dSenate = dQueue.poll();
            // 比较谁先出列。后出列者会被禁言，先出者参加下一轮。
            if(rSenate > dSenate){
                dQueue.offer(dSenate + len);
            }else {
                rQueue.offer(rSenate + len);
            }
        }
        return dQueue.isEmpty() ? "Radiant" : "Dire";
    }
}
