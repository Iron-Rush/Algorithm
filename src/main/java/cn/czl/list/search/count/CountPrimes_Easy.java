package cn.czl.list.search.count;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author RedRush
 * @date 2020/12/3 9:42
 * @description:
 *      204. 计数质数
 *      - 统计所有小于非负整数 n 的质数的数量。
 *      - 示例 1：
 *          输入：n = 10
 *          输出：4
 *          解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 */
public class CountPrimes_Easy {

    @Test
    public void TestSolution(){
        System.out.println(countPrimes3(100));
    }

    /**
     * 厄拉多塞筛法（埃氏筛）[填充长度为n的int数组]
     * 若该数3，则铺满n以下，从3开始，步长为3，填充数组。
     * 执行用时：25 ms, 在所有 Java 提交中击败了34.59%的用户
     * 内存消耗：42.6 MB, 在所有 Java 提交中击败了10.96%的用户
     * */
    public int countPrimes(int n) {
        int count = 0;
        int[] memory = new int[n + 1];
        for (int i = 2; i < n; i++) {
            if (memory[i] == 0){        // 若该位置未被填充，则该数为质数
                count++;
                for (int j = i; j < n; j+=i) {  // 步长为i，填充数组
                    memory[j]++;
                }
            }
        }
        return count;
    }

    /**
     * 厄拉多塞筛法（埃氏筛）[填充boolean数组]
     * 执行用时：17 ms, 在所有 Java 提交中击败了63.37%的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了70.11%的用户
     * */
    public int countPrimes2(int n) {
        int count = 0;
        boolean[] memory = new boolean[n + 1];
        for (int i = 2; i < n; i++) {
            if (!memory[i]){
                count++;
                for (int j = i; j < n; j+=i) {
                    memory[j] = true;
                }
            }
        }
        return count;
    }

    /**
     * set记忆-超时
     * */
    public int countPrimes3(int n) {
        int count = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i < n; i++) {
            if (set.add(i)){
                count++;
                for (int j = i*2; j < n; j+=i) {
                    set.add(j);
                }
            }
        }
        return count;
    }

    /**
     * 枚举，逐个判断每个数是否为质数
     * 执行用时：703 ms, 在所有 Java 提交中击败了5.58%的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了95.69%的用户
     * */
    public int countPrimes4(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            count += isPrime(i) ? 1 : 0;
        }
        return count;
    }
    // 判断当前数是否为质数
    public boolean isPrime(int x){
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0){
                return false;
            }
        }
        return true;
    }
}
