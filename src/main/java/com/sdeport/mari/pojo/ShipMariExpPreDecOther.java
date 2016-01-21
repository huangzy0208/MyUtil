package com.sdeport.mari.pojo;


import com.sdeport.common.utils.SqlType;

import java.io.Serializable;

/**
 * @author fanshilei
 * @date 2015/8/19.17:09
 */
public class ShipMariExpPreDecOther implements Serializable {
    private static final long serialVersionUID = 503544467586768184L;

    private String decId;

    /**
     * 申报单位
     */
    private String decCompany;

    /**
     * 申报员
     */
    private String decPerson;

    /**
     * 报给单位
     */
    private String reportToDepartment;

    /**
     * *国际国内
     */
    private String ifInternational;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 附件列表
     */
    private String attachmentList;

    /**
     * *申报日期
     */
    @SqlType(type = "date", format = "yyyy-mm-dd hh24:mi:ss", defaultValue = "sysdate")
    private String decDate;

    public String getDecId() {
        return this.decId;
    }

    public void setDecId(String decId) {
        this.decId = decId;
    }

    public String getDecCompany() {
        return this.decCompany;
    }

    public void setDecCompany(String decCompany) {
        this.decCompany = decCompany;
    }

    public String getDecPerson() {
        return this.decPerson;
    }

    public void setDecPerson(String decPerson) {
        this.decPerson = decPerson;
    }

    public String getReportToDepartment() {
        return this.reportToDepartment;
    }

    public void setReportToDepartment(String reportToDepartment) {
        this.reportToDepartment = reportToDepartment;
    }

    public String getIfInternational() {
        return this.ifInternational;
    }

    public void setIfInternational(String ifInternational) {
        this.ifInternational = ifInternational;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAttachmentList() {
        return this.attachmentList;
    }

    public void setAttachmentList(String attachmentList) {
        this.attachmentList = attachmentList;
    }

    public String getDecDate() {
        return decDate;
    }

    public void setDecDate(String decDate) {
        this.decDate = decDate;
    }

}
