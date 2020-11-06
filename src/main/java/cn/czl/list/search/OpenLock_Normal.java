package cn.czl.list.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author RedRush
 * @date 2020/11/5 10:33
 * @description:
 *      752. 打开转盘锁
 *      - 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字：
 *      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 *      每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。
 *      每次旋转都只能旋转一个拨轮的一位数字。
 *      - 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *      - 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，
 *      这个锁将会被永久锁定，无法再被旋转。
 *      - 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，
 *      - 如果无论如何不能解锁，返回 -1。
 */
public class OpenLock_Normal {

    public int openLock(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set<String> deads = new HashSet<>();
        for (String temp : deadends) {
            deads.add(temp);
        }
        // 记录已穷举密码
        Set<String> visited = new HashSet<>();
        // 初始化队列
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");
        int count = 0;
        while (!queue.isEmpty()){
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                String cur = queue.poll();
                // 终止条件判断
                if (deads.contains(cur)){
                    continue;
                }
                if (cur.equals(target)){
                    return count;
                }
                // 推测8种情况，并加入队列
                for (int j = 0; j < 4; j++) {
                    String up = upOne(cur, j);
                    String down = downOne(cur, j);
                    if (!visited.contains(up)){
                        queue.offer(up);
                        visited.add(up);
                    }
                    if (!visited.contains(down)){
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            count++;    // 增加步数
        }
        return -1;  // 若穷举过程中未返回，则说明无解
    }

    // 向上拨动
    String upOne(String s, int pos){
        char[] chars = s.toCharArray();
        if (chars[pos] == '9'){
            chars[pos] = '0';
        }else {
            chars[pos] += 1;
        }
        return new String(chars);
    }

    // 向下拨动
    String downOne(String s, int pos){
        char[] chars = s.toCharArray();
        if (chars[pos] == '0'){
            chars[pos] = '9';
        }else {
            chars[pos] -= 1;
        }
        return new String(chars);
    }

}
