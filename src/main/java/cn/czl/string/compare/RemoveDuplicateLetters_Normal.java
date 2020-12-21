package cn.czl.string.compare;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/12/20 10:21
 * @description:
 *      316/1081. 去除重复字母
 *      - 给你一个字符串 s ，请你去除字符串中重复的字母，
 *      使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *      示例 1：
 *          输入：s = "bcabc"
 *          输出："abc"
 *      示例 2：
 *          输入：s = "cbacdcbc"
 *          输出："acdb"
 *      提示：
 *          1 <= s.length <= 104
 *          s 由小写英文字母组成
 */
public class RemoveDuplicateLetters_Normal {

    private String S1 = "bcabc";
    private String S2 = "cbacdcbc";

    @Test
    public void TestSolution(){
        System.out.println("abc".equals(removeDuplicateLetters3(S1)));
        System.out.println("acdb".equals(removeDuplicateLetters3(S2)));
    }

    /**
     * 单调栈  基于StringBuilder直接构建字符串
     * 第一优先顺序为 不打乱给定字符串顺序
     * 其次要使删除后字符串字典序尽可能小
     * 即，如果stack.peek() > curChar && counter[stack.peek-'a'] > 0
     * 则说明，栈顶字符与当前字符字典序相悖，
     * 如果后面还有栈顶对应字符，则可删除栈顶字符，待后面再遇到时使用
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 86.89% 的用户
     * 内存消耗： 37.1 MB , 在所有 Java 提交中击败了 96.00% 的用户
     * */
    public String removeDuplicateLetters(String s) {
        boolean[] vis = new boolean[26];
        int[] counter = new int[26];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            counter[ch - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = chars[i];
            if (!vis[ch - 'a']){
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch){
                    if(counter[sb.charAt(sb.length() - 1) - 'a'] > 0){
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    }else {
                        break;
                    }
                }
                vis[ch - 'a'] = true;
                sb.append(ch);
            }
            counter[ch - 'a']--;
        }
        return sb.toString();
    }

    /**
     * 基于双向队列   构建单调栈
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 86.89% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 87.59% 的用户
     * */
    public String removeDuplicateLetters2(String s) {
        char[] chars = s.toCharArray();
        int[] lastIdx = new int[26];    // 记录该元素最后一次出现的位置
        for (int i = 0; i < s.length(); i++) {
            lastIdx[chars[i] - 'a'] = i;
        }
        Deque<Character> stack = new LinkedList<>();
        boolean[] visited = new boolean[26];    // 某个元素是否在栈中出现
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if(visited[ch - 'a']){
                continue;
            }
            // 当前字符在栈顶元素之前，且栈顶元素在后面还有
            while (!stack.isEmpty() && stack.peekLast() > ch && lastIdx[stack.peekLast() - 'a'] > i){
                char c = stack.pollLast();  // 移除栈顶元素
                visited[c - 'a'] = false;   // 表示该字符没有出现在栈中
            }
            stack.addLast(ch);
            visited[ch - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 基于栈      构建单调栈
     * */
    public String removeDuplicateLetters3(String s) {
        char[] chars = s.toCharArray();
        int[] lastIdx = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIdx[chars[i] - 'a'] = i;
        }
        Stack<Character> stack = new Stack<>();
        boolean[] visited = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char curChar = chars[i];
            if(!visited[curChar - 'a']){
                while (!stack.isEmpty() && stack.peek() > curChar){
                    if(lastIdx[stack.peek() - 'a'] > i){
                        char out = stack.pop();
                        visited[out - 'a'] = false;
                    }else {
                        break;
                    }
                }
                stack.add(curChar);
                visited[curChar - 'a'] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : stack) {
            sb.append(ch);
        }
        return sb.toString();
    }

}
