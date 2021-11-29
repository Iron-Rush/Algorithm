package cn.czl.utils.auto.operate;



import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author RedRush
 * @date 2021/11/25 17:58
 * @description:
 */
public class AutoClick {
    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        while(true) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(600);
        }
    }

}
