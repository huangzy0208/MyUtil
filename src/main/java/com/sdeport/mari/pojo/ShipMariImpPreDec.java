package com.sdeport.mari.pojo;

import com.sdeport.common.UploadFile;

import java.io.Serializable;
import java.util.List;

/**
 * 海事申报-进口预报
 *
 * @author zhangmeng
 * @date 2015/9/11
 */
public class ShipMariImpPreDec implements Serializable {

    private static final long serialVersionUID = 992996201209821027L;

    private String decId;

    private ShipMariImpPreDecBasic basicInfo;
    private ShipMariImpPreDecOther other;
    private ShipMariImpPreDecAddInfo addInfo;
    private ShipMariImpPsngCargoInfo cargoInfo;
    private List<UploadFile> attachmentInfo;

    public String getDecId() {
        return this.decId;
    }

    public void setDecId(final String decId) {
        this.decId = decId;
    }

    public ShipMariImpPreDecBasic getBasicInfo() {
        return this.basicInfo;
    }

    public void setBasicInfo(final ShipMariImpPreDecBasic basicInfo) {
        this.basicInfo = basicInfo;
    }

    public ShipMariImpPreDecOther getOther() {
        return this.other;
    }

    public void setOther(final ShipMariImpPreDecOther other) {
        this.other = other;
    }

    public ShipMariImpPreDecAddInfo getAddInfo() {
        return this.addInfo;
    }

    public void setAddInfo(final ShipMariImpPreDecAddInfo addInfo) {
        this.addInfo = addInfo;
    }

    public ShipMariImpPsngCargoInfo getCargoInfo() {
        return this.cargoInfo;
    }

    public void setCargoInfo(final ShipMariImpPsngCargoInfo cargoInfo) {
        this.cargoInfo = cargoInfo;
    }

    public List<UploadFile> getAttachmentInfo() {
        return this.attachmentInfo;
    }

    public void setAttachmentInfo(List<UploadFile> attachmentInfo) {
        this.attachmentInfo = attachmentInfo;
    }

    @Override
    public String toString() {
        return "ShipMariImpPreDec{" +
                "decId='" + decId + '\'' +
                ", basicInfo=" + basicInfo +
                ", other=" + other +
                ", addInfo=" + addInfo +
                ", cargoInfo=" + cargoInfo +
                ", attachmentInfo=" + attachmentInfo +
                '}';
    }
}
