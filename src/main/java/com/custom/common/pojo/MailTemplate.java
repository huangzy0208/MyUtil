package com.custom.common.pojo;

/**
 * 邮箱模板映射关系
 *
 * @author zhangmeng
 * @date 2015/12/11,11:01
 */

public class MailTemplate {

    private String templateId; //模板ID
    private String templateFile; //模板名称
    private String remark;//备注

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MailTemplate{" +
                "templateId='" + templateId + '\'' +
                ", templateFile='" + templateFile + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
