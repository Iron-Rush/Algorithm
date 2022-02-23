package cn.czl.search.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2022/2/21 17:30
 * @description:
 *      838. 推多米诺
 *      - n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。
 *          在开始时，同时把一些多米诺骨牌向左或向右推。
 *      - 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。
 *          同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 *      - 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡，
 *          该骨牌仍然保持不变。
 *      - 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下
 *          或已经倒下的多米诺骨牌施加额外的力。
 *      - 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 *          - dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 *          - dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 *          - dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 *      - 返回表示最终状态的字符串。
 *      示例 1：
 *          输入：dominoes = "RR.L"
 *          输出："RR.L"
 *          解释：第一张多米诺骨牌没有给第二张施加额外的力。
 *      示例 2：
 *          输入：dominoes = ".L.R...LR..L.."
 *          输出："LL.RR.LLRRLL.."
 *      提示：
 *          n == dominoes.length
 *          1 <= n <= 10^5
 *          dominoes[i] 为 'L'、'R' 或 '.'
 */
public class PushDominoes_Normal {

    private String d1 = "RR..L";
    private String d2 = "..RR...L..";
    private String d3 = "L..LR";
    private String d4 = ".L.R...LR..L..";
    @Test
    public void TestSolution(){
        System.out.println(pushDominoes(d1).equals("RRRLL"));
        System.out.println(pushDominoes(d2).equals("..RRR.LL.."));
        System.out.println(pushDominoes(d3).equals("LLLLR"));
        System.out.println(pushDominoes(d4).equals("LL.RR.LLRRLL.."));
    }

    /**
     * 逐次模拟
     * 执行用时： 26 ms , 在所有 Java 提交中击败了 28.83% 的用户
     * 内存消耗： 48.9 MB , 在所有 Java 提交中击败了 5.40% 的用户
     * */
    public String pushDominoes(String dominoes) {
        char[] chs = dominoes.toCharArray();
        int len = chs.length;
        // 下一刻可能倾倒的骨牌
        Queue<Integer> queue = new ArrayDeque<>(len);
        // 此刻要左倒的骨牌
        Queue<Integer> lAdd = new ArrayDeque<>(len);
        // 此刻要右倒的骨牌
        Queue<Integer> rAdd = new ArrayDeque<>(len);
        // 可能倾倒的骨牌入队
        for(int i = 0; i < len; i++){
            if(chs[i] == '.'){
                if(i == 0 || i == len-1 || !(chs[i-1] == '.' && chs[i+1] == '.')){
                    queue.add(i);
                }
            }
        }
        // 循环模拟倾倒
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int cur = queue.poll();
                // 夹在中间 或 已经受力平衡的骨牌 不做处理
                if(cur != 0 && cur != len - 1 && chs[cur-1] == 'R' && chs[cur+1] == 'L'){
                    continue;
                }
                // 右倾骨牌
                if(cur != 0 && chs[cur - 1] == 'R'){
                    rAdd.add(cur);
                    if(cur + 1 <= len - 1 && chs[cur + 1] == '.'){
                        queue.add(cur+1);
                    }
                }
                // 左倾骨牌
                if(cur != len - 1 && chs[cur + 1] == 'L'){
                    lAdd.add(cur);
                    if(cur - 1 >= 0 && chs[cur - 1] == '.'){
                        queue.add(cur-1);
                    }
                }
            }
            if(lAdd.isEmpty() && rAdd.isEmpty())   break;
            // 一次性推倒此刻骨牌
            add(chs, lAdd, rAdd);
        }
        return new String(chs);
    }
    // 模拟倒牌函数
    private void add(char[] chs, Queue<Integer> lAdd, Queue<Integer> rAdd){
        while(!lAdd.isEmpty()){
            chs[lAdd.poll()] = 'L';
        }
        while(!rAdd.isEmpty()){
            chs[rAdd.poll()] = 'R';
        }
    }

    /**
     * BFS 广度优先搜索 (记录 比较受力时间，处理中间受力平衡的骨牌)
     * 执行用时： 24 ms , 在所有 Java 提交中击败了 34.68% 的用户
     * 内存消耗： 49 MB , 在所有 Java 提交中击败了 5.40% 的用户
     * */
    public String pushDominoes2(String dominoes) {
        char[] chs = dominoes.toCharArray();
        int len = chs.length;
        int[] arr = new int[len];
        Deque<int[]> deque = new ArrayDeque<>(len);
        for (int i = 0; i < len; i++) {
            if(chs[i] == '.')   continue;
            int dire = chs[i] == 'L' ? -1 : 1;
            deque.add(new int[]{i, 1, dire});  // [pos, time, direction]
            arr[i] = 1;
        }
        while (!deque.isEmpty()){
            int[] info = deque.pollFirst();
            int idx = info[0], time = info[1], dire = info[2];
            int next = idx + dire;
            if(chs[idx] == '.' || next < 0 || next >= len)  continue;
            if(arr[next] == 0){     // 首次受力
                deque.addLast(new int[]{next, time + 1, dire});
                arr[next] = time + 1;
                chs[next] = dire == -1 ? 'L' : 'R';
            }else if(arr[next] == time + 1){    // 多次受力 处于平衡
                chs[next] = '.';
            }
        }
        return new String(chs);
    }
}
