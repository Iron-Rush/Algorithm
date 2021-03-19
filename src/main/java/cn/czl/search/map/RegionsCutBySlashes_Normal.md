**959. 由斜杠划分区域**
- 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
- （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
- 返回区域的数目。

示例 1：
```
输入：
[
  " /",
  "/ "
]
输出：2
解释：2x2 网格如下：
```
![RegionsCutBySlashes1](../../../../../resources/search/map/RegionsCutBySlashes1.png "RegionsCutBySlashes1")

示例 2：
```
输入：
[
  " /",
  "  "
]
输出：1
解释：2x2 网格如下：
```
![RegionsCutBySlashes2](../../../../../resources/search/map/RegionsCutBySlashes2.png "RegionsCutBySlashes2")

示例 3：
```
输入：
[
  "\\/",
  "/\\"
]
输出：4
解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
2x2 网格如下：
```
![RegionsCutBySlashes3](../../../../../resources/search/map/RegionsCutBySlashes3.png "RegionsCutBySlashes3")

示例 4：
```
输入：
[
  "/\\",
  "\\/"
]
输出：5
解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
2x2 网格如下：
```
![RegionsCutBySlashes4](../../../../../resources/search/map/RegionsCutBySlashes4.png "RegionsCutBySlashes4")

示例 5：
```
输入：
[
  "//",
  "/ "
]
输出：3
解释：2x2 网格如下：
```
![RegionsCutBySlashes5](../../../../../resources/search/map/RegionsCutBySlashes5.png "RegionsCutBySlashes5")

提示：
- 1 <= grid.length == grid[0].length <= 30
- grid[i][j] 是 '/'、'\'、或 ' '。

RegionsCutBySlashes1.png