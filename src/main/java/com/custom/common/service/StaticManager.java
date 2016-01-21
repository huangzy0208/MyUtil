package com.custom.common.service;

import com.custom.common.dao.StaticDao;
import com.custom.mail.MailCreator;
import com.custom.mail.MailSender;
import com.custom.message.MsgCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 管理需要静态注入的伪静态类
 *
 * @author zhangmeng
 * @date 2015/12/11,15:39
 */
@Service
@Lazy
public class StaticManager {

    @Autowired
    MsgCreator msgCreator;
    @Autowired
    MailCreator mailCreator;
    @Autowired
    MailSender mailSender;
    @Autowired
    StaticDao staticDao;

}
