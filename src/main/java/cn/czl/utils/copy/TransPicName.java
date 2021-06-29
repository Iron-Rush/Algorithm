package cn.czl.utils.copy;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author RedRush
 * @date 2021/6/28 10:53
 * @description: 将根目录下的全部文件，粘贴至目标地址，将之前的路径层级，通过文件名展示
 */
public class TransPicName {

    private String PICHEAD = "前女友";
    private String ROOT = "D:\\trans\\";
    private String SOURCE = "D:\\Downloads\\前女友系列\\";

    @Test
    public void TestGetFiles(){
        int skip = 0, copy = 0;
        List<File> files = getFiles(SOURCE);
        CopyFile copyFile = new CopyFile();
        for(File f : files){
            if (skip(f)){
                System.out.println("skip:" + f.getAbsolutePath());
                skip++;
                continue;
            }
            System.out.println(f.getAbsolutePath());
            String picUrl = f.getAbsolutePath().substring(SOURCE.length()).replaceAll("\\\\", "_");
//            picUrl = picUrl.replaceAll("9999 ", "");
            picUrl = ROOT + PICHEAD + special(picUrl);
            System.out.println(picUrl);
            copyFile.copy(f.getAbsolutePath(), picUrl);
            copy++;
        }
        System.out.println("拷贝任务结束，共" + files.size() + "个文件");
        System.out.println("拷贝" + copy + "个文件,跳过" + skip + "个文件");
    }

    // 目标文件名进行特判处理
    public String special(String str){
        String res = str.replaceAll("9", "");
        res = res.replaceAll(" ", "");
        return res;
    }

    // 小文件跳过
    public boolean skip(File f){
        return f.length() < 1000;
    }

    // 获取目录下的全部文件
    public List<File> getFiles(String path){
        File root = new File(path);
        List<File> files = new ArrayList<File>();
        if(!root.isDirectory()){
            files.add(root);
        }else{
            File[] subFiles = root.listFiles();
            for(File f : subFiles){
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }
}
