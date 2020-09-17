package cn.czl.search.str;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/9/17 9:28
 * @description:
 *          28.实现 strStr()
 *          1). 实现 strStr() 函数。
 *          2). 给定一个 haystack 字符串和一个 needle 字符串，
 *          在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
 *          如果不存在，则返回-1。(needle为空？返回0)
 *      例：
 *          - 输入: haystack = "hello", needle = "ll"
 *          - 输出: 2
 */
public class FindStrInStr_Easy {

    private static String HAYSTACK = "hello";
    private static String NEEDLE = "";

    @Test
    public void TestSolution(){
        System.out.println(strStr2(HAYSTACK, NEEDLE));
    }

    /**
     * Java API方法，indexOf
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了80.08%的用户
     * */
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * charAt(遍历比较)
     * 执行用时：2 ms, 在所有 Java 提交中击败了50.48%的用户
     * 内存消耗：37.2 MB, 在所有 Java 提交中击败了80.94%的用户
     * */
    public int strStr2(String haystack, String needle) {
        int pos = 0;
        int nLen = needle.length(), hLen = haystack.length();
//        if (nLen == 0){
//            return pos;
//        }
//        if (nLen > hLen){
//            return -1;
//        }
        for (; pos <= hLen - nLen; pos++) {
            int j = 0;
            while( j < nLen && haystack.charAt(pos + j) == needle.charAt(j)) {
                j++;
            }
            if (j == nLen) {
                return pos;
            }
        }
        return -1;
    }

}
