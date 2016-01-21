package com.custom.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 智卓字符串工具类
 */
public class ZzStringUtil {

    public final static String TYPE_MARI = "Mari";//海事
    public final static String TYPE_PORT = "Port";//港口
    public final static String TYPE_CUST = "";//海关
    public final static String TYPE_CIQ = "Ciq";//检验检疫
    public final static String RECV_MSA = "C370000001";
    public final static String RECV_CIQ = "B370000001";
    public final static String SQL_PART_TYPE = "type";
    public final static String SQL_PART_TABLE = "table";
    public final static String SQL_PART_VALUE = "value";
    public final static String SQL_PART_WHERE = "where";

    /**
     * 获得一个文件的后缀名
     *
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {
        String suffix = "";
        if (StringUtils.isNotEmpty(fileName) && fileName.contains(".")) {
            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return suffix;
    }

    /**
     * 获得一个文件的前缀
     *
     * @param fileName
     * @return
     */
    public static String getFilePrefix(String fileName) {
        String suffix = fileName;
        if (StringUtils.isNotEmpty(fileName) && fileName.contains(".")) {
            suffix = fileName.substring(0, fileName.lastIndexOf("."));
        }
        return suffix;
    }

    /**
     * 转换结束时间
     *
     * @param endTime
     * @return
     */
    public static String translateEndTime(String endTime) {
        if (StringUtils.isNotEmpty(endTime) && !endTime.contains(" ")) {
            endTime += " 23:59:59";
        }
        return endTime;
    }

    /**
     * 转换开始时间
     *
     * @param startTime
     * @return
     */
    public static String translateStartTime(String startTime) {
        if (StringUtils.isNotEmpty(startTime) && !startTime.contains(" ")) {
            startTime += " 00:00:00";
        }
        return startTime;
    }

    /**
     * 驼峰命名改为下划线命名
     *
     * @param name
     * @return
     */
    public static String transLateUpper2UnderLine(final String name) {
        String rtn = name;
        if (StringUtils.isNotEmpty(name)) {
            rtn = name.replaceAll("([A-Z])", "_$1").toUpperCase();
        }
        return rtn;
    }

    /**
     * 下划线转驼峰
     *
     * @param name
     * @return
     */
    public static String transLateUnderLine2Upper(final String name) {
        String rtn = "";
        if (StringUtils.isNotEmpty(name)) {
            String[] splits = name.toLowerCase().split("_");
            for (String str : splits) {
                rtn += (str.substring(0, 1).toUpperCase() + str.substring(1));
            }
            rtn = rtn.substring(0, 1).toLowerCase() + rtn.substring(1);
        }
        return rtn;
    }

