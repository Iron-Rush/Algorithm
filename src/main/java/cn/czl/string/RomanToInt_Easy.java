package cn.czl.string;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

/**
 * @author RedRush
 * @date 2020/8/28 14:37
 */
/**
 * I-1  V-5 X-10 L-50 C-100 D-500 M-1000
 * */
public class RomanToInt_Easy {
    private static String ROMANSTR = "MCMXCIV";

    @Test
    public void testSolution(){
        System.out.println("Trans result:" + romanToInt3(ROMANSTR));
    }
    /**
     * 方法过慢，疑似spilt函数导致
     * */
    public int romanToInt(String s) {
        int result = 0;
        HashMap<String, Integer> romanMap = new HashMap();
        romanMap.put("I", 1);
        romanMap.put("V", 5);
        romanMap.put("X", 10);
        romanMap.put("C", 100);
        romanMap.put("D", 500);
        romanMap.put("M", 1000);
        String[] strs = s.split("");
        int temp = 0;
        for (int i = strs.length-1; i >= 0; i--) {
            int target = romanMap.get(strs[i]);
            if (target < temp){
                result -= target;
            }else {
                result += target;
            }
            System.out.println("Temp=" + temp + "，Target=" + target);
            temp = target;
        }
        return result;
    }
    /**
     * 较快，map取数太慢
     * */
    public int romanToInt2(String s) {
        int result = 0;
        HashMap<Character, Integer> romanMap = new HashMap();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        int temp = 0;
        int target = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            target = romanMap.get(s.charAt(i));
            if (target < temp){
                result -= target;
            }else {
                result += target;
            }
            System.out.println("Temp=" + temp + "，Target=" + target);
            temp = target;
        }
        return result;
    }
    /**
     * 提取出为switch-case取数方法，效率大幅提升。
     * */
    public int romanToInt3(String s) {
        int result = 0;
        int temp = 0;
        int target = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            target = getValue(s.charAt(i));
            if (target < temp){
                result -= target;
            }else {
                result += target;
            }
            System.out.println("Temp=" + temp + "，Target=" + target);
            temp = target;
        }
        return result;
    }
    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
