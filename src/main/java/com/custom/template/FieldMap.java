package com.custom.template;


import com.custom.common.pojo.FieldMapping;
import com.custom.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板映射bean
 */
public class FieldMap {

    @Autowired
    CommonService commonService;
    private static Map<String, Map<String, String>> fieldMap;

    public void initFieldMap() {
        fieldMap = new HashMap<>();
        List<FieldMapping> listMap = commonService.qryAll("busi_param.field_mapping", new FieldMapping());
        if (!CollectionUtils.isEmpty(listMap)) {
            for (FieldMapping map : listMap) {
                if (!fieldMap.containsKey(map.getMappingType())) {
                    fieldMap.put(map.getMappingType(), new HashMap<String, String>());
                }
                if (!fieldMap.get(map.getMappingType()).containsKey(map.getFieldName())) {
                    fieldMap.get(map.getMappingType()).put(map.getFieldName(), map.getMappingField());
                } else {
                    fieldMap.get(map.getMappingType()).put(map.getFieldName(), fieldMap.get(map.getMappingType()).get(map.getFieldName()) + "#" + map.getMappingField());
                }
            }
        }
    }

    public Map<String, Map<String, String>> getFieldMap() {
        return this.fieldMap;
    }
}
