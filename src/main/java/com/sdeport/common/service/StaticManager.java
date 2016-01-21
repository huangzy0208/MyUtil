package com.sdeport.common.service;

import com.sdeport.common.dao.StaticDao;
import com.sdeport.mail.MailCreator;
import com.sdeport.mail.MailSender;
import com.sdeport.message.MsgCreator;
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
