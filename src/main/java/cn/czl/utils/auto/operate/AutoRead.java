package cn.czl.utils.auto.operate;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2021/11/25 16:48
 * @description:    自动读取文件夹下文本
 */
public class AutoRead {
    String ROOT = "D:\\trans\\txt";
    Queue<String> queue = new LinkedList<>();
    Deque<File> fileQueue = new LinkedList<>();


    public static void main(String[] args) {
        AutoRead autoRead = new AutoRead();
//        autoRead.keepReadingToPasteBoard();
        autoRead.autoSetPasteBoard();
    }

    public void autoSetPasteBoard(){
        try {
            Thread.sleep( 1000 );
            for (int i = 0; i < 999999; i++) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                // 封装文本内容
                Transferable trans = new StringSelection(String.valueOf(i));
                // 把文本内容设置到系统剪贴板
                clipboard.setContents(trans, null);
                Thread.sleep( 1000 );
                System.out.println("i:" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void keepReadingToPasteBoard(){

        try {
            String key = "";
            while (!key.equals("1")){
                // 系统剪贴板
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                setPasteBoard(clipboard);
                Thread.sleep( 1000 );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPasteBoard(Clipboard clipboard){
        // 获取系统剪贴板
        if (queue.isEmpty()){
            readTxt();
        }
        // 封装文本内容
        Transferable trans = new StringSelection(queue.poll());
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
    }

    private void getTxtFile(File f){
        if(f.isFile()){
            fileQueue.add(f);
        }else {
            for(File temp : f.listFiles()){
                getTxtFile(temp);
            }
        }
    }

    private void readTxt(){
        try {
            if (fileQueue.isEmpty()){
                getTxtFile(new File(ROOT));
            }
            File f = fileQueue.pop();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null){
                queue.add(lineTxt);
//                System.out.println(lineTxt);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void TestMethod(){
        getTxtFile(new File(ROOT));
        System.out.println(fileQueue.size());
//        fileQueue.add(new File("D:\\trans\\txt\\魔术/111_桷雏香.txt"));
//        readTxt();
    }
}
