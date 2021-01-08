package cn.czl.study.stackAndQueue.queue;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/12/24 16:17
 * @description:
 *          232. 用栈实现队列
 *          - 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）：
 *          - 实现 MyQueue 类：
 *              void push(int x) 将元素 x 推到队列的末尾
 *              int pop() 从队列的开头移除并返回元素
 *              int peek() 返回队列开头的元素
 *              boolean empty() 如果队列为空，返回 true ；否则，返回 false
 */
public class DesignQueueByStack_Easy {
    @Test
    public void TestSolution(){
        MyQueueByStack2 myQueueByStack2 = new MyQueueByStack2();
        myQueueByStack2.push(1);
        myQueueByStack2.push(2);
        myQueueByStack2.push(3);
        myQueueByStack2.push(4);
        System.out.println(myQueueByStack2.peek());
        System.out.println(myQueueByStack2.pop());
    }
}

/**
 * 每次插入元素时，先将新元素入到一个新的栈中，
 * 然后将原先栈中元素，加入到新栈中
 * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
 * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 76.14% 的用户
 * */
class MyQueueByStack {

    Stack<Integer> stack;
    // 构造方法
    public MyQueueByStack() {
        stack = new Stack<>();
    }

    // 向队尾添加元素
    public void push(int x) {
        if(empty()){
            stack.add(x);
        }else{
            Stack<Integer> next = new Stack<>();
            next.add(x);
            for(int temp : stack){
                next.add(temp);
            }
            stack = next;
        }
    }

    // 队列头部元素出队
    public int pop() {
        if(empty()){
            return -1;
        }
        return stack.pop();
    }

    // 返回队列头部元素
    public int peek() {
        if(empty()){
            return -1;
        }
        return stack.peek();
    }

    // 判断队列是否为空
    public boolean empty() {
        return stack.isEmpty();
    }
}

/**
 * 双栈实现
 * */
class MyQueueByStack2 {
    Stack<Integer> stack;
    Stack<Integer> temp;
    // 构造方法
    public MyQueueByStack2() {
        stack = new Stack<>();
        temp = new Stack<>();
    }
    // 向队尾添加元素
    public void push(int x) {
        if(empty()){
            stack.push(x);
        }else{
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                temp.add(stack.pop());
            }
            stack.add(x);
            for (int i = 0; i < size; i++) {
                stack.add(temp.pop());
            }
//            while (!stack.isEmpty()){
//                temp.push(stack.pop());
//            }
//            stack.push(x);
//            while (!temp.isEmpty()){
//                stack.push(temp.pop());
//            }
        }

    }
    // 队列头部元素出队
    public int pop() {
        return stack.pop();
    }
    // 返回队列头部元素
    public int peek() {
        return stack.peek();
    }
    // 判断队列是否为空
    public boolean empty() {
        return stack.size()==0;
    }
}