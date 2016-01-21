package com.custom.message;


import com.custom.common.dao.CommonDao;
import com.custom.common.utils.FreeMarkerUtil;
import com.custom.common.utils.XmlUtils;
import com.custom.common.utils.ZzStringUtil;
import com.custom.template.TemplateMap;
import com.custom.template.TemplateMapping;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Message 生成器
 *
 * @Author: zhangmeng
 */
@Component
public class MessageGenerator {

    private static final Logger logger = LoggerFactory.getLogger(MessageGenerator.class);
    private static final Map<String, InterfaceHead> mapHead = new HashMap<>();

    static {
        mapHead.put("CustHead", new MessageHead());
        mapHead.put("OtherHead", new MessageOtherHead());
    }

    @Autowired
    private CommonDao commonDao;

    public static CommonDao dao;

    @PostConstruct
    public void init() {
        dao = commonDao;
    }

    /**
     * 生成message
     *
     * @param bodyBean
     * @param <T>
     * @return
     */
    public static <T> Message create(T bodyBean, final TemplateMap templateMap, String type, String sendId, String msgId) {

        Message msg = null;
        if (null != bodyBean) {
            msg = new Message();
            msg.setMessageHead(createMsgHead(bodyBean, templateMap, type, sendId, msgId));
            msg.setMessageBody(new MessageBody(bodyBean));
        }
        return msg;
    }

    /**
     * 创建报文头
     *
     * @param bodyBean
     * @param templateMap
     * @param <T>
     * @return
     */
    private static <T> InterfaceHead createMsgHead(T bodyBean, final TemplateMap templateMap, String type, String
            sendId, String msgId) {
        return mapHead.get(templateMap.getTemplateMap().get(
                ZzStringUtil.getTemplateClassName(bodyBean.getClass(),
                        type)).getHeadName()).createHead(bodyBean, sendId, msgId);
    }


    /**
     * 生成xml报文
     *
     * @param <T>
     * @param bodyBean
     * @param templateMap
     * @param type
     * @param sendId
     * @return
     */
    public static <T> String createXml(T bodyBean, final TemplateMap templateMap, final String type, String sendId, String msgId)
            throws Exception {
        String msgXml = "";
        if (null != bodyBean) {
            Message msg = create(bodyBean, templateMap, type, sendId, msgId);
            try {
                msgXml = FreeMarkerUtil.processTemplate(templateMap.getTemplateMap().get(
                        ZzStringUtil.getTemplateClassName(bodyBean.getClass(), type))
                        .getTemplate(), msg);
                XmlUtils.validateXml(msgXml, "utf-8");
            } catch (Exception e) {
                logger.error("读取报文失败,报文模板：{" + templateMap.getTemplateMap() + "}", e);
                throw e;
            }
        }
        return msgXml;
    }

    /**
     * 生成xml报文文件
     *
     * @param <T>
     * @param path
     * @param bodyBean
     * @param templateMap
     * @param type
     * @param sendId
     * @return
     */
    public static <T> boolean createXmlFile(String path, T bodyBean, final TemplateMap templateMap, final String type,
                                            String sendId, String msgId)
            throws Exception {
        String msgXml = createXml(bodyBean, templateMap, type, sendId, msgId);
        String fileName = generateMsgFileName(bodyBean, templateMap, type);
        File dir = new File(path);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        String fullFileName = path + "/" + fileName;
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(new File(fullFileName));
            outStream.write(msgXml.getBytes("utf-8"));
            outStream.close();
        } catch (IOException e) {
            logger.error("保存文件失败", e);
            if (null != outStream) {
                outStream.close();
            }
            throw e;
        }
        return true;
    }

    /**
     * 生成报文文件名称
     *
     * @return
     * @TODO:需要增加senderCode
     */
    public static <T> String generateMsgFileName(T bodyBean, final TemplateMap templateMap, String type) {
        String fileName = "";
        TemplateMapping map = templateMap.getTemplateMap().get(
                ZzStringUtil.getTemplateClassName(bodyBean.getClass(), type));
        String fileNameFormat = "";
        if (StringUtils.isEmpty(map.getMsgNo())) {
            fileNameFormat = "%s_1P0_%s_%s." + ZzStringUtil.getFileSuffix(map.getTemplate());
            fileName = String.format(fileNameFormat, ZzStringUtil.getFilePrefix(map.getTemplate()),
                    MessageHead.DEFAULT_SENDER_CODE,
                    ZzStringUtil.getDefaultDateTime());
        } else {
            fileNameFormat = "%s_%s_%s." + ZzStringUtil.getFileSuffix(map.getTemplate());
            fileName = String.format(fileNameFormat, map.getMsgNo(), ZzStringUtil.getDefaultDateTime(), dao
                    .getCommonSeqId().trim());
        }
        return fileName;
    }

}
