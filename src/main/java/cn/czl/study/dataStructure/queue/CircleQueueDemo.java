package cn.czl.study.dataStructure.queue;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2021/1/12 16:58
 * @description:
 *      环形队列
 */
public class CircleQueueDemo {
    @Test
    public void TestSolution(){
        CircleQueue2 queue = new CircleQueue2(4);
        queue.add(1);
        queue.add(1);
        queue.print();
        queue.poll();
        queue.poll();
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.print();
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.add(1);
        queue.print();
        System.out.println(queue.poll());
    }
}
class CircleQueue{
    private int[] array;
    private int size;
    private int curSize;
    private int start;
    private int end;

    public void CircleQueue(int size){
        this.size = size;
        array = new int[size];
        curSize = 0;
        start = -1;
        end = -1;
    }
    public boolean add(int x){
        if(isFull()){
            return false;
        }
        array[end] = x;
        end = (end + 1) % size;
        curSize++;
        return true;
    }
    public boolean poll(){
        if(isEmpty()){
            return false;
        }
        start = (start + 1) % size;
        curSize--;
        return true;
    }
    // 获取队首元素
    public int peekHead(){
        if(isEmpty()){
            return -1;
        }
        return array[start];
    }
    // 获取队尾元素
    public int peekLast(){
        if(isEmpty()){
            return -1;
        }
        return array[end];
    }
    // 当前栈是否已满
    public boolean isFull(){
        return curSize == size;
    }
    // 当前栈是否为空
    public boolean isEmpty(){
        return curSize == 0;
    }

}
class CircleQueue2{
    private int maxSize;    // 环形队列最大容量
    private int start;
    private int end;
    private int[] data;

    public CircleQueue2(int size){
        this.maxSize = size;
        data = new int[size];
        start = 0;
        end = 0;
    }
    // 添加数据
    public boolean add(int x){
        if(isFull()){
            return false;
        }
        data[end++] = x;
        end %= maxSize;
        return true;
    }
    // 出队列
    public int poll(){
        if(isEmpty()){
            return -1;
        }
        int num = data[start];
        start = (start + 1) % maxSize;
        return num;
    }
    // 获取队首元素
    public int peekHead(){
        if(isEmpty()){
            return -1;
        }
        return data[start];
    }
    // 获取队尾元素
    public int peekLast(){
        if (isEmpty()){
            return -1;
        }
        return data[end];
    }
    // 打印队列
    public void print(){
        if (isEmpty()){
            System.out.println("[]");
        }
        for (int i = start; i < (end < start ? end + maxSize : end); i++) {
            int idx = i % maxSize;
            System.out.printf("arr[%d]=%d\n",idx,data[idx]);
        }
    }
    // 获取当前队列中元素个数
    public int size(){
        return (end + maxSize - start) % maxSize;
    }
    // 判断是否已满
    public boolean isFull(){
        return (end + 1) % maxSize == start;
    }
    // 判断是否为空
    public boolean isEmpty(){
        return start == end;
    }
}