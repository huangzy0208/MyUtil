package com.sdeport.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期处理工具类
 *
 * @author zhangguowei
 * @date 2015-10-08
 */
public class DateUtil {

    /**
     * 海关报文日期格式特殊处理
     *
     * @param date
     * @return
     */
    public static String dealDateString(String date) {
        if (StringUtils.isNotEmpty(date) && date.length() >= 19) {
            return date.substring(0, 19).replace("T", " ");
        }
        return date;
    }

    /**
     * 年月日 yyyy-MM-dd hh:mm:ss
     *
     * @param date
     * @return
     */
    public static String nowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date());
    }
}
