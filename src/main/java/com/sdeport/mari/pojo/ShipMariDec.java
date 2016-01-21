package com.sdeport.mari.pojo;


import com.sdeport.common.utils.SqlType;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 船舶申报-海事申报
 */
public class ShipMariDec implements Serializable {

    private static final long serialVersionUID = 696951424491787501L;

    private String decId;

    /**
     * *IMO号
     */
    private String imo;

    /**
     * 中文船名
     */
    private String shipNameCn;

    /**
     * 英文船名
     */
    private String shipNameEn;

    /**
     * 进口航次
     */
    private String impSailings;

    /**
     * 出口航次
     */
    private String expSailings;

    /**
     * 抵港时间
     */
    @SqlType(type = "date", format = "yyyy-mm-dd", qryStart = "startTime", qryEnd = "endTime")
    private String dateArrival;

    @SqlType(inTable = false)
    private String startTime = "";
    @SqlType(inTable = false)
    private String endTime = "";

    /**
     * 进口预报
     */
    private String impPreDecStatus;

    /**
     * 进口确报
     */
    private String impConfirmDecStatus;

    /**
     * 出口预报
     */
    private String expPreDecStatus;

    /**
     * 移泊申报
     */
    private String shiftStatus;

    /**
     * 回执信息
     */
    @SqlType(inTable = false)
    private String receiptInfo;

    /**
     * 录入人组织机构
     */
    private String organizationId;
    /**
     * 录入人公司代码
     */
    private String companyId;
    /**
     * 录入人公司名称
     */
    private String companyName;
    /**
     * 录入人部门名称
     */
    private String departmentId;

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(final String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(final String departmentId) {
        this.departmentId = departmentId;
    }

    public String getOrganizationId() {
        return this.organizationId;
    }

    public void setOrganizationId(final String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDecId() {
        return decId;
    }

    public void setDecId(String decId) {
        this.decId = decId;
    }

    /**
     * @return *IMO号
     */
    public String getImo() {
        return imo;
    }

    /**
     * @param imo *IMO号
     */
    public void setImo(String imo) {
        this.imo = imo;
    }

    /**
     * @return 中文船名
     */
    public String getShipNameCn() {
        return shipNameCn;
    }

    /**
     * @param shipNameCn 中文船名
     */
    public void setShipNameCn(String shipNameCn) {
        this.shipNameCn = shipNameCn;
    }

    /**
     * @return 英文船名
     */
    public String getShipNameEn() {
        return shipNameEn;
    }

    /**
     * @param shipNameEn 英文船名
     */
    public void setShipNameEn(String shipNameEn) {
        this.shipNameEn = shipNameEn;
    }

    /**
     * @return 进口航次
     */
    public String getImpSailings() {
        return impSailings;
    }

    /**
     * @param impSailings 进口航次
     */
    public void setImpSailings(String impSailings) {
        this.impSailings = impSailings;
    }

    /**
     * @return 出口航次
     */
    public String getExpSailings() {
        return expSailings;
    }

    /**
     * @param expSailings 出口航次
     */
    public void setExpSailings(String expSailings) {
        this.expSailings = expSailings;
    }

    /**
     * @return 抵港时间
     */
    public String getDateArrival() {
        return dateArrival;
    }

    /**
     * @param dateArrival 抵港时间
     */
    public void setDateArrival(String dateArrival) {
        this.dateArrival = dateArrival;
    }

    /**
     * @return 进口预报
     */
    public String getImpPreDecStatus() {
        return impPreDecStatus;
    }

    /**
     * @param impPreDecStatus 进口预报
     */
    public void setImpPreDecStatus(String impPreDecStatus) {
        this.impPreDecStatus = impPreDecStatus;
    }

    /**
     * @return 进口确报
     */
    public String getImpConfirmDecStatus() {
        return impConfirmDecStatus;
    }

    /**
     * @param impConfirmDecStatus 进口确报
     */
    public void setImpConfirmDecStatus(String impConfirmDecStatus) {
        this.impConfirmDecStatus = impConfirmDecStatus;
    }

    /**
     * @return 出口预报
     */
    public String getExpPreDecStatus() {
        return expPreDecStatus;
    }

    /**
     * @param expPreDecStatus 出口预报
     */
    public void setExpPreDecStatus(String expPreDecStatus) {
        this.expPreDecStatus = expPreDecStatus;
    }

    /**
     * @return 移泊申报
     */
    public String getShiftStatus() {
        return shiftStatus;
    }

    /**
     * @param shiftStatus 移泊申报
     */
    public void setShiftStatus(String shiftStatus) {
        this.shiftStatus = shiftStatus;
    }

    public String getReceiptInfo() {
        return StringUtils.isEmpty(receiptInfo) ? "查看" : receiptInfo;
    }

    public void setReceiptInfo(String receiptInfo) {
        this.receiptInfo = receiptInfo;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
