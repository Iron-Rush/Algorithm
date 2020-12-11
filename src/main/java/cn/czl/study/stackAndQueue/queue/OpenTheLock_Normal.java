package cn.czl.study.stackAndQueue.queue;

import org.junit.jupiter.api.Test;
import sun.dc.pr.PRError;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author RedRush
 * @date 2020/12/11 16:22
 * @description:
 *      752. 打开转盘锁
 *      - 你有一个带有四个圆形拨轮的转盘锁。
 *          每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 *          每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。
 *          每次旋转都只能旋转一个拨轮的一位数字。
 *      - 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *      - 列表 deadends 包含了一组死亡数字，
 *      一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *      - 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，
 *      如果无论如何不能解锁，返回 -1。
 *      示例 1:
 *          输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 *          输出：6
 *      示例 2:
 *          输入: deadends = ["8888"], target = "0009"
 *          输出：1
 */
public class OpenTheLock_Normal {
    private String[] DEADENDS = {"0201","0101","0102","1212","2002"};
    private String TARGET = "0202";
    @Test
    public void TestSolution(){
        System.out.println(openLock(DEADENDS, TARGET));
    }

    /**
     * BFS-广度优先搜索(带HashSet记录走过的位置，和死锁位置)
     * 上拨/下拨工具类
     * 执行用时：91 ms, 在所有 Java 提交中击败了67.31%的用户
     * 内存消耗：44.1 MB, 在所有 Java 提交中击败了60.32%的用户
     * */
    public int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for (String temp : deadends) {
            deads.add(temp);
        }
        int step = 0;
        Queue<String > queue = new LinkedList<>();
        queue.add("0000");
        visited.add("0000");
        while (!queue.isEmpty()){
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                String cur = queue.poll();
                if (deads.contains(cur)){
                    continue;
                }
                if (cur.equals(target)){
                    return step;
                }
                for (int j = 0; j < 4; j++) {
                    String upOne = upOne(cur, j);
                    String downOne = downOne(cur, j);
                    if(visited.add(upOne)){
                        queue.add(upOne);
                    }
                    if (visited.add(downOne)){
                        queue.add(downOne);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    /**
     * 双向BFS(优化)    **双向BFS，须知道起点和终点位置
     * 执行用时：27 ms, 在所有 Java 提交中击败了98.70%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了97.70%的用户
     * */
    public int openLock2(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for (String temp : deadends) {
            deads.add(temp);
        }
        // 不用队列，改用Set快速判断元素是否存在
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();
        q1.add("0000");
        q2.add(target);
        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()){
            // 用于存储本次迭代结果的临时set(HashSet在遍历过程中不能修改)
            Set<String> temp = new HashSet<>();
            // 扩散q1
            for (String cur : q1) {
                if (deads.contains(cur)){
                    continue;
                }
                if (q2.contains(cur)){  // 若q2中存在当前数，则相遇->返回步数
                    return step;
                }
                visited.add(cur);
                // 存储每个扩散的可能性
                for (int i = 0; i < 4; i++) {
                    String up = upOne(cur, i);
                    String down = downOne(cur, i);
                    if (!visited.contains(up)){
                        temp.add(up);
                    }
                    if (!visited.contains(down)){
                        temp.add(down);
                    }
                }
            }
            step++;     // 步进
            q1 = q2;    // 交换q1,q2
            q2 = temp;  // 保存本轮扩散结果
        }
        return -1;
    }

    // 向上拨动
    String upOne(String s, int pos){
        char[] chars = s.toCharArray();
        if(chars[pos] == '9'){
            chars[pos] = '0';
        }else {
            chars[pos]++;
        }
        return new String(chars);
    }
    // 向下拨动
    String downOne(String s, int pos){
        char[] chars = s.toCharArray();
        if(chars[pos] == '0'){
            chars[pos] = '9';
        }else {
            chars[pos]--;
        }
        return new String(chars);
    }

}
