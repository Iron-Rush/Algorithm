package cn.czl.utils.auto.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author RedRush
 * @date 2021/9/18 9:53
 * @description:
 */
public class ArraySortTest {

    private static String PRIFIX = "cn.czl.study.sort.";
    private static String MethodName = "sort";
    private static String ClassName = "";
    private static int Runtime = 100;
    private static int Size = 1000;
    private static int Start = -10000;
    private static int End = 10000;

    public static void test(String className, String methodName){
        MethodName = methodName;
        test(className);
    }
    public static void test(String className){
        int aCount = 0, bCount = 0, count = 0;
        boolean flag =true;
        try {
            Class<?> cls = Class.forName(PRIFIX + className);
            Object o = cls.newInstance();
            Method method = cls.getMethod(MethodName, (new int[]{}).getClass());
            for (int i = 0; i < Runtime; i++) {
                int[] arr1 = GenerateArray.generate(Size, Start, End);
                int[] arr2 = arr1.clone();
                long t1 = System.currentTimeMillis();
                method.invoke(o, arr1);
                long t2 = System.currentTimeMillis();
                Arrays.sort(arr2);
                long t3 = System.currentTimeMillis();
                flag &= Arrays.equals(arr1, arr2);
                count++;
                int res = (t2 - t1) > (t3 - t2) ? aCount++ : bCount++;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("未找到对应类");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(flag ? "通过测试" : "排序算法出错！！！");
        System.out.println("共进行" + count + "次测试，传入方法更快" + aCount + "次，系统排序更快" + bCount + "次");
    }

    public static void setRange(int start, int end){
        Start = start;
        End = end;
    }
    public static String getPRIFIX() {
        return PRIFIX;
    }

    public static void setPRIFIX(String PRIFIX) {
        ArraySortTest.PRIFIX = PRIFIX;
    }

    public static String getMethodName() {
        return MethodName;
    }

    public static void setMethodName(String methodName) {
        MethodName = methodName;
    }

    public static String getClassName() {
        return ClassName;
    }

    public static void setClassName(String className) {
        ClassName = className;
    }

    public static int getRuntime() {
        return Runtime;
    }

    public static void setRuntime(int runtime) {
        Runtime = runtime;
    }

    public static int getSize() {
        return Size;
    }

    public static void setSize(int size) {
        Size = size;
    }

    public static int getStart() {
        return Start;
    }

    public static void setStart(int start) {
        Start = start;
    }

    public static int getEnd() {
        return End;
    }

    public static void setEnd(int end) {
        End = end;
    }
}
