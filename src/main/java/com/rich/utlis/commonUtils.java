package com.rich.utlis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: rich-brain
 * @description: 通用工具类
 * @author: Yafeng.Qin
 * @create: 2020-11-26 13:26
 **/
@Slf4j
public class commonUtils {

    /**
     * Compare the length of two strings to find the intersection and difference
     * 比较两个字符串的长度以找到交集和差集
     * @param longString
     * @param shortString
     */
    public static void compareTwoStrings(String longString,String shortString){
        String[] leftStr = longString.split(",");
        String[] rightStr = shortString.split(",");
        log.info(leftStr.toString());
        log.info(rightStr.toString());
        List leftStrList = new ArrayList(Arrays.asList(leftStr));
        List rightStrList = new ArrayList(Arrays.asList(rightStr));

        List intersection = new ArrayList();//交集
        List short_subtraction = new ArrayList();;//差集
        List long_subtraction = new ArrayList();;//差集
        rightStrList.forEach( str->{
            if (leftStrList.contains(str)){
                intersection.add(str);
            }
        });
        rightStrList.forEach(str ->{
            if (!intersection.contains(str)){
                short_subtraction.add(str);
            }
        });
        leftStrList.forEach(str ->{
            if (!intersection.contains(str)){
                long_subtraction.add(str);
            }
        });
        log.info("交集 intersection 【{}】",intersection.toString());
        log.info("差集 short_subtraction 【{}】",short_subtraction.toString());
        log.info("差集 long_subtraction 【{}】",long_subtraction.toString());
    }

    /**
     * Map集合模糊匹配
     * @param map  map集合
     * @param keyLike  模糊key
     * @return
     */
    public static List<Object> getLikeByMap(Map<String, Object> map, String keyLike){
        List<Object> list=new ArrayList<>();
        for (Map.Entry<String, Object> entity : map.entrySet()) {
            if(entity.getKey().toUpperCase().indexOf(keyLike.toUpperCase())>-1){
                list.add(entity.getValue());
            }
        }
        return list;
    }
}
