package cn.czl.list;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author RedRush
 * @date 2020/8/31 11:24
 * @description:
 */
public class VisitAllRoom_Normal {


    @Test
    public void TestSoulution(){
        List<List<Integer>> ROOMLIST =  new ArrayList<List<Integer>>();
        ROOMLIST.add(Arrays.asList(1));
        ROOMLIST.add(Arrays.asList(2));
        ROOMLIST.add(Arrays.asList(3));
        ROOMLIST.add(Arrays.asList());
//        for (List<Integer> listTemp : ROOMLIST) {
//            for (int temp : listTemp) {
//                System.out.print(temp + "\t");
//            }
//            System.out.println();
//        }
        System.out.println(canVisitAllRooms(ROOMLIST));
    }
    /**
     * 第一版
     * */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int roomCount = rooms.size();
        Boolean[] roomLocks = new Boolean[roomCount];
        UnlockRoom(0, rooms, roomLocks);
        roomLocks[0] = true;
//        for (int roomNum = 0; roomNum < roomCount; roomNum++) {
//            try {
//                if (roomLocks[roomNum]){
//                    UnlockRoom(roomNum, rooms, roomLocks);
//                }
//            }catch (Exception e){
//                continue;
//            }
//        }
        for (Boolean temp : roomLocks) {
            try {
                if (!temp){
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }
        return true;
    }
    void UnlockRoom(int pos, List<List<Integer>> rooms, Boolean[] roomLocks){
        for(Integer key : rooms.get(pos)){
            try {
                if (!roomLocks[key]){
                }
            }catch (Exception e){
                roomLocks[key] = true;
                System.out.println("key:" + key + "；roomLocks-" + roomLocks[key]);
                UnlockRoom(key, rooms,roomLocks);
            }
        }
    }
    /**
     * 第二版
     * 深度搜索，(非标准dfs),省去非必要循环。
     * 判断是否全部遍历处，仍可优化；开锁函数仍可以优化逻辑。
     * */
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        int roomCount = rooms.size();
        Boolean[] roomLocks = new Boolean[roomCount];
        UnlockRoom2(0, rooms, roomLocks);
        roomLocks[0] = true;
        for (Boolean temp : roomLocks) {
            try {
                if (!temp){
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }
        return true;
    }
    void UnlockRoom2(int pos, List<List<Integer>> rooms, Boolean[] roomLocks){
        for(Integer key : rooms.get(pos)){
            try {
                if (!roomLocks[key]){
                }
            }catch (Exception e){
                roomLocks[key] = true;
                UnlockRoom2(key, rooms,roomLocks);
            }
        }
    }

    /**
     * 标准深度搜索(dfs)
     * */
    boolean[] UnlockMark;
    int UnlockNumber;
    public boolean canVisitAllRooms3(List<List<Integer>> rooms) {
        int n = rooms.size();
        UnlockNumber = 0;
        UnlockMark = new boolean[n];
        dfs(rooms, 0);
        return UnlockNumber == n;
    }

    public void dfs(List<List<Integer>> rooms, int x) {
        UnlockMark[x] = true;
        UnlockNumber++;
        for (int it : rooms.get(x)) {
            if (!UnlockMark[it]) {
                dfs(rooms, it);
            }
        }
    }
    /**
     * 标准广度优先搜索
     * */
    public boolean canVisitAllRooms4(List<List<Integer>> rooms) {
        int n = rooms.size(), num = 0;
        boolean[] vis = new boolean[n];
        Queue<Integer> que = new LinkedList<Integer>();
        vis[0] = true;
        que.offer(0);
        while (!que.isEmpty()) {
            int x = que.poll();
            num++;
            for (int it : rooms.get(x)) {
                if (!vis[it]) {
                    vis[it] = true;
                    que.offer(it);
                }
            }
        }
        return num == n;
    }

}
