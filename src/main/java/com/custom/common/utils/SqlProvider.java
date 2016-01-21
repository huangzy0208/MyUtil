package com.sdeport.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * sql Provider
 * by zhangmeng
 */
public class SqlProvider {

    /**
     * date查询数据结构
     */
    static class DateQry {

        String start;
        String end;

        public DateQry() {

        }

        public String getStart() {
            return this.start;
        }

        public void setStart(final String start) {
            this.start = start;
        }

        public String getEnd() {
            return this.end;
        }

        public void setEnd(final String end) {
            this.end = end;
        }

        public DateQry(final String start, final String end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "DateQry{" +
                    "start='" + start + '\'' +
                    ", end='" + end + '\'' +
                    '}';
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(SqlProvider.class);

    /**
     * get sequence
     *
     * @param paramsMap
     * @return sql
     */
    public String getMonthSequence(Map<String, Object> paramsMap) {

        return "SELECT TO_CHAR(SYSDATE,'yyyyMM')||SUBSTR("
                + (String) paramsMap.get("sequenceName")
                + ".NEXTVAL,-4,4) AS SEQNUM FROM DUAL";
    }

    /**
     * get sequence
     *
     * @param paramsMap
     * @return sql
     */
    public String getLongSequence(Map<String, Object> paramsMap) {
        return "SELECT TO_CHAR(SYSDATE,'yyyyMMddhh24miss')||SUBSTR("
                + (String) paramsMap.get("sequenceName")
                + ".NEXTVAL,-4,4) AS SEQNUM FROM DUAL";
    }

    /**
     * delete provider
     *
     * @param mapPara
     * @return
     * @Author steven 删除
     */
    public String delete(Map<String, Object> mapPara) {
        String tableName = (String) mapPara.get("tableName");
        String sql = "DELETE FROM " + tableName;
        String whereCondition = (String) mapPara.get("deleteCondition");
        sql += whereCondition;
        return sql;
    }


    /**
     * insert provider
     *
     * @param mapPara
     * @return
     */
    public String insert(Map<String, Object> mapPara) {
        String tableName = (String) mapPara.get("tableName");
        Object insertObj = mapPara.get("insertObj");
        String sql = "INSERT INTO " + tableName;
        String insertContent = generateInsertCondition(insertObj);
        sql += insertContent;
        return sql;
    }

    /**
     * update provider
     *
     * @param mapPara
     * @return
     */
    public String update(Map<String, Object> mapPara) {
        String tableName = (String) mapPara.get("tableName");
        Object updateObj = mapPara.get("updateObj");
        String sql = "UPDATE " + tableName;
        String updateContent = generateUpdateContent(updateObj);
        sql += updateContent;
        String whereCondition = (String) mapPara.get("updateCondition");
        sql += whereCondition;
        return sql;
    }

    /**
     * 生成更新设置内容
     *
     * @param updateObj
     * @return
     */
    private String generateUpdateContent(final Object updateObj) {
        StringBuffer buffer = new StringBuffer("");
        Field fields[] = updateObj.getClass().getDeclaredFields();
        StringBuffer updateSb = new StringBuffer("");
        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(updateObj);
                if (null == value && (null == field.getAnnotation(SqlType.class) || StringUtils.isEmpty(
                        field.getAnnotation(SqlType.class).defaultValue()))) {
                    continue;
                }
                if (StringUtils.isEmpty(updateSb)) {
                    updateSb.append(" SET ");
                }
                String name = field.getName();
                if ("serialVersionUID".equals(name) || "rowid".equals(name) || (null != field.getAnnotation(
                        SqlType.class) && !field.getAnnotation(SqlType.class).inTable())) {
                    continue;
                }

                updateSb.append(ZzStringUtil.transLateUpper2UnderLine(name) + "=");
                if (null == value && null != field.getAnnotation(SqlType.class) && StringUtils.isNotEmpty(
                        field.getAnnotation(SqlType.class).defaultValue())) {
                    updateSb.append("" + field.getAnnotation(SqlType.class).defaultValue() + ",");

                } else if (null != field.getAnnotation(SqlType.class) && "date".equals(field.getAnnotation(
                        SqlType.class)
                        .type())) {
                    updateSb.append("to_date(#{updateObj." + name + "},'" + field.getAnnotation(SqlType.class)
                            .format() +
                            "'),");
                } else {
                    updateSb.append("#{updateObj." + name + "},");
                }
            } catch (IllegalAccessException e) {
                logger.error("生成sql错误", e);
            }

        }
        if (updateSb.toString().endsWith(",")) {
            buffer.append(updateSb.substring(0, updateSb.length() - 1)).append(" ");
        }
        return buffer.toString();
    }

