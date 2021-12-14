package cn.czl.utils.auto.operate;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author RedRush
 * @date 2021/12/14 17:07
 * @description: 删除路径下，全部空文件夹
 */
public class CleanEmptyDir {

    /**
     * 删除空文件夹
     * */
    @Test
    public void DelDir(){
        String path = "D:\\delTest";
        delEmptyDir(path);
    }

    @Test
    public void RenameFile(){
        String path = "D:\\Downloads\\Picture\\WeiXin";
        replaceFiles(path, "gBigMsg.", "msg");
    }

    public static void delEmptyDir(String path) {
        File root = new File(path);
        Deque<File> files = getAllDir(root);
        while (!files.isEmpty()){
            File dir = files.pop();
            if (dir.listFiles().length == 0){
                System.out.println("删除目录:" + dir.getAbsolutePath());
                dir.delete();
            }
        }
    }

    /**
     * 获取目录下全部文件夹
     * */
    public static Deque<File> getAllDir(File root){
        Deque<File> ans = new LinkedList<>();
        if (root.isFile())  return ans;
        Deque<File> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            File file = queue.poll();
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                queue.addAll(Arrays.asList(files));
                ans.push(file);
            }
        }
        return ans;
    }

    /**
     * 替换目标文件夹下，全部文件名中的字符串
     * */
    public static void replaceFiles(String path, String regex, String replacement){
        Deque<File> files = getAllFile(new File(path));
        while (!files.isEmpty()){
            File file = files.poll();
            File toFile = new File(file.getParent() + File.separator + file.getName().replaceAll(regex, replacement));
//            System.out.println(toFile.getAbsolutePath());
            System.out.println(file.getParent());
            System.out.println(toFile.getName());
            file.renameTo(toFile);
        }
    }
    /**
     * 获取目录下全部文件
     * */
    public static Deque<File> getAllFile(File root){
        Deque<File> ans = new LinkedList<>();
        if (root.isFile())  return ans;
        Deque<File> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            File file = queue.poll();
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                queue.addAll(Arrays.asList(files));
            }else{
                ans.push(file);
            }
        }
        return ans;
    }
}
