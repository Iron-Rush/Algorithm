package cn.czl.heap;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

/**
 * @author RedRush
 * @date 2021/9/16 13:37
 * @description:
 *      剑指 Offer 41. 数据流中的中位数
 *      - 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *      - 例如，
 *          [2,3,4] 的中位数是 3
 *          [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *      设计一个支持以下两种操作的数据结构：
 *          void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 *          double findMedian() - 返回目前所有元素的中位数。
 *      示例 1：
 *          输入：
 *          ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 *          [[],[1],[2],[],[3],[]]
 *          输出：[null,null,null,1.50000,null,2.00000]
 *      示例 2：
 *          输入：
 *          ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 *          [[],[2],[],[3],[]]
 *          输出：[null,null,2.00000,null,2.50000]
 *      限制： 最多会对 addNum、findMedian 进行 50000 次调用。
 */
public class MedianFinder_Hard {

    @Test
    public void TestSolution(){
        MedianFinder finder = new MedianFinder();
        finder.addNum(-1);
        System.out.println(finder.findMedian());

        finder.addNum(-2);
        System.out.println(finder.findMedian());

        finder.addNum(-3);
        System.out.println(finder.findMedian());
    }
}

/**
 * 维护一个大顶堆一个小顶堆 [小顶堆的元素要 >= 大顶堆中的全部元素]
 * 先添加 - 取数时调整大小
 * 执行用时： 61 ms , 在所有 Java 提交中击败了 98.42% 的用户
 * 内存消耗： 49.9 MB , 在所有 Java 提交中击败了 10.48% 的用户
 * */
class MedianFinder {

    PriorityQueue<Integer> bigHeap;
    PriorityQueue<Integer> smallHeap;

    // 构造方法
    public MedianFinder() {
        bigHeap = new PriorityQueue<Integer>((a, b) -> b-a);
        smallHeap = new PriorityQueue<Integer>((a, b) -> a-b);
    }
    // 向堆中添加元素
    public void addNum(int num) {
        if (bigHeap.size() > 0 && num > bigHeap.peek()){
            smallHeap.offer(num);
        }else {
            bigHeap.offer(num);
        }
    }
    // 取中位数时，调整两个堆的大小
    public double findMedian() {
        if (bigHeap.size() == smallHeap.size()){
            return ((double)bigHeap.peek() + smallHeap.peek())/2;
        }
        while (bigHeap.size() > smallHeap.size()+1){
            smallHeap.offer(bigHeap.poll());
        }
        while (smallHeap.size() > bigHeap.size()+1){
            bigHeap.offer(smallHeap.poll());
        }
        int total = smallHeap.size() + bigHeap.size();
        if (total % 2 == 0){
            return ((double)bigHeap.peek() + smallHeap.peek())/2;
        }else {
            return smallHeap.size() > bigHeap.size() ? smallHeap.peek() : bigHeap.peek();
        }
    }

}

/**
 * 执行用时： 66 ms , 在所有 Java 提交中击败了 87.24% 的用户
 * 内存消耗： 51.1 MB , 在所有 Java 提交中击败了 5.01% 的用户
 * */
class MedianFinder2 {
    PriorityQueue<Integer> bigHeap;
    PriorityQueue<Integer> smallHeap;

    // 构造方法
    public MedianFinder2() {
        bigHeap = new PriorityQueue<Integer>((a, b) -> b-a);
        smallHeap = new PriorityQueue<Integer>((a, b) -> a-b);
    }
    /**
     * 向堆中添加元素(添加元素时调整两个堆的大小)
     * 维持 大顶堆元素 <= 小顶堆元素
     * 根据堆的规模选择要进哪个堆
     * 进堆时，不直接进行元素添加。
     * 先向另一个堆中添加，然后弹出堆顶元素，将弹出的堆顶元素添加到目标堆
     * 这样可以一次性将目标元素添加到正确位置
     * */
    public void addNum(int num) {
        if (smallHeap.size() != bigHeap.size()){
            smallHeap.add(num);
            bigHeap.add(smallHeap.poll());
        }else {
            bigHeap.add(num);
            smallHeap.add(bigHeap.poll());
        }
    }
    // 获取中位数
    public double findMedian(){
        return bigHeap.size() != smallHeap.size() ?
                smallHeap.peek() : (bigHeap.peek() + smallHeap.peek())/2.0;
    }

}
