package cn.czl.study.stackAndQueue.stack;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/12/23 15:37
 * @description:
 *          225. 用队列实现栈
 *          - 使用队列实现栈的下列操作：
 *              push(x) -- 元素 x 入栈
 *              pop() -- 移除栈顶元素
 *              top() -- 获取栈顶元素
 *              empty() -- 返回栈是否为空
 *          注意:
 *          - 你只能使用队列的基本操作,也就是 push to back, peek/pop from front,size,
 *              和 is empty 这些操作是合法的。
 *          - 你所使用的语言也许不支持队列。 你可以使用 list
 *              或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 *          - 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 */
public class DesignStackByQueue_Easy {

    @Test
    public void TestSolution(){
        MyStack2 stack = new MyStack2();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.empty());
    }
}
/**
 * 逆序存入队列，以保证出队顺序与栈相同
 * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
 * 内存消耗： 36.1 MB , 在所有 Java 提交中击败了 80.93% 的用户
 * */
class MyStack {
    Queue<Integer> queue;
    // 构造方法
    public MyStack() {
        queue = new LinkedList<>();
    }

    // 元素入栈
    public void push(int x) {
        if(queue.isEmpty()){
            queue.offer(x);
        }else {
            Queue<Integer> temp = queue;
            queue = new LinkedList<>();
            queue.add(x);
            queue.addAll(temp);
        }
    }

    // 元素出栈
    public int pop() {
        return queue.poll();
    }

    // 获取栈顶元素
    public int top() {
        return queue.peek();
    }

    // 栈是否为空
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * 新元素入栈时，让之前全部元素出队，重新到后面排队
 * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
 * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 77.47% 的用户
 * */
class MyStack2 {
    Queue<Integer> queue;

    // 构造方法
    public MyStack2() {
        queue = new LinkedList<>();
    }

    // 元素入栈
    public void push(int x) {
        int size = queue.size();
        queue.offer(x);
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
    }

    // 元素出栈
    public int pop() {
        return queue.poll();
    }

    // 获取栈顶元素
    public int top() {
        return queue.peek();
    }

    // 栈是否为空
    public boolean empty() {
        return queue.isEmpty();
    }
}