package cn.czl.search.str;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/11/11 9:35
 * @description:
 *      514. 自由之路
 *      - 给定一个字符串 ring，表示刻在外环上的编码；
 *      给定另一个字符串 key，表示需要拼写的关键词。
 *      您需要算出能够拼写关键词中所有字符的最少步数。
 *      示例：
 *      输入: ring = "godding", key = "gd"    输出: 4
 *      解释:
 *       对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
 *       对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 *       当然, 我们还需要1步进行拼写。因此最终的输出是 4
 *      提示：
 *          1.ring 和 key 的字符串长度取值范围均为 1 至 100；
 *          2.两个字符串中都只有小写字符，并且均可能存在重复字符；
 *          3.字符串 key 一定可以由字符串 ring 旋转拼出。
 */
public class FindRotateSteps_Hard {

    private String RING1 = "godding";
    private String KEY1 = "gd";
    private String RING2 = "iotfo";
    private String KEY2 = "fioot";

    @Test
    public void TestSolution(){
        System.out.println(findRotateSteps4(RING1, KEY1));
    }

    /**
     * dfs-带备忘录
     * 执行用时：22 ms, 在所有 Java 提交中击败了19.86%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了84.67%的用户
     * */
    Map<String, Integer> memory = new HashMap<>();
    public int findRotateSteps(String ring, String key) {
        return dfs(ring.toCharArray(), key.toCharArray(), 0, 0);
    }

    private int dfs(char[] ring, char[] key, int ringPos, int keyPos){
        if (keyPos == key.length){  // 输入完全部密码后，终止递归
            return 0;
        }
        // 查看备忘录中是否已有解
        String mKey = ringPos + "-" + keyPos;
        if (memory.containsKey(mKey)){
            return memory.get(mKey);
        }
        // 分别计算左侧/右侧旋转的解
        int rCount = 0, lCount = 0;
        int ringPosR = ringPos, ringPosL = ringPos;
        while (ring[ringPosR] != key[keyPos]){
            ringPosR = rotateRight(ring, ringPosR);
            rCount++;
        }
        rCount++;
        rCount += dfs(ring, key, ringPosR, keyPos + 1);
        while (ring[ringPosL] != key[keyPos]){
            ringPosL = rotateLeft(ring, ringPosL);
            lCount++;
        }
        lCount++;
        lCount += dfs(ring, key, ringPosL, keyPos + 1);
        int res = Math.min(rCount, lCount);
        // 将当前答案写入备忘录
        memory.put(mKey, res);
        return res;
    }

    // 获取顺时针旋转的下一个指针
    int rotateRight(char[] dic, int pos){
        return pos == dic.length-1 ? 0 : pos + 1;
    }
    // 获取逆时针旋转的下一个指针
    int rotateLeft(char[] dic, int pos){
        return pos == 0 ? dic.length-1 : pos - 1;
    }

    /**
     * 执行用时：38 ms, 在所有 Java 提交中击败了9.59%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了58.39%的用户
     * */
    Map<String, Integer> memo = new HashMap<>();
    public int findRotateSteps2(String ring, String key) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < ring.length(); i++) {
            char k= ring.charAt(i);
            if (!map.containsKey(k)) {
                map.put(k, new LinkedList<Integer>());
            }
            map.get(k).add(i);
        }
        return key.length() + dfs(ring, key, 0 ,0, map);
    }
    // 将罗盘存入Map，取值进行递归
    int dfs(String ring, String key, int rPos, int kPos, Map<Character, List<Integer>> map){
        if (kPos == key.length()){
            return 0;
        }
        String mkey = rPos + "-" + kPos;
        if (memo.containsKey(mkey)){
            return memo.get(mkey);
        }
        char curKey = key.charAt(kPos);
        int dist = Integer.MAX_VALUE;
        for (int ringPos : map.get(curKey)){
            int dist1 = Math.abs(rPos - ringPos);
            int dist2 = ring.length() - dist1;
            int curMin = Math.min(dist1, dist2);
            dist = Math.min(dist, curMin + dfs(ring, key, ringPos, kPos + 1, map));
        }
        memo.put(mkey, dist);
        return dist;
    }

    /**
     * 动态规划
     * */
    public int findRotateSteps4(String ring, String key) {
        // map主要用于保存ring里字符所有的index
        Map<Character, List<Integer>> map = new HashMap<>();
        // 初始化map,键:ring中的字符,值:ring对应的全部index
        for (int i = 0; i < ring.length(); i++) {
            char k= ring.charAt(i);
            if (!map.containsKey(k)) {
                map.put(k, new LinkedList<Integer>());
            }
            map.get(k).add(i);
        }
        int rLen = ring.length(), kLen = key.length();
        // dp[i][j] means that min cost from key[i] to ring[j]
        int[][] dp = new int[kLen][rLen];
        for (int i = 0; i < kLen; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int j : map.get(key.charAt(0))) {      // 初始化dp数组第一行，第一位密码的答案
            dp[0][j] = Math.min(j, rLen - j);
        }
        for (int i = 1; i < kLen; i++) {            // 遍历全部密码
            for (int j : map.get(key.charAt(i))) {  // 遍历当前密码的全部答案
                int temp = Integer.MAX_VALUE;       // 与之前密码答案进行匹配，累加
                for (int k : map.get(key.charAt(i - 1))) {  // (获取前一位密码答案)
                    // 当前距离的两种计算方式( abs(j-k) / rLen-abs(j-k) )，-> 择优
                    temp = Math.min(temp, dp[i - 1][k] + Math.abs(j - k));
                    temp = Math.min(temp, dp[i - 1][k] + rLen - Math.abs(j - k));
                }
                dp[i][j] = temp;    // 填充当前罗盘位置，最优答案
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return Arrays.stream(dp[kLen - 1]).min().getAsInt() + kLen;// 获取最后一行答案中的最小值 + 密码长度
    }

}
