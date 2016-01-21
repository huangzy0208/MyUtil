package com.sdeport.mail;


import com.sdeport.common.utils.FreeMarkerUtil;
import com.sdeport.template.TemplateMap;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 创建邮件工具类
 * Created by zhangmeng on 2015/12/11.
 */
@Component
@Lazy
public class MailCreator {

    private final static Logger logger = LoggerFactory.getLogger(MailCreator.class);
    @Autowired
    private TemplateMap templateMap;

    private static TemplateMap map;

    @PostConstruct
    public void init() {
        map = templateMap;
    }

    /**
     * 根据邮件模板创建邮件内容,如果传入的bean为字符串则直接返回
     *
     * @param bean
     * @param templateId
     * @param <T>
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static <T> String createContent(T bean, String templateId) throws IOException, TemplateException {
        if (bean instanceof String) {
            return String.valueOf(bean);
        }
        String content = FreeMarkerUtil.processTemplate(templateId, bean);
        return content;
    }

    /**
     * 创建并发送邮件
     *
     * @param bean
     * @param title
     * @param contentTemplate
     * @param mailTo
     * @param <T>
     * @return
     */
    public static <T> boolean createAndSendMail(T bean, String title, String contentTemplate, String mailTo) {
        try {
            String mailTitle = title;
            String mailContent = createContent(bean, contentTemplate);
            return MailSender.sendMail(mailTo, mailTitle, mailContent);
        } catch (Exception e) {
            logger.error("发送邮件失败{}", e);
        }
        return true;

    }

}
