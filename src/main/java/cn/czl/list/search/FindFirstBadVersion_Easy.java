package cn.czl.list.search;

import org.junit.jupiter.api.Test;

/**
 * @author RedRush
 * @date 2020/12/7 14:35
 * @description:
 *      278. 第一个错误的版本
 *      - 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 *      - 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 *      - 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 *      示例:
 *          给定 n = 5，并且 version = 4 是第一个错误的版本。
 *          调用 isBadVersion(3) -> false
 *          调用 isBadVersion(5) -> true
 *          调用 isBadVersion(4) -> true
 *          所以，4 是第一个错误的版本。
 */
public class FindFirstBadVersion_Easy {

    int BADVERSION = 10;
    int VERSION = 12;

    @Test
    public void TestSolution(){
//        System.out.println(firstBadVersion(VERSION));
        for (int i = 1; i < 10; i++) {
            this.BADVERSION = i;
            for (int j = i; j < i + 5; j++) {
                System.out.println(firstBadVersion(j) == firstBadVersion2(j));
            }
        }
    }

    /**
     * 二分查找
     * 执行用时：16 ms, 在所有 Java 提交中击败了99.15%的用户
     * 内存消耗：34.9 MB, 在所有 Java 提交中击败了93.79%的用户
     * */
    public int firstBadVersion(int n) {
        int start = 0, end = n, mid;
        while (start < end - 1){
            mid = start + (end - start)/2;  // 防溢出
            if (isBadVersion(mid)){
                end = mid;
            }else {
                start = mid + 1;
            }
        }
        return isBadVersion(start) ? start : end;
    }

    public int firstBadVersion2(int n) {
        int start = 0, end = n, mid;
        while (start < end){
            mid = start + (end - start)/2;
            if (isBadVersion(mid)){
                end = mid;
            }else {
                start = mid + 1;
            }
        }
        return start;
    }

    /**
     * 版本检查函数
     * */
    boolean isBadVersion(int version){
        if (version >= this.BADVERSION){
            return true;
        }
        return false;
    }
}
