package com.sdeport.message;

public interface InterfaceHead {

    <T>InterfaceHead createHead(T bodyBean, String sendId,String msgId);
}
