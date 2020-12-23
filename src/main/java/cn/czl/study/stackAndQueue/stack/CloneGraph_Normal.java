package cn.czl.study.stackAndQueue.stack;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/12/22 16:38
 * @description:
 *      133. 克隆图
 *      - 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 *      - 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 *       class Node {
 *          public int val;
 *          public List<Node> neighbors;
 *      }
 */
public class CloneGraph_Normal {

    @Test
    public void TestSolution(){
        Node n1 = new Node(1);
        Node n2 = new Node(1);
        Node n3 = new Node(1);
        Node n4 = new Node(1);
        n1.neighbors.add(n2);
        n1.neighbors.add(n4);
        n2.neighbors.add(n1);
        n2.neighbors.add(n3);
        n3.neighbors.add(n2);
        n3.neighbors.add(n4);
        n4.neighbors.add(n1);
        n4.neighbors.add(n3);
        Node res = cloneGraph(n1);
        System.out.println(res.val);
    }

    /**
     * DFS-深度优先搜索   拷贝图
     * 执行用时： 33 ms , 在所有 Java 提交中击败了 81.73% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 94.69% 的用户
     * */
    public Node cloneGraph(Node node) {
        if(node == null){
            return node;
        }
        Map<Integer, Node> visited = new HashMap<>();
        return dfs_Copy(node, visited);
    }
    Node dfs_Copy(Node node, Map<Integer, Node> visited){
        if(visited.containsKey(node.val)){
            return visited.get(node.val);
        }
        Node cur = new Node(node.val);
        visited.put(node.val, cur);
        for (Node next : node.neighbors) {
            cur.neighbors.add(dfs_Copy(next, visited));
        }
        return cur;
    }

    /**
     * BFS-广度优先搜索
     * 执行用时： 32 ms , 在所有 Java 提交中击败了 95.77% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 71.17% 的用户
     * */
    public Node cloneGraph2(Node node) {
        if(node == null){
            return node;
        }
        HashMap<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited.put(node, new Node(node.val));
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for (Node next : cur.neighbors) {
                if(!visited.containsKey(next)){
                    visited.put(next, new Node(next.val));
                    queue.add(next);
                }
                visited.get(cur).neighbors.add(visited.get(next));
            }
        }
        return visited.get(node);
    }

}


