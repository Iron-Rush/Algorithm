package cn.czl.study.stackAndQueue.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/12/2 15:05
 * @description:
 *      155. 最小栈
 *      - 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈
 *      - push(x) —— 将元素 x 推入栈中。
 *      - pop() —— 删除栈顶的元素。
 *      - top() —— 获取栈顶元素。
 *      - getMin() —— 检索栈中的最小元素。
 */
public class MinStack_Easy {


    @Test
    public void TestSolution(){
        MinStack minStack = new MinStack();
        minStack.push(3);
        minStack.push(-100);
        minStack.push(5);
        System.out.println(minStack.getMin());
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.getMin());
    }

}

/**
 * 通过自定义链表实现，每个节点存储当前值，当前最小值，和它前面的节点
 * 执行用时：5 ms, 在所有 Java 提交中击败了99.84%的用户
 * 内存消耗：40.4 MB, 在所有 Java 提交中击败了47.08%的用户
 * */
class MinStack {

    private Node node;

    public MinStack() {

    }

    public void push(int x) {
        if (node == null){
            node = new Node(x, x);
        }else {
            // node = new Node(x, Math.min(x, node.min), node);
            Node next = new Node(x, Math.min(x, node.min));
            next.prev = node;
            node = next;
        }
    }

    public void pop() {
        node = node.prev;
    }

    public int top() {
        return node.val;
    }

    public int getMin() {
        return node.min;
    }

    private class Node {
        int val;
        int min;
        Node prev;

        private Node(int val, int min){
            this.val = val;
            this.min = min;
        }

        private Node(int val, int min, Node prev){
            this.val = val;
            this.min = min;
            this.prev = prev;
        }
    }
}

/**
 * 栈中存储数组实现
 * 执行用时：6 ms, 在所有 Java 提交中击败了97.10%的用户
 * 内存消耗：40.3 MB, 在所有 Java 提交中击败了63.92%的用户
 * */
class MinStack2 {

    // 数组栈, [当前值, 当前最小值]
    private Stack<int[]> stack = new Stack<>();

    public MinStack2() {

    }

    public void push(int x) {
        if (stack.isEmpty()){
            stack.push(new int[]{x, x});
        }else {
            stack.push(new int[]{x, Math.min(x, stack.peek()[1])});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}
/**
 * 二倍长动态数组实现，偶数位存储当前最小值，奇数位存储当前值
 * 执行用时： 7 ms , 在所有 Java 提交中击败了 77.58% 的用户
 * 内存消耗： 40.2 MB , 在所有 Java 提交中击败了 72.25% 的用户
 * */
class MinStack3 {
    private List<Integer> list;
    /** initialize your data structure here. */
    public MinStack3() {
        list = new ArrayList<Integer>();
    }

    public void push(int x) {
        if(isEmpty()){
            list.add(x);
        }else {
            int curMin = Math.min(getMin(), x);
            list.add(curMin);
        }
        list.add(x);
    }

    public void pop() {
        if(!isEmpty()){
            list.remove(list.size() - 1);
            list.remove(list.size() - 1);
        }
    }

    public int top() {
        if(isEmpty()){
            return -1;
        }else {
            return list.get(list.size() - 1);
        }
    }

    public int getMin() {
        if(isEmpty()){
            return -1;
        }else {
            return list.get(list.size() - 2);
        }
    }
    private boolean isEmpty(){
        return list.size() < 2;
    }
}
