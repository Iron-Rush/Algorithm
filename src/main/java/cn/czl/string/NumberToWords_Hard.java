package cn.czl.string;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * @author RedRush
 * @date 2020/8/29 22:23
 * @description:
 *      273.整数转换英文表示
 *      将非负整数转换为其对应的英文表示。可以保证给定输入小于 2^31 - 1。//2 147 483 647
 * */
/**
 * 输入: 1 234 567 891
 * 输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 * */
public class NumberToWords_Hard {

    private static int number = 2147483647;
    private static final String[] UNITS = {"Hundred", "Thousand", "Million", "Billion"};
    private static final String[] BASICNUMB = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"};
    private static final String[] DEVELOPNUM = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    @Test
    public void TestSolution(){
        System.out.println(numberToWords(number));;
    }

    public String numberToWords(int num) {
        StringBuilder sb = new StringBuilder();
        StringBuilder numStrSB = new StringBuilder(String.valueOf(num)).reverse();
        String numStr = numStrSB.toString();
        StringBuffer stringBuffer = new StringBuffer();
        String[] numList = numStr.split("");
        int size = numStr.length();
        switch (size){
            case 0:
                return "";
            case 10:
                sb.append(BASICNUMB[Integer.parseInt(numList[9])] + " " + UNITS[3]);
            case 9:
                if (numList[8] != "0"){
                    sb.append(" " + BASICNUMB[Integer.parseInt(numList[8])] + " " + UNITS[0]);
                }
            case 8:
            case 7:
                if (numList[7] != "0"){

                }
            case 6:
            case 5:
            case 4:
            case 3:
            case 2:
            case 1:

        }
        return sb.toString();
    }

//    private String[] getUnit(int pos){
//        String[] strings = new String[];
//        switch (pos){
//
//        }
//    }

}
