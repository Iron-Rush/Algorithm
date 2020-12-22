package cn.czl.study.stackAndQueue.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/12/22 16:42
 * @description:
 *      Definition for a Node.
 */


public class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

