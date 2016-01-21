package com.sdeport.mari.pojo;


import com.sdeport.common.utils.SqlType;

import java.io.Serializable;

/**
 * 海事申报-出口预报->船舶基本信息
 *
 * @author fanshilei
 * @date 2015/8/19.16:39
 */
public class ShipMariExpPreDecBasic implements Serializable {

    private static final Long serialVersionUID = 601392265457241443L;

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
     * *中文国籍
     */
    private String nationalityCn;

    /**
     * *保安等级
     */
    private String securityRank;

    /**
     * *船舶所有人中文
     */
    private String ownerCn;

    /**
     * *船舶所有人英文
     */
    private String ownerEn;

    /**
     * *呼号
     */
    private String callName;

    /**
     * *IMO编号
     */
    private String imo;

    /**
     * *船舶全长
     */
    private String totalLength;

    /**
     * *船舶宽度
     */
    private String width;

    /**
     * *总吨位
     */
    private String totalTon;

    /**
     * *净吨位
     */
    private String netTon;

    /**
     * *载重吨
     */
    private String capacityTon;

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

    public String getNationalityCn() {
        return nationalityCn;
    }

    public void setNationalityCn(String nationalityCn) {
        this.nationalityCn = nationalityCn;
    }

    public String getSecurityRank() {
        return securityRank;
    }

    public void setSecurityRank(String securityRank) {
        this.securityRank = securityRank;
    }

    public String getOwnerCn() {
        return ownerCn;
    }

    public void setOwnerCn(String ownerCn) {
        this.ownerCn = ownerCn;
    }

    public String getOwnerEn() {
        return ownerEn;
    }

    public void setOwnerEn(String ownerEn) {
        this.ownerEn = ownerEn;
    }

    public String getCallName() {
        return callName;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getTotalLength() {
        return this.totalLength;
    }

    public void setTotalLength(String totalLength) {
        this.totalLength = totalLength;
    }

    public String getWidth() {
        return this.width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getTotalTon() {
        return totalTon;
    }

    public void setTotalTon(String totalTon) {
        this.totalTon = totalTon;
    }

    public String getNetTon() {
        return netTon;
    }

    public void setNetTon(String netTon) {
        this.netTon = netTon;
    }

    public String getCapacityTon() {
        return capacityTon;
    }

    public void setCapacityTon(String capacityTon) {
        this.capacityTon = capacityTon;
    }

    public String getShipNo() {
        return this.shipNo;
    }

    public void setShipNo(String shipNo) {
        this.shipNo = shipNo;
    }

    @Override
    public String toString() {
        return "ShipMariExpPreDecBasic{" +
                "decId='" + decId + '\'' +
                ", shipNo='" + shipNo + '\'' +
                ", nameCn='" + nameCn + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", nationalityCn='" + nationalityCn + '\'' +
                ", securityRank='" + securityRank + '\'' +
                ", ownerCn='" + ownerCn + '\'' +
                ", ownerEn='" + ownerEn + '\'' +
                ", callName='" + callName + '\'' +
                ", imo='" + imo + '\'' +
                ", totalLength='" + totalLength + '\'' +
                ", width='" + width + '\'' +
                ", totalTon='" + totalTon + '\'' +
                ", netTon='" + netTon + '\'' +
                ", capacityTon='" + capacityTon + '\'' +
                '}';
    }
}
