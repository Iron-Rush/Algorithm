package cn.czl.list.compare;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RedRush
 * @date 2020/11/18 9:32
 * @description:
 *      134. 加油站
 *      - 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *      - 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 *      - 你从其中的一个加油站出发，开始时油箱为空。如果你可以绕环路行驶一周，
 *      则返回出发时加油站的编号，否则返回 -1。
 *      示例：
 *          输入: gas  = [1,2,3,4,5]   cost = [3,4,5,1,2]
 *          输出: 3
 *      解释:
 *          从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 *          开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 *          开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 *          开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 *          开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 *          开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 */
public class CanCompleteCircuit_Normal {

    private int[] GAS1 = {1,2,3,4,5};
    private int[] COST1 = {3,4,5,1,2};
    private int[] GAS2 = {5,1,2,3,4};
    private int[] COST2 = {4,4,1,5,1};

    @Test
    public void TestSolution(){
        System.out.println(canCompleteCircuit2(GAS2, COST2));
    }

    /**
     * 计算出每段路程的净油量，再择点
     * 执行用时：183 ms, 在所有 Java 提交中击败了10.06%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了79.20%的用户
     * */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int allGas = 0, allWay = 0;
        int len = gas.length;
        int[] memory = new int[len];
        for (int i = 0; i < len; i++) {
            allGas += gas[i];
            allWay += cost[i];
            memory[i] = gas[i] - cost[i];
        }
        if (allWay > allGas){
            return -1;
        }
        for (int i = 0; i < len; i++) {
            int flag = 0, count = 0, pos = i;
            if (memory[pos] >= 0){
                while (flag >= 0){
                    if (count == len){
                        return i;
                    }
                    flag += memory[pos++];
                    pos %= len;
                    count++;
                }
            }
        }
        return -1;
    }

    /**
     * 优化指针移动，路程油量计算方式
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了52.67%的用户
     * */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int len = gas.length;
        for (int i = 0; i < len;) {
            int flag = 0, count = 0, pos = i;
            while (flag >= 0){
                if (count == len){
                    return i;
                }
                flag += gas[pos] - cost[pos];
                pos = (pos + 1) % len;
                count++;
            }
            i = i + count;
        }
        return -1;
    }

    /**
     * 获取 累计-油量净值最小之后的点
     * 该点即为油量开始有富余的点
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了84.96%的用户
     * */
    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int len = gas.length;
        int rest = 0;
        int minIndex = 0, minRest = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            rest += gas[i] - cost[i];
            if (rest < minRest){
                minRest = rest;
                minIndex = i;
            }
        }
        return rest < 0 ? -1 : (minIndex + 1) % len;
    }
}
