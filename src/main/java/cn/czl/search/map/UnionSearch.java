package cn.czl.search.map;

/**
 * @author RedRush
 * @date 2021/1/18 9:30
 * @description:
 *      并查集模版
 */
public class UnionSearch {
    private int[] parent;
    public UnionSearch(int size){
        parent = new int[size];
        for(int i = 0; i < size; i++){
            parent[i] = i;
        }
    }
    public int search(int x){
        if(parent[x] != x){
            parent[x] = search(parent[x]);
        }
        return parent[x];
    }
    public void union(int x, int y){
        parent[search(x)] = search(y);
    }
}
