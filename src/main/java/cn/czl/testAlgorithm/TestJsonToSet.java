package cn.czl.testAlgorithm;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author RedRush
 * @date 2020/8/31 10:25
 */
public class TestJsonToSet {

//    private static String JSONSTR = "{\"jobName\":\"02\",\"repeatInterval\":3,\"repeatUnit\":\"3\",\"startDate\":\"2020-08-31 00:00:00\",\"endDate\":\"9999-12-31 00:00:00\",\"jobPlanDesc\":null,\"IP\":\"12.3.4.5\",\"paramValue\":{\"IP\":\"12.3.4.5\",\"endDate\":\"9999-12-31 00:00:00\",\"startDate\":\"2020-08-31 00:00:00\"}}";
    private static String JSONSTR = "{\"IP\":\"12.3.4.5\",\"endDate\":\"9999-12-31 00:00:00\",\"startDate\":\"2020-08-31 00:00:00\"}";

    /**
     * JSON格式的字符串，转化为Map
     * */
    public static void main(String[] args) {
        Map<String, Object> paramValues = JSONObject.parseObject(JSONSTR);
        System.out.println("paramValues : " + paramValues);
        /**
         * 获取keySet，通过遍历全部key，进而遍历全部值。
         * */
//        for (String paramKey : paramValues.keySet()){
//            if ("startDate".equals(paramKey)){
//                System.out.println("paramVlue = " + paramValues.get(paramKey));
//            }
//        }
        /**
         * Entry遍历map，效率较高
         */
        for (Map.Entry<String, Object> paramInfo : paramValues.entrySet()){
            if ("startDate" == paramInfo.getKey()){
                System.out.println(paramInfo.getValue());
            }
        }
    }

}
