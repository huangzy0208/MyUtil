package com.sdeport.mari.pojo;

import java.io.Serializable;

/**
 * @author fanshilei
 * @date 2015/8/19.17:03
 */
public class ShipMariImpPsngCargoInfo implements Serializable {
    private static final Long serialVersionUID = 318928617838292185L;

    private String decId;

    /**
     * *进口旅客总数
     */
    private String impPsngCnt;

    /**
     * *进口中籍船员男
     */
    private String impCnCrewMale;

    /**
     * *进口外籍船员男
     */
    private String impForeignCrewMale;

    /**
     * *进口外籍船员女
     */
    private String impForeignCrewFemale;

    /**
     * *进口载货吨数
     */
    private String impCargoTon;

    /**
     * *进口普通货物吨数
     */
    private String impGeneralGoodsTon;

    /**
     * *进口中籍船员女
     */
    private String impCnCrewFemale;

    /**
     * *进口载货名称
     */
    private String impGoodsName;

    /**
     * *进口危险货物吨数
     */
    private String impDangerousCargoTon;

    /**
     * *出口旅客总数
     */
    private String expPsngs;

    /**
     * *出口中籍船员男
     */
    private String expCnCrewMale;

    /**
     * *出口外籍船员男
     */
    private String expForeignCrewMale;

    /**
     * *出口外籍船员女
     */
    private String expForeignCrewFemale;

    /**
     * *出口载货吨数
     */
    private String expCargoTon;

    /**
     * *出口普通货物吨数
     */
    private String expGeneralCargoTon;

    /**
     * *出口中籍船员女
     */
    private String expCnCrewFemale;

    /**
     * *出口载货名称
     */
    private String expCargoName;

    /**
     * *出口危险货物吨数
     */
    private String expDangerousGoodsTon;

    public String getDecId() {
        return decId;
    }

    public void setDecId(String decId) {
        this.decId = decId;
    }

    public String getImpPsngCnt() {
        return impPsngCnt;
    }

    public void setImpPsngCnt(String impPsngCnt) {
        this.impPsngCnt = impPsngCnt;
    }

    public String getImpCnCrewMale() {
        return impCnCrewMale;
    }

    public void setImpCnCrewMale(String impCnCrewMale) {
        this.impCnCrewMale = impCnCrewMale;
    }

    public String getImpForeignCrewMale() {
        return impForeignCrewMale;
    }

    public void setImpForeignCrewMale(String impForeignCrewMale) {
        this.impForeignCrewMale = impForeignCrewMale;
    }

    public String getImpForeignCrewFemale() {
        return impForeignCrewFemale;
    }

    public void setImpForeignCrewFemale(String impForeignCrewFemale) {
        this.impForeignCrewFemale = impForeignCrewFemale;
    }

    public String getImpCargoTon() {
        return impCargoTon;
    }

    public void setImpCargoTon(String impCargoTon) {
        this.impCargoTon = impCargoTon;
    }

    public String getImpGeneralGoodsTon() {
        return impGeneralGoodsTon;
    }

    public void setImpGeneralGoodsTon(String impGeneralGoodsTon) {
        this.impGeneralGoodsTon = impGeneralGoodsTon;
    }

    public String getImpCnCrewFemale() {
        return impCnCrewFemale;
    }

    public void setImpCnCrewFemale(String impCnCrewFemale) {
        this.impCnCrewFemale = impCnCrewFemale;
    }

    public String getImpGoodsName() {
        return impGoodsName;
    }

    public void setImpGoodsName(String impGoodsName) {
        this.impGoodsName = impGoodsName;
    }

    public String getImpDangerousCargoTon() {
        return impDangerousCargoTon;
    }

    public void setImpDangerousCargoTon(String impDangerousCargoTon) {
        this.impDangerousCargoTon = impDangerousCargoTon;
    }

    public String getExpPsngs() {
        return expPsngs;
    }

    public void setExpPsngs(String expPsngs) {
        this.expPsngs = expPsngs;
    }

    public String getExpCnCrewMale() {
        return expCnCrewMale;
    }

    public void setExpCnCrewMale(String expCnCrewMale) {
        this.expCnCrewMale = expCnCrewMale;
    }

    public String getExpForeignCrewMale() {
        return expForeignCrewMale;
    }

    public void setExpForeignCrewMale(String expForeignCrewMale) {
        this.expForeignCrewMale = expForeignCrewMale;
    }

    public String getExpForeignCrewFemale() {
        return expForeignCrewFemale;
    }

    public void setExpForeignCrewFemale(String expForeignCrewFemale) {
        this.expForeignCrewFemale = expForeignCrewFemale;
    }

    public String getExpCargoTon() {
        return expCargoTon;
    }

    public void setExpCargoTon(String expCargoTon) {
        this.expCargoTon = expCargoTon;
    }

    public String getExpGeneralCargoTon() {
        return expGeneralCargoTon;
    }

    public void setExpGeneralCargoTon(String expGeneralCargoTon) {
        this.expGeneralCargoTon = expGeneralCargoTon;
    }

    public String getExpCnCrewFemale() {
        return expCnCrewFemale;
    }

    public void setExpCnCrewFemale(String expCnCrewFemale) {
        this.expCnCrewFemale = expCnCrewFemale;
    }

    public String getExpCargoName() {
        return expCargoName;
    }

    public void setExpCargoName(String expCargoName) {
        this.expCargoName = expCargoName;
    }

    public String getExpDangerousGoodsTon() {
        return expDangerousGoodsTon;
    }

    public void setExpDangerousGoodsTon(String expDangerousGoodsTon) {
        this.expDangerousGoodsTon = expDangerousGoodsTon;
    }
}
