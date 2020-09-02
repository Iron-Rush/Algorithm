package cn.czl.search.bfs;

import java.util.List;

/**
 * @author RedRush
 * @date 2020/9/1 17:17
 * @description:
 *      559.N叉树的最大深度
 *      给定一个 N 叉树，找到其最大深度。
 */
public class MaxDepth_Easy {
    /**
     * 递归-遍历全部节点
     * 执行用时：0 ms,在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.2 MB,在所有 Java 提交中击败了11.82%的用户
     * */
    public int maxDepth(Node root) {
        if (root == null){
            return 0;
        }
        int count = 0;
        count = getDepth(root, 0);
        return count;
    }
    public int getDepth(Node node, int depth){
        if (node.children.size() == 0){
            return ++depth;
        }
        int depCompare = 0;
        for (Node nodeChild : node.children) {
            int depTemp = getDepth(nodeChild, depth);
            depCompare = depCompare > depTemp ? depCompare : depTemp;
        }
        return depCompare + 1;
    }
    /**
     * 递归-简洁版
     * */
    public int maxDepth2(Node root) {
        if (null == root) {
            return 0;
        }
        int result = 1;
        for (Node child : root.children) {
            result = Math.max(result, 1 + maxDepth(child));
        }
        return result;
    }
}
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}