    /**
     * 生成插入内容
     *
     * @param insertObj
     * @return
     */
    private String generateInsertCondition(final Object insertObj) {
        StringBuffer buffer = new StringBuffer("");
        Field fields[] = insertObj.getClass().getDeclaredFields();
        StringBuffer fieilds = new StringBuffer("(");
        StringBuffer values = new StringBuffer("VALUES(");
        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(insertObj);
                if (null == value && (null == field.getAnnotation(SqlType.class) || StringUtils.isEmpty(
                        field.getAnnotation(SqlType.class).defaultValue()))) {
                    continue;
                }
                String name = field.getName();
                if ("serialVersionUID".equals(name) || "rowid".equals(name) || (null != field.getAnnotation(SqlType
                        .class) &&
                        !field.getAnnotation(
                                SqlType
                                        .class).inTable())) {
                    continue;
                }
                if (null == value && null != field.getAnnotation(SqlType.class) && StringUtils.isNotEmpty(
                        field.getAnnotation(SqlType.class).defaultValue())) {
                    values.append("" + field.getAnnotation(SqlType.class).defaultValue() + ",");

                } else if (null != field.getAnnotation(SqlType.class) && "date".equals(field.getAnnotation(
                        SqlType.class)
                        .type())) {
                    values.append("to_date(#{insertObj." + name + "},'" + field.getAnnotation(SqlType.class).format() +
                            "'),");
                } else {
                    values.append("#{insertObj." + name + "},");
                }
                fieilds.append(ZzStringUtil.transLateUpper2UnderLine(name) + ",");
            } catch (IllegalAccessException e) {
                logger.error("生成sql错误", e);
            }

        }
        buffer.append(fieilds.substring(0, fieilds.length() - 1 > 0 ? fieilds.length() - 1 : 0)).append(")");
        buffer.append(values.substring(0, values.length() - 1 > 0 ? values.length() - 1 : 0)).append(")");
        return buffer.toString();
    }

    /**
     * 查询总数provider
     *
     * @param mapPara
     * @return
     */
    public String selectCount(Map<String, Object> mapPara) {
        String tableName = (String) mapPara.get("tableName");
        Object qryObj = mapPara.get("qryObj");
        String sql = "select count(1) from " + tableName + " where 1=1";
        String whereCondition = generateLikeCondition(qryObj);
        sql += whereCondition;
        return sql;
    }

    /**
     * 分页查询provider，使用like
     *
     * @param mapPara
     * @return
     */
    public String selectByPage(Map<String, Object> mapPara) {
        String tableName = (String) mapPara.get("tableName");
        Object qryObj = mapPara.get("qryObj");
        int start = (Integer) mapPara.get("start");
        int end = (Integer) mapPara.get("end");
        String sql = "SELECT " + generateSelectLike(qryObj)
                + " FROM " + tableName + " where rowid in (SELECT RR FROM ( SELECT A.RR, ROWNUM RN " +
                "FROM (SELECT rowid RR FROM " + tableName + " where 1=1 ";

        String whereCondition = generateLikeCondition(qryObj);
        sql += whereCondition;

        if (mapPara.containsKey("appendWhere") && StringUtils.isNotEmpty((String) mapPara.get("appendWhere"))) {
            sql += mapPara.get("appendWhere");
        }
        String orederSql = "";
        if (mapPara.containsKey("sortOrder")) {
            Object sortOrder = mapPara.get("sortOrder");
            if (null != sortOrder && sortOrder instanceof String && !"".equals(String.valueOf(sortOrder))) {
                orederSql = " order by " + sortOrder;

            }
        }
        sql += orederSql;
        String sqlSuffix = ") A WHERE ROWNUM <= " + end + " ) WHERE RN >= " + start + ") " + orederSql;
        sql += sqlSuffix;
        return sql;
    }

    /**
     * 查询一条记录，精准匹配
     *
     * @param mapPara
     * @return
     */
    public String selectOne(Map<String, Object> mapPara) {
        mapPara.put("selectNum", 1);
        return this.selectNum(mapPara);
    }

    /**
     * 查询所有记录，精准匹配
     *
     * @param mapPara
     * @return
     */
    public String selectAll(Map<String, Object> mapPara) {
        mapPara.put("selectNum", 0);
        mapPara.put("sortOrder", null);
        return this.selectNum(mapPara);
    }

    /**
     * 查询指定条数，精准匹配
     *
     * @param mapPara
     * @return
     */
    public String selectNum(Map<String, Object> mapPara) {
        String tableName = (String) mapPara.get("tableName");
        Object qryObj = mapPara.get("qryObj");
        int selectNum = (Integer) mapPara.get("selectNum");
        String sql = "SELECT " + generateSelectLike(qryObj)
                + " FROM " + tableName + " where 1=1 ";
        String sqlSuffix = "AND ROWNUM <= " + selectNum;
        String whereCondition = generateCondition(qryObj);
        sql += whereCondition;

        if (selectNum > 0) {
            sql += sqlSuffix;
        }

        Object sortOrder =  mapPara.containsKey("sortOrder") ? mapPara.get("sortOrder") : null;;

        if (null != sortOrder && sortOrder instanceof String && !"".equals(String.valueOf(sortOrder))) {
            sql += (" order by " + sortOrder);
        }

        return sql;
    }

    /**
     * 查询指定条数，精准匹配
     *
     * @param mapPara
     * @return
     */
    public String updateMap(Map<String, Object> mapPara) {
        String tableName = (String) mapPara.get("tableName");
        Map<String, String> updateMap = (Map<String, String>) mapPara.get("updateMap");
        Map<String, String> whereMap = (Map<String, String>) mapPara.get("whereMap");
        String sql = "UPDATE " + tableName;
        if (!CollectionUtils.isEmpty(updateMap) && !CollectionUtils.isEmpty(whereMap)) {
            String updateContent = generateMapUpdateContent(updateMap);
            sql += updateContent;
            String whereCondition = generateMapWhereContent(whereMap);
            sql += whereCondition;
        }
        return sql;
    }

    /**
     * 根据map生成查询条件
     *
     * @param whereMap
     * @return
     */
    private String generateMapWhereContent(Map<String, String> whereMap) {
        StringBuffer buffer = new StringBuffer("");
        if (!CollectionUtils.isEmpty(whereMap)) {
            buffer.append(" where 1=1 ");
            for (Entry<String, String> entry : whereMap.entrySet()) {
                buffer.append(" and " + entry.getKey() + "=#{updateObj." + entry.getValue() + "}");
            }
        }
        return buffer.toString();
    }

    /**
     * 根据map生成更新内容
     *
     * @param updateMap
     * @return
     */
    private String generateMapUpdateContent(Map<String, String> updateMap) {
        StringBuffer buffer = new StringBuffer("");
        if (!CollectionUtils.isEmpty(updateMap)) {
            buffer.append(" set ");
            for (Entry<String, String> entry : updateMap.entrySet()) {
                buffer.append(entry.getKey() + "=#{updateObj." + entry.getValue() + "},");
            }
        }
        return buffer.toString().replaceAll(",$", "");
    }

    /**
     * 生成返回条件
     *
     * @param qryObj
     * @return
     */
    public static String generateSelectLike(final Object qryObj) {
        StringBuffer buffer = new StringBuffer("");
        Field fields[] = qryObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            String name = field.getName();
            if ("serialVersionUID".equals(name) || (null != field.getAnnotation(SqlType.class) && !field.getAnnotation(
                    SqlType.class).inTable())) {
                continue;
            }
            if (null != field.getAnnotation(SqlType.class) && "date".equals(field.getAnnotation(SqlType.class)
                    .type())) {
                buffer.append("to_char(" + ZzStringUtil
                        .transLateUpper2UnderLine(field.getName()) + ",'" + field.getAnnotation(SqlType.class).format()
                        + "') " + field.getName() + ",");
            } else {
                buffer.append(ZzStringUtil.transLateUpper2UnderLine(field.getName()) + ",");
            }
        }
        return buffer.substring(0, buffer.length() - 1);
    }

    /**
     * 生成查询条件like匹配
     *
     * @param qryObj
     * @return
     */
    public static String generateLikeCondition(final Object qryObj) {
        StringBuffer buffer = new StringBuffer("");
        Map<String, DateQry> mapQry = new HashMap<String, DateQry>();
        Map<String, String> mapField = new HashMap<String, String>();
        String dateCondition = "";
        Field fields[] = qryObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(qryObj);
                if (null != value) {
                    if (null != field.getAnnotation(SqlType.class) && !field.getAnnotation(SqlType.class).inTable()) {
                        if (StringUtils.isNotEmpty(String.valueOf(value))) {
                            mapField.put(field.getName(), String.valueOf(value));
                        }

                    } else {
                        if (value instanceof String && StringUtils.isNotEmpty(String.valueOf(value))) {
                            buffer.append(generate1LikeCondition(field, qryObj));
                        }
                    }
                }
                if (field.getType() == java.sql.Date.class || field.getType() == java.util.Date.class || (null != field
                        .getAnnotation(SqlType.class) && "date".equals(field
                        .getAnnotation(
                                SqlType.class)
                        .type()))) {
                    mapQry.put(field.getName(), getDateQry(field));
                }

            } catch (IllegalAccessException e) {
                logger.error("生成sql错误", e);
            }
        }

        if (!mapQry.isEmpty()) {

            for (Entry<String, DateQry> qry : mapQry.entrySet()) {
                /**
                 * 支持如果只有一个date字段，则可以不设置qryStart与qryEnd
                 */
                if (StringUtils.isEmpty(qry.getValue().getStart()) && mapQry.size() == 1) {
                    qry.getValue().setStart("startTime");
                }
                if (StringUtils.isEmpty(qry.getValue().getEnd()) && mapQry.size() == 1) {
                    qry.getValue().setEnd("endTime");
                }
                if (mapField.containsKey(qry.getValue().getStart())) {
                    dateCondition +=
                            (" and " + ZzStringUtil.transLateUpper2UnderLine(qry.getKey()) + ">=to_date('" +
                                    ZzStringUtil.translateStartTime(mapField.get(qry.getValue().getStart())) +
                                    "','yyyy-MM-dd hh24:mi:ss')");
                }

                if (mapField.containsKey(qry.getValue().getEnd())) {
                    dateCondition += (" and " + ZzStringUtil.transLateUpper2UnderLine(qry.getKey()) +
                            "<=to_date('" + ZzStringUtil.translateEndTime(mapField.get(qry.getValue()
                            .getEnd())) +
                            "','yyyy-MM-dd hh24:mi:ss')");
                }
            }
        }

        buffer.append(dateCondition);
        return buffer.toString();
    }

    /**
     * 生成一条like条件
     *
     * @param field
     * @return
     */
    private static String generate1LikeCondition(final Field field, final Object qryObj) throws IllegalAccessException {
        String condition = "";
        if (null != field.getAnnotation(SqlType.class)) {
            String suffix = field.getAnnotation(SqlType.class).inSuffix();
            if (StringUtils.isNotEmpty(suffix) && String.valueOf(field.get(qryObj)).startsWith(suffix)) {
                condition = " and " + ZzStringUtil.transLateUpper2UnderLine(
                        field.getName()) + " in(" + String.valueOf(field.get(qryObj)).substring(suffix.length()) + ")";
            }
        }
        if (StringUtils.isEmpty(condition)) {
            condition = " and " + ZzStringUtil
                    .transLateUpper2UnderLine(field.getName()
                    ) + " like " +
                    "'%'||#{qryObj." + field.getName
                    () +
                    "}||'%'";
        }
        return condition;
    }

    /**
     * 获取字段的的date查询
     *
     * @param field
     * @return
     */
    private static DateQry getDateQry(final Field field) {
        String start = "";
        String end = "";
        if (null != field.getAnnotation(SqlType.class)) {
            start = StringUtils.isNotEmpty(field.getAnnotation(SqlType.class).qryStart()) ? field.getAnnotation
                    (SqlType.class).qryStart() : "";
            end = StringUtils.isNotEmpty(field.getAnnotation(SqlType.class).qryEnd()) ? field.getAnnotation
                    (SqlType.class).qryEnd() : "";
        }
        return new DateQry(start, end);
    }

    /**
     * 生成查询条件精准匹配
     *
     * @param qryObj
     * @return
     */
    public String generateCondition(final Object qryObj) {
        StringBuffer buffer = new StringBuffer("");


        Field fields[] = qryObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(qryObj);
                String name = field.getName();
                if ("serialVersionUID".equals(name) || (null != field.getAnnotation(SqlType.class) &&
                        !field.getAnnotation(
                                SqlType
                                        .class).inTable())) {
                    continue;
                }
                if (null != value) {
                    buffer.append(generate1Condition(field, qryObj));
                }
            } catch (IllegalAccessException e) {
                logger.error("生成sql错误", e);
            }
        }
        return buffer.toString();
    }

    /**
     * 生成一条查询条件
     *
     * @return
     */
    private String generate1Condition(Field field, final Object qryObj) throws IllegalAccessException {
        String condition = "";
        if (null != field.getAnnotation(SqlType.class)) {
            String suffix = field.getAnnotation(SqlType.class).inSuffix();
            if (StringUtils.isNotEmpty(suffix) && String.valueOf(field.get(qryObj)).startsWith(suffix)) {
                condition = " and " + ZzStringUtil.transLateUpper2UnderLine(
                        field.getName()) + " in(" + String.valueOf(field.get(qryObj)).substring(suffix.length()) + ")";
            }
        }
        if (StringUtils.isEmpty(condition)) {
            condition = " and " + ZzStringUtil.transLateUpper2UnderLine(
                    field.getName()) + " = #{qryObj." + field.getName() + "}";
        }
        return condition;
    }
}
