package cn.czl.string.compare;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/10/21 9:20
 * @description:
 *      925. 长按键入
 *          你的朋友正在使用键盘输入他的名字 name。偶尔，
 *          在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 *      示例 1：
 *          输入：name = "alex", typed = "aaleex"
 *          输出：true     解释：'alex' 中的 'a' 和 'e' 被长按。
 *      示例 2：
 *          输入：name = "saeed", typed = "ssaaedd"
 *          输出：false    解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 */
public class LongPressedName_Easy {

    private String NAME1 = "alex";      // true
    private String TYPED1 = "aaleex";
    private String NAME2 = "saaeed";     // false
    private String TYPED2 = "ssaaeedd";
    private String NAME3 = "ppyplrza";    // false
    private String TYPED3 = "pyypllrza";

    @Test
    public void TestSolution(){
        System.out.println(isLongPressedName2(NAME2, TYPED2));
    }

    /**
     * 双指针，charAt提取字符
     * 执行用时：6 ms, 在所有 Java 提交中击败了6.67%的用户
     * 内存消耗：36.8 MB, 在所有 Java 提交中击败了46.86%的用户
     * */
    public boolean isLongPressedName(String name, String typed) {
        int Nlen = name.length(), Tlen = typed.length();
        int Npos = Nlen-1, Tpos = Tlen-1;
        if (Nlen > Tlen){
            return false;
        }
        while (Npos >= 0 && Tpos >= 0){
            char Nchar = name.charAt(Npos--);
            char Tchar = typed.charAt(Tpos--);
            if (Nchar == Tchar){
                while (Npos >= 0 && Tpos >= 0 && name.charAt(Npos) == Nchar){
                    if (name.charAt(Npos--) != typed.charAt(Tpos--)){
                        return false;
                    }
                }
                while (Tpos >= 0 && typed.charAt(Tpos) == Nchar){
                    Tpos--;
                }
            }else{
                return false;
            }
        }
        System.out.println(Npos + "," + Tpos);
        if (Npos != -1 || Tpos != -1){
            return false;
        }
        return true;
    }

    /**
     * 双指针，通过字符数组比对
     * 执行用时：6 ms, 在所有 Java 提交中击败了6.67%的用户
     * 内存消耗：36.5 MB, 在所有 Java 提交中击败了81.71%的用户
     * */
    public boolean isLongPressedName2(String name, String typed) {
        int Nlen = name.length(), Tlen = typed.length();
        int Npos = Nlen-1, Tpos = Tlen-1;
        char[] Nlist = name.toCharArray(), Tlist = typed.toCharArray();
        if (Nlen > Tlen){
            return false;
        }
        while (Npos >= 0 && Tpos >= 0){
            char Nchar = Nlist[Npos--];
            char Tchar = Tlist[Tpos--];
            if (Nchar == Tchar){
                while (Npos >= 0 && Tpos >= 0 && Nlist[Npos] == Nchar){
                    if (Nlist[Npos--] != Tlist[Tpos--]){
                        return false;
                    }
                }
                while (Tpos >= 0 && Tlist[Tpos] == Nchar){
                    Tpos--;
                }
            }else{
                return false;
            }
        }
        if (Npos != -1 || Tpos != -1){
            return false;
        }
        return true;
    }

    /**
     * 双指针
     * 执行用时：1 ms, 在所有 Java 提交中击败了86.83%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了95.24%的用户
     * */
    public boolean isLongPressedName3(String name, String typed) {
        int nPos = 0, tPos = 0;
        while (tPos < typed.length()){
            if (nPos < name.length() && name.charAt(nPos) == typed.charAt(tPos)){
                nPos++;
                tPos++;
            }else if(tPos > 0 && typed.charAt(tPos) == typed.charAt(tPos-1)){
                tPos++;
            }else {
                return false;
            }
        }
        return nPos == name.length();
    }

}
