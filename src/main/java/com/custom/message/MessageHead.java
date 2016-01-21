package com.custom.message;


import com.custom.common.utils.ZzStringUtil;
import org.apache.commons.lang3.StringUtils;

public class MessageHead implements InterfaceHead {

    public static final String DEFAULT_SENDER_CODE = "7206199360424";
    public static final String SENDER_ID = "DXPSDC0000000027";
    private String messageID = "";
    private String functionCode = "";
    private String messageType = "";
    private String senderID = "";
    private String receiverID = "";
    private String sendTime = "";
    private String version = "";

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

    @Override
    public String toString() {
        return "MessageHead{" +
                "messageID='" + messageID + '\'' +
                ", functionCode='" + functionCode + '\'' +
                ", messageType='" + messageType + '\'' +
                ", senderID='" + senderID + '\'' +
                ", receiverID='" + receiverID + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    /**
     * 生成报文头
     *
     * @return
     * @TODO:需要根据具体规则重新修改此处报文头
     */
    @Override
    public <T> MessageHead createHead(T bodyBean, String reciveId, String msgId) {
        MessageHead msgHead = new MessageHead();
        msgHead.setSendTime(ZzStringUtil.getSendTime());
        msgHead.setMessageID(StringUtils.isEmpty(msgId) ? ZzStringUtil.getMsSendTime() : msgId);
        msgHead.setSenderID(SENDER_ID);
        msgHead.setMessageType("0");
        return msgHead;
    }
}
