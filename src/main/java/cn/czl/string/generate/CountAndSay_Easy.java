package cn.czl.string.generate;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/11/27 14:39
 * @description:
 *      38. 外观数列
 *      - 给定一个正整数 n ，输出外观数列的第 n 项。
 *      示例 ：
 *          输入：n = 4
 *          输出："1211"
 *      解释：
 *          countAndSay(1) = "1"
 *          countAndSay(2) = 读 "1" = 一 个 1 = "11"
 *          countAndSay(3) = 读 "11" = 二 个 1 = "21"
 *          countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
 */
public class CountAndSay_Easy {

    private int N = 1;

    @Test
    public void TestSolution(){
        System.out.println(countAndSay(2));
        System.out.println(countAndSay3(2));
    }


    /**
     * 递归-生成外观数列
     * 执行用时：2 ms, 在所有 Java 提交中击败了73.14%的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了95.08%的用户
     * */
    public String countAndSay(int n) {
        if (n < 1){
        return "";
    }
        return counter(n).toString();
}

    public StringBuilder counter (int n){
        if (n == 1){
            return new StringBuilder("1");
        }
        StringBuilder result = new StringBuilder();
        StringBuilder sb = counter(n-1);
        for (int i = 0; i < sb.length(); i++) {
            int pre = i;
            char cur = sb.charAt(i);
            while (i < sb.length()-1 && sb.charAt(i+1) == cur){
                i++;
            }
            result.append(i-pre+1);
            result.append(cur);
        }
        return result;
    }
    /**
     * 迭代-生成外观数列
     * 执行用时：8 ms, 在所有 Java 提交中击败了29.54%的用户
     * 内存消耗：35.6 MB, 在所有 Java 提交中击败了98.16%的用户
     * */
    public String countAndSay2(int n) {
        if (n < 1){
            return "";
        }
        StringBuilder preStr = new StringBuilder("1");
        StringBuilder result = new StringBuilder();
        for (int j = 1; j < n; j++) {
            result = new StringBuilder();
            for (int i = 0; i < preStr.length(); i++) {
                int pre = i;
                char cur = preStr.charAt(i);
                while (i < preStr.length()-1 && preStr.charAt(i+1) == cur){
                    i++;
                }
                result.append(i-pre+1);
                result.append(cur);
            }
            preStr = result;
        }
        return result.toString();
    }

    /**
     * 通过队列存储数据，迭代实现
     * 执行用时：10 ms, 在所有 Java 提交中击败了26.29%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了26.54%的用户
     * */
    public String countAndSay3(int n) {
        if (n < 1) {
            return "";
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer("1");
        for (int i = 1; i < n; i++) {
            int size = queue.size();
            for (int j = 0; j < size;) {
                String cur = queue.poll();
                int count = 1;
                while (count+j < size && queue.peek().equals(cur)){
                    count++;
                    queue.poll();
                }
                queue.offer(String.valueOf(count));
                queue.offer(cur);
                j += count;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()){
            sb.append(queue.poll());
        }
        return sb.toString();
    }

}
