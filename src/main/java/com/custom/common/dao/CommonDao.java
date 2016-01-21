package com.custom.common.dao;

import com.custom.common.utils.SqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * commondao 提供公共的更新及修改方法以及部分查询方法
 */
@Repository
@Lazy
public interface CommonDao {

    @SelectProvider(type = SqlProvider.class, method = "getMonthSequence")
    @Options(flushCache = true, useCache = false)
    String getMonthSequence(@Param("sequenceName") String sequenceName);

    @SelectProvider(type = SqlProvider.class, method = "selectAll")
    <T> List<T> qryAllCommon(@Param("tableName") final String tableName, @Param("qryObj") final T
            t);

    @SelectProvider(type = SqlProvider.class, method = "selectCount")
    <T> int qryCountCommon(@Param("tableName") final String tableName, @Param("qryObj") final T
            t);

    @UpdateProvider(type = SqlProvider.class, method = "update")
    <T> void updateCommon(@Param("tableName") String tableName, @Param("updateObj") final T t,
                          @Param("updateCondition") final String updateCondition);

    @UpdateProvider(type = SqlProvider.class, method = "update")
    <T> boolean updateCommonB(@Param("tableName") String tableName, @Param("updateObj") final T t, @Param
            ("updateCondition") final String updateCondition);

    @SelectProvider(type = SqlProvider.class, method = "selectOne")
    <T> T qryOneCommon(@Param("tableName") String tableName, @Param("qryObj") final T t,
                       @Param("sortOrder") String sortOrder);

    @InsertProvider(type = SqlProvider.class, method = "insert")
    <T> void insertCommon(@Param("tableName") String tableName, @Param("insertObj") final T t);

    @InsertProvider(type = SqlProvider.class, method = "insert")
    <T> boolean insertCommonB(@Param("tableName") String tableName, @Param("insertObj") final T t);

    @UpdateProvider(type = SqlProvider.class, method = "updateMap")
    <T> void updateCommonMap(@Param("tableName") String tableName, @Param("updateObj") Map<String, Object> objectMap,
                             @Param("updateMap") final Map<String, String> updateMap,
                             @Param("whereMap") final Map<String, String> whereMap);

    @Select("select to_char(busi_param.seq_commonid.nextval,'000') from dual")
    @Options(flushCache = true, useCache = false)
    String getCommonSeqId();

    @SelectProvider(type = SqlProvider.class, method = "getLongSequence")
    @Options(flushCache = true, useCache = false)
    String getLongSequence(@Param("sequenceName") String sequenceName);

    @Select("select entry_port from busi_param.para_port where port_code=#{portCode}")
    String getPortEntry(@Param("portCode") String portCode);

    @Select("select to_char(${sequence}.nextval) from dual")
    @Options(flushCache = true, useCache = false)
    String getSequenceValue(@Param("sequence") String sequence);

    @SelectProvider(type = SqlProvider.class, method = "selectOne")
    <T> T qryOne(@Param("tableName") String tableName, @Param("qryObj") T t, @Param("sortOrder") String sortOrder);

    @Select("select * from ${tableName} where ${whereCondition}")
    List<Map<String, Object>> qryAllByWhere(@Param("tableName") final String tableName, @Param("whereCondition") final String whereCondition);

    @Delete("delete from vehicle.t_generate_record where dec_id=#{decId} and dec_type=#{decType}")
    void deleteGenerateRecord(@Param("decId") String decId, @Param("decType") String decType);
}
