package com.sdeport.common;


import java.lang.String;

/**
 * 上传文件bean
 */
public class UploadFile {

    private long fileId;
    private String listId;
    private String filePath = "";
    private String fileName = "";
    private String trueFileName = "";
    private String uploadTime;

    public long getFileId() {
        return fileId;
    }

    public void setFileId(final long fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public String getTrueFileName() {
        return this.trueFileName;
    }

    public String getUploadTime() {
        return this.uploadTime;
    }

    public void setUploadTime(final String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void setTrueFileName(final String trueFileName) {
        this.trueFileName = trueFileName;
    }

    public String getListId() {
        return this.listId;
    }

    public void setListId(final String listId) {
        this.listId = listId;
    }

    @Override
    public String toString() {
        return "UploadFile{" +
               "fileId=" + fileId +
               ", listId='" + listId + '\'' +
               ", filePath='" + filePath + '\'' +
               ", fileName='" + fileName + '\'' +
               ", trueFileName='" + trueFileName + '\'' +
               ", uploadTime='" + uploadTime + '\'' +
               '}';
    }
}
