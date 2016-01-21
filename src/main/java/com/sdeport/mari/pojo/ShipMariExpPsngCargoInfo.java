package com.sdeport.mari.pojo;

import java.io.Serializable;

/**
 * @author fanshilei
 * @date 2015/8/19.17:03
 */
public class ShipMariExpPsngCargoInfo implements Serializable {

    private static final long serialVersionUID = 318928617838292185L;

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
        return this.impPsngCnt;
    }

    public void setImpPsngCnt(final String impPsngCnt) {
        this.impPsngCnt = impPsngCnt;
    }

    public String getImpCnCrewMale() {
        return this.impCnCrewMale;
    }

    public void setImpCnCrewMale(final String impCnCrewMale) {
        this.impCnCrewMale = impCnCrewMale;
    }

    public String getImpForeignCrewMale() {
        return this.impForeignCrewMale;
    }

    public void setImpForeignCrewMale(final String impForeignCrewMale) {
        this.impForeignCrewMale = impForeignCrewMale;
    }

    public String getImpForeignCrewFemale() {
        return this.impForeignCrewFemale;
    }

    public void setImpForeignCrewFemale(final String impForeignCrewFemale) {
        this.impForeignCrewFemale = impForeignCrewFemale;
    }

    public String getImpCargoTon() {
        return this.impCargoTon;
    }

    public void setImpCargoTon(final String impCargoTon) {
        this.impCargoTon = impCargoTon;
    }

    public String getImpGeneralGoodsTon() {
        return this.impGeneralGoodsTon;
    }

    public void setImpGeneralGoodsTon(final String impGeneralGoodsTon) {
        this.impGeneralGoodsTon = impGeneralGoodsTon;
    }

    public String getImpCnCrewFemale() {
        return this.impCnCrewFemale;
    }

    public void setImpCnCrewFemale(final String impCnCrewFemale) {
        this.impCnCrewFemale = impCnCrewFemale;
    }

    public String getImpGoodsName() {
        return impGoodsName;
    }

    public void setImpGoodsName(String impGoodsName) {
        this.impGoodsName = impGoodsName;
    }

    public String getImpDangerousCargoTon() {
        return this.impDangerousCargoTon;
    }

    public void setImpDangerousCargoTon(final String impDangerousCargoTon) {
        this.impDangerousCargoTon = impDangerousCargoTon;
    }

    public String getExpPsngs() {
        return this.expPsngs;
    }

    public void setExpPsngs(final String expPsngs) {
        this.expPsngs = expPsngs;
    }

    public String getExpCnCrewMale() {
        return this.expCnCrewMale;
    }

    public void setExpCnCrewMale(final String expCnCrewMale) {
        this.expCnCrewMale = expCnCrewMale;
    }

    public String getExpForeignCrewMale() {
        return this.expForeignCrewMale;
    }

    public void setExpForeignCrewMale(final String expForeignCrewMale) {
        this.expForeignCrewMale = expForeignCrewMale;
    }

    public String getExpForeignCrewFemale() {
        return this.expForeignCrewFemale;
    }

    public void setExpForeignCrewFemale(final String expForeignCrewFemale) {
        this.expForeignCrewFemale = expForeignCrewFemale;
    }

    public String getExpCargoTon() {
        return this.expCargoTon;
    }

    public void setExpCargoTon(final String expCargoTon) {
        this.expCargoTon = expCargoTon;
    }

    public String getExpGeneralCargoTon() {
        return this.expGeneralCargoTon;
    }

    public void setExpGeneralCargoTon(final String expGeneralCargoTon) {
        this.expGeneralCargoTon = expGeneralCargoTon;
    }

    public String getExpCnCrewFemale() {
        return this.expCnCrewFemale;
    }

    public void setExpCnCrewFemale(final String expCnCrewFemale) {
        this.expCnCrewFemale = expCnCrewFemale;
    }

    public String getExpCargoName() {
        return expCargoName;
    }

    public void setExpCargoName(String expCargoName) {
        this.expCargoName = expCargoName;
    }

    public String getExpDangerousGoodsTon() {
        return this.expDangerousGoodsTon;
    }

    public void setExpDangerousGoodsTon(String expDangerousGoodsTon) {
        this.expDangerousGoodsTon = expDangerousGoodsTon;
    }

    @Override
    public String toString() {
        return "ShipMariExpPsngCargoInfo{" +
                "decId='" + decId + '\'' +
                ", impPsngCnt='" + impPsngCnt + '\'' +
                ", impCnCrewMale='" + impCnCrewMale + '\'' +
                ", impForeignCrewMale='" + impForeignCrewMale + '\'' +
                ", impForeignCrewFemale='" + impForeignCrewFemale + '\'' +
                ", impCargoTon='" + impCargoTon + '\'' +
                ", impGeneralGoodsTon='" + impGeneralGoodsTon + '\'' +
                ", impCnCrewFemale='" + impCnCrewFemale + '\'' +
                ", impGoodsName='" + impGoodsName + '\'' +
                ", impDangerousCargoTon='" + impDangerousCargoTon + '\'' +
                ", expPsngs='" + expPsngs + '\'' +
                ", expCnCrewMale='" + expCnCrewMale + '\'' +
                ", expForeignCrewMale='" + expForeignCrewMale + '\'' +
                ", expForeignCrewFemale='" + expForeignCrewFemale + '\'' +
                ", expCargoTon='" + expCargoTon + '\'' +
                ", expGeneralCargoTon='" + expGeneralCargoTon + '\'' +
                ", expCnCrewFemale='" + expCnCrewFemale + '\'' +
                ", expCargoName='" + expCargoName + '\'' +
                ", expDangerousGoodsTon='" + expDangerousGoodsTon + '\'' +
                '}';
    }
}
