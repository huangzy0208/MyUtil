package com.sdeport.common.utils;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Map与Json转换工具类
 * by zhangmeng
 */
public class JSonMapUtil {

    private final static Logger logger = LoggerFactory.getLogger(JSonMapUtil.class);

    /**
     * 字符串转json
     *
     * @param jsonStr
     * @return
     */
    public static JSONObject parseJson(String jsonStr) {
        return new JSONObject(jsonStr);
    }

    public static void main(String args[]){
        System.out.println(parseJson("{a:[{d:1},{f:[3,4]}],b:2,C:3}"));
    }

}
