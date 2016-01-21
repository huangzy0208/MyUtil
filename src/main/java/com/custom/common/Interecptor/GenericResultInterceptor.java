package com.sdeport.common.Interecptor;


import com.sdeport.common.utils.BeanMapUtill;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Properties;

/**
 * 泛型返回类型通用查询拦截器
 *
 * @author zhangmeng
 * @date 2015/12/15,11:25
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class})})
public class GenericResultInterceptor implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;

        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        if (null != parameter && parameter instanceof Map && ((Map) parameter).containsKey("qryObj")) {

            if (mappedStatement.getResultMaps().size() > 0) {
                try {
                    Method sqlMethod = null;
                    String sqlId = mappedStatement.getId();
                    String className = mappedStatement.getResource().replaceAll("\\.java.*", "").replaceAll("/", ".");
                    String methodName = sqlId.substring(className.length() + 1);
                    Class sqlClazz = Class.forName(className);
                    Method[] methods = sqlClazz.getMethods();

                    for (Method method : methods) {
                        if (methodName.equals(method.getName())) {
                            sqlMethod = method;
                        }
                    }
                    if ("T".equals(sqlMethod.getGenericReturnType().toString()) || sqlMethod.getGenericReturnType() instanceof ParameterizedType) {
                        BeanMapUtill.setFieldValue(mappedStatement.getResultMaps().get(0), "type", ((Map) parameter).get("qryObj").getClass());
                    }
                } catch (Exception e) {
                    logger.error("", e);
                }
            }
        }
        Object returnValue = null;
        returnValue = invocation.proceed();
        return returnValue;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
