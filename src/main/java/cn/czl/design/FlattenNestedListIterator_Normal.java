package cn.czl.design;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2021/3/23 9:27
 * @description:
 *      341. 扁平化嵌套列表迭代器
 *      - 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 *      - 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 *      示例 1:
 *          输入: [[1,1],2,[1,1]]
 *          输出: [1,1,2,1,1]
 *          解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 *      示例 2:
 *          输入: [1,[4,[6]]]
 *          输出: [1,4,6]
 *          解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 */
public class FlattenNestedListIterator_Normal {


    @Test
    public void TestSolution(){

    }
}

/**
 * 借助队列 - 存储数据
 * 执行用时： 3 ms , 在所有 Java 提交中击败了 91.05% 的用户
 * 内存消耗： 40.6 MB , 在所有 Java 提交中击败了 82.45% 的用户
 * */
class NestedIterator2 implements Iterator<Integer> {
    // 缓存需要迭代的数据
    private Queue<Integer> queue = new LinkedList<>();

    public NestedIterator2(List<NestedInteger> nestedList) {
        addToList(nestedList);
    }
    // 递归 - 将全部元素添加至队列中
    private void addToList(List<NestedInteger> nestedList){
        for (NestedInteger nested : nestedList) {
            if(nested.isInteger()){
                queue.offer(nested.getInteger());
            }else {
                addToList(nested.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

/**
 * 借助数组 - 存储数据
 * 执行用时： 3 ms , 在所有 Java 提交中击败了 91.05% 的用户
 * 内存消耗： 40.9 MB , 在所有 Java 提交中击败了 29.44% 的用户
 * */
class NestedIterator3 implements Iterator<Integer> {
    // 缓存需要迭代的数据
    private List<Integer> arr = new ArrayList<>();
    private int index;

    public NestedIterator3(List<NestedInteger> nestedList) {
        addToList(nestedList);
    }
    // 递归 - 将全部元素添加至队列中
    private void addToList(List<NestedInteger> nestedList){
        for (NestedInteger nested : nestedList) {
            if(nested.isInteger()){
                arr.add(nested.getInteger());
            }else {
                addToList(nested.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return arr.get(index ++);
    }

    @Override
    public boolean hasNext() {
        return index < arr.size();
    }
}

/**
 * 执行用时： 5 ms , 在所有 Java 提交中击败了 18.40% 的用户
 * 内存消耗： 41 MB , 在所有 Java 提交中击败了 20.21% 的用户
 * */
class NestedIterator implements Iterator<Integer> {

    private List<NestedInteger> nestedList;
    private int size;
    private int i = 0;
    private NestedIterator iterator = null;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        size = nestedList.size();
    }

    @Override
    public Integer next() {
        NestedInteger curr = nestedList.get(i);
        if(curr.isInteger()){
            i++;
            return curr.getInteger();
        }else {
            return iterator.next();
        }
    }

    @Override
    public boolean hasNext() {
        while (i < size){
            NestedInteger curr = nestedList.get(i);
            if(curr.isInteger()){
                return true;
            }else {
                if (iterator == null) {
                    iterator = new NestedIterator((curr.getList()));
                }
                if(iterator.hasNext()){
                    return true;
                }else {
                    iterator = null;
                    i++;
                }
            }
        }
        return false;
    }
}