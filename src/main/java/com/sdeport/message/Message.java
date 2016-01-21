package com.sdeport.message;


/**
 * 报文定义
 */
public class Message {

    InterfaceHead messageHead;

    MessageBody messageBody;

    public InterfaceHead getMessageHead() {
        return this.messageHead;
    }

    public void setMessageHead(final InterfaceHead messageHead) {
        this.messageHead = messageHead;
    }

    public MessageBody getMessageBody() {
        return this.messageBody;
    }

    public void setMessageBody(final MessageBody messageBody) {
        this.messageBody = messageBody;
    }


    @Override
    public String toString() {
        return "Message{" +
               "messageHead=" + messageHead +
               ", messageBody=" + messageBody +
               '}';
    }
}
