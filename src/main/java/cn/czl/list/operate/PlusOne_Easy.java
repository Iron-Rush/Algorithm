package cn.czl.list.operate;

/**
 * @author RedRush
 * @date 2020/11/19 15:57
 * @description:
 *      66. 加一
 *      - 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *      - 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *      - 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *      示例：
 *          输入：digits = [1,2,3]
 *          输出：[1,2,4]
 *          解释：输入数组表示数字 123。
 */
public class PlusOne_Easy {

    /**
     * 若有进位，则下一位仍执行的是+1操作。若无进位，则直接返回数组。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了84.37%的用户
     * */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i >=0 ; i--) {
            digits[i] = digits[i]++ % 10;
            if (digits[i] != 0){
                return digits;
            }
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }
}
