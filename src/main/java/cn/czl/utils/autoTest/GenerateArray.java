package cn.czl.utils.autoTest;

import java.util.Random;

/**
 * @author RedRush
 * @date 2021/9/18 9:44
 * @description:     生成随机数组
 */
public class GenerateArray {
    static Random random = new Random();

    public static int[] generate(int size){
        return generate(size, -100000, 100000);
    }

    public static int[] generate(int size, int min, int max){
        int[] ans = new int[size];
        int space = max - min + 1;
        for (int i = 0; i < size; i++) {
            ans[i] = min + random.nextInt(space);
        }
        return ans;
    }
}
