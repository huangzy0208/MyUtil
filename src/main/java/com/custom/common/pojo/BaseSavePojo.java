package com.sdeport.common.pojo;


import com.sdeport.common.utils.BeanMapUtill;
import com.sdeport.common.utils.SqlType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础保存Pojo
 */
public abstract class BaseSavePojo {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @SqlType(inTable = false)
    private String saveTableName;
    @SqlType(inTable = false)
    private Map<String, String> updateCondition;
    @SqlType(inTable = false)
    protected Map<String, String> updateMap = new HashMap<String, String>();

    public boolean checkPojo() {
        return true;
    }

    protected abstract void setUpdateMap();

    public String getSaveTableName() {
        return this.saveTableName;
    }

    public void setSaveTableName(String saveTableName) {
        this.saveTableName = saveTableName;
    }

    public Map<String, String> getUpdateCondition() {
        return this.updateCondition;
    }

    public void setUpdateCondition(Map<String, String> updateCondition) {
        this.updateCondition = updateCondition;
    }

    /**
     * 生成查询map
     *
     * @param pojo
     * @param <T>
     * @return
     */
    public <T extends BaseSavePojo> T generateQryMap(T pojo) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Map<String, Object> qryMap = new HashMap<String, Object>();
        Field fields[] = pojo.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(pojo);
                if (null == value && (null == field.getAnnotation(SqlType.class) || StringUtils.isEmpty(
                        field.getAnnotation(SqlType.class).defaultValue()))) {
                    continue;
                }
                if (this.updateCondition.containsKey(field.getName())) {
                    qryMap.put(field.getName(), value);
                }
            } catch (IllegalAccessException e) {
                log.error("生成查询map错误,pojo:{}", pojo, e);
            }
        }
        return (T) BeanMapUtill.map2Bean(qryMap, new HashMap<String, String>(), pojo.getClass(), true);
    }

    /**
     * 生成更新条件
     *
     * @return
     */
    public <T extends BaseSavePojo> String generateUpdateCondition(T pojo) {
        StringBuffer whereBuffer = new StringBuffer("where 1=1 ");
        Field fields[] = pojo.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(pojo);
                if (null == value) {
                    continue;
                }
                if (this.updateCondition.containsKey(field.getName())) {
                    whereBuffer.append(" and " + this.updateCondition.get(field.getName()) + "=#{updateObj." + field.getName() + "}");
                }
            } catch (IllegalAccessException e) {
                log.error("生成查询map错误,pojo:{}", pojo, e);
            }
        }
        return whereBuffer.toString();
    }

    @Override
    public String toString() {
        return "BaseSavePojo{" +
                "saveTableName='" + saveTableName + '\'' +
                ", updateCondition=" + updateCondition +
                '}';
    }
}
