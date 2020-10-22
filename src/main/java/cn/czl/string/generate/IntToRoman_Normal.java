package cn.czl.string.generate;

/**
 * @author RedRush
 * @date 2020/8/28 17:01
 */

import org.junit.jupiter.api.Test;

/**
 * I-1  V-5 X-10 L-50 C-100 D-500 M-1000
 * */
public class IntToRoman_Normal {

    private static Integer NUMBER = 58;

    @Test
    public void testSolution(){
        System.out.println(intToRoman2(NUMBER));
    }

    public String intToRoman(int num) {

        StringBuilder sb = new StringBuilder();
        int unit = 1;
        while (unit <= num){
            int temp = (num % (unit*10))/unit;
            System.out.println("temp=" + temp);
            if ( temp > 4 ){
                temp = temp % 5;
                sb.insert(0,getRoman(unit, temp, unit * 5));
            } else{
                sb.insert(0,getRoman(unit, temp, 0));
            }
            unit *= 10;
            num -= temp;
        }
        return sb.toString();
    }

    private void getChar(int numb, StringBuilder sb) {
        switch(numb) {
            case 1:
                sb.append('I');
                return;
            case 5:
                sb.append('V');
                return;
            case 10:
                sb.append('X');
                return;
            case 50:
                sb.append('L');
                return;
            case 100:
                sb.append('C');
                return;
            case 500:
                sb.append('D');
                return;
            case 1000:
                sb.append('M');
                return;
            default:
                return;
        }
    }

    private StringBuilder getRoman(int unit, int temp, int basis){
        StringBuilder ch = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        getChar(unit, ch);
        switch (temp){
            case 0:
                getChar(basis, sb);
                return sb;
            case 1:
            case 2:
            case 3:
                getChar(basis,sb);
                while (temp > 0){
                    sb.append(ch);
                    temp--;
                }
                return sb;
            case 4:
                sb.append(ch);
                if (basis == 0){
                    getChar(unit * 5,sb);
                    return sb;
                }
                getChar(basis * 2,sb);
                return sb;
        }
        return sb;
    }

    private String intToRoman2(int num){
        StringBuilder sb = new StringBuilder();
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        for (int i = 0; i < 13; i++) {
            while (values[i] <= num){
                sb.append(symbols[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }


}
