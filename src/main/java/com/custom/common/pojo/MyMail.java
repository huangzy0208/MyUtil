package com.sdeport.common.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮箱模板映射关系
 *
 * @author zhangmeng
 * @date 2015/12/11,11:01
 */

public class MyMail {
    String titleTemplate;
    String contentTemplate;
    Map<String, Object> mailParams = new HashMap<>();

    public String getTitleTemplate() {
        return titleTemplate;
    }

    public void setTitleTemplate(String titleTemplate) {
        this.titleTemplate = titleTemplate;
    }

    public String getContentTemplate() {
        return contentTemplate;
    }

    public void setContentTemplate(String contentTemplate) {
        this.contentTemplate = contentTemplate;
    }

    public Map<String, Object> getMailParams() {
        return mailParams;
    }

    public void setMailParams(Map<String, Object> mailParams) {
        this.mailParams = mailParams;
    }

    @Override
    public String toString() {
        return "MyMail{" +
                "titleTemplate='" + titleTemplate + '\'' +
                ", contentTemplate='" + contentTemplate + '\'' +
                ", mailParams=" + mailParams +
                '}';
    }
}
