package cn.czl.study.dataStructure.queue;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2021/1/12 11:07
 * @description:
 *      使用数组模拟队列    出栈后的空间无法被复用
 */
public class ArrayQueueDemo {

    @Test
    public void TestSolution(){
        ArrayQueue arrayQueue = new ArrayQueue(3);
        arrayQueue.add(1);
        arrayQueue.add(2);
        arrayQueue.add(3);
        arrayQueue.add(2);
        System.out.println(arrayQueue.isFull());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.isEmpty());
    }
}

// 使用数组模拟队列
class ArrayQueue{
    private int maxSize;    // 数组最大容量
    private int start;      // 队首指针
    private int end;        // 队尾指针
    private int[] arr;      // 模拟队列的数组

    public ArrayQueue(int arrSize){
        maxSize = arrSize;
        arr = new int[arrSize];
        start = -1;     // 指向队首的前一个位置
        end = -1;       // 指向队列尾部的数据
    }
    // 判断队列是否已满
    public boolean isFull(){
        return end == maxSize - 1;
    }
    // 判断队列是否为空
    public boolean isEmpty(){
        return start == end;
    }
    // 添加数据到队列
    public boolean add(int x){
        if(isFull()){
            return false;
        }
        arr[++end] = x;
        return true;
    }
    // 获取队列的数据
    public int peek(){
        if(isEmpty()){
            return -1;
//            throw new RuntimeException("队列为空，不能获取数据");
        }
        return arr[start + 1];
    }
    // 获取队列的数据，出队列
    public int poll(){
        if(isEmpty()){
            return -1;
//            throw new RuntimeException("队列为空，不能获取数据");
        }
        return arr[++start];
    }
    // 打印队列中有效数据
    public void printQueue(){
        int index = start + 1;
        while (index <= end){
            System.out.print(arr[index++] + ",");
        }
    }
}
