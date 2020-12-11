package cn.czl.study.stackAndQueue;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/12/11 11:44
 * @description:
 *      622. 设计循环队列
 *      - 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则
 *      并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 *      - 循环队列的一个好处是我们可以利用这个队列之前用过的空间。
 *      在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，
 *      即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 *      - 支持如下操作
 *          1. MyCircularQueue(k): 构造器，设置队列长度为 k 。
 *          2. Front: 从队首获取元素。如果队列为空，返回 -1 。
 *          3. Rear: 获取队尾元素。如果队列为空，返回 -1 。
 *          4. enQueue(value): 向循环队列插入一个元素。如果成功插入则返回true。
 *          5. deQueue(): 从循环队列中删除一个元素。如果成功删除则返回true。
 *          6. isEmpty(): 检查循环队列是否为空。
 *          7. isFull(): 检查循环队列是否已满。
 */
public class MyCircularQueue_Design {

    @Test
    public void TestSolution(){
        MyCircularQueue circularQueue = new MyCircularQueue(3);
        System.out.println(circularQueue.enQueue(1));   // 返回 true
        System.out.println(circularQueue.enQueue(2));   // 返回 true
        System.out.println(circularQueue.enQueue(3));   // 返回 true
        System.out.println(circularQueue.enQueue(4));   // 返回 false，队列已满
        System.out.println(circularQueue.Rear());               // 返回 3
        System.out.println(circularQueue.isFull());             // 返回 true
        System.out.println(circularQueue.deQueue());            // 返回 true
        System.out.println(circularQueue.enQueue(4));    // 返回 true
        System.out.println(circularQueue.Rear());               // 返回 4
    }
}

/**
 * 基于数组实现，循环队列
 * 通过控制首尾指针实现元素的增删。
 * 执行用时：6 ms, 在所有 Java 提交中击败了 97.25% 的用户
 * 内存消耗： 39.1 MB, 在所有 Java 提交中击败了 66.92% 的用户
 * */
class MyCircularQueue {

    private int[] data;
    private int start;
    private int end;
    private int size;
    private int cur;

    // MyCircularQueue(k): 构造器，设置队列长度为 k 。
    public MyCircularQueue(int k) {
        data = new int[k];
        size = k;
        cur = 0;
        start = 0;
        end = 0;
    }
    // enQueue(value): 向循环队列插入一个元素。如果成功插入则返回true。
    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }
        data[end++] = value;
        end %= size;
        cur++;
        return true;
    }
    // deQueue(): 从循环队列中删除一个元素。如果成功删除则返回true。
    public boolean deQueue() {
        if (isEmpty()){
            return false;
        }
        start = (start+1) % size;
        cur--;
        return true;
    }
    // Front: 从队首获取元素。如果队列为空，返回 -1 。
    public int Front() {
        if(isEmpty()){
            return -1;
        }
        return data[start];
    }
    // Rear: 获取队尾元素。如果队列为空，返回 -1 。
    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        return data[end == 0 ? size-1 : end-1];
    }
    // isEmpty(): 检查循环队列是否为空。
    public boolean isEmpty() {
        return cur == 0;
    }
    // isFull(): 检查循环队列是否已满。
    public boolean isFull() {
        return cur == size;
    }
}

class MyCircularQueue2 {

    private int[] data;
    private int head;
    private int tail;
    private int size;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue2(int k) {
        data = new int[k];
        head = -1;
        tail = -1;
        size = k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull() == true) {
            return false;
        }
        if (isEmpty() == true) {
            head = 0;
        }
        tail = (tail + 1) % size;
        data[tail] = value;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty() == true) {
            return false;
        }
        if (head == tail) {
            head = -1;
            tail = -1;
            return true;
        }
        head = (head + 1) % size;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty() == true) {
            return -1;
        }
        return data[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty() == true) {
            return -1;
        }
        return data[tail];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return head == -1;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return ((tail + 1) % size) == head;
    }
}
