package com.custom.message;

public interface InterfaceHead {

    <T>InterfaceHead createHead(T bodyBean, String sendId,String msgId);
}
