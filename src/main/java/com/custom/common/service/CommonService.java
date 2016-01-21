package com.sdeport.common.service;

import com.sdeport.common.dao.CommonDao;
import com.sdeport.common.pojo.BaseSavePojo;
import com.sdeport.common.utils.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 公共入库service
 *
 * @author zhangmeng
 * @date 2015/10/21,15:39
 */
@Service
@Lazy
public class CommonService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommonDao commonDao;

    /**
     * 批量入库
     *
     * @param listProcessPojo
     * @return
     */
    public <T extends BaseSavePojo> boolean savePojo(List<T> listProcessPojo) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if (CollectionUtils.isNotEmpty(listProcessPojo)) {
            for (T processPojo : listProcessPojo) {
                savePojo(processPojo);
            }
        }
        return true;
    }

    /**
     * 单独入库，如果根据查询条件查询存在则更新，否则新增
     *
     * @param pojo
     * @return
     */
    public <T extends BaseSavePojo> boolean savePojo(T pojo) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if (pojo.checkPojo()) {
            T qryPojo = commonDao.qryOneCommon(pojo.getSaveTableName(), pojo.generateQryMap(pojo), "");
            if (null == qryPojo) {
                commonDao.insertCommon(pojo.getSaveTableName(), pojo);
            } else {
                commonDao.updateCommon(pojo.getSaveTableName(), pojo, pojo.generateUpdateCondition(pojo));
            }
        }
        return true;
    }

    /**
     * 入库处理，动态检出可自动入库的对象，大量使用反射及递归，需要性能的场合请酌情使用
     *
     * @param processPojo
     * @param <T>
     * @return
     */
    public <T> boolean processParsePojo(T processPojo) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<BaseSavePojo> listPojo = getSaveList(processPojo);
        return savePojo(listPojo);
    }

    /**
     * 动态解析入库对象为list
     *
     * @param processPojo
     * @param <T>
     * @return
     */
    public <T> List<BaseSavePojo> getSaveList(T processPojo) throws IllegalAccessException {
        List<BaseSavePojo> listPojo = new ArrayList<>();
        if (null != processPojo) {
            if (processPojo instanceof BaseSavePojo) {
                listPojo.add((BaseSavePojo) processPojo);
            } else {
                Field fields[] = processPojo.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (CommonUtil.isWrapClass(field.getType())) {
                        continue;
                    }
                    field.setAccessible(true);
                    if (field.getType().isAssignableFrom(BaseSavePojo.class)) {
                        listPojo.add((BaseSavePojo) field.get(processPojo));
                    }
                    if (field.getType().isAssignableFrom(List.class)) {
                        if (null != field.get(processPojo)) {
                            for (Object o : (List) field.get(processPojo)) {
                                listPojo.addAll(getSaveList(o));
                            }
                        }
                    } else {
                        listPojo.addAll(getSaveList(field.get(processPojo)));
                    }
                    field.setAccessible(false);

                }
            }
        }
        return listPojo;
    }

    /**
     * 通用查询
     *
     * @param tableName
     * @param t
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public <T> List<T> qryAll(final String tableName, final T
            t) {
        return commonDao.qryAllCommon(tableName, t);
    }

    /**
     * 通用查询
     *
     * @param tableName
     * @param t
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public <T> T qryOne(final String tableName, final T
            t) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return commonDao.qryOneCommon(tableName, t, "");
    }

}
