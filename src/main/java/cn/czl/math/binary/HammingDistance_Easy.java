package cn.czl.math.binary;

/**
 * @author RedRush
 * @date 2020/12/10 17:04
 * @description:
 *      461. 汉明距离
 *      - 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *      - 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *      - 注意： 0 ≤ x, y < 231.
 *      示例:
 *          输入: x = 1, y = 4
 *          输出: 2
 *      解释:
 *          1   (0 0 0 1)
 *          4   (0 1 0 0)
 *                 ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 */
public class HammingDistance_Easy {

    /**
     * 通过1,不断与x,y每位进行`&`运算，并对比每一位上的结果
     * 如果该位不同则执行+1
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35 MB, 在所有 Java 提交中击败了93.52%的用户
     * */
    public int hammingDistance(int x, int y) {
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            boolean flag1 = (x & mask) != 0;
            boolean flag2 = (y & mask) != 0;
            mask <<= 1;
            if(flag1 != flag2){
                count++;
            }
        }
        return count;
    }
    public int hammingDistance2(int x, int y) {
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if(((x & mask) != 0) != ((y & mask) != 0)){
                count++;
            }
            mask <<= 1;
        }
        return count;
    }
    /**
     * 通过`^`异或运算，得出x,y中不同的位数的结果。
     * 统计结果中的1的数量
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了69.82%的用户
     * */
    public int hammingDistance3(int x, int y){
        int diff = x ^ y;
        return Integer.bitCount(diff);
    }
    /**
     * 布赖恩·克尼根算法。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.1 MB, 在所有 Java 提交中击败了83.12%的用户
     * */
    public int hammingDistance4(int x, int y){
        int diff = x ^ y;
        int count = 0;
        while (diff != 0){
            count++;
            diff &= (diff-1);
        }
        return count;
    }

    /**
     * 移位算法
     * */
    public int hammingDistance5(int x, int y){
        int diff = x ^ y;
        int count = 0;
        while(diff != 0){
            if(diff % 2 == 1){
                count ++;
            }
            diff >>>= 1;
        }
        return count;
    }

}
