package cn.czl.string.compare;

/**
 * @author RedRush
 * @date 2021/11/8 10:43
 * @description:
 *      299. 猜数字游戏
 *      - 你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
 *      - 写出一个秘密数字，并请朋友猜这个数字是多少。朋友每猜测一次，你就会给他一个包含下述信息的提示：
 *      - 猜测数字中有多少位属于数字和确切位置都猜对了（称为 "Bulls", 公牛），
 *          - 有多少位属于数字猜对了但是位置不对（称为 "Cows", 奶牛）。
 *            也就是说，这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字。
 *          - 给你一个秘密数字 secret 和朋友猜测的数字 guess ，请你返回对朋友这次猜测的提示。
 *      - 提示的格式为 "xAyB" ，x 是公牛个数， y 是奶牛个数，A 表示公牛，B 表示奶牛。
 *      - 请注意秘密数字和朋友猜测的数字都可能含有重复数字。
 *      示例 1:
 *          输入: secret = "1807", guess = "7810"
 *          输出: "1A3B"
 *          解释: 数字和位置都对（公牛）用 '|' 连接，数字猜对位置不对（奶牛）的采用斜体加粗标识。
 *               "1807"
 *                 |
 *               "7810"
 *      示例 2:
 *          输入: secret = "1123", guess = "0111"
 *          输出: "1A1B"
 *          解释: 数字和位置都对（公牛）用 '|' 连接，数字猜对位置不对（奶牛）的采用斜体加粗标识。
 *               "1123"        "1123"
 *                 |      or     |
 *               "0111"        "0111"
 *          注意，两个不匹配的 1 中，只有一个会算作奶牛（数字猜对位置不对）。通过重新排列非公牛数字，其中仅有一个 1 可以成为公牛数字。
 *      示例 3：
 *          输入：secret = "1", guess = "0"
 *          输出："0A0B"
 *      示例 4：
 *          输入：secret = "1", guess = "1"
 *          输出："1A0B"
 *      提示：
 *          1 <= secret.length, guess.length <= 1000
 *          secret.length == guess.length
 *          secret 和 guess 仅由数字组成
 */
public class BullsAndCows_Normal {

    /**
     * 计数 - 统计
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 74.11% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 31.75% 的用户
     * -- 优化为StringBuilder返回
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.9 MB , 在所有 Java 提交中击败了 96.23% 的用户
     * */
    public String getHint(String secret, String guess) {
        int[] sCounter = new int[10];
        int[] gCounter = new int[10];
        int xCount = 0, yCount = 0;
        int len = secret.length();
        char[] sChs = secret.toCharArray(), gChs = guess.toCharArray();
        for(int i = 0; i < len; i++){
            int s = sChs[i] - '0';
            int g = gChs[i] - '0';
            if(s == g){
                xCount++;
            }else{
                sCounter[s]++;
                gCounter[g]++;
            }
        }
        for(int i = 0; i < 10; i++){
            yCount += Math.min(sCounter[i], gCounter[i]);
        }
//        return xCount + "A" + yCount + "B";
        StringBuilder sb = new StringBuilder();
        sb.append(xCount).append("A").append(yCount).append("B");
        return sb.toString();
    }

    /**
     * 优化为操作一个计数数组
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 56.85% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 79.07% 的用户
     * */
    public String getHint2(String secret, String guess) {
        int xCount = 0, yCount = 0;
        int[] counter = new int[10];
        int len = secret.length();
        for(int i = 0; i < len; i++){
            if(secret.charAt(i) == guess.charAt(i)){
                xCount++;
            }else{
                if (counter[secret.charAt(i) - '0']-- > 0){
                    yCount++;
                }
                if (counter[guess.charAt(i) - '0']++ < 0){
                    yCount++;
                }
            }
        }
        return xCount + "A" + yCount + "B";
    }
}
