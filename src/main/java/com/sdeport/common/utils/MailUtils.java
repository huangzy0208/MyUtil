package com.sdeport.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * mail工具类
 * Created by zhangmeng on 2015/12/11.
 */
public class MailUtils {
    private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);

    /**
     * 发送邮件
     *
     * @param mailServer
     * @param fromMail
     * @param user
     * @param password
     * @param toMail
     * @param mailTitle
     * @param mailContent
     * @throws Exception
     */
    public static void sendMail(String mailServer, String fromMail, String user, String password,
                                String toMail,
                                String mailTitle,
                                String mailContent) throws Exception {
        Properties props = new Properties();
        // 使用smtp：简单邮件传输协议
        props.put("mail.smtp.host", mailServer);//存储发送邮件服务器的信息
        props.put("mail.smtp.auth", "true");//同时通过验证

        Session session = Session.getInstance(props);//根据属性新建一个邮件会话
        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
        message.setFrom(new InternetAddress(fromMail));//设置发件人的地址
        //群发时发送给一个虚拟用户，隐藏邮件人列表
        if (StringUtils.isNotEmpty(toMail) && toMail.contains(",")) {
            InternetAddress toUser = new InternetAddress("users@sdeport.com");
            toUser.setPersonal("尊敬的山东电子口岸用户", "GBK");
            message.setRecipient(Message.RecipientType.TO, toUser);
            message.setRecipients(Message.RecipientType.BCC, toMail);
        } else {
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
        }
        message.setSubject(mailTitle);//设置标题
        //设置信件内容
        message.setContent(mailContent, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富
        message.setSentDate(new Date());//设置发信时间
        message.saveChanges();//存储邮件信息

        //发送邮件
        Transport transport = session.getTransport("smtp");
        transport.connect(user, password);
        transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
        transport.close();
    }
}
