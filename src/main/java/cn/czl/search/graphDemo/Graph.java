package cn.czl.search.graphDemo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
g * @date 2022/1/29 11:52
 * @description: 无向图的数据结构定义
 */
public class Graph {


    /**
     * 广度优先搜索Demo
     * */
    public void bfs(int s, int target){
        if(s == target)  return;
        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        Arrays.fill(prev, -1);
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for (int i = 0; i < adj[cur].size(); i++) {
                int next = adj[cur].get(i);
                if (!visited[next]){
                    prev[next] = cur;
                    if(next == target){
                         printPath(prev, s, target);
                        return;
                    }
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }
    // 获取当前节点全部路径
    private void printPath(int[] prev, int source, int target){
        if(prev[target] != -1 && source != target){
            printPath(prev, source, prev[target]);
        }
        System.out.println(target + " ");
    }


    /**
     * 深度优先搜索Demo
     * */
    boolean found = false;  // 全局变量
    public void dfs(int s, int target){
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        Arrays.fill(prev, -1);

        printPath(prev, s, target);
    }
    // 深度优先搜索 递归函数
    private void recurDfs(int cur, int target, boolean[] visited, int[] prev){
        if(found)  return;
        visited[cur] = true;
        if(cur == target){
            found = true;
            return;
        }
        LinkedList<Integer> toVisit = adj[cur];
        for (int i = 0; i < toVisit.size(); i++) {
            int visit = toVisit.get(i);
            if(!visited[visit]){
                prev[visit] = cur;
                recurDfs(visit, target, visited, prev);
            }
        }
    }


    private int v;      // 定点的个数
    private LinkedList<Integer>[] adj;  // 邻接表
    // 初始化图的大小
    public Graph(int v){
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }
    // 连通两个定点
    public void addEdge(int s, int t){
        adj[s].add(t);
        adj[t].add(s);
    }
}
