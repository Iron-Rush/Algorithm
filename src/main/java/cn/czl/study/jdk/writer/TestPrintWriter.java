package cn.czl.study.jdk.writer;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * @author RedRush
 * @date 2021/10/27 14:28
 * @description:    PrintWriter测试
 */
public class TestPrintWriter {

    @Test
    public void TestPW(){
        try {
            File file = new File("./log.txt");
            PrintWriter printWriter = new PrintWriter(file);
//            PrintWriter printWriter = new PrintWriter("./log.txt");
            String nullStr = null;
            printWriter.append("append");
            printWriter.append(nullStr);
            printWriter.print("print");
            printWriter.print(nullStr);
            printWriter.println("println");
            printWriter.println(nullStr);
            printWriter.write("write");
//            printWriter.write(nullStr);       // write不可写null
            printWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestPWAutoFlush(){
        try {
            FileOutputStream fos = new FileOutputStream("./log.txt");
//            PrintWriter pw = new PrintWriter(fos);      // 默认不开启自动刷新
            PrintWriter pw = new PrintWriter(fos, true);
//            pw.append("append");
            pw.println("println");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
