package com.sdeport.mail;


import com.sdeport.common.utils.MailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 发送邮件工具类
 * Created by zhangmeng on 2015/12/11.
 */
@Component
@Lazy
public class MailSender {
    private static final Logger logger = LoggerFactory.getLogger(MailSender.class);
    @Value("${mail.server}")
    private String mailSever;
    @Value("${mail.from}")
    private String mailFrom;
    @Value("${mail.usr}")
    private String mailUsr;
    @Value("${mail.pwd}")
    private String mailPwd;

    private static String MAIL_SERVER;
    private static String MAIL_FROM;
    private static String MAIL_USR;
    private static String MAIL_PWD;

    @PostConstruct
    public void init() {
        MAIL_SERVER = mailSever;
        MAIL_FROM = mailFrom;
        MAIL_USR = mailUsr;
        MAIL_PWD = mailPwd;
    }

    /**
     * 发送邮件
     *
     * @param mailTo
     * @param mailTitle
     * @param mailContent
     * @return
     */
    public static boolean sendMail(String mailTo, String mailTitle, String mailContent) {
        try {
            MailUtils.sendMail(MAIL_SERVER, MAIL_FROM, MAIL_USR, MAIL_PWD, mailTo, mailTitle, mailContent);
        } catch (Exception e) {
            logger.error("发送邮件失败，{}", e);
            return false;
        }
        return true;
    }

}
