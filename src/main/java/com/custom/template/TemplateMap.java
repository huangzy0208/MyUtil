package com.sdeport.template;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板映射bean
 */
public class TemplateMap {

    @Autowired
    TemplateMappingDao templateMappingDao;
    private static Map<String, TemplateMapping> templateMap;

    public void initTemplateMap() {
        List<TemplateMapping> listMap = templateMappingDao.queryAllTemplate();
        templateMap = new HashMap<String, TemplateMapping>();
        if (!CollectionUtils.isEmpty(listMap)) {
            for (TemplateMapping map : listMap) {
                templateMap.put(map.getClassName(), map);
            }
        }
    }

    public Map<String, TemplateMapping> getTemplateMap() {
        return this.templateMap;
    }
}