    /**
     * 返回指定格式的时间
     *
     * @param format
     * @return
     */
    public static String getNowTime(String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.CHINA);
        return dateFormat.format(cal.getTime());
    }

    /**
     * 首字符大写
     *
     * @param str
     * @return
     */
    public static String capFirst(final String str) {
        String rtnStr = str;
        if (StringUtils.isNotEmpty(str)) {
            rtnStr = str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return rtnStr;
    }

    /**
     * 生成随机字符串包括字母数字下划线
     *
     * @param minLen
     * @param maxLen
     * @return
     */
    public static String generateRandomStr(int minLen, int maxLen) {
        int len = minLen + (int) Math.round(Math.random() * (maxLen - minLen));
        String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz_";
        String rtnStr = "";
        for (int i = 0; i < len; i++) {
            rtnStr += generateSource.charAt((int) Math.floor(Math.random() * generateSource.length()));
        }
        return rtnStr;
    }

    /**
     * 给一个对象随机赋值
     *
     * @param o
     */
    public static void autoSetRandom(Object o) throws IllegalAccessException {
        Field fields[] = o.getClass().getDeclaredFields();
        StringBuffer fieilds = new StringBuffer("(");
        StringBuffer values = new StringBuffer("VALUES(");
        for (Field field : fields) {
            field.setAccessible(true);
            if (String.class == field.getType()) {
                field.set(o, generateRandomStr(3, 5));
            }
        }
    }

    /**
     * 生成报文的sendTime
     *
     * @return
     */
    public static String getSendTime() {
        return getNowTime("yyyyMMddHHmm+08");
    }

    /**
     * 获得毫秒时间
     *
     * @return
     */
    public static String getMsSendTime() {
        return getNowTime("yyyyMMddHHmmssSSS");
    }

    /**
     * 获取默认时间格式
     *
     * @return
     */
    public static String getDefaultDate() {
        return getNowTime("yyyyMMdd");
    }

    /**
     * 获取默认时间格式
     *
     * @return
     */
    public static String getDefaultDateTime() {
        return getNowTime("yyyyMMddHHmmss");
    }


    /*
        '1': "未申报", '2': "已申报", '3': "退回", '4': "已受理", '5': "已通过", 6: "未受理", 7: "已办理"
        */
    public static Map<String, Boolean> mapEditAble = new HashMap<String, Boolean>() {
        {
            put("1", true);
            put("2", false);
            put("3", true);
            put("4", false);
            put("5", false);
            put("6", false);
            put("7", false);
            put("", false);
        }
    };

    /**
     * 获取类的模板class名称
     *
     * @param clazz
     * @param type
     * @return
     */
    public static String getTemplateClassName(Class clazz, String type) {
        String className = clazz.getSimpleName();
        if (StringUtils.isNotEmpty(type)) {
            className += ("#" + type);
        }
        return className;
    }

    /**
     * 生成指定大小的文件
     *
     * @param size
     * @param fileName
     */
    public static void generateSizeFile(int size, String fileName) {
        byte[] bytes = new byte[size];
        File file = new File(fileName);
        BufferedOutputStream out = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new BufferedOutputStream(new FileOutputStream(file));
            out.write(bytes);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 生成不重复随机字符串包括字母数字
     *
     * @param len
     * @return
     */
    public static String generateRandomStr(int len) {
        //字符源，可以根据需要删减
        String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz";
        String rtnStr = "";
        for (int i = 0; i < len; i++) {
            //循环随机获得当次字符，并移走选出的字符
            String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
            rtnStr += nowStr;
            generateSource = generateSource.replaceAll(nowStr, "");
        }
        return rtnStr;
    }

    /**
     * 校验是否是有效的imo
     *
     * @param imo
     * @return
     */
    public static boolean validImo(String imo) {
        return StringUtils.isNotEmpty(imo) && (StringUtils.isAlphanumeric(imo));
    }

    /**
     * 获得一个源码文件的属性及其注释
     * 目前只获取位于代码上方以及右方的注释以及private 的属性
     *
     * @param sourceFile 源码文件路径
     * @return
     */
    public static Map<String, String> getFieldsAnnotation(String sourceFile, String code) throws IOException {
        Map<String, String> mapFieldsAnnotation = new HashMap<>();
        if (StringUtils.isNotEmpty(sourceFile)) {
            File srcFile = new File(sourceFile);
            if (srcFile.exists() && !srcFile.isDirectory()) {
                String source = XmlMapUtil.inputStream2SrcString(new FileInputStream(srcFile), code);
                if (StringUtils.isNotEmpty(source)) {
                    //匹配/**/类型注释
                    String regAnnotationIxI = "/\\*([\\w|\\W]+?)\\*/[\\w|\\W]+?private\\W+\\w+\\W+(\\w+)[^;]*;.*$";
                    //匹配右侧 // 类型注释
                    String regAnnotationII = ".*private\\W+\\w+\\W+(\\w+)[^;]*;[^/]*//(.*)";
                    int[] regIxI = {2, 1};
                    int[] regII = {1, 2};
                    mapFieldsAnnotation.putAll(getRegexMap(source, regAnnotationIxI, regIxI));
                    mapFieldsAnnotation.putAll(getRegexMap(source, regAnnotationII, regII));
                }
            }
        }
        return mapFieldsAnnotation;
    }

    /**
     * @param sourceFile
     * @param regAnnotation
     * @param ths           取值顺序
     * @return
     */
    public static Map<String, String> getRegexMap(String sourceFile, String regAnnotation, int[] ths) {
        Map<String, String> mapRtn = new HashMap<>();
        Pattern pattern;
        pattern = Pattern.compile(regAnnotation, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(sourceFile);
        while (matcher.find()) {
            mapRtn.put(matcher.group(1).replaceAll("//", "").replaceAll(" +", "").replaceAll("\r\n", "").replaceAll("^\\*+", ""), matcher.group(2).replaceAll("//", "").replaceAll(" +", "").replaceAll("\r\n", "").replaceAll("^\\*\\*", ""));
        }
        return mapRtn;
    }

    /**
     * 在指定字符串中查找正则表达式中的group
     *
     * @param ciqMsg
     * @param regFind
     * @return
     */
    public static String getFindMsg(String ciqMsg, String regFind) {
        String findMsg = "";
        Pattern pattern;
        pattern = Pattern.compile(regFind,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ciqMsg);
        if (matcher.find() && matcher.groupCount() > 0) {
            findMsg = matcher.group(1);
        }
        return findMsg.trim();
    }

    /**
     * 解析修改内容的sql（insert,delete,update)
     *
     * @param sql
     * @return
     */
    public static Map<String, String> parseUpdateSql(String sql) {
        String[] sqlRegexes = {
                "\\W*(delete)\\W+from\\W+([\\w|\\.]+)\\W+where\\W(.+)",
                "\\W*(insert)\\W+into\\W+([\\w|\\.]+)(.+)",
                "\\W*(update)\\W+([\\w|\\.]+)\\W+set\\W+(.+)\\Wwhere\\W+(.+)"
        };
        Map<String, String> mapTypeParam = new HashMap() {{
            put("insert1", SQL_PART_TYPE);
            put("insert2", SQL_PART_TABLE);
            put("insert3", SQL_PART_VALUE);
            put("update1", SQL_PART_TYPE);
            put("update2", SQL_PART_TABLE);
            put("update3", SQL_PART_VALUE);
            put("update4", SQL_PART_WHERE);
            put("delete1", SQL_PART_TYPE);
            put("delete2", SQL_PART_TABLE);
            put("delete3", SQL_PART_WHERE);
        }};
        Map<String, String> tmpParseMap = new HashMap<>();
        Map<String, String> parseMap = new HashMap<>();
        for (String regex : sqlRegexes) {
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(sql);
            if (matcher.find() && matcher.groupCount() > 0) {
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    tmpParseMap.put("" + i, matcher.group(i).trim());
                }
                break;
            }
        }
        if (!tmpParseMap.isEmpty()) {
            Iterator<Map.Entry<String, String>> iterator = tmpParseMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (mapTypeParam.containsKey(tmpParseMap.get("1").toLowerCase() + entry.getKey())) {
                    parseMap.put(mapTypeParam.get(tmpParseMap.get("1").toLowerCase() + entry.getKey()), entry.getValue());
                }
            }
        }
        return parseMap;
    }

    /**
     * map2String
     *
     * @param map
     * @return
     */
    public static <K, V> String map2String(Map<K, V> map) {
        if (null == map) {
            return "null";
        }
        Iterator<Map.Entry<K, V>> i = map.entrySet().iterator();
        if (!i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (; ; ) {
            Map.Entry<K, V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append(key);
            sb.append('=');
            sb.append(value.getClass().isArray() ? Arrays.toString((Object[]) value) : value);
            if (!i.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }

    /**
     * 解析update的set语句
     *
     * @param s
     * @param isCalm 是否是驼峰命名key
     * @return
     */
    public static Map<String, Object> parseUpdateSet(String s, boolean isCalm) {
        Map<String, Object> mapSet = new HashMap<>();
        boolean notInQuot = true;
        int inBracket = 0;
        List<String> listSets = new ArrayList<>();
        List<Integer> listPos = new ArrayList<>();
        listPos.add(-1);
        for (int i = 0; i < s.length(); i++) {
            char nowChar = s.charAt(i);
            if ('(' == nowChar) {
                inBracket++;
            }
            if (')' == nowChar) {
                inBracket--;
            }
            if ('\'' == nowChar) {
                notInQuot = !notInQuot;
            }
            if (',' == nowChar && notInQuot && 0 == inBracket) {
                listPos.add(i);
            }
        }
        listPos.add(s.length());
        for (int i = 1; i < listPos.size(); i++) {
            listSets.add(s.substring(listPos.get(i - 1) + 1, listPos.get(i)));
        }
        if (!org.springframework.util.CollectionUtils.isEmpty(listSets)) {
            for (String set : listSets) {
                mapSet.put(isCalm ? ZzStringUtil.transLateUnderLine2Upper(set.substring(0, set.indexOf("="))) : set.substring(0, set.indexOf("=")),
                        set.substring(set.indexOf("=") + 1).replaceAll("^'", "").replaceAll("'$", "").replaceAll("''", "'"));

            }
        }
        return mapSet;
    }

    /**
     * sql日期格式转化为java日期格式
     *
     * @param sqlDateFormat
     * @return
     */
    public static String dateSqlFormat2javaFormat(String sqlDateFormat) {
        String javaDateFormat = sqlDateFormat.toUpperCase();
        javaDateFormat = javaDateFormat.replaceAll("Y", "y").replaceAll("H", "h").replaceAll("hh24", "HH").replaceAll("MI", "mm").replaceAll("SS", "ss").replaceAll("DD", "dd");

        return javaDateFormat;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(dateSqlFormat2javaFormat("yyyy-mm-dd hh24:mi:ss"));
    }


}
