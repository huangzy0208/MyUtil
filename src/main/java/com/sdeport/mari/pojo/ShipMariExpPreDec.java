package com.sdeport.mari.pojo;

import com.sdeport.common.UploadFile;

import java.io.Serializable;
import java.util.List;

/**
 * 海事申报-出口预报
 *
 * @author zhangmeng
 * @date 2015/9/11
 */
public class ShipMariExpPreDec implements Serializable {

    private static final long serialVersionUID = 992996201209821027L;

    private String decId;

    private ShipMariExpPreDecBasic basicInfo;
    private ShipMariExpPreDecOther other;
    private ShipMariExpPreDecAddInfo addInfo;
    private ShipMariExpPsngCargoInfo cargoInfo;
    private List<UploadFile> attachmentInfo;

    public String getDecId() {
        return this.decId;
    }

    public void setDecId(final String decId) {
        this.decId = decId;
    }

    public ShipMariExpPreDecBasic getBasicInfo() {
        return this.basicInfo;
    }

    public void setBasicInfo(final ShipMariExpPreDecBasic basicInfo) {
        this.basicInfo = basicInfo;
    }

    public ShipMariExpPreDecOther getOther() {
        return this.other;
    }

    public void setOther(final ShipMariExpPreDecOther other) {
        this.other = other;
    }

    public ShipMariExpPreDecAddInfo getAddInfo() {
        return this.addInfo;
    }

    public void setAddInfo(final ShipMariExpPreDecAddInfo addInfo) {
        this.addInfo = addInfo;
    }

    public ShipMariExpPsngCargoInfo getCargoInfo() {
        return this.cargoInfo;
    }

    public void setCargoInfo(final ShipMariExpPsngCargoInfo cargoInfo) {
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
        return "ShipMariExpPreDec{" +
                "decId='" + decId + '\'' +
                ", basicInfo=" + basicInfo +
                ", other=" + other +
                ", addInfo=" + addInfo +
                ", cargoInfo=" + cargoInfo +
                ", attachmentInfo=" + attachmentInfo +
                '}';
    }
}
