package com.custom.message;


import com.custom.template.TemplateMap;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by zhangmeng on 2015/8/20.
 */
@Component
@Lazy
public class MsgCreator {

    @Value("${msg.store.path}")
    private String configPath;

    private static String msgPath;

    @Autowired
    private TemplateMap templateMap;

    private static TemplateMap map;

    @PostConstruct
    public void init() {
        map = templateMap;
        msgPath = configPath;
    }

    /**
     * 生成xml报文文件
     *
     * @param <T>
     * @param bodyBean
     * @return
     */
    public static <T> boolean createXmlFile(T bodyBean, String type, String sendId, String msgId) throws Exception {
        return MessageGenerator.createXmlFile(getPath(msgPath, sendId), bodyBean, map, type, sendId, msgId);
    }

    /**
     * 生成xml文件，可自行指定路径
     *
     * @param path
     * @param bodyBean
     * @param type
     * @param sendId
     * @param <T>
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static <T> boolean createXmlFile(String path, T bodyBean, String type, String sendId, String msgId) throws Exception {
        return MessageGenerator.createXmlFile(path, bodyBean, map, type, sendId, msgId);
    }

    /**
     * 获得报文存储目录
     *
     * @param msgPath
     * @param sendId
     * @return
     */
    public static String getPath(String msgPath, String sendId) {
        String path = msgPath;
        if (!StringUtils.isEmpty(sendId)) {
            path += ("/" + MessageGenerator.dao.getPortEntry(sendId) + "/" + "send");
        }
        return path;
    }

    public static <T> String createXml(T bodyBean, String type, String sendId, String msgId) throws Exception {
        String jsonXml = MessageGenerator.createXml(bodyBean, map, type, sendId, msgId);
        if (StringUtils.isNotEmpty(jsonXml)) {
            return jsonXml.replaceAll("\\r\\n", "").replaceAll("\\t", "").trim();
        }
        return jsonXml;
    }

}
