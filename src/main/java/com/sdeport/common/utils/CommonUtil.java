package com.sdeport.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangguowei on 2015/8/22.
 */
public class CommonUtil {

    /**
     * 多行删除字符串校验
     *
     * @param idStr
     * @return
     */
    public static boolean checkDel(String idStr) {
        return match("^[a-zA-Z,_0-9]+$", idStr);
    }

    /**
     * 多行删除字符串转换
     *
     * @param inStr
     * @return
     */
    public static String changeDelIn(String inStr) {
        String[] in = inStr.split(",");
        String re = "";
        for (int i = 0; i < in.length; i++) {
            re += "'" + in[i] + "'" + ",";
        }
        return re.substring(0, re.length() - 1);
    }

    /**
     * 将Class成员变量转换成字符串数组
     *
     * @param obj
     * @return
     */
    public static String[] getProperties(Class obj) {
        Field[] fields = obj.getDeclaredFields();
        List<String> list = new ArrayList();
        for (Field field : fields) {
            //在java的反射使用中,如果字段是私有的,那么必须要对这个字段设置
            field.setAccessible(true);
            if (null != field.getAnnotation(SqlType.class) && field.getAnnotation(SqlType.class).inTemplate()) {
                list.add(field.getName());
            }
        }
        final int size = list.size();
        return list.toArray(new String[size]);
    }

    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断传入类型是否是基本类型以及包装类
     *
     * @param clz
     * @return
     */
    public static boolean isWrapClass(Class clz) {
        try {
            return clz.isPrimitive() || ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(checkDel("122,s,s,s,s,',12,"));
    }

}
