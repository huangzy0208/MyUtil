package com.sdeport.mari.pojo;


import com.sdeport.common.utils.SqlType;

import java.io.Serializable;

/**
 * 海事申报-出口预报->船舶申报信息
 *
 * @author fanshilei
 * @date 2015/8/19.16:51
 */
public class ShipMariExpPreDecAddInfo implements Serializable {

    private static final long serialVersionUID = 992996001209868029L;

    private String decId;

    /**
     * *航次
     */
    private String sailings;

    /**
     * 出发港代码
     */
    private String departurePort;

    /**
     * 出发日期
     */
    @SqlType(type = "date", format = "yyyy-mm-dd")
    private String dateDeparture;

    /**
     * 经过港口
     */
    private String passPort;

    /**
     * 抵港时间
     */
    @SqlType(type = "date", format = "yyyy-mm-dd")
    private String dateArrival;

    /**
     * 泊位代码
     */
    private String berthCode;

    /**
     * *船舶类型
     */
    private String shipType;

    /**
     * 前进口吃水
     */
    private String frontDraft;

    /**
     * 后进口吃水
     */
    private String backDraft;

    /**
     * *船速
     */
    private String speed;

    /**
     * 离港时间
     */
    @SqlType(type = "date", format = "yyyy-mm-dd")
    private String dateLeave;

    /**
     * 开往港口
     */
    private String destPort;

    /**
     * *出口最大吃水
     */
    private String expMaxDraft;

    public String getDecId() {
        return decId;
    }

    public void setDecId(String decId) {
        this.decId = decId;
    }

    public String getSailings() {
        return sailings;
    }

    public void setSailings(String sailings) {
        this.sailings = sailings;
    }

    public String getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(String departurePort) {
        this.departurePort = departurePort;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public String getPassPort() {
        return passPort;
    }

    public void setPassPort(String passPort) {
        this.passPort = passPort;
    }

    public String getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(String dateArrival) {
        this.dateArrival = dateArrival;
    }

    public String getBerthCode() {
        return berthCode;
    }

    public void setBerthCode(String berthCode) {
        this.berthCode = berthCode;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public String getFrontDraft() {
        return this.frontDraft;
    }

    public void setFrontDraft(String frontDraft) {
        this.frontDraft = frontDraft;
    }

    public String getBackDraft() {
        return this.backDraft;
    }

    public void setBackDraft(String backDraft) {
        this.backDraft = backDraft;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDateLeave() {
        return dateLeave;
    }

    public void setDateLeave(String dateLeave) {
        this.dateLeave = dateLeave;
    }

    public String getDestPort() {
        return destPort;
    }

    public void setDestPort(String destPort) {
        this.destPort = destPort;
    }

    public String getExpMaxDraft() {
        return expMaxDraft;
    }

    public void setExpMaxDraft(String expMaxDraft) {
        this.expMaxDraft = expMaxDraft;
    }
}
