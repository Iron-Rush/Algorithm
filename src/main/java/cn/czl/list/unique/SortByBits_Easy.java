package cn.czl.list.unique;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/11/6 9:43
 * @description:
 *      1356. 根据数字二进制下 1 的数目排序
 *      - 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 *      - 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 *      - 请你返回排序后的数组。
 *      示例 1：
 *          输入：arr = [0,1,2,3,4,5,6,7,8]
 *          输出：[0,1,2,4,8,3,5,6,7]
 *          解释：[0] 是唯一一个有 0 个 1 的数。
 *          [1,2,4,8] 都有 1 个 1 。
 *          [3,5,6] 有 2 个 1 。
 *          [7] 有 3 个 1 。
 *          按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
 */
public class SortByBits_Easy {

    private int[] ARR1 = new int[]{0,1,2,3,4,5,6,7,8};

    @Test
    public void TestSolution(){
        int[] res = sortByBits3(ARR1);
        System.out.println(Arrays.toString(res));
    }



    /**
     * 定义二维数组存储原数组的数字和频率，重写比较器
     * 执行用时：10 ms, 在所有 Java 提交中击败了40.42%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了54.36%的用户
     * */
    public int[] sortByBits(int[] arr) {
        Arrays.sort(arr);
        int bitCount[][] = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            bitCount[i][0] = arr[i];    // 0存数值，1存频率
            bitCount[i][1] = oneCounter(arr[i]);
        }
        Arrays.sort(bitCount, (a,b) -> a[1] - b[1]);    // 重写排序器，根据二维数组，每个数组的第二个数进行排序
        for (int i = 0; i < arr.length; i++) {
            arr[i] = bitCount[i][0];
        }
        return arr;
    }

    /**
     * 获取频率，重写排序器规则(Arrays.sort排序器的lambda函数实现)
     * 执行用时：13 ms, 在所有 Java 提交中击败了29.96%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了46.65%的用户
     * */
    public int[] sortByBits2(int[] arr) {
        int[] bit = new int[10001];
        List<Integer> list = new ArrayList<>();
        for (int x : arr) {
            list.add(x);
            bit[x] = oneCounter(x);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override   // 返回值为int类型，大于0表示正序，小于0表示逆序
            public int compare(Integer x, Integer y) {
                if (bit[x] != bit[y]){  // 比较x,y `1`频率
                    return bit[x] - bit[y];// 按`1`的频率排序
                }else {
                    return x - y;       // 若`1`频率相通，则按数值大小排序
                }
            }
        });
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    /**
     * 先排序，二次排序时仅需处理频率不同的情况(Collections排序器的lambda函数实现)
     * */
    public int[] sortByBits3(int[] arr) {
        Arrays.sort(arr);
        int[] bit = new int[10001];
        List<Integer> list = new ArrayList<>();
        for (int x : arr) {
            list.add(x);
            bit[x] = oneCounter(x);
        }
        Collections.sort(list, (a,b) -> bit[a] - bit[b]);
//        Collections.sort(list, new Comparator<Integer>() {
//            @Override   // 返回值为int类型，大于0表示正序，小于0表示逆序
//            public int compare(Integer x, Integer y) {
//                return bit[x] - bit[y];// 按`1`的频率排序
//            }
//        });
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    // 计算目标数二进制中1的数量。递归版
    private int oneCounter (int x){
        int count = 0;
        switch (x){
            case 0:
                return count;
            case 1:
                return 1;
            default:
                if (x % 2 == 0){
                    count = oneCounter(x/2);
                }else {
                    count = oneCounter((x-1)/2);
                    count++;
                }
        }
        return count;
    }
    // 计算目标数二进制中1的数量。简化迭代版本
    private int oneCounter2 (int x){
        int count = 0;
        while (x != 0){
            count += x % 2;
            x /= 2;
        }
        return count;
    }

}
