package com.sdeport.common.utils;

import java.lang.annotation.*;

/**
 * 自定义sql注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface SqlType {

    /*
    字段类型
     */
    String type() default "";

    //字段格式
    String format() default "";

    //起始查询
    String qryStart() default "";

    //结束查询
    String qryEnd() default "";

    //是否存在于实体表中
    boolean inTable() default true;

    //默认值
    String defaultValue() default "";

    //是否存在于导入模板中
    boolean inTemplate() default false;

    //使用in查询的前缀
    String inSuffix() default "";

    String dictGroupId() default "";
}
