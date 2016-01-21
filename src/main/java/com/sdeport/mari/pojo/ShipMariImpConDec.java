package com.sdeport.mari.pojo;


import com.sdeport.common.utils.SqlType;

import java.io.Serializable;

/**
 * 海事申报-进口确报
 *
 * @author fanshilei
 * @date 2015/8/20.15:15
 */
public class ShipMariImpConDec implements Serializable {
    private static final Long serialVersionUID = 587547895184193072L;

    private String decId;
    @SqlType(inTable = false)
    private String shipNo;
    /**
     * 中文船名
     */
    private String nameCn;

    /**
     * 英文船名
     */
    private String nameEn;

    /**
     * 内外贸标志
     */
    private String ifInternational;

    /**
     * 上一港
     */
    private String lastPort;

    /**
     * 下一港
     */
    private String nextPort;

    /**
     * 离港时间
     */
    @SqlType(type = "date", format = "yyyy-mm-dd hh24:mi:ss")
    private String dateDeparture;

    /**
     * *货物名称
     */
    private String goodsName;

    /**
     * *普通货物数量
     */
    private String generalGoods;

    /**
     * *旅客总数
     */
    private String psngs;

    /**
     * *中籍旅客男
     */
    private String cnPsngMale;

    /**
     * *外籍旅客男
     */
    private String foreignPsngMale;

    /**
     * *外籍旅客女
     */
    private String foreignPsngFemale;

    /**
     * *航次
     */
    private String sailings;

    /**
     * 抵港时间
     */
    @SqlType(type = "date", format = "yyyy-mm-dd hh24:mi:ss")
    private String dateArrival;

    /**
     * 预靠泊位
     */
    private String planBerth;

    /**
     * *危险货物数量
     */
    private String dangerousGoods;

    /**
     * *中籍旅客女
     */
    private String cnPsngFemale;

    /**
     * 申报单位
     */
    private String decCompany;

    /**
     * 申报人员
     */
    private String decPerson;

    /**
     * 报给单位
     */
    private String reportToDepartment;

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
        return decId;
    }

    public void setDecId(String decId) {
        this.decId = decId;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getIfInternational() {
        return ifInternational;
    }

    public void setIfInternational(String ifInternational) {
        this.ifInternational = ifInternational;
    }

    public String getLastPort() {
        return lastPort;
    }

    public void setLastPort(String lastPort) {
        this.lastPort = lastPort;
    }

    public String getNextPort() {
        return nextPort;
    }

    public void setNextPort(String nextPort) {
        this.nextPort = nextPort;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGeneralGoods() {
        return generalGoods;
    }

    public void setGeneralGoods(String generalGoods) {
        this.generalGoods = generalGoods;
    }

    public String getPsngs() {
        return psngs;
    }

    public void setPsngs(String psngs) {
        this.psngs = psngs;
    }

    public String getCnPsngMale() {
        return cnPsngMale;
    }

    public void setCnPsngMale(String cnPsngMale) {
        this.cnPsngMale = cnPsngMale;
    }

    public String getForeignPsngMale() {
        return foreignPsngMale;
    }

    public void setForeignPsngMale(String foreignPsngMale) {
        this.foreignPsngMale = foreignPsngMale;
    }

    public String getForeignPsngFemale() {
        return foreignPsngFemale;
    }

    public void setForeignPsngFemale(String foreignPsngFemale) {
        this.foreignPsngFemale = foreignPsngFemale;
    }

    public String getSailings() {
        return sailings;
    }

    public void setSailings(String sailings) {
        this.sailings = sailings;
    }

    public String getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(String dateArrival) {
        this.dateArrival = dateArrival;
    }

    public String getPlanBerth() {
        return planBerth;
    }

    public void setPlanBerth(String planBerth) {
        this.planBerth = planBerth;
    }

    public String getDangerousGoods() {
        return dangerousGoods;
    }

    public void setDangerousGoods(String dangerousGoods) {
        this.dangerousGoods = dangerousGoods;
    }

    public String getCnPsngFemale() {
        return cnPsngFemale;
    }

    public void setCnPsngFemale(String cnPsngFemale) {
        this.cnPsngFemale = cnPsngFemale;
    }

    public String getDecCompany() {
        return decCompany;
    }

    public void setDecCompany(String decCompany) {
        this.decCompany = decCompany;
    }

    public String getDecPerson() {
        return decPerson;
    }

    public void setDecPerson(String decPerson) {
        this.decPerson = decPerson;
    }

    public String getReportToDepartment() {
        return reportToDepartment;
    }

    public void setReportToDepartment(String reportToDepartment) {
        this.reportToDepartment = reportToDepartment;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAttachmentList() {
        return attachmentList;
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

    public String getShipNo() {
        return this.shipNo;
    }

    public void setShipNo(String shipNo) {
        this.shipNo = shipNo;
    }

    @Override
    public String toString() {
        return "ShipMariImpConDec{" +
                "decId='" + decId + '\'' +
                ", shipNo='" + shipNo + '\'' +
                ", nameCn='" + nameCn + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", ifInternational='" + ifInternational + '\'' +
                ", lastPort='" + lastPort + '\'' +
                ", nextPort='" + nextPort + '\'' +
                ", dateDeparture='" + dateDeparture + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", generalGoods='" + generalGoods + '\'' +
                ", psngs='" + psngs + '\'' +
                ", cnPsngMale='" + cnPsngMale + '\'' +
                ", foreignPsngMale='" + foreignPsngMale + '\'' +
                ", foreignPsngFemale='" + foreignPsngFemale + '\'' +
                ", sailings='" + sailings + '\'' +
                ", dateArrival='" + dateArrival + '\'' +
                ", planBerth='" + planBerth + '\'' +
                ", dangerousGoods='" + dangerousGoods + '\'' +
                ", cnPsngFemale='" + cnPsngFemale + '\'' +
                ", decCompany='" + decCompany + '\'' +
                ", decPerson='" + decPerson + '\'' +
                ", reportToDepartment='" + reportToDepartment + '\'' +
                ", remarks='" + remarks + '\'' +
                ", attachmentList='" + attachmentList + '\'' +
                ", decDate='" + decDate + '\'' +
                '}';
    }
}
