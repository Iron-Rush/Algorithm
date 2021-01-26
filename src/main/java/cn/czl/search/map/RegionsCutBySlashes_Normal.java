package cn.czl.search.map;

import java.util.HashSet;
import java.util.Set;

/**
 * @author RedRush
 * @date 2021/1/25 9:37
 * @description:
 *      959. 由斜杠划分区域
 *      - 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 *      - （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
 *      - 返回区域的数目。
 *      示例 1：
 *          输入：
 *              [ " /",
 *                "/ "]
 *          输出：2
 *      示例 2：
 *          输入：
 *              [ " /",
 *                "  "]
 *          输出：1
 *      示例 3：
 *          输入：
 *              [ "\\/",
 *                "/\\" ]
 *          输出：4
 *          解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
 *      示例 4：
 *          输入：
 *              [ "/\\",
 *                "\\/" ]
 *          输出：5
 *      示例 5：
 *          输入：
 *              [ "//",
 *                "/ " ]
 *          输出：3
 *      提示：
 *       1 <= grid.length == grid[0].length <= 30
 *       grid[i][j] 是 '/'、'\'、或 ' '。
 */
public class RegionsCutBySlashes_Normal {

    
    /**
     * 并查集，将每个矩形划分为四个三角形，根据线段，关联三角形内部连通
     * 执行用时： 11 ms , 在所有 Java 提交中击败了 12.03% 的用户
     * 内存消耗： 37.9 MB , 在所有 Java 提交中击败了 28.77% 的用户
     * */
    public int regionsBySlashes(String[] grid) {
        int size = grid.length;
        UnionSearch us = new UnionSearch(size * size * 4);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int idx = i * size + j;
                if(i < size - 1){
                    int bottom = idx + size;
                    us.union(idx * 4 + 2, bottom * 4);
                }
                if(j < size - 1){
                    int right = idx + 1;
                    us.union(idx * 4 + 1, right * 4 + 3);
                }
                if(grid[i].charAt(j) == '/'){
                    us.union(idx * 4, idx * 4 + 3);
                    us.union(idx * 4 + 1, idx *4 + 2);
                }else if(grid[i].charAt(j) == '\\'){
                    us.union(idx * 4, idx * 4 + 1);
                    us.union(idx * 4 + 2, idx * 4 + 3);
                }else {
                    us.union(idx * 4, idx * 4 + 1);
                    us.union(idx * 4 + 1, idx * 4 + 2);
                    us.union(idx * 4 + 2, idx * 4 + 3);
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < size * size * 4; i++) {
            set.add(us.search(i));
        }
        return set.size();
    }

}

