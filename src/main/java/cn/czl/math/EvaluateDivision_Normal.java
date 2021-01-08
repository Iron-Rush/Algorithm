package cn.czl.math;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2021/1/6 9:25
 * @description:
 *      399. 除法求值
 *      - 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 *      - 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 *      - 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 *      - 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 *      示例 1：
 *          输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 *          输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 *          解释：
 *              条件：a / b = 2.0, b / c = 3.0
 *              问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 *              结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 *      示例 2：
 *          输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 *          输出：[3.75000,0.40000,5.00000,0.20000]
 *      示例 3：
 *          输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 *          输出：[0.50000,2.00000,-1.00000,-1.00000]
 *      提示：
 *          1 <= equations.length <= 20
 *          equations[i].length == 2
 *          1 <= Ai.length, Bi.length <= 5
 *          values.length == equations.length
 *          0.0 < values[i] <= 20.0
 *          1 <= queries.length <= 20
 *          queries[i].length == 2
 *          1 <= Cj.length, Dj.length <= 5
 *          Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 */
public class EvaluateDivision_Normal {

    private List<List<String>> EQUATIONS = new ArrayList<>();
    private double[] VALUES = {2,3};
    private List<List<String>> QUERIES = new ArrayList<>();

    @Test
    public void TestSolution(){
        EQUATIONS.add(new ArrayList<String>(){{add("a");add("b");}});
        EQUATIONS.add(new ArrayList<String>(){{add("b");add("c");}});
        QUERIES.add(new ArrayList<String>(){{add("a");add("c");}});
        QUERIES.add(new ArrayList<String>(){{add("b");add("a");}});
        QUERIES.add(new ArrayList<String>(){{add("a");add("e");}});
        QUERIES.add(new ArrayList<String>(){{add("a");add("a");}});
        QUERIES.add(new ArrayList<String>(){{add("x");add("x");}});
        System.out.println(Arrays.toString(calcEquation(EQUATIONS, VALUES, QUERIES)));
    }

    /**
     * 邻接矩阵 + 深度优先搜索
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.24% 的用户
     * 内存消耗： 37.3 MB , 在所有 Java 提交中击败了 46.02% 的用户
     * */
    double[][] graph;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] res = new double[queries.size()];
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        // 为字符设置编号
        for (List<String> temp : equations) {
            for (String key : temp) {
                if(!map.containsKey(key)){
                    map.put(key, index++);
                }
            }
        }
        // 建立临街矩阵
        graph = new double[index + 1][index + 1];
        index = 0;
        for (List<String> equation : equations) {
            int a = map.get(equation.get(0));   // 获取除数/被除数编号
            int b = map.get(equation.get(1));
            double value = values[index++];
            graph[a][b] = value;
            graph[b][a] = 1 / value;
        }
        for (int i = 0; i < res.length; i++) {
            List<String> temp = queries.get(i);
            boolean[] vis = new boolean[graph.length];
            // 未存在的定点
            if(!map.containsKey(temp.get(0)) || !map.containsKey(temp.get(1))){
                res[i] = -1;
                continue;
            }
            int a = map.get(temp.get(0));
            int b = map.get(temp.get(1));
            res[i] = dfs(a, b, graph.length, vis);
            // 将得出的值的结果添加到邻接矩阵
            if(res[i] != -1 && graph[a][b] != 0){
                graph[a][b] = res[i];
                graph[b][a] = 1 / res[i];
            }
        }
        return res;
    }
    // 深度优先搜索
    double dfs(int a, int b, int len, boolean[] vis){
        if(graph[a][b] != 0){
            return graph[a][b];
        }
        // 记录遍历过的节点，防止重复
        vis[a] = true;
        for (int i = 0; i < len; i++) {
            if(!vis[i] && graph[a][i] != 0){
                double temp = dfs(i, b, len, vis);
                if(temp != -1){
                    return graph[a][i] * temp;
                }
            }
        }
        return -1;
    }
}
