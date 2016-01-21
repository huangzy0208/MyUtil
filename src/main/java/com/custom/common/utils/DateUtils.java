package com.sdeport.vehicle.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间日期工具类
 */
public final class DateUtils {

    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    //23位时间格式 yyyy-MM-dd HH:mm:ss,SSS
    public static final String DATETIME_23 = "yyyy-MM-dd HH:mm:ss,SSS";

    //19位时间格式 yyyyMMddHHmmss
    public static final String DATETIME_19 = "yyyy-MM-dd HH:mm:ss";

    //获得当前时间的年月日字符串
    public static final String DATE_8 = "yyyyMMdd";

    //14位时间格式 yyyyMMddHHmmss
    public static final String DATETIME_14 = "yyyyMMddHHmmss";

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private DateUtils() {

    }

    /**
     * 转换字符串为Date
     *
     * @param dateStr 要转换的时间字符串
     * @param format  时间格式
     * @return Date 时间类型结果
     */
    public static Date formatStringToDate(String dateStr, String format) {
        if (StringUtils.isEmpty(dateStr)) {
            logger.warn("invalid data,dateStr={}", dateStr);
            return null;
        }

        if (StringUtils.isEmpty(format)) {
            logger.warn("invalid format,format={}", format);
            return null;
        }
        DateFormat df = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = df.parse(dateStr);
            if (!dateStr.equals(df.format(date))) {
                date = null;
            }
        } catch (ParseException e) {
            logger.error("fail to parse date", e);
        }
        return date;
    }

    /**
     * 排除时区偏差
     *
     * @param date 日期
     * @return 格林尼治时间到当前的秒数
     */
    public static long calcExpiredTime(final Date date) {
        Date dateRes = date;
        if (null == date) {
            dateRes = new Date();
        }
        return (dateRes.getTime() + TimeZone.getDefault().getRawOffset()) / 1000;
    }

    /**
     * 将日期转换未指定类型的字符串
     *
     * @param date   要转换的date
     * @param format 格式
     * @return 转换完成的字符串
     */
    public static String formatDateToString(Date date, String format) {
        if (null == date) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 返回当前时间戳
     *
     * @param format 默认为：yyyyMMddHHmmss
     * @return string 时间字符串
     * @author huanghui
     * @see [类、类#方法、类#成员]
     */
    public static String getCurrentTimestamp(final String format) {
        String defaultFormat = format;
        if (StringUtils.isBlank(defaultFormat)) {
            defaultFormat = DATETIME_14;
        }
        return formatDateToString(new Date(), defaultFormat);
    }

    /**
     * 日期字符串装换成秒数
     *
     * @param dateString 日期字符串，要求格式yyyy-MM-dd HH:mm:ss
     * @return 1970-1-1 00:00:00至指定日期的秒数
     */
    public static long dateString2Second(String dateString) {
        long minute = 0L;
        try {
            Date date = new SimpleDateFormat(DATETIME_19).parse(dateString);
            minute = date.getTime() / 1000;
        } catch (ParseException e) {
            logger.error("dateString2Second exception {}", e);
        }
        return minute;
    }

    /**
     * 获得当前时间的年月日字符串
     *
     * @return
     */
    public static String getCurDateForString() {
        return formatDateToString(new Date(), DATE_FORMAT_YYYY_MM_DD);
    }

    /**
     * 获取本月总天数
     *
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取上周一的日期
     *
     * @return
     */
    public static Date getMonDayOfLastWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    /**
     * 获取到今日23:59:59时间
     * 时间格式“YYYY-MM-DD 23:59:59”
     *
     * @see [类、类#方法、类#成员]
     */
    public static String getTodayTimeString() {
        return formatDateToString(new Date(), DATE_FORMAT_YYYY_MM_DD) + " 23:59:59";
    }

    /**
     * 根据指定日期计算指定天数后日期
     */
    public static String getSpecificDateByDay(String startDate, int day) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance();
        try {
            final Date sDate = formatter.parse(startDate);
            calendar.setTime(sDate);
            calendar.add(Calendar.DAY_OF_MONTH, day);
        } catch (ParseException e) {
            logger.error("ParseException", e);
        }
        return formatDateToString(calendar.getTime(), DATE_FORMAT_YYYY_MM_DD);
    }

    /**
     * 根据月份获得本月最后一天的日期
     *
     * @param month
     * @return
     */
    public static String getLastDateByMonth(String month) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance();
        try {
            Date mDate = formatter.parse(month);
            calendar.setTime(mDate);
            calendar.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
            calendar.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        } catch (ParseException e) {
            logger.error("ParseException", e);
        }
        return formatDateToString(calendar.getTime(), DATE_FORMAT_YYYY_MM_DD);
    }

    /**
     * 获得本月第一天
     */
    public static String getStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return formatDateToString(calendar.getTime(), DATE_FORMAT_YYYY_MM_DD);
    }


    /**
     * 获得本月最后一天
     */
    public static String getLastedDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return formatDateToString(calendar.getTime(), DATE_FORMAT_YYYY_MM_DD);
    }

    /**
     * 日期格式校验
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        /**
         * 判断日期格式和范围
         */
        String rexp =
            "^((\\d{2}(([02468][048])|([13579][26]))((((0?[13578])|(1[02]))((0?[1-9])|" +
            "([1-2][0-9])|(3[01])))|(((0?[469])|(11))((0?[1-9])|([1-2][0-9])|(30)))|(0?2(" +
            "(0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|" +
            "(1[02]))((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))((0?[1-9])|" +
            "([1-2][0-9])|(30)))|(0?2((0?[1-9])|(1[0-9])|(2[0-8]))))))";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(date);

        boolean dateType = mat.matches();

        return dateType;
    }

}
