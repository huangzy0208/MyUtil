package com.custom.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Map与bean互转工具类,默认map key值首字母大写，bean 字段首字母小写
 * by zhangmeng
 */
public class BeanMapUtill {

    private final static Logger logger = LoggerFactory.getLogger(BeanMapUtill.class);

    //处理超长字段
    private static Map<String, String> transMap = new HashMap<String, String>() {{
        put("MaximumAccommodatingShipsNum", "MaximumAccomShipsNum");
        put("BelongsToTheMarineDepartment", "BelongsMarineDepartment");
        put("ShipborneApplicationBusiness", "ShipborneAppBusiness");
        put("ShipReportingApplicationService", "ShipReportingAppService");
        put("GoodFaithCommitmentToSafety", "GoodFaithSafety");
        put("InternationalShippingNewspaper", "InternatShippingNewspaper");
    }};

    /**
     * bean2map
     *
     * @param objBean
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> bean2Map(T objBean) {
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        Field fields[] = objBean.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(objBean);
                if (null != value) {
                    rtnMap.put(ZzStringUtil.capFirst(field.getName()), value);
                }
            } catch (IllegalAccessException e) {
                logger.error("", e);
            }
            field.setAccessible(false);

        }
        return rtnMap;
    }

    /**
     * map2bean
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T map2Bean(Map<String, Object> map, Map<String, String> translateMap, Class clazz, boolean caseSense)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (null == map) {
            return null;
        }
        Map<String, Object> keyMap = new HashMap<String, Object>();
        if (null == translateMap) {
            translateMap = new HashMap<String, String>();
        }
        translateMap.putAll(transMap);
        for (Map.Entry<String, String> entry : translateMap.entrySet()) {
            if (map.containsKey(entry.getKey())) {
                String values[] = entry.getValue().split("#");
                for (String value : values) {
                    map.put(value, map.get(entry.getKey()));
                }

            }
        }
        if (caseSense) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                keyMap.put(ZzStringUtil.transLateUnderLine2Upper(entry.getKey()).toLowerCase(), entry.getValue());
            }
        } else {
            keyMap = map;
        }
        clazz.getConstructors();
        Constructor constructor = clazz.getConstructors()[0];
        T bean = (T) constructor.newInstance();
        Field fields[] = clazz.getDeclaredFields();
        if (!CollectionUtils.isEmpty(map)) {
            for (Field field : fields) {
                field.setAccessible(true);
                String xmlNodeName = ZzStringUtil.capFirst(field.getName());
                if (caseSense) {
                    xmlNodeName = xmlNodeName.toLowerCase();
                }
                if (keyMap.containsKey(xmlNodeName) && !"serialVersionUID".equalsIgnoreCase(field.getName())) {
                    if (field.getType().isAssignableFrom(List.class)) {
                        if (keyMap.get(xmlNodeName) instanceof List) {
                            field.set(bean, mapList2BeanList((List<Map<String, Object>>) keyMap.get(xmlNodeName), translateMap, getFieldClass(field)));
                        } else if (keyMap.get(xmlNodeName) instanceof Map) {
                            field.set(bean, map2BeanList((Map<String, Object>) keyMap.get(xmlNodeName), translateMap, getFieldClass(field)));
                        } else {
                            field.set(bean, null);
                        }
                    } else {
                        if (keyMap.get(xmlNodeName) instanceof Map) {
                            field.set(bean, map2Bean((Map<String, Object>) keyMap.get(xmlNodeName), translateMap, getFieldClass(field), true));
                        } else {
                            try {
                                setField(bean, field, keyMap.get(xmlNodeName));
                            } catch (IllegalArgumentException e) {
                                field.set(bean, null);
                            }
                        }
                    }
                }
                field.setAccessible(false);
            }
        }
        return bean;
    }

    /**
     * @param bean
     * @param field
     * @param value
     * @param <T>
     * @throws IllegalAccessException
     */
    private static <T> void setField(T bean, Field field, Object value) throws IllegalAccessException {
        if (null != value) {
            Object setValue = convertValue(field.getType(), value);
            field.set(bean, setValue);
        }
    }

    /**
     * 类型转换,目前仅处理BigDecimal
     *
     * @param type
     * @param value
     * @return
     * @TODO:完善类型转换代码
     */
    private static Object convertValue(Class<?> type, Object value) {
        Object covertValue = null;
        if (null == value || type == value.getClass()) {
            covertValue = value;
        } else if (type == BigDecimal.class && StringUtils.isNumeric(String.valueOf(value))) {
            covertValue = new BigDecimal(String.valueOf(value));
        }
        return covertValue;
    }

