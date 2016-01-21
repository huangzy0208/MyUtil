package com.custom.common.pojo;

/**
 * field映射关系
 *
 * @author zhangmeng
 * @date 2015/11/14,11:01
 */

public class FieldMapping {

    private String mappingType;// 映射类型
    private String fieldName;// 字段名
    private String mappingField;// 映射字段
    private String remark;// 备注

    public String getMappingType() {
        return this.mappingType;
    }

    public void setMappingType(String mappingType) {
        this.mappingType = mappingType;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMappingField() {
        return this.mappingField;
    }

    public void setMappingField(String mappingField) {
        this.mappingField = mappingField;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FeildMapping{" +
                "mappingType='" + mappingType + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", mappingField='" + mappingField + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
