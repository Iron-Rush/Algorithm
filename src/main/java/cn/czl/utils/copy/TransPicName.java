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

    private String PICHEAD = "";
//    private String ROOT = "D:\\trans\\output\\";       // 文件输出路径
    private String ROOT = "D:\\trans\\docx\\";       // 文件输出路径
//    private String SOURCE = "D:\\Downloads\\pythonDownload\\new\\";     // 文件读取路径
    private String SOURCE = "G:\\Story\\";     // 文件读取路径
//    private String SOURCE = "D:\\Downloads\\前女友系列\\";     // 文件读取路径

    @Test
    public void TestGetFiles(){
        File root = new File(ROOT);
        if(!root.exists()){//如果文件夹不存在
            root.mkdir();//创建文件夹
        }
        int skip = 0, copy = 0;
        CopyFile copyFile = new CopyFile();
//        List<File> files = getFiles(SOURCE);
//        for(File f : files){
//            if (skip(f)){
//                System.out.println("skip:" + f.getAbsolutePath());
//                f.delete();
//                skip++;
//                continue;
//            }
//            System.out.println(f.getAbsolutePath());
//            String picUrl = f.getAbsolutePath().substring(SOURCE.length()).replaceAll("\\\\", "_");
//            picUrl = ROOT + PICHEAD + special(picUrl);
//            System.out.println(picUrl);
//            copyFile.copy(f.getAbsolutePath(), picUrl);
//            copy++;
//        }
        List<String> dirs = getDirs(SOURCE);
            for (String d : dirs) {
                String source = SOURCE + d + "\\";
                String r = ROOT + d + "\\";
                File rootDir = new File(r);
                List<File> files = getFiles(source);
                if(!rootDir.exists()){//如果文件夹不存在
                    rootDir.mkdir();//创建文件夹
                }
                for(File f : files){
                    if (skip(f)){
                        System.out.println("skip:" + f.getAbsolutePath());
                        f.delete();
                        skip++;
                        continue;
                    }
                    System.out.println(f.getAbsolutePath());
                    String picUrl = f.getAbsolutePath().substring(source.length()).replaceAll("\\\\", "_");
                    picUrl = r + PICHEAD + special(picUrl);
                    System.out.println(picUrl);
                    copyFile.copy(f.getAbsolutePath(), picUrl);
                    copy++;
                }
        }
        System.out.println("拷贝任务结束，共" + (copy + skip) + "个文件");
        System.out.println("拷贝" + copy + "个文件,跳过" + skip + "个文件");
    }

    // 目标文件名进行特判处理
    public String special(String str){
        String res = str.replaceAll("", "");
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

    public List<String> getDirs(String path){
        File root = new File(path);
        List<String> files = new ArrayList<String>();
        File[] subFiles = root.listFiles();
        for (File f : subFiles) {
            if (f.isDirectory()){
                files.add(f.getName());
            }
        }
        return files;
    }
}
