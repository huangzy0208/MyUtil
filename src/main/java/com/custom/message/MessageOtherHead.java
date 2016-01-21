package com.custom.message;


import com.custom.common.utils.ZzStringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class MessageOtherHead implements InterfaceHead {

    public static final String DEFAULT_SENDER_CODE = "7206199360424";
    public static final String SENDER_ID = "0370000001";
    public static final String DEFAULT_NUM = "1";
    public static final String DEFAULT_VERSION = "0.1";
    private String messageID = "";
    private String functionCode = "";
    private String messageType = "";
    private String messageMark = "";
    private String senderID = "";
    private String receiverID = "";
    private String sendTime = "";
    private String version = "";
    private String actualSenderID = "";
    private String actualReceiverID = "";
    private String sendNum = "";

    public String getMessageID() {
        return this.messageID;
    }

    public void setMessageID(final String messageID) {
        this.messageID = messageID;
    }

    public String getFunctionCode() {
        return this.functionCode;
    }

    public void setFunctionCode(final String functionCode) {
        this.functionCode = functionCode;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public void setMessageType(final String messageType) {
        this.messageType = messageType;
    }

    public String getSenderID() {
        return this.senderID;
    }

    public void setSenderID(final String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return this.receiverID;
    }

    public void setReceiverID(final String receiverID) {
        this.receiverID = receiverID;
    }

    public String getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(final String sendTime) {
        this.sendTime = sendTime;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public String getActualSenderID() {
        return this.actualSenderID;
    }

    public void setActualSenderID(final String actualSenderID) {
        this.actualSenderID = actualSenderID;
    }

    public String getActualReceiverID() {
        return this.actualReceiverID;
    }

    public void setActualReceiverID(final String actualReceiverID) {
        this.actualReceiverID = actualReceiverID;
    }

    public String getSendNum() {
        return this.sendNum;
    }

    public void setSendNum(final String sendNum) {
        this.sendNum = sendNum;
    }

    @Override
    public String toString() {
        return "MessageOtherHead{" +
                "messageID='" + messageID + '\'' +
                ", functionCode='" + functionCode + '\'' +
                ", messageType='" + messageType + '\'' +
                ", senderID='" + senderID + '\'' +
                ", receiverID='" + receiverID + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", version='" + version + '\'' +
                ", actualSenderID='" + actualSenderID + '\'' +
                ", actualReceiverID='" + actualReceiverID + '\'' +
                ", sendNum='" + sendNum + '\'' +
                '}';
    }


    /**
     * 创建默认的报文头
     *
     * @param bodyBean
     * @param receiverId
     * @param <T>
     * @return
     */
    @Override
    public <T> MessageOtherHead createHead(T bodyBean, String receiverId, String msgId) {
        MessageOtherHead msgHead = new MessageOtherHead();
        msgHead.setSendTime(ZzStringUtil.getDefaultDateTime());
        msgHead.setMessageID(StringUtils.isEmpty(msgId) ? String.valueOf(UUID.randomUUID()) : msgId);
        msgHead.setSenderID(SENDER_ID);
        msgHead.setSendNum(DEFAULT_NUM);
        msgHead.setVersion(DEFAULT_VERSION);
        msgHead.setActualReceiverID(receiverId);
        msgHead.setActualSenderID(SENDER_ID);
        msgHead.setReceiverID(MessageGenerator.dao.getPortEntry(receiverId));
        return msgHead;
    }
}
