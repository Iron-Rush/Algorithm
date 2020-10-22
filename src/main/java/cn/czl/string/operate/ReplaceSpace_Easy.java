package cn.czl.string.operate;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/9/7 14:51
 * @description:
 */
public class ReplaceSpace_Easy {

    private static String STR = "We are happy";

    @Test
    public void TestSolution(){
        System.out.println(replaceSpace2(STR));
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了27.45%的用户
     * 内存消耗：37.6 MB, 在所有 Java 提交中击败了48.38%的用户
     * */
    public String replaceSpace(String s) {
//        s = s.replace(" ", "%20");
        s = replaceTools(s, ' ', "%20");
        return s;
    }
    public String replaceTools(String str, char target, String replacement){
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == target){
                sb.replace(i, i+1, replacement);
            }
        }
        return sb.toString();
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.4 MB, 在所有 Java 提交中击败了70.12%的用户
     * */
    public String replaceSpace2(String s){
        if(s.isEmpty()){
            return s;
        }
        char[] chars = new char[s.length() * 3];
        int pos = 0;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == ' '){
                chars[pos++] = '%';
                chars[pos++] = '2';
                chars[pos++] = '0';
                continue;
            }
            chars[pos++] = c;
        }
        return String.valueOf(chars,0,pos);
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了86.93%的用户
     * */
    public String replaceSpace3(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' '){
                sb.append("%20");
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

}
