package cn.czl.study.book.recursive;

/**
 * @author RedRush
 * @date 2021/5/24 20:37
 * @description:
 */
public class ClassicDemo {

    // 汉诺塔
    void TowersOfHanoi(int n, char fromPeg, char toPeg, char auxPeg){
        // 如果仅有一个圆盘，直接移动，然后返回
        if(n == 1){
            System.out.println("从" + fromPeg + "移动一个圆盘至" + toPeg);
            return;
        }
        // 利用C柱作辅助，将A柱最上面n-1个圆盘移动至B柱
        TowersOfHanoi(n-1, fromPeg, auxPeg, toPeg);
        System.out.println("从" + fromPeg + "移动一个圆盘至" + toPeg);
        // 利用A柱作辅助，将B住最上面n-1个圆盘移动至C柱
        TowersOfHanoi(n-1, auxPeg, toPeg, fromPeg);
    }
}
