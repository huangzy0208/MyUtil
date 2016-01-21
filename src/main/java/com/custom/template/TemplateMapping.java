package com.custom.template;


/**
 * 模板映射bean
 */
public class TemplateMapping {

    private String className;
    private String template;
    private String headName;
    private String msgNo;

    public String getClassName() {
        return this.className;
    }

    public void setClassName(final String className) {
        this.className = className;
    }

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(final String template) {
        this.template = template;
    }

    public String getHeadName() {
        return this.headName;
    }

    public void setHeadName(final String headName) {
        this.headName = headName;
    }

    public String getMsgNo() {
        return this.msgNo;
    }

    public void setMsgNo(final String msgNo) {
        this.msgNo = msgNo;
    }

    @Override
    public String toString() {
        return "TemplateMapping{" +
               "className='" + className + '\'' +
               ", template='" + template + '\'' +
               ", headName='" + headName + '\'' +
               ", msgNo='" + msgNo + '\'' +
               '}';
    }
}
