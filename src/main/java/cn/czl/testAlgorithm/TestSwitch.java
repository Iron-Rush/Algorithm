package cn.czl.testAlgorithm;


/**
 * @author RedRush
 * @date 2020/8/25 11:47
 */
public class TestSwitch {
    public static void main(String[] args) {
        int repeatInterval = 500;
        String repeatUnit = "4";
//        TestSwitch ts = new TestSwitch();
        TestSwitch.getInterval_If_Else(repeatInterval, repeatUnit);
//        TestSwitch.getInterval_Switch_Case(repeatInterval, repeatUnit);
    }
    /**
     * if-else效率测试
     * */
    public static long getInterval_If_Else(int repeatInterval, String repeatUnit){
        long interval=0L;
        long startTime = System.nanoTime();

        if(repeatUnit.equals("1")){//秒
            interval=repeatInterval*1000;
        } else if (repeatUnit.equals("2")) {//分钟
            interval=repeatInterval*60*1000;

        }else if (repeatUnit.equals("3")) {//小时
            interval=repeatInterval*60*60*1000;

        }else if (repeatUnit.equals("4")) {//天
            interval=repeatInterval*24*60*60*1000;

        }else if (repeatUnit.equals("5")) {//月
            interval=repeatInterval*30*24*60*60*1000;

        }else if (repeatUnit.equals("6")) {//周
            interval=repeatInterval*7*24*60*60*1000;

        }
        long endTime = System.nanoTime();
        System.out.println("if-else Test：" + (endTime - startTime));
        return interval;
    }
    /**
     * switch-case效率测试
     * */
    public static long getInterval_Switch_Case(int repeatInterval, String repeatUnit){
        long interval = 0L;
        long startTime = System.nanoTime();
        switch (repeatUnit){
            case "1":
                interval=repeatInterval*1000;
                break;
            case "2":
                interval=repeatInterval*60*1000;
                break;
            case "3":
                interval=repeatInterval*60*60*1000;
                break;
            case "4":
                interval=repeatInterval*24*60*60*1000;
                break;
            case "5":
                interval=repeatInterval*30*24*60*60*1000;
                break;
            case "6":
                interval=repeatInterval*7*24*60*60*1000;
                break;
        }
        long endTime = System.nanoTime();
        System.out.println("switch-case Test：" + (endTime - startTime));
        return interval;
    }
}
