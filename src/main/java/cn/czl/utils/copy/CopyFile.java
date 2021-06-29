package cn.czl.utils.copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author RedRush
 * @date 2021/6/28 10:08
 * @description:
 */
public class CopyFile {

    /**
     * sFile为被拷贝文件的绝对路径
     * tFile为拷贝文件的输出路径
     * */
    public boolean copy(String sFile, String tFile){
        //获取源文件的名称
//        String newFileName = sFile.substring(sFile.lastIndexOf("\\")+1); //目标文件地址
//        System.out.println("源文件:"+newFileName);
//        tFile = tFile + File.separator + newFileName; //源文件地址
        System.out.println("目标文件地址:"+tFile);
        try{
            FileInputStream fis = new FileInputStream(sFile);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(tFile); //创建输出流对象               
            byte datas[] = new byte[1024*8];//创建搬运工具
            int len = 0;//创建长度   
            while((len = fis.read(datas))!=-1){ //循环读取数据
                fos.write(datas,0,len);
            }
            fis.close();//释放资源
            fis.close();//释放资源
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
