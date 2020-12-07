package cn.czl.list.generate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author RedRush
 * @date 2020/12/7 17:02
 * @description:
 *      412. Fizz Buzz
 *      - 写一个程序，输出从 1 到 n 数字的字符串表示。
 *       1. 如果 n 是3的倍数，输出“Fizz”；
 *       2. 如果 n 是5的倍数，输出“Buzz”；
 *       3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 *      示例：
 *          n = 15,
 *      返回:
 *      [ "1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz",
 *     "11","Fizz","13","14","FizzBuzz" ]
 */
public class FizzBuzz_Easy {

    @Test
    public void TestSolution(){
        System.out.println(fizzBuzz2(15));
    }

    /**
     * 根据余数，生成字符串数组
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.99%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了95.49%的用户
     * */
    public List<String> fizzBuzz(int n) {
        String fizz = "Fizz", buzz = "Buzz", fb = "FizzBuzz";
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int a = i % 3, b = i % 5;
            if (a == 0 || b == 0){
                list.add(a == 0 ? (b == 0 ? fb : fizz) : buzz);
            }else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }

    /**
     * 散列表【扩展、维护性强】，仅需维护map中的数据
     * 执行用时：7 ms, 在所有 Java 提交中击败了33.95%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了47.30%的用户
     * */
    public List<String> fizzBuzz2(int n){
        List<String> list = new ArrayList<>();
        HashMap<Integer, String> dict = new HashMap<Integer, String>(){
            {
                put(3, "Fizz");
                put(5, "Buzz");
            }
        };
        for (int num = 1; num <= n; num++) {
            String str = "";
            for(Integer key : dict.keySet()){
                if (num % key == 0){
                    str += dict.get(key);
                }
            }
            if (str.equals("")){
                str += Integer.toString(num);
            }
            list.add(str);
        }
        return list;
    }

}
