package cn.czl.study.stackAndQueue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/12/11 11:09
 * @description:
 *      - 队列实现
 *      - 通过动态数组实现
 */

public class MyQueue_Design{

    @Test
    public void TestSolution(){
        MyQueue myQueue = new MyQueue();
        System.out.println(myQueue.isEmpty());
        myQueue.addNumber(1);
        myQueue.addNumber(2);
        System.out.println(myQueue.getFront());
        myQueue.delNumber();
        System.out.println(myQueue.getFront());
    }

}

class MyQueue {

    private List<Integer> data; // 数据存储
    private int start;          // 头指针
    // 构造函数
    public MyQueue(){
        data = new ArrayList<Integer>();
        start = 0;
    }
    // 元素加入队列
    public boolean addNumber(int x){
        return data.add(x);
    }
    // 元素出队
    public boolean delNumber(){
        if(isEmpty() == true){
            return false;
        }
        start++;
        return true;
    }
    // 获取头元素
    public int getFront(){
        return data.get(start);
    }
    // 判断队列是否为空
    public boolean isEmpty(){
        return start >= data.size();
    }
}
