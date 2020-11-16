package cn.czl.list.unique;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/11/14 17:09
 * @description:
 *      1122. 数组的相对排序
 *      - 给你两个数组，arr1 和 arr2，
 *          arr2 中的元素各不相同
 *          arr2 中的每个元素都出现在 arr1 中
 *      - 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
 *      未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *      示例：
 *          输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 *          输出：[2,2,2,1,4,3,3,9,6,7,19]
 */
public class RelativeSortArray_Rasy {

    private int[] ARR1 = new int[]{2,3,1,3,2,4,6,7,9,2,19};
    private int[] ARR2 = new int[]{2,1,4,3,9,6};

    @Test
    public void TestSolution(){
        int[] res = relativeSortArray(ARR1, ARR2);
        System.out.println(Arrays.toString(res));
    }

    /**
     * set存储arr2中包含的元素，遍历arr1，若set包含则存入map
     * map(key为数值，value为频率)，否则存入数组。
     * 先根据arr2中的元素和顺序，以及arr1中对应的频率，完成res前部分的录入
     * 然后将数组中的数拼接到res尾部
     * 执行用时：3 ms, 在所有 Java 提交中击败了39.49%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了53.65%的用户
     * */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] res = new int[arr1.length];
        HashMap<Integer, Integer> dict = new HashMap<>();
        List<Integer> rest = new ArrayList<>();
        for (int i = 0; i < arr2.length; i++) {
            dict.put(arr2[i], 0);
        }
        for (int i = 0; i < arr1.length; i++) {
            if(dict.containsKey(arr1[i])){
                dict.put(arr1[i], dict.get(arr1[i])+1);
            }else {
                rest.add(arr1[i]);
            }
        }
        Collections.sort(rest);
        int index = res.length - rest.size();
        for (int i = 0, pos = 0; i < index; pos++) {
            int count = dict.get(arr2[pos]);
            while (count > 0){
                res[i] = arr2[pos];
                count--;
                i++;
            }
        }
        for (int i = 0; i < rest.size(); i++) {
            res[index + i] = rest.get(i);
        }
        return res;
    }

    /**
     * 重写比较器，设置比较规则。
     * 排序规则：字典中存在优先,其中索引小优先，不存在中，数值小的有限
     * 执行用时：3 ms, 在所有 Java 提交中击败了39.49%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了52.08%的用户
     * */
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> dict = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr2.length; i++) {
            dict.put(arr2[i], i);
        }
        for (int temp : arr1) {
            list.add(temp);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (dict.containsKey(o1) || dict.containsKey(o2)){
                    return dict.getOrDefault(o1, Integer.MAX_VALUE) - dict.getOrDefault(o2, Integer.MAX_VALUE);
                }else {
                    return o1 - o2;
                }
            }
        });
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = list.get(i);
        }
        return arr1;
    }

    /**
     * 获取排序数组最大值，新建计数数组counter
     * 将arr1的数值作为下标，counter对应下标的数为频率
     * 遍历arr2，按照arr2顺序，读取counter中对应频率，
     * 根据当前值和对应频率，写入res数组，已读频率归0
     * 再次从小至大，遍历counter数组，完成剩余数字的写入
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了43.49%的用户
     * */
    public int[] relativeSortArray3(int[] arr1, int[] arr2) {
        int maxValue = Integer.MIN_VALUE;
        for (int temp : arr1) {
            maxValue = Math.max(temp, maxValue);
        }
        int[] counter = new int[maxValue + 1];
        for (int pos : arr1) {
            counter[pos]++;
        }
        int[] res= new int[arr1.length];
        int index = 0;
        for (int cur : arr2) {
            int size = counter[cur];
            while (size > 0){
                res[index++] = cur;
                size--;
            }
            counter[cur] = 0;
        }
        for (int i = 0; i <= maxValue; i++) {
            int size = counter[i];
            while (size > 0){
                res[index++] = i;
                size--;
            }
        }
        return res;
    }
}