    /**
     * 将srcbean内容合并到target中
     *
     * @param srcBean
     * @param targetBean
     * @param <T>
     */
    public static <T> void mergeBean(T srcBean, T targetBean) {
        Field fields[] = targetBean.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(srcBean);
                if (null != value) {
                    setField(targetBean, field, value);
                }
            } catch (IllegalAccessException e) {
                logger.error("", e);
            }
            field.setAccessible(false);

        }
    }

    /**
     * map转list
     *
     * @param objectMap
     * @param fieldClass
     * @param <T>
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private static <T> List<T> map2BeanList(Map<String, Object> objectMap, Map<String, String> translateMap, Class fieldClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<T> rtnList = new ArrayList<T>();
        rtnList.add((T) map2Bean(objectMap, translateMap, fieldClass, true));
        return rtnList;
    }

    /**
     * 获取field的类型，如果是复合对象，获取的是泛型的类型
     *
     * @param field
     * @return
     */
    public static Class getFieldClass(Field field) {
        Class fieldClazz = field.getType();

        if (fieldClazz.isAssignableFrom(List.class)) {
            Type fc = field.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型

            if (fc instanceof ParameterizedType) {// 如果是泛型参数的类型
                ParameterizedType pt = (ParameterizedType) fc;

                fieldClazz = (Class) pt.getActualTypeArguments()[0]; //得到泛型里的class类型对象。
            }
        }

        return fieldClazz;
    }

    /**
     * mapList2BeanList
     *
     * @param mapList
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> mapList2BeanList(List<Map<String, Object>> mapList, Map<String, String> transMap, Class clazz) {
        List<T> rtnList = new ArrayList<T>();
        for (Map<String, Object> map : mapList) {
            try {
                rtnList.add((T) map2Bean(map, transMap, clazz, true));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                logger.error("数据转换为：{}失败", clazz, e);
            }
        }
        return rtnList;
    }

    /**
     * map2Bean
     *
     * @param map
     * @param clazz
     * @param levelKey
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static <T> T getMapBean(Map<String, Object> map, Map<String, String> transMap, Class clazz, String levelKey)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object lvValue = getLevelValue(map, levelKey);
        if (null != lvValue && lvValue instanceof Map) {
            return map2Bean((Map<String, Object>) lvValue, transMap, clazz, true);
        }
        return null;
    }

    /**
     * getMapBeanList
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getMapBeanList(Map<String, Object> map, Map<String, String> transMap, Class clazz, String levelKey)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<T> rtnList = new ArrayList<T>();
        Object lvValue = getLevelValue(map, levelKey);
        if ((null != lvValue)) {
            if (lvValue instanceof List) {
                rtnList.addAll((List<T>) mapList2BeanList((List<Map<String, Object>>) lvValue, transMap, clazz));
            } else if (lvValue instanceof Map) {
                rtnList.add((T) map2Bean((Map<String, Object>) lvValue, transMap, clazz, true));
            }
        }
        return rtnList;
    }

    /**
     * 从map中获得指定的对象，key值为用.分隔的层级
     *
     * @return
     */
    public static Object getLevelValue(Map<String, Object> map, String levelKey) {
        Object rtn = map;
        if (!CollectionUtils.isEmpty(map) && StringUtils.isNotEmpty(levelKey)) {
            String[] keys = levelKey.split("\\.");
            Object tmpMap = map;
            for (String key : keys) {
                if (null != tmpMap && tmpMap instanceof Map) {
                    tmpMap = ((Map) tmpMap).get(key);
                }
            }
            rtn = tmpMap;
        }
        return rtn;
    }

    public static <T, E> E swap(T from, E to, Map<String, String> mapper) {
        try {
            if (null == mapper) {
                return swap(from, to);
            }

            Map<String, String> fromMap = BeanUtils.describe(from);
            Map<String, String> toMap = BeanUtils.describe(to);

            for (String key : mapper.keySet()) {
                toMap.put(mapper.get(key), fromMap.get(key));
            }

            BeanUtils.populate(to, toMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return to;
    }

    public static <T, E> E swap(T from, E to) {
        try {
            Map<String, String> fromMap = BeanUtils.describe(from);
            BeanUtils.populate(to, fromMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return to;
    }

    public static <T, E> E swap(T from, Class clazz) {
        E to = null;
        try {
            Constructor constructor = clazz.getConstructor(null);
            to = (E) constructor.newInstance();
            swap(from, to);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return to;
    }

    public static <T, E> E swap(T from, Class clazz, Map<String, String> mapper) {
        E to = null;
        try {
            if (null == mapper) {
                return swap(from, clazz);
            }

            Constructor constructor = clazz.getConstructor(null);
            to = (E) constructor.newInstance();
            swap(from, to, mapper);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return to;
    }

    /**
     * map类型转换 字符串转obj
     *
     * @param workSheetMap
     * @return
     */
    public static Map<String, Object> str2obj(Map<String, String> workSheetMap) {
        Map<String, Object> objMap = null;
        if (null != workSheetMap) {
            objMap = new HashMap<>();
            for (Map.Entry<String, String> entry : workSheetMap.entrySet()) {
                objMap.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return objMap;
    }

    /**
     * 合并map
     * {b=456,a=123,d={e=you,f=i},c=[hhe,haha]}合并为
     * {b=456, a=123, d#e=you, c[0]=hhe, d#f=i, c[1]=haha}
     *
     * @param map
     * @return
     */
    public static Map<String, Object> mergeMap(Map<String, Object> map, String split) {
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        List<Map.Entry<String, Object>> list = new ArrayList<>();
        list.addAll(map.entrySet());
        while (!isPrimiteList(list)) {
            for (int i = list.size() - 1; i >= 0; i--) {
                if (list.get(i).getValue() instanceof Map || list.get(i).getValue() instanceof List) {
                    list.addAll(mergeEntry(list.get(i), split));
                    list.remove(i);
                }
            }
        }
        for (Map.Entry<String, Object> entry : list) {
            rtnMap.put(entry.getKey(), entry.getValue());
        }
        return rtnMap;
    }

    /**
     * 合并entry，合并list与map
     *
     * @param stringObjectEntry
     * @param split
     * @return
     */
    private static Collection<? extends Map.Entry<String, Object>> mergeEntry(Map.Entry<String, Object> stringObjectEntry, String split) {
        List<Map.Entry<String, Object>> rtnList = new ArrayList<>();
        if (stringObjectEntry.getValue() instanceof Map) {
            for (Map.Entry<String, Object> en : ((Map<String, Object>) ((Map) stringObjectEntry.getValue())).entrySet()) {
                Map.Entry<String, Object> entry = new AbstractMap.SimpleEntry<String, Object>(stringObjectEntry.getKey() + split + en.getKey(), en.getValue());
                rtnList.add(entry);
            }
        } else if (stringObjectEntry.getValue() instanceof List) {
            List list = (List) stringObjectEntry.getValue();
            for (int i = 0; i < list.size(); i++) {
                Map.Entry<String, Object> entry = new AbstractMap.SimpleEntry<String, Object>(stringObjectEntry.getKey() + "[" + i + "]", list.get(i));
                rtnList.add(entry);
            }
        }
        return rtnList;
    }

    /**
     * 列表是否包含复合对象
     *
     * @param list
     * @return
     */
    private static boolean isPrimiteList(Collection<Map.Entry<String, Object>> list) {
        for (Map.Entry<String, Object> entry : list) {
            if (entry.getValue() instanceof Map || entry.getValue() instanceof List) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        Map<String, Object> testMap = new HashMap<String, Object>();
        List<Map.Entry<String, Object>> list = new ArrayList<>();
        testMap.put("a", 123);
        testMap.put("b", "456");
        testMap.put("c", new ArrayList<String>());
        ((ArrayList<String>) testMap.get("c")).add("hhe");
        ((ArrayList<String>) testMap.get("c")).add("haha");
        testMap.put("d", new HashMap<String, String>());
        ((HashMap<String, String>) testMap.get("d")).put("e", "you");
        ((HashMap<String, String>) testMap.get("d")).put("f", "i");
        ((HashMap<String, String>) testMap.get("d")).put("h", "j");
        long l1 = System.currentTimeMillis();
        mergeMap(testMap, ".");
        System.out.println(System.currentTimeMillis() - l1);
        System.out.println(mergeMap(testMap, "."));
    }

    /**
     * 设置list内容的对象的指定字段为指定值
     *
     * @param list
     * @param fieldName
     * @param value
     */
    public static <T> void setFieldValue(List<T> list, Class clazz, String fieldName, Object value) throws IllegalAccessException {
        Field field = getFieldByName(clazz, fieldName);
        if (null != list && null != field) {
            field.setAccessible(true);
            for (T o : list) {
                setField(o, field, value);
            }
            field.setAccessible(false);
        }

    }

    /**
     * 设置对象的指定字段为指定值
     *
     * @param t
     * @param fieldName
     * @param value
     */
    public static <T> void setFieldValue(T t, String fieldName, Object value) throws IllegalAccessException {
        Field field = getFieldByName(t.getClass(), fieldName);
        field.setAccessible(true);
        setField(t, field, value);
        field.setAccessible(false);
    }

    /**
     * 获取指定class的指定名字的field
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public static Field getFieldByName(Class clazz, String fieldName) {
        Field field = null;
        Field fields[] = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.getName().equals(fieldName)) {
                field = f;
                break;
            }
        }
        return field;
    }

    /**
     * 获取对象的指定字段的值
     *
     * @param t
     * @param fieldName
     */
    public static <T> Object getFieldValue(T t, String fieldName) throws IllegalAccessException {
        Object rtnValue = null;
        Field field = getFieldByName(t.getClass(), fieldName);
        if (null != field) {
            field.setAccessible(true);
            rtnValue = field.get(t);
            field.setAccessible(false);
        }
        return rtnValue;
    }
}

