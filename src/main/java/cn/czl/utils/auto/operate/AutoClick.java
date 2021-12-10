package cn.czl.utils.auto.operate;



import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

/**
 * @author RedRush
 * @date 2021/11/25 17:58
 * @description:
 */
public class AutoClick {
    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        robot.delay(2000);
        for (int i = 0; i < 999999; i++) {
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            autoSetPasteBoard(i);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(1000);
            System.out.println(i);
        }
//        while(true) {
//            robot.keyPress(KeyEvent.VK_CONTROL);
//            robot.keyPress(KeyEvent.VK_V);
//            robot.keyRelease(KeyEvent.VK_CONTROL);
//            robot.keyRelease(KeyEvent.VK_V);
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//            robot.delay(600);
//        }
    }

    public static void autoSetPasteBoard(int i){
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            // 封装文本内容
            Transferable trans = new StringSelection(String.valueOf(i));
            // 把文本内容设置到系统剪贴板
            clipboard.setContents(trans, null);
            System.out.println("i:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
