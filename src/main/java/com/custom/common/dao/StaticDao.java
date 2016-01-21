package com.sdeport.common.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * 伪静态dao，提供类似静态dao的调用方式
 */
@Component
@Lazy
public class StaticDao {

    @Autowired
    private CommonDao commonDao;

    static CommonDao dao;

    @PostConstruct
    public void init() {
        dao = commonDao;
    }

    public static String getMonthSequence(String sequenceName) {
        return dao.getMonthSequence(sequenceName);
    }

    public static <T> List<T> qryAllCommon(final String tableName, final T t) {
        return dao.qryAllCommon(tableName, t);
    }

    public static <T> void updateCommon(@Param("tableName") String tableName, @Param("updateObj") final T t,
                                        @Param("updateCondition") final String updateCondition) {
        dao.updateCommon(tableName, t, updateCondition);
    }

    public static <T> boolean updateCommonB(@Param("tableName") String tableName, @Param("updateObj") final T t, @Param
            ("updateCondition") final String updateCondition) {
        return dao.updateCommonB(tableName, t, updateCondition);
    }

    public static <T> T qryOneCommon(@Param("tableName") String tableName, @Param("qryObj") final T t,
                                     @Param("sortOrder") String sortOrder) {
        return dao.qryOneCommon(tableName, t, sortOrder);
    }

    public static <T> void insertCommon(String tableName, final T t) {
        dao.insertCommonB(tableName, t);
    }

    public static <T> boolean insertCommonB(String tableName, final T t) {
        return dao.insertCommonB(tableName, t);
    }

    public static <T> void updateCommonMap(String tableName, Map<String, Object> objectMap,
                                           final Map<String, String> updateMap,
                                           final Map<String, String> whereMap) {

        dao.updateCommonMap(tableName, objectMap, updateMap, whereMap);

    }

    public static String getCommonSeqId() {
        return dao.getCommonSeqId();
    }

    public static String getLongSequence(String sequenceName) {
        return dao.getLongSequence(sequenceName);
    }

    public static String getPortEntry(String portCode) {
        return dao.getPortEntry(portCode);
    }

    public static String getSequenceValue(String sequence) {
        return dao.getSequenceValue(sequence);
    }

    public static <T> int qryCountCommon(final String tableName, final T t) {
        return dao.qryCountCommon(tableName, t);
    }

    public static <T> T qryOne(@Param("tableName") String tableName, @Param("qryObj") final T t,
                               @Param("sortOrder") String sortOrder) {
        return dao.qryOne(tableName, t, sortOrder);
    }

    public static List<Map<String, Object>> qryAll(final String tableName, String whereCondition) {
        return dao.qryAllByWhere(tableName, whereCondition);
    }
}
