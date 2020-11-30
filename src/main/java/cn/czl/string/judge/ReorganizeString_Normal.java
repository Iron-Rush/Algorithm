package cn.czl.string.judge;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author RedRush
 * @date 2020/11/30 11:17
 * @description:
 *      767. 重构字符串
 *      - 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 *      - 若可行，输出任意可行的结果。若不可行，返回空字符串。
 *      示例 1:
 *          输入: S = "aab"
 *          输出: "aba"
 *      示例 2:
 *          输入: S = "aaab"
 *          输出: ""
 *      注意: S 只包含小写字母并且长度在[1, 500]区间内。
 */
public class ReorganizeString_Normal {

    @Test
    public void TestSolution(){
        System.out.println(reorganizeString3("aaabc"));
    }

    /**
     * [大顶堆]优先队列，队列中数组，0为字母频率，1为对应字母
     * 根据字母频率降序排列。
     * 执行用时：2 ms, 在所有 Java 提交中击败了58.78%的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了70.36%的用户
     * */
    public String reorganizeString(String S) {
        if(S.length() < 2){
            return S;
        }
        StringBuilder sb = new StringBuilder();
        int[] counter = new int[26];
        int maxCount = 0;
        for (char ch : S.toCharArray()) {
            int c = ch - 'a';
            counter[c]++;
            maxCount = Math.max(maxCount, counter[c]);
        }
        if (maxCount > (S.length() + 1) / 2){   // 如果某字符出现频率大于字符串长度的一半+1，则无解
            return "";
        }
        PriorityQueue<int[]> priority = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        for (int i = 0; i < 26; i++) {
            if (counter[i] > 0){
                priority.offer(new int[]{counter[i], i});
            }
        }
        while (priority.size() > 1){
            int[] ch1 = priority.poll();
            int[] ch2 = priority.poll();
            sb.append((char)('a' + ch1[1]));
            sb.append((char)('a' + ch2[1]));
            ch1[0]--;
            ch2[0]--;
            if (ch1[0] > 0){
                priority.offer(ch1);
            }
            if (ch2[0] > 0){
                priority.offer(ch2);
            }
        }
        if (priority.size() > 0){
            sb.append((char)('a' + priority.poll()[1]));
        }
        return sb.toString();
    }


    /**
     * 贪心算法，低频字母优先填充奇数位，高频填充偶数位
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.65%的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了82.41%的用户
     * */
    public String reorganizeString2(String S) {
        if(S.length() < 2){
            return S;
        }
        int[] counter = new int[26];
        int maxCount = 0;
        for (char ch : S.toCharArray()) {
            int c = ch - 'a';
            counter[c]++;
            maxCount = Math.max(maxCount, counter[c]);
        }
        if (maxCount > (S.length() + 1) / 2){   // 如果某字符出现频率大于字符串长度的一半+1，则无解
            return "";
        }
        char[] result = new char[S.length()];
        int evenPos = 0, oddPos = 1;
        for (int i = 0; i < 26; i++) {
            char ch = (char)('a' + i);
            // 低频率字符优先填充奇数位，偶数位留给高频字符
            while (counter[i] > 0 && oddPos < result.length && counter[i] <= S.length()/2){
                result[oddPos] = ch;
                counter[i]--;
                oddPos += 2;
            }
            while (counter[i] > 0){
                result[evenPos] = ch;
                counter[i]--;
                evenPos += 2;
            }
        }
        return new String(result);
    }

    /**
     * 贪心算法2，低频字母优先填充奇数位，高频填充偶数位
     * 直接填充高频字符
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.65%的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了82.41%的用户
     * */
    public String reorganizeString3(String S) {
        if(S.length() < 2){
            return S;
        }
        int[] counter = new int[26];
        int maxIndex = 0;
        for (char ch : S.toCharArray()) {
            counter[ch-'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if(counter[i] > counter[maxIndex]){
                maxIndex = i;
            }
        }
        if (counter[maxIndex] > (S.length() + 1) / 2){   // 如果某字符出现频率大于字符串长度的一半+1，则无解
            return "";
        }
        char[] result = new char[S.length()];
        int pos = 0;
        // 优先将高频字符铺满偶数位
        while (counter[maxIndex] > 0){
            result[pos] = (char)('a' + maxIndex);
            pos += 2;
            counter[maxIndex]--;
        }
        // 填充其他字符
        for (int i = 0; i < 26; i++) {
            while (counter[i] > 0){
                if (pos >= S.length()){
                    pos = 1;    // 若偶数位填充满，开始填充奇数位
                }
                result[pos] = (char)('a' + i);
                counter[i]--;
                pos += 2;
            }
        }
        return new String(result);
    }

}
