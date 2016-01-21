package com.sdeport.message;


import com.sdeport.common.utils.BeanMapUtill;
import com.sdeport.common.utils.XmlMapUtil;
import org.dom4j.DocumentException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Message 生成器
 *
 * @Author: zhangmeng
 */
@Component
@Lazy
public class MessageParser {

    /**
     * 获取海关消息头
     *
     * @param rootMap
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static MessageHead parseMsgHead(Map<String, Object> rootMap)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return BeanMapUtill.getMapBean(rootMap, new HashMap<String, String>(), MessageHead.class, "Message.MessageHead");
    }

    /**
     * 获取其他消息头
     *
     * @param rootMap
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static MessageOtherHead parseMsgOtherHead(Map<String, Object> rootMap)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return BeanMapUtill.getMapBean(rootMap, new HashMap<String, String>(), MessageOtherHead.class, "Message.MessageHead");
    }

    /**
     * 通用解析bean
     *
     * @param rootMap
     * @param t
     * @param valueKey
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static <T> T parseBean(Map<String, Object> rootMap, Map<String, String> transMap, T t, String valueKey)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if (null == transMap) {
            transMap = new HashMap<String, String>();
        }
        return BeanMapUtill.getMapBean(rootMap, transMap, t.getClass(), valueKey);
    }

    /**
     * 通用解析bean
     *
     * @param rootMap
     * @param t
     * @param valueKey
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static <T> T parseBean(Map<String, Object> rootMap, T t, String valueKey)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return parseBean(rootMap, null, t, valueKey);
    }

    public static void main(String args[]) throws IOException, InstantiationException, NoSuchMethodException, ParserConfigurationException, IllegalAccessException, InvocationTargetException, SAXException, DocumentException {
        System.out.println(parseBean(XmlMapUtil.readXml("F:\\testxml\\msa\\SendDZKAFile\\MSA0021_20150928153159_001.xml"), null, "Message.MessageBody.Declaration.InformationLists"));
    }
}
