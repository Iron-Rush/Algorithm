package cn.czl.study.stackAndQueue.queue;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/12/11 14:16
 * @description:
 *      广度优先搜索-模版
 *      - 有两种情况你不需要使用哈希集：
 *       1.你完全确定没有循环，例如，在树遍历中；
 *       2.你确实希望多次将结点添加到队列中。
 */
public class BFS_TEMPLATE {

    /**
     * 代码所示，在每一轮中，队列中的结点是等待处理的结点。
     * 在每个更外一层的 while 循环之后，我们距离根结点更远一步。
     * 变量 step 指示从根结点到我们正在访问的当前结点的距离。
     * */
    int BFS(Node root, Node target){
        Queue<Node> queue = new LinkedList<>();
        int step = 0;
        if(root != null) queue.add(root);
        while (!queue.isEmpty()){
            step++;
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                Node cur = queue.poll();
                if(cur == target){
                    return step;
                }
                for (Node temp : cur.list) {
                    queue.add(temp);
                }
            }
        }
        return -1;
    }

    /**
     * 确保同一节点不会被重复访问[防止死循环]
     * 通过添加一个哈希集来解决
     */
    int BFS_Hash(Node root, Node target){
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        int step = 0;
        if(root != null){
            queue.add(root);
            visited.add(root);
        }
        while (!queue.isEmpty()){
            step++;
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                Node cur = queue.poll();
                if(cur == target){
                    return step;
                }
                for (Node temp : cur.list) {
                    if(!visited.contains(temp)){
                        visited.add(temp);
                        queue.add(temp);
                    }
                }
            }
        }
        return -1;
    }

}
class Node{
    int val;
    List<Node> list;
    public Node() {}
    public Node(int val) {
        this.val = val;
    }
    public Node(int val, List<Node> list){
        this.val = val;
        this.list = list;
    }
}