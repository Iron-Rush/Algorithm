package cn.czl.string.compare;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/10/19 11:29
 * @description:
 *      844. 比较含退格的字符串
 *          给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，
 *          判断二者是否相等，并返回结果。 # 代表退格字符。
 *          注意：如果对空文本输入退格字符，文本继续为空
 *      示例 1：
 *          输入：S = "ab#c", T = "ad#c"
 *          输出：true
 *          解释：S 和 T 都会变成 “ac”。
 */
public class BackspaceCompare_Easy {

    private static String S1 = "ab#c";  // true
    private static String T1 = "ad#c";
    private static String S2 = "ab##";  // true
    private static String T2 = "c#d#";
    private static String S3 = "a##c";  // true
    private static String T3 = "#a#c";
    private static String S4 = "bxj###tw";   // false
    private static String T4 = "bxj##tw";
    private static String S5 = "bbbetm";    // false
    private static String T5 = "bbb#etm";
    @Test
    public void TestSolution(){
        System.out.println(backspaceCompare2(S5, T5));
    }
    /**
     * 分别处理两个字符串，生成新字符串 -> 对比新字符串
     * 执行用时：1 ms, 在所有 Java 提交中击败了96.52%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了100.00%的用户
     * */
    public boolean backspaceCompare(String S, String T) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int Sflag = 0, Tflag = 0;
        for (int Spos = S.length()-1; Spos >= 0; Spos--) {
            if(S.charAt(Spos) == '#'){
                Sflag++;
            }else if(Sflag > 0){
                Sflag--;
            }else {
                sb1.append(S.charAt(Spos));
            }
        }
        for (int Tpos = T.length()-1; Tpos >= 0; Tpos--) {
            if(T.charAt(Tpos) == '#'){
                Tflag++;
            }else if(Tflag > 0){
                Tflag--;
            }else {
                sb2.append(T.charAt(Tpos));
            }
        }
        System.out.println(sb1+ "," +sb2);
        return sb1.toString().equals(sb2.toString());
    }

    /**
     * 双指针，同时遍历两字符串
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了100.00%的用户
     * */
    public boolean backspaceCompare2(String S, String T) {
        int Sflag = 0, Tflag = 0;
        int Spos = S.length()-1, Tpos = T.length()-1;
       while (Spos >= 0 || Tpos >= 0){
           while (Spos >= 0){
               if (S.charAt(Spos) == '#'){
                   Sflag++; Spos--;
               }else if(Sflag > 0){
                   Sflag--; Spos--;
               }else {
                   break;
               }
           }
           while (Tpos >= 0){
               if (T.charAt(Tpos) == '#'){
                   Tflag++; Tpos--;
               }else if(Tflag > 0){
                   Tflag--; Tpos--;
               }else {
                   break;
               }
           }
           if (Spos >= 0 && Tpos >= 0){
               if (S.charAt(Spos) != T.charAt(Tpos)){
                   return false;
               }
           }else {
               if (Spos >= 0 || Tpos >= 0){
                   return false;
               }
           }
           Tpos--; Spos--;
       }
        return true;
    }

}
