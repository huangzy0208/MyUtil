package com.sdeport.common.attachmentlist;


/**
 * 附件列表bean
 */
public class AttachmentList {

    private String listId;
    private String fileId;
    private String remark;

    public AttachmentList(final String fileId) {
        this.fileId = fileId;
    }

    public AttachmentList(final String listId, final String fileId) {
        this.listId = listId;
        this.fileId = fileId;
    }

    public AttachmentList() {
    }

    public String getListId() {
        return this.listId;
    }

    public void setListId(final String listId) {
        this.listId = listId;
    }

    public String getFileId() {
        return this.fileId;
    }

    public void setFileId(final String fileId) {
        this.fileId = fileId;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AttachmentList{" +
               "listId='" + listId + '\'' +
               ", fileId='" + fileId + '\'' +
               ", remark='" + remark + '\'' +
               '}';
    }
}
