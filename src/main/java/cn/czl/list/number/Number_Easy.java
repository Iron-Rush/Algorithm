package cn.czl.list.number;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/8/25 11:43
 */
public class Number_Easy {
    @Test
    public void testSolution(){
//        System.out.println(subtractProductAndSum(234));
        int[] arr = new int[]{1,2,2,6,6,6,6,7,10};
        System.out.println(findSpecialInteger(arr));
    }

    public int subtractProductAndSum(int n) {
        int product = 1,sum = 0;
        while (n >= 1){
            int temp = n % 10;
            sum += temp;
            product *= temp;
            System.out.println("product=" + product);
            System.out.println("sum=" + sum);
            n = n/10;
        }
        return  product - sum;
    }

    public int findSpecialInteger(int[] arr) {
        int res = arr[0], len = arr.length;
        int target = len / 4;
        for (int i = 0; i < len; i++){
            int count = 0, temp = arr[i];
            while (i < len-1 && arr[i] == arr[i + 1]){
                count++;
                i++;
            }
            if (count > target){
                return temp;
            }
        }
        return res;
    }
}
