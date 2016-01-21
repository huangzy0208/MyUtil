package com.custom.template;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单dao
 *
 * @author
 */
@Repository
public interface TemplateMappingDao {

    @Select(
        "SELECT * FROM VEHICLE.TEMPLATE_MAPPING WHERE 1=1")
    List<TemplateMapping> queryAllTemplate();
}
