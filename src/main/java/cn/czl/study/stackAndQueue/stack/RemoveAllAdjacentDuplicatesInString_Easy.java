package cn.czl.study.stackAndQueue.stack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2021/3/9 17:55
 * @description:
 *      1047. 删除字符串中的所有相邻重复项
 *      - 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *      - 在 S 上反复执行重复项删除操作，直到无法继续删除。
 *      - 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *      示例：
 *          输入："abbaca"
 *          输出："ca"
 *          解释：例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，
 *              这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，
 *              其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 *      提示：
 *          1 <= S.length <= 20000
 *          S 仅由小写英文字母组成。
 */
public class RemoveAllAdjacentDuplicatesInString_Easy {

    private String S1 = "abbaca";
    private String S2 = "aaaaaa";
    private String S3 = "aaabaaab";
    @Test
    public void TestSolution(){
        System.out.println(removeDuplicates(S3));
    }

    /**
     * 借助栈实现[StringBuilder构建结果字符串]
     * 执行用时： 62 ms , 在所有 Java 提交中击败了 17.28% 的用户
     * 内存消耗： 39.1 MB , 在所有 Java 提交中击败了 57.37% 的用户
     * */
    public String removeDuplicates(String S) {
        int len = S.length();
        if(len <= 1){
            return S;
        }
        char[] chs = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        stack.add(chs[0]);
        boolean flag = false;
        for(int i = 1; i < len; i++){
            char curCh = chs[i];
            while(!stack.isEmpty() && curCh == stack.peek() && i < len){
                if(i + 1 == len){
                    flag = true;
                }else{
                    curCh = chs[++i];
                }
                stack.pop();
            }
            if(!flag){
                stack.add(curCh);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
    /**
     * 借助栈实现[char[] 构建结果字符串]
     * 执行用时： 20 ms , 在所有 Java 提交中击败了 65.89% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 83.67% 的用户
     * */
    public String removeDuplicates2(String S) {
        int len = S.length();
        if(len <= 1){
            return S;
        }
        char[] chs = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        stack.add(chs[0]);
        boolean flag = false;
        for(int i = 1; i < len; i++){
            char curCh = chs[i];
            while(!stack.isEmpty() && curCh == stack.peek() && i < len){
                if(i + 1 == len){
                    flag = true;
                }else{
                    curCh = chs[++i];
                }
                stack.pop();
            }
            if(!flag){
                stack.add(curCh);
            }
        }
        char[] res = new char[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return new String(res);
    }

    /**
     * 直接操作StringBuffer作为栈
     * 执行用时： 13 ms , 在所有 Java 提交中击败了 77.81% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 98.41% 的用户
     * */
    public String removeDuplicates3(String S) {
        StringBuffer stack = new StringBuffer();
        int top = -1;
        for (int i = 0; i < S.length(); ++i) {
            char ch = S.charAt(i);
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
                --top;
            } else {
                stack.append(ch);
                ++top;
            }
        }
        return stack.toString();
    }

    /**
     * 转化为char[],直接在该数组上操作，并根据游标截取成字符串
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 99.48% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 76.91% 的用户
     * */
    public String removeDuplicates4(String S) {
        char[] chs = S.toCharArray();
        int index = -1;
        for (char ch : chs) {
            if(index >= 0 && ch == chs[index]){
                index--;
            }else {
                chs[++index] = ch;
            }
        }
        return new String(chs, 0, index + 1);
    }

}
