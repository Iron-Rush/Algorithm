package cn.czl.study.stackAndQueue.stack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author RedRush
 * @date 2020/12/22 16:21
 * @description:
 *      DFS深度优先搜索-模版
 */
public class DFS_TEMPLATE {
    /**
     * @Author: RedRush
     * @Date:   2020/12/22 16:23
     * @description: DFS递归实现模版
     */
    boolean DFS (Node cur, Node target, Set<Node> visited){
        if(cur == target){
            return true;
        }
        for(Node next : cur.neighbors){
            if(visited.add(next)){
                boolean flag = DFS(next, target, visited);
                if(flag){
                    return true;
                }else {
                    continue;
                }
            }
        }
        return false;
    }

    /**
     * @Author: RedRush
     * @Date:   2020/12/23 15:06
     * @description: DFS迭代实现模版
     */
    boolean DFS2(Node root, Node target){
        Stack<Node> stack = new Stack<>();
        Set<Node> visited = new HashSet<>();
        if(root != null){
            stack.add(root);
            visited.add(root);
        }
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            if(cur == target){
                return true;
            }
            for (Node next : cur.neighbors) {
                if(visited.contains(next)){
                    stack.add(next);
                    visited.add(next);
                }
            }
        }
        return false;
    }
}

