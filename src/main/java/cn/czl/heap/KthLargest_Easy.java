package cn.czl.heap;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

/**
 * @author RedRush
 * @date 2021/9/16 11:36
 * @description:
 *      703. 数据流中的第 K 大元素
 *      - 设计一个找到数据流中第 k 大元素的类（class）。
 *          注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 *      - 请实现 KthLargest 类：
 *      - KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 *      - int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 *      示例：
 *          输入：
 *              ["KthLargest", "add", "add", "add", "add", "add"]
 *              [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 *          输出：
 *              [null, 4, 5, 5, 8, 8]
 *      解释：
 *          KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 *          kthLargest.add(3);   // return 4
 *          kthLargest.add(5);   // return 5
 *          kthLargest.add(10);  // return 5
 *          kthLargest.add(9);   // return 8
 *          kthLargest.add(4);   // return 8
 *      提示：
 *          1 <= k <= 10^4
 *          0 <= nums.length <= 10^4
 *          -10^4 <= nums[i] <= 10^4
 *          -10^4 <= val <= 10^4
 *          最多调用 add 方法 10^4 次
 *          题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 */
public class KthLargest_Easy {
    @Test
    public void TestSolution(){
        int[] nums = {4,5,8,2};
        KthLargest kthLargest = new KthLargest(3, nums);
        System.out.println(kthLargest.add2(3));
        System.out.println(kthLargest.add2(5));
        System.out.println(kthLargest.add2(10));
        System.out.println(kthLargest.add2(9));
        System.out.println(kthLargest.add2(4));
    }
}

class KthLargest {

    private PriorityQueue<Integer> heap;    // 小顶堆
    private int MAX_SIZE;       // 小顶堆 - 最大容量
    // 构造函数
    public KthLargest(int k, int[] nums) {
        heap = new PriorityQueue<>((a, b) -> a-b);
        MAX_SIZE = k;
        if (nums != null){
            for (int num : nums) {
                add(num);
            }
        }

    }
    // 添加元素方法
    public int add(int val) {
        if (heap.size() < MAX_SIZE){
            heap.add(val);
        }else if (val > heap.peek()){
            heap.poll();
            heap.add(val);
        }
        return heap.peek();
    }
    // 添加元素方法 - 赋值逻辑优化
    public int add2(int val){
        heap.offer(val);
        if (heap.size() > MAX_SIZE){
            heap.poll();
        }
        return heap.peek();
    }
}